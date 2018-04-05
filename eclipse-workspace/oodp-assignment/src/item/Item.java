package item;

import java.io.Serializable;

public class Item implements Serializable {

	private int itemID;
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
	 * @param itemID
	 * @param itemName
	 * @param itemDescription
	 * @param itemPrice
	 */
	public Item(int itemID, String itemName, String itemDescription, float itemPrice) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the itemID
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 *            the itemID to set
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
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
	 * @return the id
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.itemID;
	}

	/**
	 * @return true if id is the same
	 */
	public boolean equals(int itemID) {
		return (itemID == hashCode());
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
		return this.itemID + ". " + this.itemName + "\n   Price:" + this.itemPrice;
	}

}
