package item;

import java.io.Serializable;


/**
* <h1>Menu Item Entity</h1>
*
* <p>Represents a item in a menu. </p>
* <p>Item object contains itemName, itemDescription and itemPrice.</p>
*
*
* @author  Deane Looi
* @version 1.0
* @since   2018-04-12
*/

public class Item implements Serializable {

	/**
	 * Primary Key of Item Entity<p>
	 * Name of Item Entity
	 */
	private String itemName;
	
	/**
	 * Description of Item Entity
	 */
	private String itemDescription;
	
	/**
	 * Price of Item Entity
	 */
	private double itemPrice;

	/**
	 * Default constructor
	 */
	public Item() {
		super();
	}

	/**
	 * 
	 * Creates a new Item Entity object with the name, description and price
	 * 
	 * @param itemName Name of Item
	 * @param itemDescription Description of Item
	 * @param itemPrice Price of Item
	 */
	public Item(String itemName, String itemDescription, double itemPrice) {
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
	 *             itemName to set
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
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice
	 *            the itemPrice to set
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}


	/**
	 *Used to compare between two Item Entity objects
	 * 
	 * @return Returns true if itemName is the same between two objects, else returns false
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
	 * Returns a string value of this Item Entity object to be displayed in a Menu
	 * 
	 * @return Returns string value of item object
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  this.itemName + "\n   Price: " + this.itemPrice+"   Description: "+this.itemDescription;
	}

}
