package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import guest.Guest;
import guest.GuestController;
import item.Item;
import item.ItemController;
import reservation.Reservation;
import reservation.ReservationController;

public class MainController {
	static Scanner input = new Scanner(System.in);
	static int i = 0;
	static int option;
	static boolean result;
	
	public static void createGuest() {
		String name, email, country, gender, passport, address, phoneNo, creditCardNo;
		Guest guest;
		
		System.out.print("Passport No: ");
		passport = input.next();
		
		if(GuestController.getGuestByPassport(passport) == null) {
			System.out.println("Guest with passport number " + passport + " already exist.");
		}
		else {
			System.out.print("Name: ");
			name = input.next(); 
			
			System.out.print("Email: ");
			email = input.next(); // to do: validate email
			
			System.out.print("Country: ");
			country = input.next();
			
			System.out.print("Gender: ");
			gender = input.next(); // to do: validate gender 
			
			System.out.print("Address: ");
			address = input.next(); 
			
			System.out.print("Phone Number: ");
			phoneNo = input.next(); 
			
			System.out.print("Credit Card Number: ");
			creditCardNo = input.next();
			
			guest = new Guest(name, email, country, gender, passport, address, phoneNo, creditCardNo);
			result = GuestController.updateGuestList(guest);
			if(result == true) {
				System.out.println("Guest created successfully.");
			}
			else {
				System.out.println("Error...");
			}
		} 
	}
	
	public static void updateGuest() {
		String name, email, country, gender, passport, address, phoneNo, creditCardNo;
		Guest guest;
		
		System.out.print("Enter the passport of the guest to update: ");
		passport = input.next();
		guest = GuestController.getGuestByPassport(passport);
		
		if(guest == null) {
			System.out.println("Guest does not exist");
		}
		else {
			System.out.println("Enter the option that you would like to update");
			System.out.println("1. Name");
			System.out.println("2. Email");
			System.out.println("3. Country");
			System.out.println("4. Gender");
			System.out.println("5. Address");
			System.out.println("6. Phone Number");
			System.out.println("7. Credit Card Number");
			System.out.println("8. Exit");
			System.out.print("Option: ");
			option = input.nextInt(); // check if input is int
			do {
				switch(option) {
					case 1: System.out.print("Enter new name: ");
							name = input.next();
							guest.setName(name);
							break;
					case 2: System.out.print("Enter new email: "); // to do validate email
							email = input.next();
							guest.setEmail(email);
							break;
					case 3: System.out.print("Enter new country: ");
							country = input.next();
							guest.setCountry(country);
							break;
					case 4: System.out.print("Enter new gender: "); // to do validate gender
							gender = input.next();
							guest.setEmail(gender);
							break;
					case 5: System.out.println("Enter new address: ");
							address = input.next();
							guest.setAddress(address);
							break;
					case 6: System.out.print("Enter new phone number: ");
							phoneNo = input.next();
							guest.setPhoneNo(phoneNo);
							break;
					case 7: System.out.print("Enter new credit card number: ");
							creditCardNo = input.next();
							guest.setCreditCardNo(creditCardNo);
							break;
					case 8: break;
					default: System.out.println("Invalid option");
							 System.out.print("Re-enter your option: ");
							 option = input.nextInt();
				}
			}while(!(option >= 1 && option <= 8));
			
			result = GuestController.updateGuestList(guest);
			if(result == true) {
				System.out.println("Guest updated successfully.");
			}
			else {
				System.out.println("Error...");
			}
		}
	}
	
	public static void searchGuest() {
		String keyword;
		List<Guest> guestList;
		
		System.out.print("Enter the keyword: ");
		keyword = input.next();
		guestList = GuestController.searchByKeyword(keyword);
		if(guestList.size() == 0) {
			System.out.println("No such guest with keyword " + keyword);
		}
		else {
			for(i=0; i< guestList.size(); i++) {
				System.out.println(guestList.get(i).toString());
			}
		}
	}
	
