package guest;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Serialization;

/**
 * Guest Controller
 * Basically make use of the Guest entity class and manipulate the data
 * Hence, it role is to include of logics that involves the Guest entity class
 * 
 * @author Low Shu En
 * @version 1.0
 * @since 13/4/2017
 */
public class GuestController {
	/**
	 * Stores the list of all guest objects created
	 * by calling the retrieveGuestList() method
	 */
	private static List<Guest> guestList = retrieveGuestList();
	/**
	 * @return the list containing all guest objects
	 */
	@SuppressWarnings("unchecked")
	public static List<Guest> retrieveGuestList() {

		List<Guest> guestList = null;
		guestList = (List<Guest>) Serialization.readSerializedObject(Constants.GUEST_DATA);

		if (guestList != null)
			return guestList;
		else {
			//System.out.println("No data");
			return null;
		}
	}
	/**
	 * This method basically create or update the particular Guest object
	 * that is being passed into the parameter
	 * 
	 * It checks if the guest object exist or not, if exists, it is being override (updated)
	 * else it creates the guest into the data file
	 * 
	 * guest list is being updated accordingly
	 * 
	 * @param guest Guest object to be created or updated
	 * @return true if guest is updated / created successfully
	 * else @return false if updating or creating of guest is unsuccessful
	 */
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
			guestList = new ArrayList<Guest>();
			guestList.add(guest);
		}
		
		return Serialization.writeSerializedObject(Constants.GUEST_DATA, guestList);

	}

	/**
	 * This method basically remove that particular guest that is being passed into the 
	 * parameter from the guest list and data file
	 * 
	 * @param guest Guest object to be removed
	 * @return true if guest is removed successfully
	 * else @return false if the removal is unsuccessful
	 */
	public static boolean removeGuest(Guest guest) {
		if (guestList != null) {
			if (guestList.remove(guest)) {
				
				return Serialization.writeSerializedObject(Constants.GUEST_DATA, guestList);

			} else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * Search by passport only returns one Guest object as passport is a primary key
	 * @param passport Passport of Guest object
	 * @return the guest object that contains the passport that is being passed in
	 * else @return null if no such passport exist
	 */
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
	
	/**
	 * Search by entering keyword of name
	 * @param keyword Keyword for search
	 * @return the list of guest objects with names containing that keyword
	 * else @return if no such name with keyword exist
	 */
	public static List<Guest> searchByKeyword(String keyword) {
		int i = 0;
		
		if(guestList != null) {
			List<Guest> guestList2 = new ArrayList<Guest>();
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
