package reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import reservation.Reservation;
import service.Service;
import utils.Constants;
import utils.Serialization;

public class ReservationController {
	
private static List<Reservation> reservationList = retrieveReservationList();
	
	
	public static List<Reservation> retrieveReservationList() {

		reservationList = (List<Reservation>) Serialization.readSerializedObject(Constants.RESERVATION_DATA);

		if (reservationList != null)
			return reservationList;
		else {
			System.out.println("No data");
			return null;
		}

	}

	public static boolean updateReservationList(Reservation reservation) {

		
		

		if (reservationList != null) {
			

			if (reservation.getReservationCode() == null)
			{
				int reservationNo = reservationList.size()+10001;
				reservation.setReservationCode("R"+reservationNo);
			}

			if (reservationList.contains(reservation)) {
				int i = 0;

				for (i = 0; i < reservationList.size(); i++) {
					if (reservation.equals(reservationList.get(i)))
						break;
				}

				reservationList.set(i, reservation);
			}

			else
				reservationList.add(reservation);
		}

		else {
			reservation.setReservationCode("R10001");
			reservationList = new ArrayList();
			reservationList.add(reservation);
		}

		try {
			Serialization.writeSerializedObject(Constants.RESERVATION_DATA, reservationList);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean removeReservation(Reservation reservation) {


		if (reservationList != null) {
			if (reservationList.remove(reservation)) {
				try {
					Serialization.writeSerializedObject(Constants.RESERVATION_DATA, reservationList);

				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

				return true;
			} else
				return false;
		}

		else
			return false;
	}
	
	public static Reservation getReservationByCode(String reservationCode) {
		
		Reservation checkRes = new Reservation();
		checkRes.setReservationCode(reservationCode);
		int i = 0;
	

		if (reservationList != null) {
			if (reservationList.contains(checkRes)) {
				for (i = 0; i < reservationList.size(); i++) {
					if (checkRes.equals(reservationList.get(i)))
						break;
				}
			}
			
			else
				return null;

			return reservationList.get(i);
		}

		else
			return null;
		
		
	}
	
	
	public static List<Reservation> getReservationByRoomNo(String roomNo) {

		if (reservationList != null) {
			
				List<Reservation> reservations = new ArrayList();

				for (int i = 0; i < reservationList.size(); i++) {
					if (roomNo.equals((reservationList.get(i).getRoomNo())))
						reservations.add(reservationList.get(i));
				}
				
				return reservations;
			
			

		}

		else
			return null;

	}
	
	public static Reservation getReservationByRoomNo(String roomNo, String status) {
		
		
		int i = 0;
	

		if (reservationList != null) {
				for (i = 0; i < reservationList.size(); i++) {
					if (reservationList.get(i).getRoomNo().equals(roomNo) && reservationList.get(i).getStatus().equals(status))
						break;
				}
			
				if(reservationList.get(i).getRoomNo().equals(roomNo) && reservationList.get(i).getStatus().equals(status))
					return reservationList.get(i);
		}

		return null;
		
		
	}



}
