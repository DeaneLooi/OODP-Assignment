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
import payment.Payment;
import payment.PaymentController;
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
		passport = input.next();

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
		passport = input.next();
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
			while (!input.hasNextInt()) {
				System.out.println("Please enter a number!");
				input.nextInt();
			}
			option = input.nextInt();
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

			if (room.getStatus().equals(Constants.ROOM_STATUS_VACANT)) {
				reservation.setStatus(Constants.STATUS_CONFIRMED);
			}

			else if (room.getStatus().equals(Constants.ROOM_STATUS_OCCUPIED)
					|| room.getStatus().equals(Constants.ROOM_STATUS_UNDER_MAINTAINENCE)
					|| room.getStatus().equals(Constants.ROOM_STATUS_RESERVED))
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
		int number, status;
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
			while (!input.hasNextInt()) {
				System.out.println("Please enter a number!");
				input.nextInt();
			}
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
					number = input.nextInt();// to-do:validate
					reservation.setNoAdults(number);
					break;
				case 5:
					System.out.println("Enter new no. of children: ");
					number = input.nextInt();
					reservation.setNoChildren(number);
					break;
				case 6:
					System.out.println("Choose new status: ");
					System.out.println("1.	Confirmed");
					System.out.println("2.  In Waitlist");
					System.out.println("3.  Checked-in");
					System.out.println("4.  Checked-out");
					status = input.nextInt();

					switch (status) {
					case 1:
						reservation.setStatus(Constants.STATUS_CONFIRMED);
						break;
					case 2:
						reservation.setStatus(Constants.STATUS_WAITLIST);
						break;
					case 3:
						reservation.setStatus(Constants.STATUS_CHECKED_IN);
						break;
					case 4:
						reservation.setStatus(Constants.STATUS_CHECKED_OUT);
						break;
					default:
						System.out.println("Invalid option");
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

		String roomNo, roomType = null, bedType = null, wifi = null, view = null, smoking = null, status = null;
		Room room;

		System.out.println("Enter the room number: ");
		roomNo = input.next();
		room = RoomController.getRoom(roomNo);
		if (room != null) {
			System.out.println("Room " + roomNo + " already exists.");
		} else {

			System.out.println("Choose room type: ");
			System.out.println("1. Single");
			System.out.println("2. Double");
			System.out.println("3. Deluxe");
			System.out.println("4. Vip Suite");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					roomType = Constants.ROOM_TYPE_SINGLE;
					break;
				case 2:
					roomType = Constants.ROOM_TYPE_DOUBLE;
					break;
				case 3:
					roomType = Constants.ROOM_TYPE_DELUXE;
					break;
				case 4:
					roomType = Constants.ROOM_TYPE_VIP;
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}

			} while (option < 1 || option > 4);

			System.out.println("Choose bed type: ");
			System.out.println("1. Twin");
			System.out.println("2. Double");
			System.out.println("3. Queen");
			System.out.println("4. King");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					bedType = Constants.BED_TYPE_TWIN;
					break;
				case 2:
					bedType = Constants.BED_TYPE_DOUBLE;
					break;
				case 3:
					bedType = Constants.BED_TYPE_QUEEN;
					break;
				case 4:
					bedType = Constants.BED_TYPE_KING;
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}

			} while (option < 1 || option > 4);

			System.out.println("WI-FI: ");
			System.out.println("1. Enabled");
			System.out.println("2. Disabled");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					wifi = Constants.WIFI_ENABLED;
					break;
				case 2:
					wifi = Constants.WIFI_DISABLED;
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}

			} while (option < 1 || option > 2);

			System.out.println("Choose view: ");
			System.out.println("1. Ocean View");
			System.out.println("2. City View");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					view = Constants.ROOM_VIEW_OCEAN;
					break;
				case 2:
					view = Constants.ROOM_VIEW_CITY;
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}

			} while (option < 1 || option > 2);

			System.out.println("Smoking");
			System.out.println("1. Allowed");
			System.out.println("2. Prohibited");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					smoking = Constants.SMOKING_ALLOWED;
					break;
				case 2:
					smoking = Constants.SMOKING_PROHIBITED;
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}

			} while (option < 1 || option > 2);

			System.out.println("Choose status: ");
			System.out.println("1. Vacant");
			System.out.println("2. Occupied");
			System.out.println("3. Under Maintainence");
			option = input.nextInt();
			do {
				switch (option) {
				case 1:
					status = Constants.ROOM_STATUS_VACANT;
					break;
				case 2:
					status = Constants.ROOM_STATUS_OCCUPIED;
					break;
				case 3:
					status = Constants.ROOM_STATUS_UNDER_MAINTAINENCE;
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					option = input.nextInt();
				}

			} while (option < 1 || option > 3);

			room = new Room(roomNo, roomType, bedType, wifi, view, smoking, status);
			result = RoomController.updateRoomList(room);
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
	public static void updateRoom() {

		String roomNo;
		Room room;
		int updateOption;
		System.out.println("Enter the room number: ");
		roomNo = input.next();

		room = RoomController.getRoom(roomNo);

		if (room != null) {

			do {
				System.out.println(room.toString());
				System.out.println("Enter the option that you would like to update");
				System.out.println("1. Room Type");
				System.out.println("2. Bed Type");
				System.out.println("3. Wifi");
				System.out.println("4. View");
				System.out.println("5. Smoking");
				System.out.println("6. Status");
				System.out.println("7. Update");
				System.out.println("8. Cancel");
				System.out.print("Option: ");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number!");
					input.nextInt();
				}
				updateOption = input.nextInt();
				
				switch (updateOption) {
				case 1:
					System.out.println("Choose room type: ");
					System.out.println("1. Single");
					System.out.println("2. Double");
					System.out.println("3. Deluxe");
					System.out.println("4. Vip Suite");
					option = input.nextInt();
					if (option < 1)
						System.out.println("Option < 1");
					else if (option > 4)
						System.out.println("Option > 4");
					do {
						switch (option) {
						case 1:
							room.setRoomType(Constants.ROOM_TYPE_SINGLE);
							break;
						case 2:
							room.setRoomType(Constants.ROOM_TYPE_DOUBLE);
							break;
						case 3:
							room.setRoomType(Constants.ROOM_TYPE_DELUXE);
							break;
						case 4:
							room.setRoomType(Constants.ROOM_TYPE_VIP);
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}

					} while (option < 1 || option > 4);

					break;
				case 2:
					System.out.println("Choose bed type: ");
					System.out.println("1. Twin");
					System.out.println("2. Double");
					System.out.println("3. Queen");
					System.out.println("4. King");
					option = input.nextInt();
					do {
						switch (option) {
						case 1:
							room.setBedType(Constants.BED_TYPE_TWIN);
							break;
						case 2:
							room.setBedType(Constants.BED_TYPE_DOUBLE);
							break;
						case 3:
							room.setBedType(Constants.BED_TYPE_QUEEN);
							break;
						case 4:
							room.setBedType(Constants.BED_TYPE_KING);
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}

					} while (option < 1 || option > 4);
					break;
				case 3:
					System.out.println("WI-FI: ");
					System.out.println("1. Enabled");
					System.out.println("2. Disabled");
					option = input.nextInt();
					do {
						switch (option) {
						case 1:
							room.setWifi(Constants.WIFI_ENABLED);
							break;
						case 2:
							room.setWifi(Constants.WIFI_DISABLED);
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}

					} while (option < 1 || option > 2);

					break;
				case 4:
					System.out.println("Choose view: ");
					System.out.println("1. Ocean View");
					System.out.println("2. City View");
					option = input.nextInt();
					do {
						switch (option) {
						case 1:
							room.setView(Constants.ROOM_VIEW_OCEAN);
							break;
						case 2:
							room.setView(Constants.ROOM_VIEW_CITY);
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}

					} while (option < 1 || option > 2);
					break;
				case 5:
					System.out.println("Smoking");
					System.out.println("1. Allowed");
					System.out.println("2. Prohibited");
					option = input.nextInt();
					do {
						switch (option) {
						case 1:
							room.setSmoking(Constants.SMOKING_ALLOWED);
							break;
						case 2:
							room.setSmoking(Constants.SMOKING_PROHIBITED);
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}

					} while (option < 1 || option > 2);
					break;
				case 6:

					System.out.println("Choose status: ");
					System.out.println("1. Vacant");
					System.out.println("2. Occupied");
					System.out.println("3. Under Maintainence");
					option = input.nextInt();
					do {
						switch (option) {
						case 1:
							room.setStatus(Constants.ROOM_STATUS_VACANT);
							break;
						case 2:
							room.setStatus(Constants.ROOM_STATUS_OCCUPIED);
							break;
						case 3:
							room.setStatus(Constants.ROOM_STATUS_UNDER_MAINTAINENCE);
							break;
						default:
							System.out.println("Invalid option");
							System.out.print("Re-enter your option: ");
							option = input.nextInt();
						}

					} while (option < 1 || option > 3);
					break;
				case 7:
					if (RoomController.updateRoomList(room))
						System.out.println("Room updated");
					else
						System.out.println("Error");
					break;
				case 8:
					System.out.println("Exiting to Main Menu");
					break;
				default:
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					updateOption = input.nextInt();
				}
			} while (updateOption < 7 || updateOption > 8);
		}

		else {
			System.out.println("Room does not exist");
		}

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
	public static void printReportByRoomType() {

		RoomController.printReport(Constants.PRINT_REPORT_BY_ROOM_TYPE);
	}

	/**
	 * 
	 */
	public static void printReportByAvailability() {

		RoomController.printReport(Constants.PRINT_REPORT_BY_STATUS);
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
			while (!input.hasNextDouble() || input.nextDouble() <= 0) {
				System.out.println("Invalid price");
				System.out.println("Please re-enter price: S$");
				input.nextDouble();
			}
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
			while (!input.hasNextInt()) {
				System.out.println("Please enter a number!");
				input.nextInt();
			}
			option = input.nextInt();
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
				if (room.getStatus().equals(Constants.ROOM_STATUS_VACANT)) {
					System.out.println("Your room is vacant, proceed to check in!");
					if (ReservationController.updateReservationList(reservation))
						System.out.println("Room Number : " + reservation.getRoomNo());
				}

				else if (room.getStatus().equals(Constants.ROOM_STATUS_OCCUPIED)
						|| room.getStatus().equals(Constants.ROOM_STATUS_UNDER_MAINTAINENCE)) {
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
		String rmNo;
		List<Reservation> reservationList;
		Reservation reservation = null;
		Payment payment;
		String creditCardNo;
		char choice;

		System.out.println("Enter room number: ");
		rmNo = input.next();

		reservation = ReservationController.getReservationByRoomNo(rmNo, Constants.STATUS_CHECKED_IN);

		payment = new Payment(reservation.getReservationCode(), rmNo, reservation.getGuestPassport());
		PaymentController.updatePaymentList(payment); // validate
		PaymentController.printBillInvoice(payment);

		System.out.println("Pay via: ");
		System.out.println("1. Credit card");
		System.out.println("2. Cash");
		while (!input.hasNextInt()) {
			System.out.println("Please enter a number!");
			input.nextInt();
		}
		option = input.nextInt();
		do {
			switch (option) {
			case 1:
				payment.setPaymentType(Constants.PAYMENT_TYPE_CASH);
				PaymentController.updatePaymentList(payment); // validate
				System.out.println("Payment completed");

				break;
			case 2:
				System.out.println("Pay via existing credit card number? (Y/N)");
				choice = input.next().charAt(0);
				if (choice == 'y' || choice == 'Y') {
					creditCardNo = GuestController.getGuestByPassport(reservation.getGuestPassport()).getCreditCardNo();
					payment.setCreditCard(creditCardNo);
					PaymentController.updatePaymentList(payment); // validate
					System.out.println("Payment completed");
				} else if (choice == 'n' || choice == 'N') {
					System.out.println("Enter credit card number: ");
					creditCardNo = input.next();
					payment.setCreditCard(creditCardNo);
					PaymentController.updatePaymentList(payment); // validate
					System.out.println("Payment completed");
				} else {
					System.out.println("Invalid option");
					System.out.print("Re-enter your option: ");
					choice = input.next().charAt(0);
				}
				break;

			default:
				System.out.println("Invalid option");
				System.out.print("Re-enter your option: ");
				option = input.nextInt();
			}
		} while (option != 1 || option != 2);

		reservation.setStatus(Constants.STATUS_CHECKED_OUT);
		ReservationController.updateReservationList(reservation);
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

		if (room != null) {

			reservation = ReservationController.getReservationByRoomNo(roomNo, Constants.STATUS_CHECKED_IN);

			if (reservation != null) {

				List<Service> serviceList = ServiceController
						.getServicesFromReservationCode(reservation.getReservationCode());

				if (serviceList != null) {
					for (i = 0; i < serviceList.size(); i++) {
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
					while (!input.hasNextInt()) {
						System.out.println("Please enter a number!");
						input.nextInt();
					}
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

							switch (status) {
							case 1:
								service.setStatus(Constants.STATUS_CONFIRMED);
								break;
							case 2:
								service.setStatus(Constants.SERVICE_STATUS_PREPARING);
								break;
							case 3:
								service.setStatus(Constants.SERVICE_STATUS_DELIVERED);
								break;
							default:
								System.out.println("Invalid option");
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
