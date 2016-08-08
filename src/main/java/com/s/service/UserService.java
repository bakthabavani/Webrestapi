package com.s.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.s.entities.User;

public interface UserService {
	public String createUser(User user);
	public User getUser(int userID);
	public Set<User> getAllUsers();
}
