package com.example.session.model;

public class SessionResposne {

	private String userName;
	private String firstName;
	private String lastName;
	private String loginId;
	private String phoneNumber;
	private String userUID;
	private String address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserUID() {
		return userUID;
	}

	public void setUserUID(String userUID) {
		this.userUID = userUID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SessionResposne [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", loginId=" + loginId + ", phoneNumber=" + phoneNumber + ", userUID=" + userUID + ", address="
				+ address + "]";
	}

}
