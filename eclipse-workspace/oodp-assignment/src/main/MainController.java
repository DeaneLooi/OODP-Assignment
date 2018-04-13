package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import guest.Guest;
import guest.GuestController;
import item.Item;
import item.ItemController;
import reservation.Reservation;
import reservation.ReservationController;
import room.Room;
import room.RoomController;
import service.Service;
import service.ServiceController;
import utils.Constants;

/**
 * <h1>Main Controller</h1>
 *
 * Contains methods that uses all of the other controller classes.
 *
 *
 *
 */
public class MainController {
	static Scanner input = new Scanner(System.in);
	static int i = 0;
	static int option;
	static boolean result;

	/**
	 * 
	 */
	public static void createGuest() {
		String name, email, country, gender, passport, address, phoneNo, creditCardNo;
		Guest guest;

		System.out.print("Passport No: ");
		passport = input.next();// validation

		if (GuestController.getGuestByPassport(passport) != null) {
			System.out.println("Guest with passport number " + passport + " already exist.");
		} else {
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
			creditCardNo = input.next(); // to do: credit card number format?

			guest = new Guest(name, email, country, gender, passport, address, phoneNo, creditCardNo);
			result = GuestController.updateGuestList(guest);
			if (result == true) {
				System.out.println("Guest created successfully.");
			} else {
				System.out.println("Error...");
			}
		}
	}

