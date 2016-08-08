package com.s.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import com.s.configs.H2DBConfigurer;
import com.s.dao.UserDAO;
import com.s.entities.User;

public class UserDAOImpl implements UserDAO{

	@Override
	@Consumes("application/json")
	public boolean createUser(User user) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("INSERT INTO S_USERS(username,age) VALUES(?,?)");
			pstmt.setString(1, user.getUsername());
			pstmt.setInt(2, user.getAge());
			System.out.println("Executed");
			return pstmt.executeUpdate()>0?true:false;
		}finally{
			if(conn!=null)conn.close();
		}		
	}

	@Override	
	public User getUser(int userID) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT user_id,username,age FROM S_USERS WHERE user_id=?");
			pstmt.setInt(1, userID);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return new User(rs.getInt("user_id"),rs.getString("username"),rs.getInt("age"));
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
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT user_id,username,age FROM S_USERS");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				users.add(new User(rs.getInt("user_id"),rs.getString("username"),rs.getInt("age")));
			}
			return users;
		}finally{
			conn.close();
		}
	}
	
}
