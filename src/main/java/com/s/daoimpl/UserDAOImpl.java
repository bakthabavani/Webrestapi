package com.s.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

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
			return pstmt.executeUpdate()>0?true:false;
		}finally{
			conn.close();
		}		
	}

	@Override	
	public User getUser(int userID) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			H2DBConfigurer config=new H2DBConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT user_id,username,date_of_birth FROM S_USERS WHERE user_id=?");
			pstmt.setInt(1, userID);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return new User(rs.getInt("user_id"),rs.getString("username"),new java.util.Date(rs.getDate("date_of_birth").getTime()));
			}else{
				return null;
			}			
		}finally{
			conn.close();
		}
	}

	@Override
	public Set<User> getAllUsers() throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		Set<User> users=new HashSet<User>();
		try{
			H2DBConfigurer config=new H2DBConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT user_id,username,date_of_birth FROM S_USERS");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				users.add(new User(rs.getInt("user_id"),rs.getString("username"),new java.util.Date(rs.getDate("date_of_birth").getTime())));
			}
			return users;
		}finally{
			conn.close();
		}
	}
	
}
