package com.s.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.s.entities.User;

public interface UserDAO {
	public boolean createUser(User user) throws ClassNotFoundException, IOException, SQLException;
	public User getUser(int userID) throws ClassNotFoundException, IOException, SQLException;
	/*public boolean deleteUser(int userID);
	public boolean updateUser(User user); */
	public Set<User> getAllUsers() throws ClassNotFoundException, IOException, SQLException;	
}
