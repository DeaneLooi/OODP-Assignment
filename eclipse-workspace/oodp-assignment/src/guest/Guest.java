package guest;

import java.io.Serializable;

/**
 * Guest Entity Class that contains attributes, getters and setters relating to
 * guest data needed for our program
 * 
 * @author Low Shu En
 * @version 1.0
 * @since 13/4/2017
 */
@SuppressWarnings("serial")
public class Guest implements Serializable {
	/**
	 * Name of guest (need not need to be unique)
	 */
	private String name;
	/**
	 * Email of guest
	 */
	private String email;
	/**
	 * Country that the guest is a resident of
	 */
	private String country;
	/**
	 * Gender of guest
	 */
	private String gender;
	/**
	 * Primary key of guest (i.e.: must be unique)
	 */
	private String passport;
	/**
	 * Residential address of guest
	 */
	private String address;
	/**
	 * Phone number of guest
	 */
	private String phoneNo;
	/**
	 * Credit card number of guest
	 */
	private String creditCardNo;

	/**
	 * Default Constructor
	 */
	public Guest() {
		super();
	}

	/**
	 * Mandatory attributes required for creating every guest object
	 * 
	 * @param name
	 *            Name of guest
	 * @param email
	 *            Email of guest
	 * @param country
	 *            Country of guest
	 * @param gender
	 *            Gender of guest
	 * @param passport
	 *            Passport of guest
	 * @param address
	 *            Address of guest
	 * @param phoneNo
	 *            Phone number of guest
	 * @param creditCardNo
	 *            Credit card number of guest
	 */
	public Guest(String name, String email, String country, String gender, String passport, String address,
			String phoneNo, String creditCardNo) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.passport = passport;
		this.address = address;
		this.phoneNo = phoneNo;
		this.creditCardNo = creditCardNo;
	}

	/**
	 * @return the name of guest
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            updates the name of guest
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email of guest
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            updates the email of guest
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the country of guest
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            updates the country of guest
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the gender of guest
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            updates the gender of the guest
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the passport of guest
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * @param passport
	 *            updates the passport of the guest
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/**
	 * @return the residential address of guest
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            updates the residential address of guest
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return guest phone number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            updates guest phone number
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return guest credit card number
	 */
	public String getCreditCardNo() {
		return creditCardNo;
	}

	/**
	 * @param creditCardNo
	 *            updates guest credit card number
	 */
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	/**
	 * Compares 2 Guest objects and
	 * 
	 * @return true if guest entity is same else false done by comparing the primary
	 *         key - passport
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Guest) ? (((Guest) obj).getPassport().equalsIgnoreCase(this.passport)) : false;
	}

	/**
	 * @return string of values for all guest attributes. to be displayed to the
	 *         user when needed (e.g.: searchGuest)
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + this.name + "\nEmail: " + this.email + "\nCountry: " + this.country + "\nGender: "
				+ this.gender + "\nPassport: " + this.passport + "\nAddress: " + this.address + "\nPhone No: "
				+ this.phoneNo + "\nCredit Card No: " + this.creditCardNo;
	}
}
