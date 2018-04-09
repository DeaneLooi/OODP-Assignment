package room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Serialization;

public class RoomController {
	
	private static List<Room> roomList = retrieveRoomList();
	
	private static List<Room> retrieveRoomList() {

		List<Room> roomList = null;
		roomList = (List<Room>) Serialization.readSerializedObject(Constants.ROOM_DATA);

		if (roomList != null)
			return roomList;
		else {
			System.out.println("No data.");
			return null;
		}
	}
	
	public static Room getRoom(String roomNo) {
		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				Room room = roomList.get(i);
				if (roomNo.equals(room.getRoomNo())) {
					return room;
				}
			}
		} else {
			System.out.println("No data.");
			return null;
		}
		System.out.println("Invalid room number.");
		return null;
	}
	
	public static boolean updateRoomDetails(String roomNo, String attr, String new_value) {

		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				Room room = roomList.get(i);
				if (roomNo.equals(room.getRoomNo())) {
					if (attr.equals("bedType")) {
						room.setBetType(new_value);
					} else if (attr.equals("roomType")) {
						room.setRoomType(new_value);
					} else if (attr.equals("wifi")) {
						room.setWifi(new_value);
					} else if (attr.equals("view")) {
						room.setView(new_value);
					} else if (attr.equals("smoking")) {
						room.setSmoking(new_value);
					} else if (attr.equals("status")) {
						room.setStatus(new_value);
					}
					roomList.set(i, room);
					break;
				}
			}
		}
		else {
			System.out.println("No data.")
			return false;
		}
		try {
			Serialization.writeSerializedObject(Constants.ROOM_DATA, roomList);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		roomList = retrieveRoomList();
		return true;
	}
	
	public static List<String> findSuitableRoom(String[] filters) {
		/*
		 * @param: String[] filters = [roomType, bedType, wifi, view, smoking]
		 * @return: String[] array of room numbers / null
		 */
		List<String> roomNoList = null;
		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				Room room = roomList.get(i);
				
				if (room.getRoomType().equals(filters[0]) 
				 && room.getBedType().equals(filters[1])
				 && room.getWifi.equals(filters[2])
				 && room.getView.equals(filters[3])
				 && room.getSmoking.equals(filters[4])
				 && room.getStatus.equals("Vacant")) {
					roomNoList.add(room.getRoomNo());
				}					
			}
		}
		else {
			System.out.println("No data.");
		}
		
		return roomNoList;
	}
	
	public static boolean printReport(String attr) {
		
		if (roomList != null) {
			if (attr.equals("roomType")) {
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
					if (roomType.equals("Single")) {
						Single++;
						if status.equals("Vacant") {
							Single_vacant++;
							singleRoomList = singleRoomList + ((Single_vacant > 1) ? ", %s" : "%s").format(room.getRoomNo());
						}
					} else if (roomType.equals("Double")) {
						Double++;
						if status.equals("Vacant") {
							Double_vacant++;
							doubleRoomList = doubleRoomList + ((Double_vacant > 1) ? ", %s" : "%s").format(room.getRoomNo());
						}
					} else if (roomType.equals("Deluxe")) {
						Deluxe++;
						if status.equals("Vacant") {
							Deluxe_vacant++;
							deluxeRoomList = deluxeRoomList + ((Deluxe_vacant > 1) ? ", %s" : "%s").format(room.getRoomNo());
						}
					} else if (roomType.equals("VIP Suite")) {
						VIP++;
						if status.equals("Vacant") {
							VIP_vacant++;
							vipSuiteRoomList = vipSuiteRoomList + ((VIP_vacant > 1) ? ", %s" : "%s").format(room.getRoomNo());
						}
					}
				}
				
				System.out.printf("Single: Number: %d out of %d\n\t\tRooms: %s\n", Single_vacant, Single, singleRoomList);
				System.out.printf("Double: Number: %d out of %d\n\t\tRooms: %s\n", Double_vacant, Double, doubleRoomList);
				System.out.printf("Deluxe: Number: %d out of %d\n\t\tRooms: %s\n", Deluxe_vacant, Deluxe, deluxeRoomList);
				System.out.printf("VIP Suite: Number: %d out of %d\n\t\tRooms: %s\n", VIP_vacant, VIP, vipSuiteRoomList);
				
			} else if (attr.equals("status")) {
				
				String vacantRoomList = "";
				String occupiedRoomList = "";
				String reservedRoomList = "";
				String underMaintenanceRoomList = "";
				for (int i = 0; i < roomList.size(); i++) {
					Room room = roomList.get(i);
					String status = room.getStatus();
					if (status.equals("Vacant")) {
						vacantRoomList = vacantRoomList + ((Vacant > 1) ? ", %s" : "%s").format(room.getRoomNo());
					} else if (status.equals("Occupied")) {
						occupiedRoomList = occupiedRoomList + ((Occupied > 1) ? ", %s" : "%s").format(room.getRoomNo());
					} else if (status.equals("Reserved")) {
						reservedRoomList = reservedRoomList + ((Reserved > 1) ? ", %s" : "%s").format(room.getRoomNo());
					} else if (status.equals("Under Maintenance")) {
						underMaintenanceRoomList = underMaintenanceRoomList + ((Under_Maintenance > 1) ? ", %s" : "%s").format(room.getRoomNo());
					}
				}
				
				System.out.printf("Vacant:\n\t\tRooms: %s\n", vacantRoomList);
				System.out.printf("Occupied:\n\t\tRooms: %s\n", occupiedRoomList);
				System.out.printf("Reserved:\n\t\tRooms: %s\n", reservedRoomList);
				System.out.printf("Under Maintenance:\n\t\tRooms: %s\n", underMaintenanceRoomList);
			}
			
			return true;
		} else {
			System.out.println("No data.")
			return false;
		}
	}

}