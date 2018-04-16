package service;

import java.io.Serializable;
import java.util.Date;

/**
 * <h1>Room Service Entity</h1>
 *
 * Represents a room service that a Guest can order
 * <p>
 * Contains Menu Item Entity object
 *
 */
@SuppressWarnings("serial")
public class Service implements Serializable {

	/**
	 * Primary Key of Room Service Entity
	 * <p>
	 * ID
	 */
	private int serviceID;
	/**
	 * Room number of Room Service Entity
	 */
	private String roomNo;
	/**
	 * Reservation Code of Room Service Entity
	 */
	private String reservationCode;
	/**
	 * Item Name of Room Service Entity
	 */
	private String itemName;
	/**
	 * Current datetime of Room Service Entity
	 */
	private Date dateTime;
	/**
	 * Remarks for Room Service Entity
	 */
	private String remarks;
	/**
	 * Status of Room Service Entity
	 */
	private String status;

	/**
	 * Default constructor
	 */
	public Service() {
		super();
	}

	/**
	 * 
	 * Creates a new Room Service Entity object with the roomNo, reservationCode,
	 * itemName, dateTime, remarks and status
	 * 
	 * @param roomNo
	 *            Room Number
	 * @param reservationCode
	 *            Reservation Code
	 * @param itemName
	 *            Item Name
	 * @param dateTime
	 *            Date Time of creation
	 * @param remarks
	 *            Remarks
	 * @param status
	 *            Status
	 */
	public Service(String roomNo, String reservationCode, String itemName, Date dateTime, String remarks,
			String status) {
		super();
		this.roomNo = roomNo;
		this.reservationCode = reservationCode;
		this.itemName = itemName;
		this.dateTime = dateTime;
		this.remarks = remarks;
		this.status = status;
	}

	/**
	 * 
	 * Creates a new Room Service Entity object with serviceID, roomNo,
	 * reservationCode, itemName, dateTime, remarks and status
	 * 
	 * @param serviceID
	 *            ServiceID
	 * @param roomNo
	 *            Room Number
	 * @param reservationCode
	 *            Reservation Code
	 * 
	 * @param itemName
	 *            Item Name
	 * @param dateTime
	 *            Date Time of creation
	 * @param remarks
	 *            Remarks
	 * @param status
	 *            Status
	 */
	public Service(int serviceID, String roomNo, String reservationCode, String itemName, Date dateTime, String remarks,
			String status) {
		super();
		this.serviceID = serviceID;
		this.roomNo = roomNo;
		this.reservationCode = reservationCode;
		this.itemName = itemName;
		this.dateTime = dateTime;
		this.remarks = remarks;
		this.status = status;
	}

	/**
	 * @return the serviceID
	 */
	public int getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceID
	 *            the serviceID to set
	 */
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
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
	 * @return the guestDetails
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
	 * @return the itemName
	 */
	public String getItemID() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemID(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 *            the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * 
	 * Returns primary key of Room Service object
	 * 
	 * @return Returns the serviceID
	 */
	@Override
	public int hashCode() {
		return this.serviceID;
	}

	/**
	 * Used to compare between two Room Service Entity objects
	 * 
	 * @param obj
	 *            Object to compare with
	 * @return Returns true if serviceID is the same between two objects, else
	 *         returns false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Service)
			return ((Service) obj).hashCode() == this.hashCode();

		else
			return false;
	}

	/**
	 * Returns a string value of this Room Service Entity object to be displayed
	 * 
	 * @return Returns string value of service object
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ServiceID: " + this.serviceID + "\nRoom Number: " + this.roomNo + "\nReservation Code: "
				+ this.reservationCode + "\nItem ID: " + this.getItemID() + "\nDate: " + this.dateTime + "\nRemarks: "
				+ this.getRemarks() + "\nStatus: " + this.getStatus();
	}

}
