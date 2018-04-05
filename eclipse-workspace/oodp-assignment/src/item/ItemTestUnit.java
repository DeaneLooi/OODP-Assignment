package item;

import org.junit.jupiter.api.Test;

class ItemTestUnit {

	@Test
	void testItemCreation() {

		// create item object
		Item item = new Item(1, "testItem", "RICE WITHR CHICKEN", .20f);
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
		Item item = new Item(1, "Chicken Rice","amsdkn",4.20f);
		boolean result = ItemController.removeItem(item);
		System.out.println("Remove item result: "+result);
	}

}
