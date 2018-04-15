package item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.Constants;
import utils.Serialization;

/**
* <h1>Menu Item Controller</h1>
*
* This controller class makes use of Item Entity class to do simple CRUD operations.
*
* @version 1.0
* @since   2018-04-12
*/
public class ItemController {

	/**
	 * The list of Item Entity objects in the data file
	 */
	private static List<Item> itemList = retrieveItemList();
	
	
	/**
	 * Reads the item data file and returns it in a list format
	 * 
	 * @return Returns the list of Item Entity objects in the data file
	 */
	public static List<Item> retrieveItemList() {

		itemList = (List<Item>) Serialization.readSerializedObject(Constants.ITEM_DATA);

		if (itemList != null)
			return itemList;
		else {
			//System.err.println("No data");
			return null;
		}

	}

	/**
	 * 
	 * Creates or updates the item object into the data file
	 * 
	 * @param item Item object to be created or updated
	 * @return Returns true if data file is updated, else returns false
	 */
	public static boolean updateItemList(Item item) {

		if (itemList != null) {
			
			if (itemList.contains(item)) {
				int i = 0;

				for (i = 0; i < itemList.size(); i++) {
					if (item.equals(itemList.get(i)))
						break;
				}

				itemList.set(i, item);
			}

			else
				itemList.add(item);
		}

		else {
			itemList = new ArrayList();
			itemList.add(item);
		}

		
			return Serialization.writeSerializedObject(Constants.ITEM_DATA, itemList);


	}

	/**
	 * 
	 * Displays the menu
	 * 
	 * @return Returns the menu in a String format
	 */
	public static String getMenu() {

		String menuList = "";
		for (int i = 0; i < itemList.size(); i++) {
			menuList += (i+1)+". "+itemList.get(i).toString() + "\n";
		}

		return menuList;

	}

	/**
	 * 
	 * Removes the item object from the data file
	 * 
	 * @param item Item object to be removed
	 * @return Returns true if item object is removed from data file, else returns false
	 */
	public static boolean removeItem(Item item) {


		if (itemList != null) {
			if (itemList.remove(item)) {
				
				return Serialization.writeSerializedObject(Constants.ITEM_DATA, itemList);

			} else
				return false;
		}

		else
			return false;
	}

	/**
	 * 
	 * Returns item object using the primary key of Item Entity, itemName
	 * 
	 * @param itemName The primary key of item entity
	 * @return Returns the item object from itemName
	 */
	public static Item getItemFromName(String itemName) {

		Item checkName = new Item();
		checkName.setItemName(itemName);
		int i = 0;

		if (itemList != null) {
			if (itemList.contains(checkName)) {
				for (i = 0; i < itemList.size(); i++) {
					if (checkName.equals(itemList.get(i)))
						break;
				}
			}
			
			else
				return null;

			return itemList.get(i);
		}

		else
			return null;
	}
}
