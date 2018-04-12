package item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Serialization;

public class ItemController {

	
	private static List<Item> itemList = retrieveItemList();
	
	public static List<Item> retrieveItemList() {

		itemList = (List<Item>) Serialization.readSerializedObject(Constants.ITEM_DATA);

		if (itemList != null)
			return itemList;
		else {
			System.err.println("No data");
			return null;
		}

	}

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

		try {
			Serialization.writeSerializedObject(Constants.ITEM_DATA, itemList);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static String getMenu() {

		String menuList = "";
		for (int i = 0; i < itemList.size(); i++) {
			menuList += (i+1)+". "+itemList.get(i).toString() + "\n";
		}

		return menuList;

	}

	public static boolean removeItem(Item item) {


		if (itemList != null) {
			if (itemList.remove(item)) {
				try {
					Serialization.writeSerializedObject(Constants.ITEM_DATA, itemList);

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
