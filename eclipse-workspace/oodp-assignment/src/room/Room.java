package room;

import java.io.Serializable;

/**
 * 
 *
 */
@SuppressWarnings("serial")
public class Room implements Serializable{
	private String roomNo;
	private String roomType;
	private String bedType;
	private String wifi;
	private String view;
	private String smoking;
	private String status;
	
	/**
	 * 
	 * @param roomNo
	 * @param roomType
	 * @param bedType
	 * @param wifi
	 * @param view
	 * @param smoking
	 * @param status
	 */
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
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}



	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}



	/**
	 * @return the bedType
	 */
	public String getBedType() {
		return bedType;
	}



	/**
	 * @param bedType the bedType to set
	 */
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}



	/**
	 * @return the wifi
	 */
	public String getWifi() {
		return wifi;
	}



	/**
	 * @param wifi the wifi to set
	 */
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}



	/**
	 * @return the view
	 */
	public String getView() {
		return view;
	}



	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}



	/**
	 * @return the smoking
	 */
	public String getSmoking() {
		return smoking;
	}



	/**
	 * @param smoking the smoking to set
	 */
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
