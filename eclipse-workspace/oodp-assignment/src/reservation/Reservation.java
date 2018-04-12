package reservation;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable{
	
	private String reservationCode;
	private String guestPassport;
	private String roomNo;
	private Date checkInDate;
	private Date checkOutDate;
	private String status;
	private int noAdults;
	private int noChildren;
	
	
	public Reservation()
	{
		super();
	}
	
	public Reservation(String guestPassport, String roomNo, Date checkInDate, Date checkOutDate,
			String status, int noAdults, int noChildren) {
		super();
		this.guestPassport = guestPassport;
		this.roomNo = roomNo;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.status = status;
		this.noAdults = noAdults;
		this.noChildren = noChildren;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public String getGuestPassport() {
		return guestPassport;
	}

	public void setGuestPassport(String guestPassport) {
		this.guestPassport = guestPassport;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNoAdults() {
		return noAdults;
	}

	public void setNoAdults(int noAdults) {
		this.noAdults = noAdults;
	}

	public int getNoChildren() {
		return noChildren;
	}

	public void setNoChildren(int noChildren) {
		this.noChildren = noChildren;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Reservation)
			return ((Reservation) obj).getReservationCode().equals(this.reservationCode);
		
		else
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Reservation Code: "+this.reservationCode+" Room Number: "+this.roomNo+" GuestPassport: "+this.guestPassport+" Check In Date: "+this.checkInDate+" Check Out Date: "+this.checkOutDate+" status: "+this.status+" No. of Adults: "+this.noAdults+" No. of Children: "+this.noChildren;
	}
	
	

}
