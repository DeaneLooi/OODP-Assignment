package guest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.Constants;
import utils.Serialization;

public class GuestController {
	private static List<Guest> guestList = retrieveGuestList();
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

		if (guestList != null) {

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
	
	public static Guest getGuestByPassport(String passport) {
		Guest checkGuest = new Guest();
		checkGuest.setPassport(passport);
		
		int i=0;
		
		if(guestList != null) {
			if(guestList.contains(checkGuest)) {
				for(i = 0; i < guestList.size(); i++) {
					if(guestList.get(i).equals(checkGuest)) {
						return guestList.get(i);
					}
				}
			}
		}
		return null;
	}
	
	public static List<Guest> searchByKeyword(String keyword) {
		int i = 0;
		
		if(guestList != null) {
			List<Guest> guestList2 = new ArrayList();
			for(i = 0; i < guestList.size(); i++) {
				if(((String)(guestList.get(i).getName())).indexOf(keyword) != -1) {	
					guestList2.add(guestList.get(i));
				}
			}
			return guestList2;
		}
		
		return null;
	}
}
