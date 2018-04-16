package main;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

import reservation.ReservationTimer;

/**
 * <h1>Main Application</h1>
 * 
 * <p>
 * Boundary class of the application
 * </p>
 * <p>
 * Mainly input and output, with little control
 * </p>
 *
 */
public class Main {

	/**
	 * 
	 * Boundary layer of the application
	 * 
	 * @param args
	 *            None
	 */
	public static void main(String args[]) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);

		Timer timer = new Timer();

		timer.schedule(new ReservationTimer(), new Date(), 10000);
		System.out.println("<< Main Menu");
		do {

			System.out.println("Choose option:");
			System.out.println("1. Guest");
			System.out.println("2. Reservation");
			System.out.println("3. Room");
			System.out.println("4. Room Service");
			System.out.println("5. Check-in");
			System.out.println("6. Check-out");
			System.out.println("7. Exit application");
			System.out.print("Option:");
			while (!sc.hasNextInt()) {
				System.out.println("Please enter a number!");
				System.out.print("Option:");
				sc.next();
			}
			choice = sc.nextInt();
			sc.nextLine();

			int choice2;
			int newChoice = 1;

			do {
				switch (choice) {

				case 1:
					do {
						System.out.println("<< Guest");
						System.out.println("Choose option:");
						System.out.println("1. Create Guest Detail");
						System.out.println("2. Update Guest Detail");
						System.out.println("3. Search Guest Detail");
						System.out.println("4. Main Menu");
						System.out.print("Option:");
						while (!sc.hasNextInt()) {
							System.out.println("Please enter a number!");
							System.out.print("Option:");
							sc.next();
						}
						choice2 = sc.nextInt();
						sc.nextLine();
						switch (choice2) {

						case 1:
							MainController.createGuest();
							break;
						case 2:
							MainController.updateGuest();
							break;
						case 3:
							MainController.searchGuest();
							break;
						case 4:
							System.out.println("<< Main Menu");
							break;
						default:
							System.out.println("Please enter a number from 1 to 4");
						}
					} while (choice2 < 1 || choice2 > 4);
					newChoice = 0;
					break;

				case 2:
					do {
						System.out.println("<< Reservation");
						System.out.println("Choose option:");
						System.out.println("1. Create Reservation");
						System.out.println("2. Update Reservation");
						System.out.println("3. Remove Reservation");
						System.out.println("4. Print Reservation");
						System.out.println("5. Main Menu");
						System.out.print("Option:");
						while (!sc.hasNextInt()) {
							System.out.println("Please enter a number!");
							System.out.print("Option:");
							sc.nextLine();
						}
						choice2 = sc.nextInt();
						sc.nextLine();
						switch (choice2) {

						case 1:
							MainController.createReservation();
							break;
						case 2:
							MainController.updateReservation();
							break;
						case 3:
							MainController.removeReservation();
							break;
						case 4:
							MainController.printReservation();
							break;
						case 5:
							System.out.println("<< Main Menu");
							break;
						default:
							System.out.println("Please enter a number from 1 to 5");
						}
					} while (choice2 < 1 || choice2 > 5);
					newChoice = 0;
					break;
				case 3:
					do {
						System.out.println("<< Room");
						System.out.println("Choose option:");
						System.out.println("1. Create Room");
						System.out.println("2. Update Room");
						System.out.println("3. Check Room Availability");
						System.out.println("4. Print Room Status Statistic Report by Room Type");
						System.out.println("5. Print Room Status Statistic Report by Availability");
						System.out.println("6. Main Menu");
						System.out.print("Option:");
						while (!sc.hasNextInt()) {
							System.out.println("Please enter a number!");
							System.out.print("Option:");
							sc.next();
						}
						choice2 = sc.nextInt();
						sc.nextLine();
						switch (choice2) {

						case 1:
							MainController.createRoom();
							break;
						case 2:
							MainController.updateRoom();
							break;
						case 3:
							MainController.checkRoomAvailability();
							break;
						case 4:
							MainController.printReportByRoomType();
							break;
						case 5:
							MainController.printReportByAvailability();
							break;
						case 6:
							System.out.println("<< Main Menu");
							break;
						default:
							System.out.println("Please enter a number from 1 to 6");
						}
					} while (choice2 < 1 || choice2 > 6);
					newChoice = 0;
					break;
				case 4:
					do {
						System.out.println("<< Room Service & Menu Item");
						System.out.println("Choose option:");
						System.out.println("1. Create Room Order Service");
						System.out.println("2. Update Room Order Service");
						System.out.println("3. Remove Room Order Service");
						System.out.println("4. Create Menu Item");
						System.out.println("5. Update Menu Item");
						System.out.println("6. Remove Menu Item");
						System.out.println("7. Display Menu");
						System.out.println("8. Main Menu");
						System.out.print("Option:");
						while (!sc.hasNextInt()) {
							System.out.println("Please enter a number!");
							System.out.print("Option:");
							sc.next();
						}
						choice2 = sc.nextInt();
						sc.nextLine();
						switch (choice2) {

						case 1:
							MainController.createRoomServiceOrder();
							break;
						case 2:
							MainController.updateRoomServiceOrder();
							break;
						case 3:
							MainController.removeRoomServiceOrder();
							break;
						case 4:
							MainController.createMenuItem();
							break;
						case 5:
							MainController.updateMenuItem();
							break;
						case 6:
							MainController.removeMenuItem();
							break;
						case 7:
							MainController.displayMenu();
						case 8:
							System.out.println("<< Main Menu");
							break;
						default:
							System.out.println("Please enter a number from 1 to 8");
						}
					} while (choice2 < 1 || choice2 > 8);
					newChoice = 0;
					break;
				case 5:
					MainController.checkIn();
					newChoice = 0;
					break;
				case 6:
					MainController.checkOut();
					newChoice = 0;
					break;
				case 7:
					System.out.println("<< Exiting application..");
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please enter a number from 1 to 7");
					System.out.print("Option: ");
					while (!sc.hasNextInt()) {
						System.out.println("Please enter a number");
						System.out.print("Option:");
						sc.next();
					}
					newChoice = sc.nextInt();
					sc.nextLine();
					choice = newChoice;
				}
			} while (newChoice >= 1 && newChoice <= 7);

		} while (choice != 7);

	}

}
