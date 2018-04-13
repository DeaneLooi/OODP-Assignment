package payment;

import java.io.Serializable;

/**
 * Payment Entity
 * Class that contains attributes, getters and setters
 * relating to payment data needed for our program
 * 
 * @author Low Shu En
 * @version 1.0
 * @since 13/4/2017
 */
public class Payment implements Serializable{
	/**
	 * Primary key of the payment entity
	 */
	private String paymentId;
	/**
	 * Reservation code relating to the payment to be made
	 */
	private String reservationCode;
	/**
	 * Room number relating to the payment to be made
	 */
	private String roomNo;
	/**
	 * Guest name relating to the payment to be made
	 */
	private String guestPassport;
	/**
	 * Tax in SGD for this payment
	 */
	private double tax;
	/**
	 * Discounts in SGD for this payment
	 */
	private double discount;
	/**
	 * Total bill in SGD for this payment
	 */
	private double totalBill;
	/**
	 * Payment type: cash or card for this payment
	 */
	private String paymentType;
	/**
	 * Credit card number for this payment
	 */
	private String creditCard;
	
	/**
	 * Default constructor
	 */
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Mandatory attributes required for creating every payment object
	 * @param reservationCode
	 * @param roomNo
	 * @param guestName
	 */
	public Payment(String reservationCode, String roomNo, String guestPassport) {
		super();
		this.reservationCode = reservationCode;
		this.roomNo = roomNo;
		this.guestPassport = guestPassport;
		this.tax = 0;
		this.discount = 0;
		this.totalBill = 0;
		this.paymentType = null;
		this.creditCard = null;
	}

	/**
	 * @return the payment id of this payment
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId
	 * updates the payment id
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the reservation code relating to this payment
	 */
	public String getReservationCode() {
		return reservationCode;
	}

	/**
	 * @param reservationCode
	 * updates the reservation code
	 */
	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	/**
	 * @return the room number relating to this payment
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * @param roomNo
	 * updates the room number
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	/**
	 * @return the guest name relating to this payment
	 */
	public String getGuestPassport() {
		return guestPassport;
	}

	/**
	 * @param guestName
	 * updates the guest name
	 */
	public void setGuestPassport(String guestPassport) {
		this.guestPassport = guestPassport;
	}

	/**
	 * @return the tax of this payment
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax
	 * updates the tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * @return the discount of this payment
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 * updates the discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the total bill of this payment
	 */
	public double getTotalBill() {
		return totalBill;
	}

	/**
	 * @param totalBill
	 * updates the total bill
	 */
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}

	/**
	 * @return the payment type of this payment
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 * updates the payment type
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the credit card number of this payment
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard
	 * updates the credit card number
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	/**
	 * Compares 2 Payment objects &
	 * @return true if guest entity is same else false
	 * done by comparing the primary key - payment id
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Payment)
			//return ((((Payment) obj).getReservationCode().equals(this.reservationCode))&&(((Payment) obj).getRoomNo().equals(this.roomNo))&&(((Payment) obj).getGuestName().equals(this.guestName)));
			return ((Payment)obj).getPaymentId().equals(this.paymentId);
		else
			return false;
	}

	/**
	 * @return string of values for all payment attributes.
	 * to be displayed to the user when needed
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Payment ID: " + this.paymentId + " Reservation No: " + this.reservationCode + " Room No: " + this.roomNo + " Guest Passport " + this.guestPassport + " Tax: " + this.tax + " Discount: " + this.discount + " Total Bill: " + this.totalBill + " Payment Type: " + this.paymentType + " Credit Card: " + this.creditCard;
	}
}
