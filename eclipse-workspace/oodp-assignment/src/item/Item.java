package item;

import java.io.Serializable;

public class Item implements Serializable{
	
	private int id;
	private String name;
	private String description;
	private float price;
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 */
	public Item(int id, String name, String description, float price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the id
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * @return true if name is same else false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Item)
			return ((Item) obj).name.equalsIgnoreCase(this.name);
		
		else
			return false;
	}

	/**
	 * @return string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+". "+this.name+"\n   Price:"+this.price;
	}
	
	

}
