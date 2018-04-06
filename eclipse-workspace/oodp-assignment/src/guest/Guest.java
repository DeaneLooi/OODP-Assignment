package guest;

import java.io.Serializable;

public class Guest implements Serializable{
	private String name;
	private String email;
	private String country;
	private boolean gender;
	private String passport;
	private static final String man = "Man";
	private static final String woman = "Woman";

	public Guest(String name, String email, String country, boolean gender, String passport) {
    super();
    this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.passport = passport;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCountry() {
		return country;
	}

	public boolean getGender() {
		return gender;
	}

	public String getPassport() {
		return passport;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Override
	public String hashCode() {
		// TODO Auto-generated method stub
		return this.passport;
	}

	/**
	 * @return true if passport is the same
	 */
	public boolean equals(String passport) {
		return (passport.equals(this.passport));
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
		return String.format("Name: %s\nGender: %s\nCountry: %s\nEmail: %s\nPassport: %s", name, gender ? man : woman, country, email, passport);
	}
}
