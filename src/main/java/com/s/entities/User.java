package com.s.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User {
	private int userID;
	private String username;
	private Date dateOfBirth;
	public User() {
		super();
	}
	public User(int userID, String username, Date dateOfBirth) {
		super();
		this.userID = userID;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userID != other.userID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
