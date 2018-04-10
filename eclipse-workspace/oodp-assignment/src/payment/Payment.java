package payment;

import java.io.Serializable;

public class Payment implements Serializable{
	private String paymentId;
	private String reservationCode;
	private String roomNo;
	private String guestName;
	private double tax;
	private double discount;
	private double totalBill;
	private String paymentType;
	private String creditCard;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Payment(String reservationCode, String roomNo, String guestName) {
		super();
		this.reservationCode = reservationCode;
		this.roomNo = roomNo;
		this.guestName = guestName;
		this.tax = 0;
		this.discount = 0;
		this.totalBill = 0;
		this.paymentType = "nil";
		this.creditCard = "nil";
	}
	
	public Payment(String reservationCode, String roomNo, String guestName, double tax, double discount,
			double totalBill, String paymentType, String creditCard) {
		super();
		this.reservationCode = reservationCode;
		this.roomNo = roomNo;
		this.guestName = guestName;
		this.tax = tax;
		this.discount = discount;
		this.totalBill = totalBill;
		this.paymentType = paymentType;
		this.creditCard = creditCard;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
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
	 * @return string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Payment ID: " + this.paymentId + " Reservation No: " + this.reservationCode + " Room No: " + this.roomNo + " Guest Name " + this.guestName + " Tax: " + this.tax + " Discount: " + this.discount + " Total Bill: " + this.totalBill + " Payment Type: " + this.paymentType + " Credit Card: " + this.creditCard;
	}
}
