package com.s.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import com.s.configs.H2DBConfigurer;
import com.s.dao.UserDAO;
import com.s.entities.User;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean createUser(User user) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			H2DBConfigurer config=new H2DBConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("INSERT INTO S_USERS(username,dateOfBirth) VALUES(?,?)");
			pstmt.setString(1, user.getUsername());
			pstmt.setDate(2, new Date(user.getDateOfBirth().getTime()));
		}finally{
			
		}
		return false;
	}

	@Override
	public User getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
