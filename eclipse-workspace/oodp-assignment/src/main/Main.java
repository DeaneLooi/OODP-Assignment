package main;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

import reservation.ReservationTimer;

public class Main {

	public static void main(String args[]) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);

		Timer timer = new Timer();
		
		timer.schedule(new ReservationTimer(), new Date(),10000);
		
		do {

			System.out.println("Choose one:");
			System.out.println("1. Guest");
			System.out.println("2. Reservation");
			System.out.println("3. Room");
			System.out.println("4. Room Service");
			System.out.println("5. Check-in");
			System.out.println("6. Check-out");
			System.out.println("7. Exit application");
			while (!sc.hasNextInt()) {
				System.out.println("Please enter a number!");
				sc.next();
			}
			choice = sc.nextInt();

			int choice2;

			switch (choice) {

			case 1:
				System.out.println("Choose one");
				System.out.println("1.Create guest detail");
				System.out.println("2.Update guest detail");
				System.out.println("3.Search guest detail");
				System.out.println("4. Main Menu");
				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
					switch (choice2) {

					case 1:
						createGuest();
						break;
					case 2:
						updateGuest();
						break;
					case 3:
						searchGuest();
						break;

					}

				}

				else
					sc.next();

				break;
			case 2:
				System.out.println("Choose one");
				System.out.println("1.Create Reservation");
				System.out.println("2.Update Reservation");
				System.out.println("3.Remove Reservation");
				System.out.println("4.Print Reservation");
				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
					switch (choice2) {

					case 1:
						createReservation();
						break;
					case 2:
						updateReservation();
						break;
					case 3:
						removeReservation();
						break;
					case 4:
						printReservation();
						break;
					}

				}

				else
					sc.next();

				break;
			case 3:

				System.out.println("Choose one");
				System.out.println("1.Create Room");
				System.out.println("2.Update Room");
				System.out.println("3.Check Room Availability");
				System.out.println("4.Print Room Status Statistic Report by Room Type");
				System.out.println("5.Print Room Status Statistic Report by Availability");

				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
					switch (choice2) {

					case 1:
						createRoom();
						break;
					case 2:
						updateRoom();
						break;
					case 3:
						checkRoomAvailability();
						break;
					case 4:
						printReportByRoomType();
						break;
					case 5:
						printReportByAvailability();
						break;

					}

				}

				else
					sc.next();

				break;
			case 4:

				System.out.println("Choose one");
				System.out.println("1.Create Room Order Service");
				System.out.println("2.Update Room Order Service");
				System.out.println("3.Remove Room Order Service");
				System.out.println("4.Create Menu Item");
				System.out.println("5.Update Menu Item");
				System.out.println("6.Remove Menu Item");
				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
					switch (choice2) {

					case 1:
						createRoomOrderService();
						break;
					case 2:
						updateRoomOrderService();
						break;
					case 3:
						removeRoomOrderService();
						break;
					case 4:
						createMenuItem();
						break;
					case 5:
						updateMenuItem();
						break;
					case 6:
						removeMenuItem();
						break;
					}

				}

				else
					sc.next();

				break;
			case 5:
				checkIn();
				break;
			case 6:
				checkOut();
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
