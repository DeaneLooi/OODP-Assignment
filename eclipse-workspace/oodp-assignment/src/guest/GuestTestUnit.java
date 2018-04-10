package guest;

import java.util.List;

import org.junit.jupiter.api.Test;
import guest.Guest;
import guest.GuestController;

public class GuestTestUnit {

	@Test
	void testGuestCreation(){
		// create guest object
		Guest guest = new Guest("xyz", "xyz@gmail.com", "Singapore", "Male", "S9876543R", "Blk 123, cck ave-5, #01-98, Singapore 765432", "+65 9732 2877", "123-4321-2222");
		boolean result = GuestController.updateGuestList(guest);
		System.out.println("Guest creation result 1: " + result);
		Guest guest2 = new Guest("ayz", "ayz@gmail.com", "Singapore", "Male", "S9213456Z", "Blk 423, cck ave-4, #06-78, Singapore 654241", "+65 9313 2554", "154-4352-5674");
		result = GuestController.updateGuestList(guest2);
		System.out.println("Guest creation result 2: " + result);
	}
	
	@Test
	void testGuestUpdate() {
		List<Guest> guestList = GuestController.retrieveGuestList();
		Guest guest = null;
		if(guestList!=null) {
			guest = guestList.get(0);
		}
		guest.setEmail("xyz@msn.com");
		GuestController.updateGuestList(guest);
		System.out.println(guest.toString());
	}
	
	@Test
	void testRemoveGuest() {
		Guest guest = new Guest("abc", "abc@gmail.com", "Singapore", "Female", "S9562145Z", "Blk 101, amk ave-1, #07-09, Singapore 912332", "+65 8335 3652", "123-8742-1928");
		boolean result = GuestController.removeGuest(guest);
		System.out.println("Guest deletion result: " + result);
	}
	
	@Test
	void testRetrieveGuestByPassport() {
		Guest guest = GuestController.getGuestByPassport("S9876543R");
		System.out.println(guest.toString());
	}
	
	@Test
	void testSearchByKeyword() {
		List<Guest> guestList = GuestController.searchByKeyword("yz");
		for(int i=0; i< guestList.size(); i++) {
			System.out.println(guestList.get(i).toString());
		}
	}
	
	@Test
	void testGuestRetrieve() {
		List<Guest> guestList = GuestController.retrieveGuestList();
		for(int i=0; i< guestList.size(); i++) {
			System.out.println(guestList.get(i).toString());
		}
	}
	
	
}
