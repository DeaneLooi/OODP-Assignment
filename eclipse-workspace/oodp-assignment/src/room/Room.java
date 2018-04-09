package room;

import java.io.Serializable;

public class Room implements Serializable{
	private String roomNo;
	private String roomType;
	private String bedType;
	private String wifi;
	private String view;
	private String smoking;
	private String status;
	
	public Room(String roomNo, String roomType, String bedType, String wifi, String view, String smoking, String status) {
		super();
		this.roomType = roomType;
		this.roomNo = roomNo;
		this.bedType = bedType;
		this.wifi = wifi;
		this.view = view;
		this.smoking = smoking;
		this.status = status;
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public String getRoomNo() {
		return roomNo;
	}

	public String getBedType() {
		return bedType;
	}

	public String getWifi() {
		return wifi;
	}

	public String getView() {
		return view;
	}
	
	public String getSmoking() {
		return smoking;
	}
	
	public String getStatus() {
		return status;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	
	public void setView(String view) {
		this.view = view;
	}
	
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String hashCode() {
		// TODO Auto-generated method stub
		return this.roomNo;
	}

	/**
	 * @return true if passport is the same
	 */
	public boolean equals(String roomNo) {
		return (roomNo.equals(this.roomNo));
	}

	/**
	 * @return true if room entity is same else false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Room) ? (((Room) obj).getRoomNo().equalsIgnoreCase(this.roomNo)) : false;
	}

	/**
	 * @return string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Room number: %s\nRoom type: %s\nBed type: %s\nWifi: %s\nView: %s\nSmoking: %s\nStatus: %s", roomNo, roomType, bedType, wifi, view, smoking, status);
	}
}
