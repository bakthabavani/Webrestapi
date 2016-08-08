package com.s.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.s.configs.H2DBConfigurer;
import com.s.dao.GenreDAO;
import com.s.entities.Genre;
import com.s.entities.User;

public class GenreDAOImpl implements GenreDAO{

	@Override
	public Genre getGenre(int genreID) throws SQLException, ClassNotFoundException, IOException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT genre_id,genre_name FROM S_GENRES WHERE genre_id=?");
			pstmt.setInt(1, genreID);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return new Genre(rs.getInt("genre_id"),rs.getString("genre_name"));
			}else{
				return null;
			}			
		}finally{
			conn.close();
		}
	}

	@Override
	public Genre getGenre(String genre) throws SQLException,
			ClassNotFoundException, IOException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT genre_id,genre_name FROM S_GENRES WHERE genre_name=?");
			pstmt.setString(1, genre);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return new Genre(rs.getInt("genre_id"),rs.getString("genre_name"));
			}else{
				return null;
			}			
		}finally{
			conn.close();
		}
	}

}
