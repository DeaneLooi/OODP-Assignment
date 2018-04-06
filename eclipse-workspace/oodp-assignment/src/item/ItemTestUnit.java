package item;

import org.junit.jupiter.api.Test;

class ItemTestUnit {

	@Test
	void testItemObjectCreation() {

		// create item object
		Item item = new Item("Chicken rice", "fried NOIODLES", 5.50f);
		boolean result = ItemController.updateItemList(item);
		System.out.println("Item creation result: " + result);
	}


	@Test
	void testGetMenu()
	{
		System.out.println(ItemController.getMenu());
	}
	
	@Test
	void testRemoveItem()
	{
		Item item = new Item("Chicken Rice","amsdkn",4.20f);
		boolean result = ItemController.removeItem(item);
		System.out.println("Remove item result: "+result);
	}
	
	@Test
	void testGetItemByName()
	{
		Item item = ItemController.getItemFromName("prawn Rice");
		if(item!=null)//check for null object
			System.out.println(item.toString());
		else
			System.out.println("Item cnanot be found");
		
	}

}
