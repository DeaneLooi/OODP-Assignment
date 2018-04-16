package payment;

import java.util.ArrayList;
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
import utils.RegexValidation;
import utils.Serialization;

/**
 * Payment Controller Basically make use of the Payment entity class and
 * manipulate the data Hence, it role is to include of logics that involves the
 * Payment entity class
 * 
 * @author Low Shu En
 * @version 1.0
 * @since 13/4/2017
 *
 */
public class PaymentController {
	/**
	 * Stores the list of all payment objects created by calling the
	 * retrievePaymentList() method
	 */
	private static List<Payment> paymentList = retrievePaymentList();

	/**
	 * @return the list containing all guest objects
	 */
	@SuppressWarnings("unchecked")
	public static List<Payment> retrievePaymentList() {
		List<Payment> paymentList = null;
		paymentList = (List<Payment>) Serialization.readSerializedObject(Constants.PAYMENT_DATA);

		if (paymentList != null) {
			return paymentList;
		} else {
			//System.out.println("No data");
			return null;
		}
	}

	/**
	 * This method basically create or update the particular Payment object that is
	 * being passed into the parameter
	 * 
	 * It checks if the payment object exist or not, if exists, it is being override
	 * (updated) else it creates the payment into the data file
	 * 
	 * payment list is being updated accordingly
	 * 
	 * @param payment Payment object to be created or updated
	 * @return true if payment is updated / created successfully else @return false
	 *         if updating or creating of payment is unsuccessful
	 */
	public static boolean updatePaymentList(Payment payment) {
		if (paymentList != null) {

			if (payment.getPaymentId() == null) {
				int paymentNo = paymentList.size() + 10001;
				payment.setPaymentId("P" + paymentNo);
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
			paymentList = new ArrayList<Payment>();
			paymentList.add(payment);
		}

		return Serialization.writeSerializedObject(Constants.PAYMENT_DATA, paymentList);

	}

	/**
	 * This method basically remove that particular payment that is being passed
	 * into the parameter from the payment list and data file
	 * 
	 * @param payment Payment object to be removed
	 * @return true if payment is removed successfully else @return false if the
	 *         removal is unsuccessful
	 */
	public static boolean removePayment(Payment payment) {
		if (paymentList != null) {
			if (paymentList.remove(payment)) {

				return Serialization.writeSerializedObject(Constants.PAYMENT_DATA, paymentList);

			} else
				return false;
		} else
			return false;
	}

	/**
	 * Search by payment id only returns one payment object as payment id is a
	 * primary key
	 * 
	 * @param paymentId PaymentID 
	 * @return the payment object that contains the payment id that is being passed
	 *         in else @return null if no such payment id exist
	 */
	public static Payment getPaymentByPaymentId(String paymentId) {
		Payment checkPayment = new Payment();
		checkPayment.setPaymentId(paymentId);
		int i = 0;

		if (paymentList != null) {
			if (paymentList.contains(checkPayment)) {
				for (i = 0; i < paymentList.size(); i++) {
					if (paymentList.get(i).equals(checkPayment)) {
						return paymentList.get(i);
					}
				}
			}
		}
		return null;
	}

	/**
	 * This method basically compute the room charges based on room type, weekdays
	 * and weekends
	 * 
	 * @param roomType Room Type
	 * @param weekdays No. of weekdays
	 * @param weekends No. of weekends
	 * @return room charges 
	 */
	public static double computeRoomChargesByRoomTypes(String roomType, int weekdays, int weekends) {
		if (roomType.equals(Constants.ROOM_TYPE_SINGLE)) {
			return (weekdays * Prices.SROOM_PRICE_WEEKDAY) + (weekends * Prices.SROOM_PRICE_WEEKEND);
		} else if (roomType.equals(Constants.ROOM_TYPE_DOUBLE)) {
			return (weekdays * Prices.DROOM_PRICE_WEEKDAY) + (weekends * Prices.DROOM_PRICE_WEEKEND);
		} else if (roomType.equals(Constants.ROOM_TYPE_DELUXE)) {
			return (weekdays * Prices.DELROOM_PRICE_WEEKDAY) + (weekends * Prices.DELROOM_PRICE_WEEKEND);
		} else if(roomType.equals(Constants.ROOM_TYPE_VIP)){
			return (weekdays * Prices.VIPROOM_PRICE_WEEKDAY) + (weekends * Prices.VIPROOM_PRICE_WEEKEND);
		}
		else
			return 0;
	}

	/**
	 * This method calculate the days of stay (weekends) based on check in and check
	 * out date
	 * 
	 * @param checkInDate Check-in date
	 * @param checkOutDate Check-out date
	 * @return number of weekends
	 */
	@SuppressWarnings("deprecation")
	public static int computeNoOfWeekends(Date checkInDate, Date checkOutDate) {
		int checkindate = checkInDate.getDate();
		int checkoutdate = checkOutDate.getDate();
		int weekend = 0;
		
		Date startDate = checkInDate;
		for(int i=0; i<= checkoutdate-checkindate;i++)
		{
			if(startDate.getDay() == 0 || startDate.getDay() == 6) {
				weekend++;
				startDate.setDate(startDate.getDate()+1);
			}	
		}
		return weekend;
	}

	/**
	 * This method prints the bill invoice for that payment object
	 * 
	 * @param payment Payment object 
	 */
	public static void printBillInvoice(Payment payment) {
		int i;
		
		// for breakdown of days
		Date checkInDate = ReservationController.getReservationByCode(payment.getReservationCode()).getCheckInDate(); 
		Date checkOutDate = ReservationController.getReservationByCode(payment.getReservationCode()).getCheckOutDate();  
		int weekends = computeNoOfWeekends(checkInDate, checkOutDate);
		@SuppressWarnings("deprecation")
		int weekdays = checkOutDate.getDate() - checkInDate.getDate() + 1 - weekends;
		
		// for print order items
		String itemName;
		double itemPrice, totalItemPrice = 0;
		
		// for compute room price
		String roomNo = payment.getRoomNo();
		String roomType = RoomController.getRoom(roomNo).getRoomType();
		double roomCharges = computeRoomChargesByRoomTypes(roomType, weekdays, weekends);

		System.out.println("Bill Invoice");
		System.out.println("============");
		
		System.out.println("Breakdown of Days of Stay");
		System.out.println("-------------------------");
		System.out.println("No. of weekdays: " + weekdays + " days");
		System.out.println("No. of weekends/ public holidays: " + weekends + " days");

		List<Service> OrderList = ServiceController.getServicesFromReservationCode(payment.getReservationCode());
		System.out.println("Room Service Order Items");
		System.out.println("------------------------");
		
		System.out.format(RegexValidation.tableServiceBill, "S/No.", "Name", "Price ($)");
		for (i = 0; i < OrderList.size(); i++) {
			Service s = OrderList.get(i);
			itemName = s.getItemID();
			Item item = ItemController.getItemFromName(itemName);
			itemPrice = item.getItemPrice();
			System.out.format(RegexValidation.tableServiceBill, (i+1), itemName, itemPrice);
			totalItemPrice += itemPrice;
		}
		System.out.printf("Total for room service orders: $%.2f" , totalItemPrice);
		
		// for updating and computing bill 
		payment.setDiscount(Prices.DISCOUNT * (roomCharges + totalItemPrice));
		payment.setTax(Prices.TAX * (roomCharges + totalItemPrice - payment.getDiscount()));
		payment.setTotalBill(payment.getTax() + roomCharges + totalItemPrice - payment.getDiscount());
		updatePaymentList(payment);
				
		System.out.printf("\nRoom Charges: $%.2f" , roomCharges);
		System.out.printf("\nDiscounts: $%.2f" , payment.getDiscount());
		System.out.printf("\nTax: $%.2f" , payment.getTax());
		System.out.printf("\nTotal Amount: $%.2f" , payment.getTotalBill());
	}
}