	public static void createReservation() {
		String guestPassport, roomNo, status = null, checkin;
		Date checkInDate = null, checkOutDate = null;
		int noAdults, noChildren;
		Reservation reservation;
		
		System.out.print("Passport Number: ");
		guestPassport = input.next();
		
		if(GuestController.getGuestByPassport(guestPassport) == null) {
			System.out.println("Guest does not exist. Please create the guest.");
			createGuest();
		} // check guest passport again
		
		
		// to display rooms 1st
		System.out.print("Room Number: ");
		roomNo = input.next(); // check roomNo exists or not if not, create room
		
		System.out.print("Check-in Date (dd/mm/yy): ");
		checkin = input.next();
		if(null != checkin && checkin.trim().length() > 0){
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
		    try {
				checkInDate = myFormat.parse(checkin);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} // to verify
		
		System.out.print("Check-out Date (dd/mm/yy): ");
		checkin = input.next();
		if(null != checkin && checkin.trim().length() > 0){
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
		    try {
				checkInDate = myFormat.parse(checkin);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} // to verify
		
		// status
		
		System.out.print("Number of Adults: ");
		noAdults = input.nextInt();
		
		System.out.print("Number of Children: ");
		noChildren = input.nextInt();
		
		reservation = new Reservation(guestPassport, roomNo, checkInDate, checkOutDate, status, noAdults, noChildren);
		result = ReservationController.updateReservationList(reservation);
		if(result == true) {
			System.out.println("Reservation created successfully.");
		}
		else {
			System.out.println("Error...");
		}
	}
	
	public void updateReservation() {
		
	}
	
	public void removeReservation() {
		String reservationCode;
		Reservation reservation;
		
		System.out.println("Enter the reservation code: ");
		reservationCode = input.next();
		reservation = ReservationController.getReservationByCode(reservationCode);
		if(reservation == null) {
			System.out.println("Reservation " + reservationCode + " does not exist");
		}
		else {
			ReservationController.removeReservation(reservation);
		}
	}
	
	public void printReservation() {
		List<Reservation> reservationList;
		reservationList = ReservationController.retrieveReservationList();
		for(i=0; i< reservationList.size(); i++) {
			System.out.println(reservationList.get(i).toString());
		}
	}
	
	public void createRoom() {
		
	}
	
	public void checkRoomAvailability() {
		
	}
	
	public void updateRoom() {
		
	}
	
	public void printReportByRoomType() {
		
	}
	
	public void printReportByAvailability() {
		
	}
	
	public void createMenuItem() {
		String itemName, description;
		float price;
		Item item;
		
		System.out.println("Enter the item name: ");
		itemName = input.next();
		item = ItemController.getItemFromName(itemName);
		if(item != null) {
			System.out.println(itemName + " already exists.");
		}
		else {
			System.out.println("Enter description: ");
			description = input.next();
			
			System.out.println("Enter price: S$");
			price = input.nextFloat();
			
			item = new Item(itemName, description, price);
			result = ItemController.updateItemList(item);
			if(result == true) {
				System.out.println("Item created successfully.");
			}
			else {
				System.out.println("Error...");
			}
		}
	}
	
	public void updateMenuItem() {
		String itemName, description;
		float price;
		Item item;
		
		System.out.println("Enter the item name: ");
		itemName = input.next();
		item = ItemController.getItemFromName(itemName);
		if(item == null) {
			System.out.println(itemName + " does not exists.");
		}
		else {
			System.out.println("Enter the option that you would like to update");
			System.out.println("1. Description");
			System.out.println("2. Price");
			System.out.println("3. Exit");
			System.out.print("Option: ");
			option = input.nextInt(); // check if input is int
			do {
				switch(option) {
					case 1: System.out.print("Enter new description: ");
							description = input.next();
							item.setItemDescription(description);
							break;
					case 2: System.out.print("Enter new price: "); // to do validate email
							price = input.nextFloat();
							item.setItemPrice(price);
							break;
					case 3: break;
					default: System.out.println("Invalid option");
							 System.out.print("Re-enter your option: ");
							 option = input.nextInt();
				}
			}while(!(option >= 1 && option <= 3));
			
			result = ItemController.updateItemList(item);
			if(result == true) {
				System.out.println("Item updated successfully.");
			}
			else {
				System.out.println("Error...");
			}
		}
	}
	
	public void removeMenuItem() {
		String itemName;
		Item item;
		
		System.out.print("Enter item name: ");
		itemName = input.next();
		item = ItemController.getItemFromName(itemName);
		if(item == null) {
			System.out.println(itemName + " does not exists.");
		}
		else {
			ItemController.removeItem(item);
		}
	}
	
	public void checkIn() {
		
	}
	
	public void checkOut() {
		
	}
	
	public void createRoomServiceOrder() {
		
	}
	
	public void updateRoomServiceOrder() {
		
	}
	
	public void removeRoomServiceOrder() {
		
	}
}