	/**
	 * 
	 */
	public static void updateGuest() {
		String name, email, country, gender, passport, address, phoneNo, creditCardNo;
		Guest guest;

		System.out.print("Enter the passport of the guest to update: ");
		passport = input.next();// validation
		guest = GuestController.getGuestByPassport(passport);

		if (guest == null) {
			System.out.println("Guest does not exist");
		} else {
			// print guest details
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
				switch (option) {
				case 1:
					System.out.print("Enter new name: ");
					name = input.next();
					guest.setName(name);
					break;
				case 2:
					System.out.print("Enter new email: "); // to do validate email
					email = input.next();
					guest.setEmail(email);
					break;
				case 3:
					System.out.print("Enter new country: ");
					country = input.next();
					guest.setCountry(country);
					break;
				case 4:
					System.out.print("Enter new gender: "); // to do validate gender
					gender = input.next();
					guest.setEmail(gender);
					break;
				case 5:
					System.out.println("Enter new address: ");
					address = input.next();
					guest.setAddress(address);
					break;
				case 6:
					System.out.print("Enter new phone number: ");
					phoneNo = input.next();
					guest.setPhoneNo(phoneNo);
					break;
				case 7:
					System.out.print("Enter new credit card number: ");
					creditCardNo = input.next();
					guest.setCreditCardNo(creditCardNo);
					break;
				case 8:
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}
			} while (!(option >= 1 && option <= 8));

			result = GuestController.updateGuestList(guest);
			if (result == true) {
				System.out.println("Guest updated successfully.");
			} else {
				System.out.println("Error...");
			}
		}
	}

	/**
	 * 
	 */
	public static void searchGuest() {
		String keyword;
		List<Guest> guestList;

		System.out.print("Enter the keyword: ");
		keyword = input.next();
		guestList = GuestController.searchByKeyword(keyword);
		if (guestList.size() == 0) {
			System.out.println("No such guest with keyword " + keyword);
		} else {
			for (i = 0; i < guestList.size(); i++) {
				System.out.println(guestList.get(i).toString());
			}
		}
	}

	/**
	 * 
	 */
	public static void createReservation() {
		String guestPassport, roomNo, status = null, checkin, checkout;
		Date checkInDate = null, checkOutDate = null;
		int noAdults, noChildren;
		Reservation reservation;
		Room room;

		System.out.print("Passport Number: ");
		guestPassport = input.next();

		if (GuestController.getGuestByPassport(guestPassport) == null) {
			System.out.println("Guest does not exist. Please create the guest.");
			createGuest();
		} // check guest passport again

		// to display rooms 1st
		System.out.print("Room Number: ");
		roomNo = input.next(); // check roomNo exists or not if not, create room

		System.out.print("Check-in Date (dd/mm/yy): ");
		checkin = input.next();

		System.out.print("Check-out Date (dd/mm/yy): ");
		checkout = input.next();

		System.out.print("Number of Adults: ");
		noAdults = input.nextInt();

		System.out.print("Number of Children: ");
		noChildren = input.nextInt();

		if (null != checkin && checkin.trim().length() > 0 && null != checkout && checkout.trim().length() > 0) {
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
			try {
				checkInDate = myFormat.parse(checkin);
				checkOutDate = myFormat.parse(checkout);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (checkInDate != null && checkOutDate != null) {
			reservation = new Reservation(guestPassport, roomNo, checkInDate, checkOutDate, status, noAdults,
					noChildren);

			room = RoomController.getRoom(roomNo);

			if (room.getStatus().equals(Constants.STATUS_VACANT)) {
				reservation.setStatus(Constants.STATUS_CONFIRMED);
			}

			else if (room.getStatus().equals(Constants.STATUS_OCCUPIED)
					|| room.getStatus().equals(Constants.STATUS_UNDER_MAINTAINENCE)
					|| room.getStatus().equals(Constants.STATUS_RESERVED))
				reservation.setStatus(Constants.STATUS_WAITLIST);

			if (ReservationController.updateReservationList(reservation)) {
				System.out.println("Reservation created successfully.");

			}

			else
				System.out.println("Error...");

		}

	}

	/**
	 * 
	 */
	public static void updateReservation() {

		String reservationCode, roomNo, checkin;
		Reservation reservation;
		int option, no, status;
		Date checkInDate = null;

		System.out.println("Enter the reservation code: ");
		reservationCode = input.next();

		reservation = ReservationController.getReservationByCode(reservationCode);

		if (reservation != null) {
			System.out.println(reservation.toString());
			System.out.println("Enter the option that you would like to update");
			System.out.println("1. Room Number");
			System.out.println("2. Check-in date");
			System.out.println("3. Check-out date");
			System.out.println("4. No. of adults");
			System.out.println("5. No. of children");
			System.out.println("6. Status");
			System.out.println("7. Update");
			System.out.println("8. Cancel");
			System.out.print("Option: ");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					System.out.print("Enter new room number: ");
					roomNo = input.next();
					reservation.setRoomNo(roomNo);
					break;
				case 2:
					System.out.print("Enter new check-in date: ");
					checkin = input.next();
					if (null != checkin && checkin.trim().length() > 0) {
						SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
						try {
							checkInDate = myFormat.parse(checkin);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						reservation.setCheckInDate(checkInDate);
					}
					break;
				case 3:
					System.out.print("Enter new check-out date: ");
					checkin = input.next();
					if (null != checkin && checkin.trim().length() > 0) {
						SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
						try {
							checkInDate = myFormat.parse(checkin);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						reservation.setCheckOutDate(checkInDate);
					}
					break;
				case 4:
					System.out.print("Enter new no. of adults: "); // to do validate gender
					no = input.nextInt();// to-do:validate
					reservation.setNoAdults(no);
					break;
				case 5:
					System.out.println("Enter new no. of children: ");
					no = input.nextInt();
					reservation.setNoChildren(no);
					break;
				case 6:
					System.out.println("Choose new status: ");
					System.out.println("1.	Reserved");
					System.out.println("2.  Confirmed");
					System.out.println("3.  Checked-in");
					System.out.println("4.  Checked-out");
					status = input.nextInt();

					switch(status)
					{
					case 1:
						reservation.setStatus(Constants.STATUS_RESERVED);
						break;
					case 2:
						reservation.setStatus(Constants.STATUS_CONFIRMED);
						break;
					case 3:
						reservation.setStatus(Constants.STATUS_CHECKED_IN);
						break;
					case 4:
						reservation.setStatus(Constants.STATUS_CHECKED_OUT);
						break;
					default:System.out.println("Invalid option");
					}
						
					break;
				case 7:
					if (ReservationController.updateReservationList(reservation))
						System.out.println("Reservation updated");
					else
						System.out.println("Error");
					break;
				case 8:
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}
			} while (option != 7 || option != 8);
		}

		else {
				System.out.println("Reservation does not exist");
		}
	}

	/**
	 * 
	 */
	public static void removeReservation() {
		String reservationCode;
		Reservation reservation;

		System.out.println("Enter the reservation code: ");
		reservationCode = input.next();
		reservation = ReservationController.getReservationByCode(reservationCode);
		if (reservation == null) {
			System.out.println("Reservation " + reservationCode + " does not exist");
		} else {
			ReservationController.removeReservation(reservation);
		}
	}

	/**
	 * 
	 */
	public static void printReservation() {
		List<Reservation> reservationList;
		reservationList = ReservationController.retrieveReservationList();
		for (i = 0; i < reservationList.size(); i++) {
			System.out.println(reservationList.get(i).toString());
		}
	}

	/**
	 * 
	 */
	public static void createRoom() {

	}

	/**
	 * 
	 */
	public static void checkRoomAvailability() {

		String roomNo;
		Room room;
		System.out.println("Enter room number: ");
		roomNo = input.next();
		room = RoomController.getRoom(roomNo);

		if (room != null) {
			System.out.println("Room " + room.getRoomNo() + " is " + room.getStatus());
		}

		else
			System.out.println("Room not found");

	}

	/**
	 * 
	 */
	public static void updateRoom() {

	}

	/**
	 * 
	 */
	public static void printReportByRoomType() {

	}

	/**
	 * 
	 */
	public static void printReportByAvailability() {

	}

	/**
	 * 
	 */
	public static void createMenuItem() {
		String itemName, description;
		double price;
		Item item;

		System.out.println("Enter the item name: ");
		itemName = input.next();
		item = ItemController.getItemFromName(itemName);
		if (item != null) {
			System.out.println(itemName + " already exists.");
		} else {
			System.out.println("Enter description: ");
			description = input.next();

			System.out.println("Enter price: S$");
			price = input.nextDouble();

			item = new Item(itemName, description, price);
			result = ItemController.updateItemList(item);
			if (result == true) {
				System.out.println("Item created successfully.");
			} else {
				System.out.println("Error...");
			}
		}
	}

	/**
	 * 
	 */
	public static void updateMenuItem() {
		String itemName, description;
		double price;
		Item item;

		System.out.println("Enter the item name: ");
		itemName = input.next();
		item = ItemController.getItemFromName(itemName);
		if (item == null) {
			System.out.println(itemName + " does not exists.");
		} else {
			System.out.println("Enter the option that you would like to update");
			System.out.println("1. Description");
			System.out.println("2. Price");
			System.out.println("3. Exit");
			System.out.print("Option: ");
			option = input.nextInt(); // check if input is int
			do {
				switch (option) {
				case 1:
					System.out.print("Enter new description: ");
					description = input.next();
					item.setItemDescription(description);
					break;
				case 2:
					System.out.print("Enter new price: "); // to do validate email
					price = input.nextDouble();
					item.setItemPrice(price);
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}
			} while (!(option >= 1 && option <= 3));

			result = ItemController.updateItemList(item);
			if (result == true) {
				System.out.println("Item updated successfully.");
			} else {
				System.out.println("Error...");
			}
		}
	}

	/**
	 * 
	 */
	public static void removeMenuItem() {
		String itemName;
		Item item;

		System.out.print("Enter item name: ");
		itemName = input.next();
		item = ItemController.getItemFromName(itemName);
		if (item == null) {
			System.out.println(itemName + " does not exists.");
		} else {
			ItemController.removeItem(item);
		}
	}

	/**
	 * 
	 */
	public static void checkIn() {

		String guestPassport, choice, roomNo;
		System.out.println("Enter passport details: ");
		guestPassport = input.next();

		Reservation reservation = ReservationController.getReservationByGuestPassport(guestPassport);

		if (reservation != null) {

			reservation.setStatus(Constants.STATUS_CHECKED_IN);

			if (reservation.getStatus().equals(Constants.STATUS_CONFIRMED)) {
				System.out.println("Proceed to check in!");
				if (ReservationController.updateReservationList(reservation))
					System.out.println("Room Number : " + reservation.getRoomNo());
			}

			else if (reservation.getStatus().equals(Constants.STATUS_EXPIRED))
				System.out.println("Sorry, your reservation has expired because you were late for more than 1 hour!");

			else if (reservation.getStatus().equals(Constants.STATUS_WAITLIST)) {
				Room room = RoomController.getRoom(reservation.getRoomNo());
				if (room.getStatus().equals(Constants.STATUS_VACANT)) {
					System.out.println("Your room is vacant, proceed to check in!");
					if (ReservationController.updateReservationList(reservation))
						System.out.println("Room Number : " + reservation.getRoomNo());
				}

				else if (room.getStatus().equals(Constants.STATUS_OCCUPIED)
						|| room.getStatus().equals(Constants.STATUS_UNDER_MAINTAINENCE)) {
					System.out.println("Your room is currently occupied, would you like to change your room(Y/N)");
					choice = input.next();

					if (choice.equalsIgnoreCase("Y")) {
						printReportByAvailability();
						System.out.println("Enter room number: ");
						roomNo = input.next();
						reservation.setRoomNo(roomNo);
						if (ReservationController.updateReservationList(reservation))
							System.out.println("Room Number : " + reservation.getRoomNo());
					}

					else if (choice.equalsIgnoreCase("N"))
						System.out.println("Have a nice day!");

				}

			}

		} else {
			createReservation();
			reservation = ReservationController.getReservationByGuestPassport(guestPassport);
			if (reservation.getStatus().equals(Constants.STATUS_CONFIRMED)) {
				System.out.println("Proceed to check in!");
				reservation.setStatus(Constants.STATUS_CHECKED_IN);

				if (ReservationController.updateReservationList(reservation))
					System.out.println("Room Number : " + reservation.getRoomNo());
			}
		}

	}

	/**
	 * 
	 */
	public static void checkOut() {

	}

	/**
	 * 
	 */
	public static void createRoomServiceOrder() {
		String roomNo, remarks;
		Reservation reservation;
		List<Item> itemList = ItemController.retrieveItemList();

		System.out.println("Enter room number: ");
		roomNo = input.next();
		reservation = (Reservation) ReservationController.getReservationByRoomNo(roomNo);
		displayMenu();
		int choice = 1;
		do {
			System.out.println("Choose item from menu(enter 0 to confirm)");
			choice = input.nextInt();

			System.out.println("Input any Remarks: ");
			remarks = input.next();

			Service service = new Service(roomNo, reservation.getReservationCode(),
					itemList.get(choice - 1).getItemName(), new Date(), remarks, Constants.STATUS_CONFIRMED);

			ServiceController.updateServiceList(service);

		} while (choice > 0 && choice < itemList.size());

	}

	/**
	 * 
	 */
	public static void updateRoomServiceOrder() {
		
		int serviceID, status;
		String roomNo, itemName, remarks;
		Room room;
		Reservation reservation;
		Service service;
		System.out.println("Enter room number: ");
		roomNo = input.next();
		
		room = RoomController.getRoom(roomNo);
		
		if(room!=null) {
			
			reservation = ReservationController.getReservationByRoomNo(roomNo, Constants.STATUS_CHECKED_IN);
			
			if(reservation!=null) {
				
				List<Service> serviceList = ServiceController.getServicesFromReservationCode(reservation.getReservationCode());
				
				if(serviceList!=null)
				{
					for(i=0; i<serviceList.size();i++)
					{
						System.out.println(serviceList.get(i).toString());
					}
					
					
					System.out.println("Enter serviceID to update");
					serviceID = input.nextInt();
					service = ServiceController.getServiceFromServiceID(serviceID);
					
					System.out.println("Enter the option that you would like to update");
					System.out.println("1. Item Name");
					System.out.println("2. Remarks");
					System.out.println("3. Status");
					System.out.println("4. Update");
					System.out.println("5. Cancel");
					System.out.print("Option: ");
					option = input.nextInt();
					do {
						switch (option) {
						case 1:
							System.out.print("Enter new item name: ");
							itemName = input.next();
							service.setItemID(itemName);
							break;
						case 2:
							System.out.print("Enter new remarks: ");
							remarks = input.next();
							service.setRemarks(remarks);
							break;
						case 3:
							System.out.println("Choose new status: ");
							System.out.println("1.	Confirmed");
							System.out.println("2.  Preparing");
							System.out.println("3.  Delivered");
							status = input.nextInt();

							switch(status)
							{
							case 1:
								service.setStatus(Constants.STATUS_CONFIRMED);
								break;
							case 2:
								service.setStatus(Constants.STATUS_PREPARING);
								break;
							case 3:
								service.setStatus(Constants.STATUS_DELIVERED);
								break;
							default:System.out.println("Invalid option");
							}
								
							break;
						case 4:
							if (ServiceController.updateServiceList(service))
								System.out.println("Service updated");
							else
								System.out.println("Error");
							break;
						case 5:
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}
					} while (option != 4 || option != 5);
					
				}
				
				else
					System.out.println("No room service orders has been made for that room");

			}
			
			else
				System.out.println("No reservation has been checked in for that room");
		}
		
		else
			System.out.println("Room does not exist");
		
		
		
		
	}

	/**
	 * 
	 */
	public static void removeRoomServiceOrder() {
		String roomNo;
		int serviceID;

		System.out.println("Enter room number:");
		roomNo = input.next();

		Reservation r = ReservationController.getReservationByRoomNo(roomNo, Constants.STATUS_CHECKED_IN);

		List<Service> serviceList = ServiceController.getServicesFromReservationCode(r.getReservationCode());

		for (i = 0; i < serviceList.size(); i++) {
			System.out.println(serviceList.get(i).toString());
		}

		System.out.println("Enter serviceID to remove: ");
		serviceID = input.nextInt();

		ServiceController.removeService(ServiceController.getServiceFromServiceID(serviceID));

	}

	/**
	 * 
	 */
	public static void displayMenu() {
		System.out.println(ItemController.getMenu());
	}
}
