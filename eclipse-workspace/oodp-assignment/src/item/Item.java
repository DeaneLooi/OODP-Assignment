package item;

import java.io.Serializable;

public class Item implements Serializable {

	private String itemName;
	private String itemDescription;
	private float itemPrice;

	/**
	 * default constructor
	 */
	public Item() {
		super();
	}

	/**
	 * @param itemName
	 * @param itemDescription
	 * @param itemPrice
	 */
	public Item(String itemName, String itemDescription, float itemPrice) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription
	 *            the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the itemPrice
	 */
	public float getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice
	 *            the itemPrice to set
	 */
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}


	/**
	 * @return true if name is same else false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Item)
			return (((Item) obj).getItemName().equalsIgnoreCase(this.itemName));

		else
			return false;
	}

	/**
	 * @return string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  this.itemName + "\n   Price:" + this.itemPrice;
	}

}
