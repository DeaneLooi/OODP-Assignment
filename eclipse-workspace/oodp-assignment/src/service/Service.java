package service;

import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {

	private int serviceID;
	private int roomNo;
	private int reservationNo;
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
	 * @param guestDetails
	 * @param itemName
	 * @param dateTime
	 * @param remarks
	 * @param status
	 */
	public Service(int roomNo, int reservationNo, String itemName, Date dateTime, String remarks,
			String status) {
		super();
		this.roomNo = roomNo;
		this.reservationNo = reservationNo;
		this.itemName = itemName;
		this.dateTime = dateTime;
		this.remarks = remarks;
		this.status = status;
	}
	
	/**
	 * @param serviceID
	 * @param roomNo
	 * @param guestDetails
	 * @param itemName
	 * @param dateTime
	 * @param remarks
	 * @param status
	 */
	public Service(int serviceID, int roomNo, int reservationNo, String itemName, Date dateTime, String remarks,
			String status) {
		super();
		this.serviceID = serviceID;
		this.roomNo = roomNo;
		this.reservationNo = reservationNo;
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
	public int getRoomNo() {
		return roomNo;
	}

	/**
	 * @param roomNo the roomNo to set
	 */
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	/**
	 * @return the guestDetails
	 */
	public int getReservationNo() {
		return reservationNo;
	}

	/**
	 * @param guestDetails the guestDetails to set
	 */
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
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
	 * @return true if reservation number and room number are the same
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Service)
		{
			if(((Service) obj).hashCode() == this.hashCode())
				return true;
		}
		
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
