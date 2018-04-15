package room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Serialization;

/**
 * <h1> Room Controller </h1>
 * 
 * This controller class access Room for CRUD operations and print room statistical report 
 *
 */
public class RoomController {

	/**
	 * List of Room objects in data file
	 */
	private static List<Room> roomList = retrieveRoomList();

	/**
	 * 
	 * Reads room data file and retrieve a List of room objects
	 * 
	 * @return list of room objects
	 */
	@SuppressWarnings("unchecked")
	public static List<Room> retrieveRoomList() {

		List<Room> roomList = null;
		roomList = (List<Room>) Serialization.readSerializedObject(Constants.ROOM_DATA);

		if (roomList != null)
			return roomList;
		else {
			//System.out.println("No data.");
			return null;
		}
	}

	/**
	 * Iterate through List of room objects and retrieve the one with correct room number
	 * 
	 * @param roomNo
	 * @return Room object
	 */
	public static Room getRoom(String roomNo) {
		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				Room room = roomList.get(i);
				if (roomNo.equals(room.getRoomNo())) {
					return room;
				}
			}
		} else {
			//System.out.println("No data.");
			return null;
		}
		//System.out.println("Invalid room number.");
		return null;
	}


	/**
	 * 
	 * Create a new room object or update existing room details
	 * 
	 * @param room
	 * @return true if room data file is updated, otherwise return false
	 */
	public static boolean updateRoomList(Room room) {
		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				if (room.equals(roomList.get(i))) {
					roomList.set(i, room);
					break;
				}
				if (i + 1 == roomList.size()) {
					roomList.add(room);
				}
			}
		} else {
			roomList = new ArrayList<Room>();
			roomList.add(room);
		}

		return Serialization.writeSerializedObject(Constants.ROOM_DATA, roomList);

	}

	/**
	 * 
	 * Remove room object from room list and update the data file
	 * 
	 * @param room
	 * @return true if room object removal and room data file update are successful, otherwise return false
	 */
	public static boolean removeRoom(Room room) {
		if (roomList != null) {
			if (roomList.remove(room)) {

				return Serialization.writeSerializedObject(Constants.ROOM_DATA, roomList);

			} else
				return false;
		}
		System.out.println("No data.");
		return false;
	}

	/**
	 * 
	 * Print room report by room type or room status 
	 * 
	 * @param attr
	 * @return true if list of room is not null, otherwise return false
	 */
	@SuppressWarnings("static-access")
	public static boolean printReport(String attr) {
		

		if (roomList != null) {
			if (attr.equals(Constants.PRINT_REPORT_BY_ROOM_TYPE)) {
				int Single = 0;
				int Double = 0;
				int Deluxe = 0;
				int VIP = 0;
				int Single_vacant = 0;
				int Double_vacant = 0;
				int Deluxe_vacant = 0;
				int VIP_vacant = 0;
				String singleRoomList = "";
				String doubleRoomList = "";
				String deluxeRoomList = "";
				String vipSuiteRoomList = "";
				for (int i = 0; i < roomList.size(); i++) {
					
					Room room = roomList.get(i);
					String roomType = room.getRoomType();
					String status = room.getStatus();
//					System.out.println("roomType: "+roomType+"constant single: "+Constants.ROOM_TYPE_SINGLE);
					if (roomType.equals(Constants.ROOM_TYPE_SINGLE)) {
						Single++;
						if (status.equals(Constants.ROOM_STATUS_VACANT)) {
							Single_vacant++;
							singleRoomList = singleRoomList
									+ ((singleRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
						}
					} else if (roomType.equals(Constants.ROOM_TYPE_DOUBLE)) {
//						System.out.println("Double room");
						Double++;
						if (status.equals(Constants.ROOM_STATUS_VACANT)) {
							Double_vacant++;
							doubleRoomList = doubleRoomList
									+ ((doubleRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
						}
					} else if (roomType.equals(Constants.ROOM_TYPE_DELUXE)) {
//						System.out.println("Deluxe room");
						Deluxe++;
						if (status.equals(Constants.ROOM_STATUS_VACANT)) {
							Deluxe_vacant++;
							deluxeRoomList = deluxeRoomList
									+ ((deluxeRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
						}
					} else if (roomType.equals(Constants.ROOM_TYPE_VIP)) {
//						System.out.println("VIP room");
						VIP++;
						if (status.equals(Constants.ROOM_STATUS_VACANT)) {
							VIP_vacant++;
							vipSuiteRoomList = vipSuiteRoomList
									+ ((vipSuiteRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
						}
					}
				}

				System.out.printf("Single: Number: %d out of %d\n\t\tRooms: %s\n", Single_vacant, Single,
						singleRoomList);
				System.out.printf("Double: Number: %d out of %d\n\t\tRooms: %s\n", Double_vacant, Double,
						doubleRoomList);
				System.out.printf("Deluxe: Number: %d out of %d\n\t\tRooms: %s\n", Deluxe_vacant, Deluxe,
						deluxeRoomList);
				System.out.printf("VIP Suite: Number: %d out of %d\n\t\tRooms: %s\n", VIP_vacant, VIP,
						vipSuiteRoomList);

			} else if (attr.equals(Constants.PRINT_REPORT_BY_STATUS)) {

				String vacantRoomList = "";
				String occupiedRoomList = "";
				String reservedRoomList = "";
				String underMaintenanceRoomList = "";
				for (int i = 0; i < roomList.size(); i++) {
					Room room = roomList.get(i);
					String status = room.getStatus();
					if (status.equals(Constants.ROOM_STATUS_VACANT)) {
						vacantRoomList = vacantRoomList
								+ ((vacantRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
					} else if (status.equals(Constants.ROOM_STATUS_OCCUPIED)) {
						occupiedRoomList = occupiedRoomList
								+ ((occupiedRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
					} else if (status.equals(Constants.ROOM_STATUS_RESERVED)) {
						reservedRoomList = reservedRoomList
								+ ((reservedRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
					} else if (status.equals(Constants.ROOM_STATUS_UNDER_MAINTAINENCE)) {
						underMaintenanceRoomList = underMaintenanceRoomList
								+ ((underMaintenanceRoomList.length() > 0) ? (", " + room.getRoomNo()) : (room.getRoomNo()));
					}
				}

				System.out.printf("Vacant:\n\t\tRooms: %s\n", vacantRoomList);
				System.out.printf("Occupied:\n\t\tRooms: %s\n", occupiedRoomList);
				System.out.printf("Reserved:\n\t\tRooms: %s\n", reservedRoomList);
				System.out.printf("Under Maintenance:\n\t\tRooms: %s\n", underMaintenanceRoomList);
			}

			return true;
		} else {
			//System.out.println("No data.");
			return false;
		}
	}

}