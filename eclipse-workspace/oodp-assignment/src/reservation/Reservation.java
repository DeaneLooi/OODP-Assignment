package reservation;

import java.io.Serializable;
import java.util.Date;

/**
 * <h1>Reservation Entity</h1>
 *
 * Represents a reservation object that a Guest can make.

 * @version 1.0
 * @since 2018-04-12
 */
@SuppressWarnings("serial")
public class Reservation implements Serializable {

	/**
	 * Reservation Code/Number
	 */
	private String reservationCode;
	/**
	 * Guest passport details
	 */
	private String guestPassport;
	/**
	 * Room number of reservation
	 */
	private String roomNo;
	/**
	 * Check-in date of reservation
	 */
	private Date checkInDate;
	/**
	 * Check-out date of reservation
	 */
	private Date checkOutDate;
	/**
	 * Status of reservation, eg. "Confirmed","In Wait List","Check In","Expired"
	 */
	private String status;
	/**
	 * Number of adults staying
	 */
	private int noAdults;
	/**
	 * Number of children staying
	 */
	private int noChildren;

	/**
	 * Default constructor
	 */
	public Reservation() {
		super();
	}

	/**
	 * 
	 * Creates a new Reservation Entity object with guest passport details, room number, check-in date, check-out date, status, number of adults and number of children
	 * 
	 * @param guestPassport Guest passport details
	 * @param roomNo Room number
	 * @param checkInDate Check-in date
	 * @param checkOutDate Check-out date
	 * @param status Status
	 * @param noAdults Number of adults
	 * @param noChildren Number of children
	 */
	public Reservation(String guestPassport, String roomNo, Date checkInDate, Date checkOutDate, String status,
			int noAdults, int noChildren) {
		super();
		this.guestPassport = guestPassport;
		this.roomNo = roomNo;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.status = status;
		this.noAdults = noAdults;
		this.noChildren = noChildren;
	}

	/**
	 * @return the reservationCode
	 */
	public String getReservationCode() {
		return reservationCode;
	}

	/**
	 * @param reservationCode
	 *            the reservationCode to set
	 */
	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	/**
	 * @return the guestPassport
	 */
	public String getGuestPassport() {
		return guestPassport;
	}

	/**
	 * @param guestPassport
	 *            the guestPassport to set
	 */
	public void setGuestPassport(String guestPassport) {
		this.guestPassport = guestPassport;
	}

	/**
	 * @return the roomNo
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * @param roomNo
	 *            the roomNo to set
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	/**
	 * @return the checkInDate
	 */
	public Date getCheckInDate() {
		return checkInDate;
	}

	/**
	 * @param checkInDate
	 *            the checkInDate to set
	 */
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * @return the checkOutDate
	 */
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * @param checkOutDate
	 *            the checkOutDate to set
	 */
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the noAdults
	 */
	public int getNoAdults() {
		return noAdults;
	}

	/**
	 * @param noAdults
	 *            the noAdults to set
	 */
	public void setNoAdults(int noAdults) {
		this.noAdults = noAdults;
	}

	/**
	 * @return the noChildren
	 */
	public int getNoChildren() {
		return noChildren;
	}

	/**
	 * @param noChildren
	 *            the noChildren to set
	 */
	public void setNoChildren(int noChildren) {
		this.noChildren = noChildren;
	}

	/**
	 *Used to compare between two Reservation Entity objects
	 * 
	 * @param obj Object to compare with
	 * @return Returns true if reservationCode is the same between two objects, else returns false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Reservation)
			return ((Reservation) obj).getReservationCode().equals(this.reservationCode);

		else
			return false;
	}

	/**
	 * Returns a string value of this Reservation Entity object to be displayed
	 * 
	 * @return Returns string value of reservation object
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Reservation Code: " + this.reservationCode + "\nRoom Number: " + this.roomNo + "\nGuestPassport: "
				+ this.guestPassport + "\nCheck In Date: " + this.checkInDate + "\nCheck Out Date: " + this.checkOutDate
				+ "\nStatus: " + this.status + "\nNo. of Adults: " + this.noAdults + "\nNo. of Children: "
				+ this.noChildren;
	}

}
