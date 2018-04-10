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
	private String creditCardNo;

	public Guest() {
		super();
	}

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
	
	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
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
		return "Name: " + this.name + " Email: " + this.email + " Country: " + this.country + " Gender: " + this.gender + " Passport: " + this.passport + " Address: " + this.address + " Phone No: " + this.phoneNo + " Credit Card No: " + this.creditCardNo;
	}
}
