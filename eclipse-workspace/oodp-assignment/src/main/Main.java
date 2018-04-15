package main;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

import reservation.ReservationTimer;

/**
 * <h1>Main Application</h1>
 * 
 * <p>Boundary class of the application</p>
 * <p>Mainly input and output, with little control</p> 
 *
 */
public class Main {

	/**
	 * 
	 * Boundary layer of the application
	 * 
	 * @param args None
	 */
	public static void main(String args[]) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);

		Timer timer = new Timer();
		
		timer.schedule(new ReservationTimer(), new Date(),10000);
		
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
				sc.next();
			}
			choice = sc.nextInt();

			int choice2;

			switch (choice) {

			case 1:
				System.out.println("Choose option:");
				System.out.println("1.Create Guest Detail");
				System.out.println("2.Update Guest Detail");
				System.out.println("3.Search Guest Detail");
				System.out.println("4. Main Menu");
				System.out.print("Option:");
				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
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

					}

				}

				else
					sc.next();

				break;
			case 2:
				System.out.println("Choose option");
				System.out.println("1.Create Reservation");
				System.out.println("2.Update Reservation");
				System.out.println("3.Remove Reservation");
				System.out.println("4.Print Reservation");
				System.out.println("5. Main Menu");
				System.out.print("Option:");
				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
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
					}

				}

				else
					sc.next();

				break;
			case 3:

				System.out.println("Choose option");
				System.out.println("1.Create Room");
				System.out.println("2.Update Room");
				System.out.println("3.Check Room Availability");
				System.out.println("4.Print Room Status Statistic Report by Room Type");
				System.out.println("5.Print Room Status Statistic Report by Availability");
				System.out.println("6. Main Menu");
				System.out.print("Option:");

				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
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

					}

				}

				else
					sc.next();

				break;
			case 4:

				System.out.println("Choose option");
				System.out.println("1.Create Room Order Service");
				System.out.println("2.Update Room Order Service");
				System.out.println("3.Remove Room Order Service");
				System.out.println("4.Create Menu Item");
				System.out.println("5.Update Menu Item");
				System.out.println("6.Remove Menu Item");
				System.out.println("7. Display Menu");
				System.out.println("8. Main Menu");
				System.out.print("Option:");
				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
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
					}

				}

				else
					sc.next();

				break;
			case 5:
				MainController.checkIn();
				break;
			case 6:
				MainController.checkOut();
				break;
			case 7:
				System.out.println("Exiting application..");
				break;
			default:
				System.out.println("Please enter a number from 1 to 7");
				sc.next();
				if (sc.hasNext())
					choice = sc.nextInt();
				else
					choice = 0;

			}

			System.out.println("\n\n");

		} while (choice != 7);

	}

}
