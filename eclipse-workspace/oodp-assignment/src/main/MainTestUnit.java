package main;

import java.util.List;

import org.junit.jupiter.api.Test;

import guest.Guest;
import guest.GuestController;
import room.Room;
import room.RoomController;

class MainTestUnit {

	@Test
	void testRoom() {
		
		//MainController.createRoom();
		//MainController.updateRoom();
		//MainController.checkRoomAvailability();
		//MainController.printReportByRoomType();
		//MainController.printReportByAvailability();
/*		List<Room> room = RoomController.retrieveRoomList();
		for(int i=0; i< room.size();i++) {
			System.out.println(room.size());
			System.out.println(room.get(i).toString());
		}*/
		
		
	}
	
	
	@Test
	void testGuest() {
		
		//MainController.createGuest();
		//MainController.updateGuest();
		//MainController.searchGuest();
		Guest guest = new Guest();
		List<Guest>guestList = GuestController.retrieveGuestList();
		for(int i=0; i<guestList.size();i++) {
			
			System.out.println("Guest passport is <"+guestList.get(i).getPassport()+">");
			System.out.println("Guest name is <"+guestList.get(i).getName()+">");
		}
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
