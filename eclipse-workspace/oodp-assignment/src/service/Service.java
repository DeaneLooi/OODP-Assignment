package service;

import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {

	private int serviceID;
	private String roomNo;
	private String reservationCode;
	private String itemName;
	private Date dateTime;
	private String remarks;
	private String status;


	/**
	 * default constructor
	 */
	public Service()
	{
		super();
	}
	/**
	 * @param roomNo
	 * @param reservationCode
	 * @param itemName
	 * @param dateTime
	 * @param remarks
	 * @param status
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
	 * @param serviceID
	 * @param roomNo
	 * @param reservationCode
	 * @param itemName
	 * @param dateTime
	 * @param remarks
	 * @param status
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
	 * @param roomNo the roomNo to set
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
	 * @param guestDetails the guestDetails to set
	 */
	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}
	
	/**
	 * @return the itemID
	 */
	public String getItemID() {
		return itemName;
	}

	/**
	 * @param itemID
	 *            the itemID to set
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
	 * @return the serviceid
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.serviceID;
	}


	/**
	 * @return true if serviceID is same
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Service)
			return ((Service) obj).hashCode() == this.hashCode();
		
		else
			return false;
	}


	/**
	 * @return the string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Service:"+this.serviceID+" "+this.roomNo+" "+this.getItemID()+" "+this.getRemarks()+" "+this.getStatus();
	}
	
	

}
