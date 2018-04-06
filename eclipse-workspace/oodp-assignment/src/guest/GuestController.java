package guest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.Constants;
import utils.Serialization;

public class GuestController {
	public static List<Guest> retrieveGuestList() {

		List<Guest> guestList = null;
		guestList = (List<Guest>) Serialization.readSerializedObject(Constants.GUEST_DATA);

		if (guestList != null)
			return guestList;
		else {
			System.out.println("No data");
			return null;
		}
	}
	public static boolean updateGuestList(Guest guest) {

		List<Guest> guestList = retrieveGuestList();

		if (guestList != null) {
			if (guest.getGuestID() == 0)
				guest.setGuestID(guestList.size() + 1);

			if (guestList.contains(guest)) {
				int i = 0;

				for (i = 0; i < guestList.size(); i++) {
					if (guest.equals(guestList.get(i)))
						break;
				}
				guestList.set(i, guest);
			}
			else
				guestList.add(guest);
		}
		else {
			guest.setGuestID(1);
			guestList = new ArrayList();
			guestList.add(guest);
		}
		try {
			Serialization.writeSerializedObject(Constants.GUEST_DATA, guestList);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean removeGuest(Guest guest) {
		List<Guest> guestList = retrieveGuestList();
		if (guestList != null) {
			if (guestList.remove(guest)) {
				try {
					Serialization.writeSerializedObject(Constants.GUEST_DATA, guestList);
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

	public static List<Guest> getGuestsFromReservationNo(int reservationNo) {
		List<Guest> guestList = retrieveGuestList();
		if (guestList != null) {
				List<Guest> reservationGuests = new ArrayList();
				for (int i = 0; i < guestList.size(); i++) {
					if (reservationNo==(guestList.get(i).getReservationNo()))
						reservationGuests.add(guestList.get(i));
				}
				return reservationGuests;
		}
		else
			return null;
	}
}