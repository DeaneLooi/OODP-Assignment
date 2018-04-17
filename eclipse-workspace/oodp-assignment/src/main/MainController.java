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
import utils.RegexValidation;

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
	 * Prompts for passport of the guest. If the guest already exists, it will
	 * return back to calling function. Else it will prompt for more user input for
	 * the necessary attributes to create Guest object and update the guest data
	 * file.
	 * 
	 */
	public static void createGuest() {
		String name, email, country, gender = null, passport, address, phoneNo, creditCardNo;
		Guest guest;
		char genderC;

		System.out.println("<< Guest Creation:");
		System.out.print("Passport No: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input");
			input.next();
		}
		passport = input.next();
		input.nextLine();
		if (GuestController.getGuestByPassport(passport) != null) {
			System.out.println("Guest with passport number " + passport + " already exist.");
		} else {
			System.out.print("Name: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			name = input.nextLine();
			do {
				System.out.print("Email: ");
				while (!input.hasNext()) {
					System.out.println("Wrong input");
					input.next();
				}
				email = input.next();
				input.nextLine();
				if (!email.matches(RegexValidation.emailRegex))
					System.out.println("Wrong email format");
			} while (!email.matches(RegexValidation.emailRegex));

			System.out.print("Country: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			country = input.nextLine();

			System.out.print("Gender(M/F): ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			genderC = input.next().charAt(0);
			do {
				switch (genderC) {
				case 'm':
				case 'M': {
					gender = "Male";
					break;
				}

				case 'f':
				case 'F': {
					gender = "Female";
					break;
				}

				default:
					System.out.println("Invalid input");
					System.out.print("Re-enter Gender(M/F): ");
					genderC = input.next().charAt(0);
				}
			} while (gender == null);

			input.nextLine();

			System.out.print("Address: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			address = input.nextLine();

			do {
				System.out.print("Phone Number: ");
				while (!input.hasNext()) {
					System.out.println("Wrong input");
					input.next();
				}
				phoneNo = input.next();
				input.nextLine();
				if (!phoneNo.matches(RegexValidation.phoneNumberRegex))
					System.out.println("Wrong phone number format");
			} while (!phoneNo.matches(RegexValidation.phoneNumberRegex));

			do {
				System.out.print("Credit Card Number: ");

				while (!input.hasNext()) {
					System.out.println("Wrong input");
					input.next();
				}
				creditCardNo = input.nextLine();
				creditCardNo = creditCardNo.replaceAll(RegexValidation.whiteSpaceRegex, "");
				if (!creditCardNo.matches(RegexValidation.creditCardRegex))
					System.out.println("Wrong credit card number format");
			} while (!creditCardNo.matches(RegexValidation.creditCardRegex));

			guest = new Guest(name, email, country, gender, passport, address, phoneNo, creditCardNo);
			System.out.println("<< Guest Details:\n" + guest.toString() + "\n");

			do {
				System.out.println("Choose option: ");
				System.out.println("1. Confirm");
				System.out.println("2. Cancel");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();

				switch (option) {
				case 1:
					result = GuestController.updateGuestList(guest);
					if (result == true) {
						System.out.println("<< Guest created successfully.");
					} else {
						System.out.println("Error...");
					}
					break;
				case 2:
					System.out.println("<< Cancelled");
					return;
				default:
					System.out.println("Please enter a number from 1 to 2");
				}

			} while (option < 1 || option > 2);

		}
	}

	/**
	 * 
	 * Prompts for passport of the guest. If guest does not exist, it will return
	 * back to calling function. Else it will prompt the user which attributes to
	 * update.
	 * 
	 */
	public static void updateGuest() {
		String name, email, country, gender = null, passport, address, phoneNo, creditCardNo;
		Guest guest;
		char genderC;
		int updateOption;

		if (GuestController.retrieveGuestList() == null) {
			System.out.println("No guests in database");
			System.out.println("<< Back");
		}

		else {
			System.out.println("<< Update Guest Details:");
			System.out.print("Enter the passport of the guest to update: ");
			passport = input.next();
			input.nextLine();
			guest = GuestController.getGuestByPassport(passport);

			if (guest != null) {

				do {

					do {
						System.out.println("<< Updated guest details:\n" + guest.toString());
						System.out.println("Enter the option that you would like to update");
						System.out.println("1. Name");
						System.out.println("2. Email");
						System.out.println("3. Country");
						System.out.println("4. Gender");
						System.out.println("5. Address");
						System.out.println("6. Phone Number");
						System.out.println("7. Credit Card Number");
						System.out.println("8. Update");
						System.out.println("9. Cancel");
						System.out.print("Option: ");
						while (!input.hasNextInt()) {
							System.out.println("Please enter a number!");
							System.out.print("Option: ");
							input.next();
						}
						updateOption = input.nextInt();
						input.nextLine();

						switch (updateOption) {
						case 0:
							break;
						case 1:
							System.out.println("Enter new name: ");
							while (!input.hasNext()) {
								System.out.println("Wrong input");
								input.next();
							}
							name = input.nextLine();
							guest.setName(name);
							break;
						case 2:

							do {

								System.out.print("Enter new email: ");
								while (!input.hasNext()) {
									System.out.println("Wrong input");
									input.next();
								}
								email = input.next();
								input.nextLine();
								if (!email.matches(RegexValidation.emailRegex))
									System.out.println("Wrong email format");
							} while (!email.matches(RegexValidation.emailRegex));
							guest.setEmail(email);
							break;
						case 3:
							System.out.print("Enter new country: ");
							while (!input.hasNext()) {
								System.out.println("Wrong input");
								input.next();
							}
							country = input.nextLine();
							guest.setCountry(country);
							break;
						case 4:
							System.out.println("Enter new gender: ");
							while (!input.hasNext()) {
								System.out.println("Wrong input");
								input.next();
							}
							genderC = input.next().charAt(0);
							do {
								switch (genderC) {
								case 'm':
								case 'M': {
									gender = "Male";
									break;
								}

								case 'f':
								case 'F': {
									gender = "Female";
									break;
								}

								default:
									System.out.println("Invalid input");
									System.out.print("Re-enter Gender(M/F): ");
									genderC = input.next().charAt(0);
								}
							} while (gender == null);
							input.nextLine();
							guest.setGender(gender);
							break;
						case 5:
							System.out.println("Enter new address: ");
							while (!input.hasNext()) {
								System.out.println("Wrong input");
								input.next();
							}
							address = input.nextLine();
							guest.setAddress(address);
							break;
						case 6:

							do {
								System.out.print("Enter new phone number: ");
								while (!input.hasNext()) {
									System.out.println("Wrong input");
									input.next();
								}
								phoneNo = input.next();
								input.nextLine();
								if (!phoneNo.matches(RegexValidation.phoneNumberRegex))
									System.out.println("Wrong phone number format");
							} while (!phoneNo.matches(RegexValidation.phoneNumberRegex));
							guest.setPhoneNo(phoneNo);
							break;
						case 7:

							do {
								System.out.print("Enter new credit card number: ");

								while (!input.hasNext()) {
									System.out.println("Wrong input");
									input.next();
								}
								creditCardNo = input.nextLine();
								creditCardNo = creditCardNo.replaceAll(RegexValidation.whiteSpaceRegex, "");
								if (!creditCardNo.matches(RegexValidation.creditCardRegex))
									System.out.println("Wrong credit card number format");
							} while (!creditCardNo.matches(RegexValidation.creditCardRegex));
							guest.setCreditCardNo(creditCardNo);
							break;
						case 8:
							result = GuestController.updateGuestList(guest);
							if (result == true) {
								System.out.println("<< Guest updated successfully.");
							} else {
								System.out.println("Error...");
							}
							break;
						case 9:
							System.out.println("<< Cancelled");
							break;
						default:
							System.out.println("Please enter a number from 1 to 9");
						}
					} while (updateOption != 8 && updateOption != 9);

				} while (updateOption >= 1 && updateOption <= 7);
			}

			else {
				System.out.println("Guest does not exist");
				System.out.println("<< Back");
			}
		}
	}

	/**
	 * 
	 * Search for a guest based on a keyword guest name. Shows a list of guests that
	 * have the same name that the user input.
	 * 
	 */
	public static void searchGuest() {
		String keyword;
		List<Guest> guestList = GuestController.retrieveGuestList();

		if (guestList.size() == 0) {
			System.out.println("No guests in database");
			System.out.println("<< Back");
		} else {
			System.out.println("<< Search Guest:");
			System.out.print("Enter the keyword: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			keyword = input.nextLine();
			guestList = GuestController.searchByKeyword(keyword);
			if (guestList.size() == 0) {
				System.out.println("No such guest with keyword " + keyword);
				System.out.println("<< Back");
			} else {
				System.out.println("<< Guest Details with keyword " + keyword);
				System.out.format(RegexValidation.tableGuest, "S/No.", "Name", "Email", "Country", "Gender", "Passport", "Address",
						"Phone Number", "Credit Card Number");
				for (i = 0; i < guestList.size(); i++) {
					Guest guest = guestList.get(i);
					System.out.format(RegexValidation.tableGuest, i + 1, guest.getName(), guest.getEmail(), guest.getCountry(),
							guest.getGender(), guest.getPassport(), guest.getAddress(), guest.getPhoneNo(),
							guest.getCreditCardNo());
				}
			}
		}
	}

	/**
	 * Prompts for passport of the guest. If the guest does not exists, it will
	 * prompt to create a guest. Else it will prompt for more user input for the
	 * necessary attributes to create Reservation object and update the reservation
	 * data file.
	 */
	public static void createReservation() {
		String guestPassport, roomNo = null, status = null, checkin = null, checkout = null;
		Date checkInDate = null, checkOutDate = null;
		int noAdults = 0, noChildren = 0;
		char choice;
		Reservation reservation;
		Guest guest = null;
		Room room;

		System.out.println("<< Reservation Creation:");
		System.out.println("Passport Number: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input");
			input.next();
		}
		guestPassport = input.next();
		input.nextLine();
		do {
			guest = GuestController.getGuestByPassport(guestPassport);
			if (guest == null) {
				System.out.println("Guest does not exist. Create guest? (Y/N)");
				choice = input.next().charAt(0);
				input.nextLine();
				do {
					switch (choice) {
					case 'Y':
					case 'y': {
						createGuest();
						break;
					}

					case 'N':
					case 'n': {
						System.out.println("<< Back");
						return;
					}

					default:
						System.out.println("Invalid input");
						System.out.print("Re-enter choice(Y/N): ");
						choice = input.next().charAt(0);
						input.nextLine();
					}
				} while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n');
			}

		} while (guest == null);
		
		reservation = ReservationController.getReservationByGuestPassport(guestPassport);

		if(reservation!=null) {
			System.out.println("Guest already has existing reservation");
			System.out.println("<< Back");
			return;
		}
		printReportByRoomType();

		System.out.print("Room Number: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input:");
			input.next();
		}
		roomNo = input.next();
		input.nextLine();

		if (RoomController.getRoom(roomNo) == null) {
			System.out.println("Room does not exist.");
			System.out.println("<< Back");
			return;
		}

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
		System.out.print("Number of Adults: ");
		while (!input.hasNextInt()) {
			System.out.println("Please enter a number");
			input.next();
		}
		noAdults = input.nextInt();
		input.nextLine();

		System.out.print("Number of Children: ");
		while (!input.hasNextInt()) {
			System.out.println("Please enter a number");
			input.next();
		}
		noChildren = input.nextInt();
		input.nextLine();

		if (checkInDate != null && checkOutDate != null) {
			reservation = new Reservation(guestPassport, roomNo, checkInDate, checkOutDate, status, noAdults,
					noChildren);

			room = RoomController.getRoom(roomNo);

			if (room.getStatus().equals(Constants.ROOM_STATUS_VACANT)) {
				reservation.setStatus(Constants.STATUS_CONFIRMED);
			}

			else
				reservation.setStatus(Constants.STATUS_WAITLIST);

			System.out.println("<< Reservation Details:\n" + reservation.toString());
			do {
				System.out.println("Choose option: ");
				System.out.println("1. Confirm");
				System.out.println("2. Cancel");
				System.out.print("Option:");
				option = input.nextInt();
				input.nextLine();

				switch (option) {
				case 1:
					result = ReservationController.updateReservationList(reservation);
					if (result == true) {
						System.out.println("<< Reservation created successfully.");
					} else {
						System.out.println("Error...");
					}
					break;
				case 2:
					System.out.println("<< Cancelled");
					break;
				default:
					System.out.println("Please enter a number from 1 to 2");
				}

			} while (option < 1 || option > 2);

		}

	}

	/**
	 * Prompts for reservation code. If reservation does not exist, it will return
	 * back to calling function. Else it will prompt the user which attributes to
	 * update.
	 */
	public static void updateReservation() {

		String reservationCode, roomNo, checkin;
		Reservation reservation;
		int number, status;
		Date checkInDate = null;
		int updateOption;
		List<Reservation> reservationList = ReservationController.retrieveReservationList();

		if (reservationList.size() == 0) {
			System.out.println("No reservation in database");
			System.out.println("<< Back");

		}

		else {
			System.out.println("<< Update Reservation Details:");
			System.out.println("Enter the reservation code: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			reservationCode = input.next();
			input.nextLine();

			reservation = ReservationController.getReservationByCode(reservationCode);

			if (reservation != null) {

				do {
					System.out.println("<< Updated reservation details:\n" + reservation.toString());
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
						System.out.print("Option: ");
						input.next();
					}
					updateOption = input.nextInt();
					input.nextLine();

					switch (updateOption) {
					case 1:
						System.out.print("Enter new room number: ");
						while (!input.hasNext()) {
							System.out.println("Wrong input");
							input.next();
						}
						roomNo = input.next();
						input.nextLine();
						reservation.setRoomNo(roomNo);
						break;
					case 2:
						do {
							System.out.print("Enter new check-in Date (dd/mm/yyyy) Time: 11 AM: ");
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
						checkin = checkin + " 11:00:00";
						if (null != checkin && checkin.trim().length() > 0) {
							SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
							try {
								checkInDate = myFormat.parse(checkin);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							reservation.setCheckInDate(checkInDate);
						}
						break;
					case 3:
						do {
							System.out.print("Enter new check-out Date (dd/mm/yyyy) Time: 9 AM: ");
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
						checkin = checkin + " 09:00:00";
						if (null != checkin && checkin.trim().length() > 0) {
							SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
							try {
								checkInDate = myFormat.parse(checkin);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							reservation.setCheckOutDate(checkInDate);
						}
						break;
					case 4:
						System.out.print("Enter new no. of adults: ");
						while (!input.hasNextInt()) {
							System.out.println("Please enter a number");
							input.next();
						}
						number = input.nextInt();
						input.nextLine();
						reservation.setNoAdults(number);
						break;
					case 5:
						System.out.println("Enter new no. of children: ");
						while (!input.hasNextInt()) {
							System.out.println("Please enter a number");
							input.next();
						}
						number = input.nextInt();
						input.nextLine();
						reservation.setNoChildren(number);
						break;
					case 6:
						System.out.println("Choose new status: ");
						System.out.println("1.	Confirmed");
						System.out.println("2.  In Waitlist");
						System.out.println("3.  Checked-in");
						System.out.println("4.  Checked-out");
						System.out.print("Option");
						while (!input.hasNextInt()) {
							System.out.println("Please enter a number");
							input.next();
						}
						status = input.nextInt();
						input.nextLine();

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
							System.out.println("Please enter a number from 1 to 4");

						}

						break;
					case 7:
						if (ReservationController.updateReservationList(reservation))
							System.out.println("<< Reservation updated");
						else
							System.out.println("Error");
						break;
					case 8:
						System.out.println("<< Cancelled");
						break;
					default:
						System.out.println("Please enter a number from 1 to 8");
					}
				} while (updateOption < 7 || updateOption > 8);
			}

			else {
				System.out.println("Reservation does not exist");
				System.out.println("<< Back");

			}
		}
	}

	/**
	 * 
	 * Prompts for reservation code, and removes reservation after confirmation from
	 * user if it exists.
	 * 
	 */
	public static void removeReservation() {
		String reservationCode;
		Reservation reservation;
		List<Reservation> reservationList = ReservationController.retrieveReservationList();

		if (reservationList.size() == 0) {
			System.out.println("No reservations in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Remove Reservation:");
			System.out.println("Enter the reservation code: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			reservationCode = input.next();
			input.nextLine();
			reservation = ReservationController.getReservationByCode(reservationCode);
			if (reservation == null) {
				System.out.println("Reservation " + reservationCode + " does not exist");
				System.out.println("<< Back");

			} else {
				System.out.println("<< Reservation Details\n" + reservation.toString());
				System.out.println("Choose option: ");
				System.out.println("1. Confirm");
				System.out.println("2. Cancel");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
				do {
					switch (option) {
					case 1:
						reservation.setStatus(Constants.STATUS_CHECKED_OUT);
						result = ReservationController.updateReservationList(reservation);
						if (result == true) {
							System.out.println("<< Reservation removed successfully.");
						} else {
							System.out.println("Error...");
						}
						break;
					case 2:
						System.out.println("<< Cancelled");
						break;
					default:
						System.out.println("Please enter a number from 1 to 2");
					}

				} while (option < 1 || option > 2);

			}
		}
	}

	/**
	 * 
	 * Prints all the reservations that has been made
	 * 
	 * 
	 */
	public static void printReservation() {
		List<Reservation> reservationList;
		reservationList = ReservationController.retrieveReservationList();
		int size = reservationList.size();
		if (size == 0) {
			System.out.println("No reservations in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Reservation Details:");
			System.out.format(RegexValidation.tableReservation, "Reservation Code", "Guest Passport", "Room Number", "Check In Date",
					"Check Out Date", "Status", "No. of Adults", "No. of Children");

			for (i = 0; i < size; i++) {
				Reservation reservation = reservationList.get(i);
				if (!reservation.getStatus().equals(Constants.STATUS_EXPIRED))
					System.out.format(RegexValidation.tableReservation, reservation.getReservationCode(), reservation.getGuestPassport(),
							reservation.getRoomNo(), reservation.getCheckInDate().toString(),
							reservation.getCheckOutDate().toString(), reservation.getStatus(),
							reservation.getNoAdults(), reservation.getNoChildren());

			}
		}
	}

	/**
	 * Prompts for room number. If the room does not exists, it will return back to
	 * calling function. Else it will prompt for more user input for the necessary
	 * attributes to create ROom object and update the Room data file.
	 */
	public static void createRoom() {

		String roomNo, roomType = null, bedType = null, wifi = null, view = null, smoking = null, status = null;
		Room room;

		System.out.println("<< Room Creation:");
		System.out.println("Enter the room number: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input");
			input.next();
		}
		roomNo = input.next();
		input.nextLine();
		room = RoomController.getRoom(roomNo);
		if (room != null) {
			System.out.println("Room " + roomNo + " already exists.");
			System.out.println("<< Back");

		} else {

			do {
				System.out.println("Choose room type: ");
				System.out.println("1. Single");
				System.out.println("2. Double");
				System.out.println("3. Deluxe");
				System.out.println("4. Vip Suite");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");

					input.next();
				}
				option = input.nextInt();
				input.nextLine();
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
					System.out.println("Please enter a number from 1 to 4");
				}

			} while (option < 1 || option > 4);

			do {
				System.out.println("Choose bed type: ");
				System.out.println("1. Twin");
				System.out.println("2. Double");
				System.out.println("3. Queen");
				System.out.println("4. King");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
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
					System.out.println("Please enter a number from 1 to 4");

				}

			} while (option < 1 || option > 4);

			do {
				System.out.println("WI-FI: ");
				System.out.println("1. Enabled");
				System.out.println("2. Disabled");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
				switch (option) {
				case 1:
					wifi = Constants.WIFI_ENABLED;
					break;
				case 2:
					wifi = Constants.WIFI_DISABLED;
					break;
				default:
					System.out.println("Please enter a number from 1 to 2");

				}

			} while (option < 1 || option > 2);

			do {
				System.out.println("Choose view: ");
				System.out.println("1. Ocean View");
				System.out.println("2. City View");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
				switch (option) {
				case 1:
					view = Constants.ROOM_VIEW_OCEAN;
					break;
				case 2:
					view = Constants.ROOM_VIEW_CITY;
					break;
				default:
					System.out.println("Please enter a number from 1 to 2");

				}

			} while (option < 1 || option > 2);

			do {
				System.out.println("Smoking: ");
				System.out.println("1. Allowed");
				System.out.println("2. Prohibited");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
				switch (option) {
				case 1:
					smoking = Constants.SMOKING_ALLOWED;
					break;
				case 2:
					smoking = Constants.SMOKING_PROHIBITED;
					break;
				default:
					System.out.println("Please enter a number from 1 to 2");

				}

			} while (option < 1 || option > 2);

			do {
				System.out.println("Choose status: ");
				System.out.println("1. Vacant");
				System.out.println("2. Occupied");
				System.out.println("3. Under Maintainence");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
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
					System.out.println("Please enter a number from 1 to 3");

				}

			} while (option < 1 || option > 3);

			do {
				room = new Room(roomNo, bedType, roomType, wifi, view, smoking, status);
				System.out.println("<< Room Details:\n" + room.toString());
				System.out.println("Choose option: ");
				System.out.println("1. Confirm");
				System.out.println("2. Cancel");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number");
					System.out.print("Option:");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
				switch (option) {
				case 1:

					result = RoomController.updateRoomList(room);
					if (result == true) {
						System.out.println("<< Room created successfully.");
					} else {
						System.out.println("Error...");
					}
					break;
				case 2:
					System.out.println("<< Cancelled");
					break;
				default:
					System.out.println("Please enter a number from 1 to 2");

				}

			} while (option < 1 || option > 2);

		}

	}

	/**
	 * Prompts for room number. If room does not exist, it will return back to
	 * calling function. Else it will prompt the user which attributes to update.
	 */
	public static void updateRoom() {
		String roomNo;
		Room room;
		int updateOption;

		if (RoomController.retrieveRoomList() == null) {
			System.out.println("No rooms in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Update Room Details:");
			System.out.println("Enter the room number: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			roomNo = input.next();
			input.nextLine();

			room = RoomController.getRoom(roomNo);

			if (room != null) {

				do {
					System.out.println("Updated room details:\n" + room.toString());
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
						System.out.print("Option:");
						input.next();
					}
					updateOption = input.nextInt();
					input.nextLine();
					switch (updateOption) {
					case 1:
						do {
							System.out.println("Choose room type: ");
							System.out.println("1. Single");
							System.out.println("2. Double");
							System.out.println("3. Deluxe");
							System.out.println("4. Vip Suite");
							System.out.print("Option:");
							while (!input.hasNextInt()) {
								System.out.println("Please enter a number");
								System.out.print("Option:");
								input.next();
							}
							option = input.nextInt();
							input.nextLine();
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
								System.out.println("Please enter a number from 1 to 4");

							}

						} while (option < 1 || option > 4);

						break;
					case 2:
						do {
							System.out.println("Choose bed type: ");
							System.out.println("1. Twin");
							System.out.println("2. Double");
							System.out.println("3. Queen");
							System.out.println("4. King");
							System.out.print("Option:");
							while (!input.hasNextInt()) {
								System.out.println("Please enter a number");
								System.out.print("Option:");
								input.next();
							}
							option = input.nextInt();
							input.nextLine();
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
								System.out.println("Please enter a number from 1 to 4");
							}

						} while (option < 1 || option > 4);
						break;
					case 3:
						do {
							System.out.println("WI-FI: ");
							System.out.println("1. Enabled");
							System.out.println("2. Disabled");
							System.out.print("Option:");
							while (!input.hasNextInt()) {
								System.out.println("Please enter a number");
								System.out.print("Option:");
								input.next();
							}
							option = input.nextInt();
							input.nextLine();
							switch (option) {
							case 1:
								room.setWifi(Constants.WIFI_ENABLED);
								break;
							case 2:
								room.setWifi(Constants.WIFI_DISABLED);
								break;
							default:
								System.out.println("Please enter a number from 1 to 2");
							}

						} while (option < 1 || option > 2);

						break;
					case 4:
						do {
							System.out.println("Choose view: ");
							System.out.println("1. Ocean View");
							System.out.println("2. City View");
							System.out.print("Option:");
							while (!input.hasNextInt()) {
								System.out.println("Please enter a number");
								System.out.print("Option:");
								input.next();
							}
							option = input.nextInt();
							input.nextLine();
							switch (option) {
							case 1:
								room.setView(Constants.ROOM_VIEW_OCEAN);
								break;
							case 2:
								room.setView(Constants.ROOM_VIEW_CITY);
								break;
							default:
								System.out.println("Please enter a number from 1 to 2");
							}

						} while (option < 1 || option > 2);
						break;
					case 5:
						do {
							System.out.println("Smoking");
							System.out.println("1. Allowed");
							System.out.println("2. Prohibited");
							System.out.print("Option:");
							while (!input.hasNextInt()) {
								System.out.println("Please enter a number");
								System.out.print("Option:");
								input.next();
							}
							option = input.nextInt();
							input.nextLine();
							switch (option) {
							case 1:
								room.setSmoking(Constants.SMOKING_ALLOWED);
								break;
							case 2:
								room.setSmoking(Constants.SMOKING_PROHIBITED);
								break;
							default:
								System.out.println("Please enter a number from 1 to 2");

							}

						} while (option < 1 || option > 2);
						break;
					case 6:
						do {
							System.out.println("Choose status: ");
							System.out.println("1. Vacant");
							System.out.println("2. Occupied");
							System.out.println("3. Under Maintainence");
							System.out.print("Option:");
							while (!input.hasNextInt()) {
								System.out.println("Please enter a number");
								System.out.print("Option:");
								input.next();
							}
							option = input.nextInt();
							input.nextLine();
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
								System.out.println("Please enter a number from 1 to 3");
							}

						} while (option < 1 || option > 3);
						break;
					case 7:
						if (RoomController.updateRoomList(room))
							System.out.println("<< Room updated");
						else
							System.out.println("Error");
						break;
					case 8:
						System.out.println("<< Cancelled");
						break;
					default:
						System.out.println("Please enter a number from 1 to 8");
					}
				} while (updateOption < 7 || updateOption > 8);
			}

			else {
				System.out.println("Room does not exist");
				System.out.println("<< Back");

			}
		}
	}

	/**
	 * 
	 * Prompts for room number and shows the status of the room if it exists.
	 * 
	 */
	public static void checkRoomAvailability() {
		String roomNo;
		Room room;

		if (RoomController.retrieveRoomList() == null) {
			System.out.println("No room in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Check Room Availability:");
			System.out.println("Enter room number: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			roomNo = input.next();
			input.nextLine();
			room = RoomController.getRoom(roomNo);

			if (room != null) {
				System.out.println("<< Room " + room.getRoomNo() + " is " + room.getStatus());
			}

			else {
				System.out.println("Room not found");
				System.out.println("<< Back");
			}

		}

	}

	/**
	 * 
	 * Shows the rooms that are vacant for that room type
	 * 
	 * 
	 */
	public static void printReportByRoomType() {

		System.out.println("<< Report by room type:");
		RoomController.printReport(Constants.PRINT_REPORT_BY_ROOM_TYPE);

	}

	/**
	 * 
	 * Shows the rooms availability based on their status
	 * 
	 */
	public static void printReportByAvailability() {

		System.out.println("<< Report by availability");
		RoomController.printReport(Constants.PRINT_REPORT_BY_STATUS);
	}

	/**
	 * 
	 * Prompts for item name. If the item already exists, it will return back to
	 * calling function. Else it will prompt for more user input for the necessary
	 * attributes to create Item object and update the item data file.
	 * 
	 */
	public static void createMenuItem() {
		String itemName, description;
		double price = 0;
		Item item;

		System.out.println("<< Menu Item Creation:");
		System.out.println("Enter the item name: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input");
			input.next();
		}
		itemName = input.nextLine();
		item = ItemController.getItemFromName(itemName);
		if (item != null) {
			System.out.println(itemName + " already exists.");
			System.out.println("<< Back");

		} else {
			System.out.println("Enter description: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			description = input.nextLine();

			System.out.println("Enter price: S$");
			while (!input.hasNextDouble()) {
				System.out.println("Please enter a number!");
				input.next();
			}
			price = input.nextDouble();
			input.nextLine();

			item = new Item(itemName, description, price);

			System.out.println("<< Menu Item Details:\n" + item.toString());
			System.out.println("Choose option: ");
			System.out.println("1. Confirm");
			System.out.println("2. Cancel");
			System.out.print("Option:");
			while (!input.hasNextInt()) {
				System.out.println("Please enter a number!");
				System.out.print("Option: ");
				input.next();
			}
			option = input.nextInt();
			input.nextLine();
			do {
				switch (option) {
				case 1:
					result = ItemController.updateItemList(item);
					if (result == true) {
						System.out.println("<< Item created successfully.");
					} else {
						System.out.println("Error...");
					}
					break;
				case 2:
					System.out.println("<< Cancelled");
					break;
				default:
					System.out.println("Please enter a number from 1 to 2");
				}

			} while (option < 1 || option > 2);

		}
	}

	/**
	 * 
	 * Prompts for item name. If menu item does not exist, it will return back to
	 * calling function. Else it will prompt the user which attributes to update.
	 */
	public static void updateMenuItem() {
		String itemName, description;
		double price;
		Item item;

		int updateOption;

		if (ItemController.retrieveItemList() == null) {
			System.out.println("No menu item in database");
			System.out.println("<< Back");

		} else {
			System.out.println("Update Menu Item:");
			System.out.println("Enter the item name: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			itemName = input.nextLine();
			item = ItemController.getItemFromName(itemName);

			if (item != null) {

				System.out.println("<< Update Menu Item");
				do {
					System.out.println("Updated menu item details:\n" + item.toString());
					System.out.println("Enter the option that you would like to update");
					System.out.println("1. Description");
					System.out.println("2. Price");
					System.out.println("3. Update");
					System.out.println("4. Cancel");
					System.out.print("Option: ");
					while (!input.hasNextInt()) {
						System.out.println("Please enter a number!");
						input.next();
					}
					updateOption = input.nextInt();
					input.nextLine();
					switch (updateOption) {
					case 1:
						System.out.print("Enter new description: ");
						while (!input.hasNext()) {
							System.out.println("Wrong input");
							input.next();
						}
						description = input.nextLine();
						item.setItemDescription(description);
						break;
					case 2:
						System.out.print("Enter new price: ");
						while (!input.hasNextDouble()) {
							System.out.println("Please enter a number!");
							input.next();
						}
						price = input.nextDouble();
						input.nextLine();
						item.setItemPrice(price);
						break;
					case 3:
						result = ItemController.updateItemList(item);
						if (result == true) {
							System.out.println("<< Menu item updated successfully.");
						} else {
							System.out.println("Error...");
						}
						break;
					case 4:
						System.out.println("<< Cancelled");
						break;
					default:
						System.out.println("Please enter a number from 1 to 4");
					}
				} while (updateOption < 3 || updateOption > 4);
			}

			else {
				System.out.println("Menu item does not exist");
				System.out.println("<< Back");

			}
		}

	}

	/**
	 * 
	 * Prompts for item name, and removes menu item after confirmation from user if
	 * it exists.
	 * 
	 */
	public static void removeMenuItem() {
		String itemName;
		Item item;

		if (ItemController.retrieveItemList() == null) {
			System.out.println("No menu item in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Remove Menu Item:");
			System.out.print("Enter item name: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			itemName = input.nextLine();
			item = ItemController.getItemFromName(itemName);
			if (item == null) {
				System.out.println(itemName + " does not exists.");
				System.out.println("<< Back");

			} else {
				System.out.println(item.toString());
				System.out.println("Choose option: ");
				System.out.println("1. Confirm");
				System.out.println("2. Cancel");
				System.out.print("Option:");
				while (!input.hasNextInt()) {
					System.out.println("Please enter a number!");
					System.out.print("Option: ");
					input.next();
				}
				option = input.nextInt();
				input.nextLine();
				do {
					switch (option) {
					case 1:
						result = ItemController.removeItem(item);
						if (result == true) {
							System.out.println("<< Item removed successfully.");
						} else {
							System.out.println("Error...");
						}
						break;
					case 2:
						System.out.println("<< Cancelled");
						break;
					default:
						System.out.println("Please enter a number from 1 to 2");
					}

				} while (option < 1 || option > 2);

			}
		}
	}

	/**
	 * 
	 * Prompts user for passport details to check in. Will update room status and
	 * reservation status accordingly.
	 * 
	 */
	public static void checkIn() {

		String guestPassport, roomNo;
		char choice;
		System.out.println("<< Check-In:");
		System.out.println("Enter passport details: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input");
			input.next();
		}
		guestPassport = input.next();
		input.nextLine();

		Reservation reservation = ReservationController.getReservationByGuestPassport(guestPassport);

		if (reservation != null) {

			if (reservation.getStatus().equals(Constants.STATUS_CONFIRMED)) {
				System.out.println("Proceed to check in!");
				reservation.setStatus(Constants.STATUS_CHECKED_IN);
				if (ReservationController.updateReservationList(reservation))
					System.out.println("<< Reservation Details:\n" + reservation.toString());
			}

			else if (reservation.getStatus().equals(Constants.STATUS_EXPIRED)) {
				System.out.println("Sorry, your reservation has expired because you were late for more than 1 hour!");
				if (ReservationController.removeReservation(reservation))
					System.out.println("Reservation removed");
				System.out.println("<< Back");
				;
			}

			else if (reservation.getStatus().equals(Constants.STATUS_WAITLIST)) {
				Room room = RoomController.getRoom(reservation.getRoomNo());
				if (room.getStatus().equals(Constants.ROOM_STATUS_VACANT)) {
					System.out.println("Your room is vacant, proceed to check in!");
					reservation.setStatus(Constants.STATUS_CHECKED_IN);
					if (ReservationController.updateReservationList(reservation))
						System.out.println("<< Reservation Details:\n" + reservation.toString());
				}

				else if (room.getStatus().equals(Constants.ROOM_STATUS_OCCUPIED)
						|| room.getStatus().equals(Constants.ROOM_STATUS_UNDER_MAINTAINENCE)) {
					System.out.println("Your room is currently occupied, would you like to change your room(Y/N)");
					while (!input.hasNext()) {
						System.out.println("Wrong input");
						input.next();
					}
					choice = input.next().charAt(0);
					input.nextLine();
					do {
						switch (choice) {
						case 'Y':
						case 'y': {

							printReportByAvailability();
							System.out.println("Enter room number: ");
							while (!input.hasNext()) {
								System.out.println("Wrong input");
								input.next();
							}
							roomNo = input.next();
							input.nextLine();
							if(RoomController.getRoom(roomNo)!=null) {
								if(!RoomController.getRoom(roomNo).getStatus().equals(Constants.ROOM_STATUS_VACANT)) {
									System.out.println("Room is not vacant");
									System.out.println("<< Back");
									return;
								}
							}
							else
							{
								System.out.println("Room does not exist");;
								System.out.println("<< Back");
								return;
							}
							
							reservation.setRoomNo(roomNo);
							reservation.setStatus(Constants.STATUS_CHECKED_IN);
							if (ReservationController.updateReservationList(reservation))
								System.out.println("<< Reservation Details:\n" + reservation.toString());
							break;
						}

						case 'N':
						case 'n': {
							System.out.println("<< Back");
							break;
						}

						default:
							System.out.println("Invalid input");
							System.out.print("Re-enter input(Y/N): ");
							choice = input.next().charAt(0);
							input.nextLine();
						}
					} while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n');

				}

			}

		} else {
			createReservation();
			reservation = ReservationController.getReservationByGuestPassport(guestPassport);
			if (reservation.getStatus().equals(Constants.STATUS_CONFIRMED)) {
				System.out.println("Proceed to check in!");
				reservation.setStatus(Constants.STATUS_CHECKED_IN);

				if (ReservationController.updateReservationList(reservation))
					System.out.println("<< Reservation Details:\n" + reservation.toString());
			}
		}

	}

	/**
	 * Prompts user for room number to check out. Print bill invoice and ask for
	 * payment via cash or credit card For credit card will prompt user on whether
	 * to use existing credit card number or a new credit card number Update
	 * reservation and room status accordingly
	 */
	public static void checkOut() {
		String rmNo;
		Reservation reservation = null;
		Payment payment;
		String creditCardNo;
		char choice;

		System.out.println("<< Check-Out:");
		System.out.println("Enter room number: ");
		while (!input.hasNext()) {
			System.out.println("Please enter a room number");
			input.next();
		}
		rmNo = input.next();
		input.nextLine();

		if (RoomController.getRoom(rmNo) == null) {
			System.out.println("<< Room " + rmNo + " does not exist");
			return;
		}
		reservation = ReservationController.getReservationByRoomNo(rmNo, Constants.STATUS_CHECKED_IN);
		if (reservation == null) {
			System.out.println("<< Reservation for room " + rmNo + " does not exist");
			return;
		}

		payment = new Payment(reservation.getReservationCode(), rmNo, reservation.getGuestPassport());
		result = PaymentController.updatePaymentList(payment);
		if (result == false) {
			System.out.println("Error...");
			return;
		}
		System.out.println("\n");
		PaymentController.printBillInvoice(payment);
		System.out.println("\n");
		do {
			System.out.println("Pay via: ");
			System.out.println("1. Cash");
			System.out.println("2. Credit card");
			System.out.print("Option: ");
			while (!input.hasNextInt()) {
				System.out.println("Please enter a number!");
				input.next();
			}
			option = input.nextInt();
			input.nextLine();

			switch (option) {
			case 1:
				payment.setPaymentType(Constants.PAYMENT_TYPE_CASH);
				PaymentController.updatePaymentList(payment); // validate
				System.out.println("Payment completed");

				break;
			case 2:
				payment.setPaymentType(Constants.PAYMENT_TYPE_CREDITCARD);
				do {
					System.out.println("Pay via existing credit card number? (Y/N)");
					while(!input.hasNext())
					{
						System.out.println("Wrong input");
						input.next();
					}
					choice = input.next().charAt(0);
					input.nextLine();
					if (choice == 'y' || choice == 'Y') {
						creditCardNo = GuestController.getGuestByPassport(reservation.getGuestPassport())
								.getCreditCardNo();
						payment.setCreditCard(creditCardNo);
						result = PaymentController.updatePaymentList(payment);
						if (result == true) {
							System.out.println("<< Payment completed");
						} else {
							System.out.println("Error...");
						}
					} else if (choice == 'n' || choice == 'N') {
						do {
							System.out.print("Credit Card Number: ");

							while (!input.hasNext()) {
								System.out.println("Wrong input");
								input.next();
							}
							creditCardNo = input.nextLine();
							creditCardNo = creditCardNo.replaceAll(RegexValidation.whiteSpaceRegex, "");
							if (!creditCardNo.matches(RegexValidation.creditCardRegex))
								System.out.println("Wrong credit card number format");
						} while (!creditCardNo.matches(RegexValidation.creditCardRegex));
						payment.setCreditCard(creditCardNo);
						result = PaymentController.updatePaymentList(payment);
						if (result == true) {
							System.out.println("<< Payment completed");
						} else {
							System.out.println("Error...");
						}
					} else {
						System.out.println("Please enter Y/N");
					}
				} while (choice != 'n' && choice != 'N' && choice != 'y' && choice != 'Y');
				break;

			default:
				System.out.println("Please enter a number from 1 to 2");
			}
		} while (option != 1 && option != 2);

		reservation.setStatus(Constants.STATUS_CHECKED_OUT);
		result = ReservationController.updateReservationList(reservation);
		if (result == false) {
			System.out.println("Error...");
		} else {
			System.out.println("<< Room updated successfully");
		}
	}

	/**
	 * Prompts for room number of the guest. If the reservation object does not
	 * exist, it will return back to calling function. Else it will prompt for more
	 * user input for the necessary attributes to create Service object and update
	 * the service data file.
	 */
	public static void createRoomServiceOrder() {
		String roomNo, remarks;
		Reservation reservation;
		Room room;
		List<Item> itemList = ItemController.retrieveItemList();
		List<Service> serviceList = new ArrayList<Service>();
		System.out.println("<< Room Service Creation:");
		System.out.println("Enter room number: ");
		while (!input.hasNext()) {
			System.out.println("Wrong input");
			input.next();
		}
		roomNo = input.next();
		input.nextLine();

		room = RoomController.getRoom(roomNo);

		if (room != null) {

			reservation = (Reservation) ReservationController.getReservationByRoomNo(roomNo,
					Constants.STATUS_CHECKED_IN);

			if (reservation != null) {
				displayMenu();
				char choice;
				int itemNumber = 0;
				do {
					System.out.println("Choose item number from menu(Y to confirm/N to cancel)");
					System.out.print("Option: ");
					if (input.hasNextInt()) {
						while (!input.hasNextInt()) {
							System.out.println("Please enter a number!");
							System.out.print("Option: ");
							input.next();
						}
						itemNumber = input.nextInt();
						input.nextLine();

						System.out.println("Input any Remarks: ");
						while (!input.hasNext()) {
							System.out.println("Wrong input");
							input.next();
						}
						remarks = input.nextLine();

						Service service = new Service(roomNo, reservation.getReservationCode(),
								itemList.get(itemNumber - 1).getItemName(), new Date(), remarks,
								Constants.STATUS_CONFIRMED);
						serviceList.add(service);

					}

					else if (input.hasNext()) {
						while (!input.hasNext()) {
							System.out.println("Wrong input");
							input.next();
						}
						choice = input.next().charAt(0);
						input.nextLine();
						do {
							switch (choice) {
							case 'Y':
							case 'y': {
								if (serviceList.size() != 0) {
									System.out.println("<< Room Service Order:");
									System.out.format(RegexValidation.tableService, "Service ID", "Room No.", "Reservation Code", "Item Menu",
											"Status", "Remarks");
									for (int i = 0; i < serviceList.size(); i++) {
										System.out.format(RegexValidation.tableService, serviceList.get(i).getServiceID(),
												serviceList.get(i).getRoomNo(), serviceList.get(i).getReservationCode(),
												serviceList.get(i).getItemID(), serviceList.get(i).getStatus(),serviceList.get(i).getRemarks());
									}
									do {
										System.out.println("Choose option: ");
										System.out.println("1. Confirm");
										System.out.println("2. Cancel");
										System.out.print("Option: ");
										while (!input.hasNextInt()) {
											System.out.println("Please enter a number!");
											System.out.print("Option: ");
											input.next();
										}
										option = input.nextInt();
										input.nextLine();

										switch (option) {
										case 1:
											for (int i = 0; i < serviceList.size(); i++) {
												result = ServiceController.updateServiceList(serviceList.get(i));
												if (result == true) {
													System.out.println("<< Room service created successfully.");
												} else {
													System.out.println("Error...");
												}
											}
											return;

										case 2:
											System.out.println("<< Cancelled");
											break;
										default:
											System.out.println("Please enter a number from 1 to 2");
										}

									} while (option < 1 || option > 2);
								}

								else {
									System.out.println("You have not chosen a menu item");
									System.out.println("<< Back");
								}

								return;
							}

							case 'N':
							case 'n': {
								System.out.println("<< Back");
								return;
							}

							default:
								System.out.println("Invalid input");
								choice = 'n';

							}
						} while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n');

					}

				} while (itemNumber > 0 || itemNumber < itemList.size());
			}

			else {
				System.out.println("No reservation has been checked in for that room");
				System.out.println("<< Back");

			}

		} else {
			System.out.println("Room does not exist");
			System.out.println("<< Back");
		}

	}

	/**
	 * Prompts for room number. If room does not exist, it will return back to
	 * calling function. Else it will display a list of room service orders that the
	 * room has made. Next it will prompt the user to input a serviceID to update.
	 * 
	 */
	public static void updateRoomServiceOrder() {

		int serviceID, status;
		String roomNo, itemName, remarks;
		Room room;
		Reservation reservation;
		Service service;
		int updateOption;

		if (ServiceController.retrieveServiceList() == null) {
			System.out.println("No service orders in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Update Room Service:");
			System.out.println("Enter room number: ");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			roomNo = input.next();
			input.nextLine();

			room = RoomController.getRoom(roomNo);

			if (room != null) {

				reservation = ReservationController.getReservationByRoomNo(roomNo, Constants.STATUS_CHECKED_IN);

				if (reservation != null) {

					List<Service> serviceList = ServiceController
							.getServicesFromReservationCode(reservation.getReservationCode());

					if (serviceList != null) {
						System.out.println("<< Room Service Order:");

						System.out.format(RegexValidation.tableService, "Service ID", "Room No.", "Reservation Code", "Item Menu",
								"Status", "Remarks");
						for (i = 0; i < serviceList.size(); i++) {
							System.out.format(RegexValidation.tableService, serviceList.get(i).getServiceID(),
									serviceList.get(i).getRoomNo(), serviceList.get(i).getReservationCode(),
									serviceList.get(i).getItemID(), serviceList.get(i).getStatus(),
									serviceList.get(i).getRemarks());
						}

						System.out.println("Enter serviceID to update");
						while (!input.hasNextInt()) {
							System.out.println("Please enter a number!");
							input.next();
						}
						serviceID = input.nextInt();
						input.nextLine();
						service = ServiceController.getServiceFromServiceID(serviceID);
						if (service != null) {

							do {
								System.out.println("Updated room service order details:\n");
								System.out.format(RegexValidation.tableService, "Service ID", "Room No.", "Reservation Code",
										"Item Menu", "Status", "Remarks");
								System.out.format(RegexValidation.tableService, service.getServiceID(), service.getRoomNo(),
										service.getReservationCode(), service.getItemID(), service.getStatus(),
										service.getRemarks());

								System.out.println("Enter the option that you would like to update");
								System.out.println("1. Item Name");
								System.out.println("2. Remarks");
								System.out.println("3. Status");
								System.out.println("4. Update");
								System.out.println("5. Cancel");
								System.out.print("Option: ");
								while (!input.hasNextInt()) {
									System.out.println("Please enter a number!");
									System.out.print("Option: ");
									input.next();
								}
								updateOption = input.nextInt();
								input.nextLine();

								switch (updateOption) {
								case 1:
									System.out.print("Enter new item name: ");
									while (!input.hasNext()) {
										System.out.println("Wrong input");
										input.next();
									}
									itemName = input.nextLine();
									service.setItemID(itemName);
									break;
								case 2:
									System.out.print("Enter new remarks: ");
									while (!input.hasNext()) {
										System.out.println("Wrong input");
										input.next();
									}
									remarks = input.nextLine();
									service.setRemarks(remarks);
									break;
								case 3:
									do {
										System.out.println("Choose new status:");
										System.out.println("1.  Confirmed");
										System.out.println("2.  Preparing");
										System.out.println("3.  Delivered");
										System.out.print("Option: ");
										while (!input.hasNextInt()) {
											System.out.println("Please enter a number!");
											System.out.print("Option: ");
											input.next();
										}
										status = input.nextInt();
										input.nextLine();

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
											System.out.println("Please enter a number from 1 to 3");
										}
									} while (status < 1 || status > 3);

									break;
								case 4:
									if (ServiceController.updateServiceList(service))
										System.out.println("<< Room Service updated");
									else
										System.out.println("Error");
									break;
								case 5:
									System.out.println("<< Cancelled");
									break;
								default:
									System.out.println("Please enter a number from 1 to 5");
								}
							} while (updateOption < 4 || updateOption > 5);
						}

					}

					else {
						System.out.println("No room service orders has been made for that room");
						System.out.println("<< Back");
					}

				}

				else {
					System.out.println("No reservation has been checked in for that room");
					System.out.println("<< Back");

				}
			}

			else {
				System.out.println("Room does not exist");
				System.out.println("<< Back");
			}
		}
	}

	/**
	 * 
	 * Prompts for room number and shows the list of service orders that has been
	 * ordered. Prompts user for serviceID to remove one service order and removes
	 * service order after confirmation from user
	 * 
	 */
	public static void removeRoomServiceOrder() {
		String roomNo;
		int serviceID;
		Service service;

		if (ServiceController.retrieveServiceList() == null) {
			System.out.println("No service order in database");
			System.out.println("<< Back");

		} else {
			System.out.println("<< Remove Room Service:");
			System.out.println("Enter room number:");
			while (!input.hasNext()) {
				System.out.println("Wrong input");
				input.next();
			}
			roomNo = input.next();
			input.nextLine();

			Reservation r = ReservationController.getReservationByRoomNo(roomNo, Constants.STATUS_CHECKED_IN);

			if (r != null) {
				List<Service> serviceList = ServiceController.getServicesFromReservationCode(r.getReservationCode());

				if (serviceList != null) {
					System.out.println("<< Room Service Order:");
					System.out.format(RegexValidation.tableService, "Service ID", "Room No.", "Reservation Code", "Item Menu",
							"Status", "Remarks");
					for (i = 0; i < serviceList.size(); i++) {
						System.out.format(RegexValidation.tableService, serviceList.get(i).getServiceID(), serviceList.get(i).getRoomNo(),
								serviceList.get(i).getReservationCode(), serviceList.get(i).getItemID(), serviceList.get(i).getStatus(),
								serviceList.get(i).getRemarks());
					}

					System.out.println("Enter serviceID to remove: ");
					while (!input.hasNextInt()) {
						System.out.println("Please enter a number!");
						input.next();
					}
					serviceID = input.nextInt();
					input.nextLine();
					service = ServiceController.getServiceFromServiceID(serviceID);
					if(service!=null) {
						result = ServiceController.removeService(service);
						if (result)
							System.out.println("<< Room service removed successfully");
						else
							System.out.println("Error..");
					}
					else
					{
						System.out.println("Room service with serviceID "+serviceID+" does not exist");
					}
					
				}

				else
					System.out.println("No room service orders has been made for that room");
			} else
				System.out.println("No reservation has been checked in for that room");
		}
	}

	/**
	 * 
	 * Shows the menu items in order, with item name, description and price.
	 * 
	 */
	public static void displayMenu() {

		if (ItemController.retrieveItemList() == null) {
			System.out.println("No item in menu");
			System.out.println("<< Back");
		}

		else {
			System.out.println("<< Menu");
			ItemController.getMenu();
		}
	}
}
