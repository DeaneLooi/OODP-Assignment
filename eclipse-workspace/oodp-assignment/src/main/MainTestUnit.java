package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import guest.Guest;
import guest.GuestController;
import room.Room;
import room.RoomController;
import utils.Constants;
import utils.RegexValidation;

class MainTestUnit {

	/*@Test
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
	}*/
	
	@Test
	void printWeekDaysAndWeekEnds() {
		Scanner input = new Scanner(System.in);
		String checkin; 
		String checkout;
		Date checkInDate = null;
		Date checkOutDate = null;
		
		do {
			System.out.print("Check-in Date (dd/mm/yyyy) Time: 11 AM: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			checkin = input.next();
			input.nextLine();
			checkin = checkin.replaceAll(RegexValidation.whiteSpaceRegex, "");
			if (!checkin.matches(RegexValidation.dateRegex))
				System.out.println("Wrong date format");
		} while (!checkin.matches(RegexValidation.dateRegex));

		do {
			System.out.print("Check-out Date (dd/mm/yyyy) Time: 9 AM: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			checkout = input.next();
			input.nextLine();
			checkout = checkout.replaceAll(RegexValidation.whiteSpaceRegex, "");
			if (!checkout.matches(RegexValidation.dateRegex))
				System.out.println("Wrong date format");
		} while (!checkout.matches(RegexValidation.dateRegex));
		checkin = checkin + " 11:00:00";
		checkout = checkout + " 09:00:00";
		if (null != checkin && checkin.trim().length() > 0 && null != checkout && checkout.trim().length() > 0) {
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			try {
				checkInDate = myFormat.parse(checkin);
				// checkInDate.setHours(11);
				checkOutDate = myFormat.parse(checkout);
				// checkOutDate.setHours(9);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		int weekend = 0;
		
		//long checkindate = checkInDate.getTime();
		//long checkoutdate = checkOutDate.getTime();
		long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
		
		Date startDate = checkInDate;
		for(int i=0; i<= diff;i++)
		{
			if(startDate.getDay() == 0 || startDate.getDay() == 6) {
				weekend++;
			}
			startDate.setDate(startDate.getDate()+1);
		}
		
		System.out.println("this" + diff);
		int weekday = (int) (diff - weekend);
		System.out.println("Weekends: " + weekend);
		System.out.println("Weekdays: " + weekday);
		
	}
	

}
