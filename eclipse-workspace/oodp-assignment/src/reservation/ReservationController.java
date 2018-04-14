package reservation;

import java.util.ArrayList;
import java.util.List;

import room.Room;
import room.RoomController;
import utils.Constants;
import utils.Serialization;

/**
 * <h1>Reservation Controller</h1>
 *
 * This controller class makes use of Reservation Entity class to do simple CRUD operations.
 *
 * @version 1.0
 * @since 2018-04-12
 */
public class ReservationController {

	/**
	 * The list of reservation objects in the data file
	 */
	private static List<Reservation> reservationList = retrieveReservationList();

	/**
	 * Reads the reservation data file and returns it in a list format
	 * 
	 * @return Returns the list of Reservation Entity objects in the data file
	 */
	public static List<Reservation> retrieveReservationList() {

		reservationList = (List<Reservation>) Serialization.readSerializedObject(Constants.RESERVATION_DATA);

		if (reservationList != null)
			return reservationList;
		else {
			System.out.println("No data");
			return null;
		}

	}

	/**
	 * 
	 *  Creates or updates the reservation object into the data file
	 * 
	 * @param reservation
	 * @return Returns true if data file is updated, else returns false
	 */
	public static boolean updateReservationList(Reservation reservation) {

		if (reservationList != null) {

			if (reservation.getReservationCode() == null) {
				int reservationNo = reservationList.size() + 10001;
				reservation.setReservationCode("R" + reservationNo);
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

		return (updateRoom(reservation) && Serialization.writeSerializedObject(Constants.RESERVATION_DATA, reservationList));

	}
	
	/**
	 * 
	 * Checks the status of the reservation object and updated the room status accordingly
	 * 
	 * @param status Status of the reservation
	 * @return Returns true if room is updated, else returns false
	 */
	public static boolean updateRoom(Reservation reservation) {
		
		Room room = RoomController.getRoom(reservation.getRoomNo());
		boolean check = true;
		if(reservation.getStatus().equals(Constants.STATUS_CONFIRMED))
				room.setStatus(Constants.ROOM_STATUS_RESERVED);
		else if(reservation.getStatus().equals(Constants.STATUS_CHECKED_IN))
				room.setStatus(Constants.ROOM_STATUS_OCCUPIED);
		else if(reservation.getStatus().equals(Constants.STATUS_CHECKED_OUT)) {
				room.setStatus(Constants.ROOM_STATUS_VACANT);
				check = removeReservation(reservation);
		}
		if(check)
			return RoomController.updateRoomList(room);
		else 
			return false;
	}

	/**
	 * 
	 * Removes the reservation object from the data file
	 * 
	 * @param reservation Reservation object to be removed
	 * @return Returns true if reservation object is removed form data file, else returns false
	 */
	public static boolean removeReservation(Reservation reservation) {

		if (reservationList != null) {
			if (reservationList.remove(reservation)) {

				return Serialization.writeSerializedObject(Constants.RESERVATION_DATA, reservationList);

			} else
				return false;
		}

		else
			return false;
	}

	/**
	 * 
	 * Returns reservation object using the primary key of Reservation Entity, reservationCode
	 * 
	 * @param reservationCode The primary key of Reservation Entity
	 * @return Returns the reservation object from reservationCode
	 */
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

	/**
	 * 
	 * Returns list of reservation objects using roomNo
	 * 
	 * @param roomNo Room number to search reservations
	 * @return Returns a list of reservation objects from roomNo
	 */
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

	/**
	 * 
	 * Returns reservation object using roomNo and status of the room which will only return one object if status is "Checked In"
	 * 
	 * @param roomNo Room number of reservation
	 * @param status Status of reservation
	 * @return Returns reservation object from roomNo and status
	 */
	public static Reservation getReservationByRoomNo(String roomNo, String status) {

		int i = 0;

		if (reservationList != null) {
			for (i = 0; i < reservationList.size(); i++) {
				if (reservationList.get(i).getRoomNo().equals(roomNo)
						&& reservationList.get(i).getStatus().equals(status))
					break;
			}

			if (reservationList.get(i).getRoomNo().equals(roomNo) && reservationList.get(i).getStatus().equals(status))
				return reservationList.get(i);
		}

		return null;

	}

	/**
	 * 
	 * Returns reservation object using guestPassport
	 * 
	 * @param guestPassport Guest passport 
	 * @return Returns reservation object from guestPassport
	 */
	public static Reservation getReservationByGuestPassport(String guestPassport) {

		int i = 0;

		if (reservationList != null) {

			for (i = 0; i < reservationList.size(); i++) {
				if (reservationList.get(i).getGuestPassport().equalsIgnoreCase(guestPassport))
					break;
			}

			return reservationList.get(i);
		}

		else
			return null;

	}

}
