package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "residents")
public class Resident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resindent_id")
	private int resindentId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "flat_number", nullable = false)
	private int flatNumber;

	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;

	@Column(name = "password", nullable = false)
	private String password;

	public Resident() {
		super();
	}

	public Resident(String firstName, String lastName, int flatNumber, String mobileNumber, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.flatNumber = flatNumber;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	public Resident(int resindentId, String firstName, String lastName, int flatNumber, String mobileNumber,
			String password) {
		super();
		this.resindentId = resindentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.flatNumber = flatNumber;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	public int getResindentId() {
		return resindentId;
	}

	public void setResindentId(int resindentId) {
		this.resindentId = resindentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
