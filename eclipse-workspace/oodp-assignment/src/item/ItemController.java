package item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Serialization;

public class ItemController {

	private static List<Item> retrieveItemList() {

		List<Item> itemList = null;
		itemList = (List<Item>) Serialization.readSerializedObject(Constants.ITEM_DATA);

		if (itemList != null)
			return itemList;
		else {
			System.out.println("No data");
			return null;
		}

	}

	public static boolean updateItemList(Item item) {

		List<Item> itemList = retrieveItemList();


		if (itemList != null) {
			

			if (item.getItemID() == 0)
				item.setItemID(itemList.size() + 1);
			
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
			item.setItemID(1);
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
		List<Item> itemList = null;
		itemList = (List<Item>) Serialization.readSerializedObject(Constants.ITEM_DATA);
		String menuList = "";
		for (int i = 0; i < itemList.size(); i++) {
			menuList += itemList.get(i).toString() + "\n";
		}

		return menuList;

	}

	public static boolean removeItem(Item item) {

		List<Item> itemList = retrieveItemList();

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

		List<Item> itemList = retrieveItemList();
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

			return itemList.get(i);
		}

		else
			return null;
	}
}
