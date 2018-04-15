package room;

import java.util.List;
import org.junit.jupiter.api.Test;


import utils.Constants;

class RoomTestUnit {
	@Test
	void testRoomCreation() {
		System.out.println("Creating rooms...");
		// create service object
		Room room = new Room("03-01", Constants.BED_TYPE_DOUBLE, Constants.ROOM_TYPE_DOUBLE, Constants.WIFI_DISABLED, Constants.ROOM_VIEW_CITY, Constants.SMOKING_ALLOWED, Constants.ROOM_STATUS_VACANT);
		boolean result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-02", Constants.BED_TYPE_KING, Constants.ROOM_TYPE_SINGLE, Constants.WIFI_ENABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_VACANT);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-03", Constants.BED_TYPE_TWIN, Constants.ROOM_TYPE_DOUBLE, Constants.WIFI_ENABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_UNDER_MAINTAINENCE);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-04", Constants.BED_TYPE_QUEEN, Constants.ROOM_TYPE_SINGLE, Constants.WIFI_ENABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_VACANT);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-05", Constants.BED_TYPE_KING, Constants.ROOM_TYPE_DOUBLE, Constants.WIFI_DISABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_VACANT);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-06", Constants.BED_TYPE_DOUBLE, Constants.ROOM_TYPE_VIP, Constants.WIFI_ENABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_OCCUPIED);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-07", Constants.BED_TYPE_KING, Constants.ROOM_TYPE_DELUXE, Constants.WIFI_ENABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_VACANT);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("03-08", Constants.BED_TYPE_DOUBLE, Constants.ROOM_TYPE_DOUBLE, Constants.WIFI_ENABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_VACANT);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
		room = new Room("02-01", Constants.BED_TYPE_KING, Constants.ROOM_TYPE_DOUBLE, Constants.WIFI_DISABLED, Constants.ROOM_VIEW_OCEAN, Constants.SMOKING_PROHIBITED, Constants.ROOM_STATUS_VACANT);
		result = RoomController.updateRoomList(room);
		System.out.println("Room creation result: " + result);
	}

	@Test
	void testRoomUpdate()
	{
		System.out.println("Updating rooms...");
		List<Room> roomList = RoomController.retrieveRoomList();
		Room room = null;
		if(roomList!=null) {
			room = roomList.get(0);
		}
		room.setStatus(Constants.ROOM_STATUS_RESERVED);
		System.out.println("Updating room result: "+RoomController.updateRoomList(room));
		
	}

	@Test
	void testGetRoom()
	{
		System.out.println("Getting room...");
		Room room = RoomController.getRoom("03-02");
		if (room != null) {
			System.out.println(room.toString() + "\n\n");
		}
	}
	
	@Test
	void testRetrieval()
	{
		System.out.println("Room list retrieving...");
		List<Room> roomList = RoomController.retrieveRoomList();
		for(int i=0; i<roomList.size();i++)
		{
			System.out.println(roomList.get(i).toString() + "\n\n");
		}
		
	}
	
	@Test
	void testRemoveRoom()
	{
		System.out.println("Removing rooms...");
		Room room = new Room();
		room.setRoomNo("02-01");
		boolean result = RoomController.removeRoom(room);
		System.out.println("Remove room result: "+result);
	}
	
	@Test
	void testPrintReport()
	{
		System.out.println("Room report(by room type):\n\n");
		RoomController.printReport(Constants.PRINT_REPORT_BY_ROOM_TYPE);
		System.out.println("\n\nRoom report(by status):\n\n");
		RoomController.printReport(Constants.PRINT_REPORT_BY_STATUS);
	}
}
