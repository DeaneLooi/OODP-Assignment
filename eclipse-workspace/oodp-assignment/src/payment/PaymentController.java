package payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import item.Item;
import item.ItemController;
import reservation.ReservationController;
import room.RoomController;
import service.Service;
import service.ServiceController;

import utils.Constants;
import utils.Prices;
import utils.Serialization;

public class PaymentController {
	private static List<Payment> paymentList = retrievePaymentList();
	
	public static List<Payment> retrievePaymentList(){
		List<Payment> paymentList = null;
		paymentList = (List<Payment>) Serialization.readSerializedObject(Constants.PAYMENT_DATA);
		
		if(paymentList != null) {
			return paymentList;
		}
		else {
			System.out.println("No data");
			return null;
		}
	}
	
	public static boolean updatePaymentList(Payment payment) {
		if (paymentList != null) {

			if (payment.getPaymentId() == null)
			{
				int paymentNo = paymentList.size()+10001;
				payment.setPaymentId("P"+paymentNo);
			}
			
			if (paymentList.contains(payment)) {
				int i = 0;

				for (i = 0; i < paymentList.size(); i++) {
					if (payment.equals(paymentList.get(i)))
						break;
				}

				paymentList.set(i, payment);
			}

			else 
				paymentList.add(payment);
		}

		else {
			payment.setPaymentId("P10001");
			paymentList = new ArrayList();
			paymentList.add(payment);
		}

		try {
			Serialization.writeSerializedObject(Constants.PAYMENT_DATA, paymentList);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean removePayment(Payment payment) {
		if(paymentList != null) {
			if(paymentList.remove(payment)){
				try {
					Serialization.writeSerializedObject(Constants.PAYMENT_DATA, paymentList);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			} else
				return false;
		}
		else
			return false;
	}
	
	public static Payment getPaymentByPaymentId(String paymentId) {
		Payment checkPayment = new Payment();
		checkPayment.setPaymentId(paymentId);
		int i=0;
		
		if(paymentList != null) {
			if(paymentList.contains(checkPayment)) {
				for(i=0; i< paymentList.size(); i++) {
					if(paymentList.get(i).equals(checkPayment)) {
						return paymentList.get(i);
					}
				}
			}
		}
		return null;
	}
	
	public static double computeRoomChargesByRoomTypes(Payment payment, int weekdays, int weekends) {
		String roomNo = payment.getRoomNo();
		String roomType = RoomController.getRoom(roomNo).getRoomType();
		if(roomType == "Single") {
			return (weekdays*Prices.SROOM_PRICE_WEEKDAY)+(weekends*Prices.SROOM_PRICE_WEEKEND);
		}
		else if(roomType == "Double") {
			return (weekdays*Prices.DROOM_PRICE_WEEKDAY)+(weekends*Prices.DROOM_PRICE_WEEKEND);
		}
		else if(roomType == "Deluxe"){
			return (weekdays*Prices.DELROOM_PRICE_WEEKDAY)+(weekends*Prices.DELROOM_PRICE_WEEKEND);
		}
		else {
			return (weekdays*Prices.VIPROOM_PRICE_WEEKDAY)+(weekends*Prices.VIPROOM_PRICE_WEEKEND);
		}
	}
	
	public static int computeNoOfWeekDays(Date CheckInDate, Date CheckOutDate) {
		// take check in and check out dates from reservation class
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(CheckInDate);
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(CheckOutDate);
		
		int workDays = 0;
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}
		
	    do {
	    	//excluding start date
		    startCal.add(Calendar.DAY_OF_MONTH, 1);
		    if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		    	++workDays;
		    }
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

		return workDays;
	}
	
	public static void printBillInvoice(Payment payment) {
		Date checkInDate = ReservationController.getReservationByCode(payment.getReservationCode()).getCheckInDate(); // to get from reservation class
		Date checkOutDate = ReservationController.getReservationByCode(payment.getReservationCode()).getCheckOutDate(); // to get from reservation class
		int i;
		int weekdays = computeNoOfWeekDays(checkInDate, checkOutDate);
		int weekends = checkOutDate.compareTo(checkInDate)-weekdays;
		String itemName;
		double itemPrice, roomCharges, totalItemPrice = 0;
		System.out.println("Bill Invoice");
		System.out.println("============");
		
		// check in & check out dates for days of stay can take from reservation class
		System.out.println("Breakdown of Days of Stay");
		System.out.println("-------------------------");
		System.out.println("No. of weekdays: " + weekdays + "days");
		System.out.println("No. of weekends/ public holidays: " + weekends + "days");
		
		// get from service
		List<Service> OrderList = ServiceController.getServicesFromReservationNo(payment.getReservationCode());
		System.out.println("Room Service Order Items");
		System.out.println("------------------------");
		System.out.println("S/No. \t Name \t Price");
		for(i=0; i< OrderList.size(); i++) {
			Service s = OrderList.get(i);
			itemName = s.getItemID();
			Item item = ItemController.getItemFromName(itemName);
			itemPrice = item.getItemPrice();
			System.out.println((i+1) + ".\t" + itemName + "\t" + itemPrice);
			totalItemPrice += itemPrice;
		}
		System.out.println("Total for room service orders: " + totalItemPrice);
		
		// get from pricing class
		roomCharges = computeRoomChargesByRoomTypes(payment, weekdays, weekends);
		payment.setDiscount(Prices.DISCOUNT * (roomCharges + totalItemPrice));
		payment.setTax(Prices.TAX * (roomCharges + totalItemPrice - payment.getDiscount()));
		payment.setTotalBill(payment.getTax() + roomCharges + totalItemPrice - payment.getDiscount());
		updatePaymentList(payment);
		System.out.println("Room Charges: $" + roomCharges);
		System.out.println("Discounts: $" + payment.getDiscount());
		System.out.println("Tax: $" + payment.getTax());
		System.out.println("Total Amount: $" + payment.getTotalBill());
	}
}