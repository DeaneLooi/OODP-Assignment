package guest;

import java.io.Serializable;

public class Guest implements Serializable{
	private String name;
	private String email;
	private String country;
	private String gender;
	private String passport;
	private String address;
	private String phoneNo;

	public Guest() {
		super();
	}

	public Guest(String name, String email, String country, String gender, String passport, String address,
			String phoneNo) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.passport = passport;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	

	/**
	 * @return true if guest entity is same else false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Guest) ? (((Guest) obj).getPassport().equalsIgnoreCase(this.passport)) : false;
	}
	
	/**
	 * @return string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + this.name + "\nEmail: " + this.email + "\nCountry: " + this.country + "\nGender: " + this.gender + "\nPassport: " + this.passport + "\nAddress: " + this.address + "\nPhone No: " + this.phoneNo;
	}
}
