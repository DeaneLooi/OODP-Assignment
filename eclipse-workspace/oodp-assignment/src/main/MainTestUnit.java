package main;

import java.util.List;

import org.junit.jupiter.api.Test;

import guest.Guest;
import guest.GuestController;
import room.Room;
import room.RoomController;
import utils.Constants;

class MainTestUnit {

	@Test
	void testRoom() {
		
		//MainController.createRoom();
		//MainController.updateRoom();
		//MainController.checkRoomAvailability();
		//MainController.printReportByRoomType();
		//MainController.printReportByAvailability();
		List<Room> room = RoomController.retrieveRoomList();
		for(int i=0; i< room.size();i++) {
			Room r = room.get(i);
			r.setStatus(Constants.ROOM_STATUS_VACANT);
			RoomController.updateRoomList(r);
		}
		
		
	}
	
	
	@Test
	void testGuest() {
		
		//MainController.createGuest();
		//MainController.updateGuest();
		//MainController.searchGuest();
		Guest guest = GuestController.getGuestByPassport("S91241872B");
		GuestController.removeGuest(guest);

	}
	
	@Test
	void TestReservation() {
		
		MainController.createReservation();
		MainController.updateReservation();
		MainController.removeReservation();
		MainController.printReservation();
		
	}
	
	@Test
	void testItem() {
		
		MainController.createMenuItem();
		MainController.updateMenuItem();
		MainController.removeMenuItem();
		MainController.displayMenu();
		
	}
	
	@Test
	void testService() {
		
		MainController.createRoomServiceOrder();
		MainController.updateRoomServiceOrder();
		MainController.removeRoomServiceOrder();
	}
	
	@Test
	void testCheckInCheckOut() {
		
		MainController.checkIn();
		MainController.checkOut();
	}
	

}
