package com.s.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.s.configs.H2DBConfigurer;
import com.s.dao.GenreDAO;
import com.s.dao.MovieDAO;
import com.s.dao.UserDAO;
import com.s.entities.Genre;
import com.s.entities.Movie;
import com.s.entities.Review;

public class MovieDAOImpl implements MovieDAO{
	
	@Override
	public Movie getMovie(int movieID) throws ClassNotFoundException, SQLException, IOException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		GenreDAO genreDAO=new GenreDAOImpl();
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT movie_id,movie_name,genre_id FROM S_MOVIES WHERE movie_id=?");
			pstmt.setInt(1, movieID);
			ResultSet rs=pstmt.executeQuery();
			System.out.println("No Result");
			if(rs.next()){
				return new Movie(rs.getInt("movie_id"),rs.getString("movie_name"),genreDAO.getGenre(rs.getInt("genre_id")));
			}else{
				return null;
			}			
		}finally{
			conn.close();
		}
	}
	
	@Override
	public Set<Movie> getMoviesByUser(int userID) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		Set<Movie> movies=new HashSet<Movie>();
		GenreDAO genreDAO=new GenreDAOImpl(); 
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT movies.movie_id mid,movies.movie_name mname,movies.genre_id gid,ratings.rating rt"
										+ " FROM S_MOVIES movies INNER JOIN S_MOVIE_RATINGS ratings "
										+ "ON movies.movie_id=ratings.movie_id WHERE ratings.user_id=?");
			pstmt.setInt(1, userID);
			ResultSet rs=pstmt.executeQuery();
			HashSet<Review> rv=null;
			while(rs.next()){
				rv=new HashSet<Review>();
				rv.add(new Review(rs.getInt("rt")));
				movies.add(new Movie(rs.getInt("mid"),rs.getString("mname"),genreDAO.getGenre(rs.getInt("gid")),rv));
			}
		}finally{
			System.out.println("Connection"+conn);
			conn.close();
		}
		return movies;
	}

	@Override
	public Set<Movie> getMoviesByGenre(String genre,int userID) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		Set<Movie> movies=new HashSet<Movie>();
		GenreDAO genreDAO=new GenreDAOImpl(); 
		UserDAO  userDAO=new UserDAOImpl();
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT movies.movie_id mid,movies.movie_name mname,movies.genre_id gid,ratings.rating rt "
										+ "FROM S_MOVIES movies INNER JOIN S_MOVIE_RATINGS ratings "
										+ "ON movies.movie_id=ratings.movie_id "
										+ "INNER JOIN S_USERS users ON users.user_id=ratings.user_id WHERE movies.genre_id=? AND (users.age<=? OR users.age>=?)");
			Genre genreObj=genreDAO.getGenre(genre);
			if(genreObj!=null){
				pstmt.setInt(1, genreObj.getGenre_id());
				pstmt.setInt(2, userDAO.getUser(userID).getAge()+5 );
				pstmt.setInt(3, userDAO.getUser(userID).getAge()-5 );
				ResultSet rs=pstmt.executeQuery();
				HashSet<Review> rv=null;
				while(rs.next()){
					rv=new HashSet<Review>();
					rv.add(new Review(rs.getInt("rt")));
					movies.add(new Movie(rs.getInt("mid"),rs.getString("mname"),genreDAO.getGenre(rs.getInt("gid")),rv));
				}
			}
		}finally{
			conn.close();
		}
		return movies;
	}

	@Override
	public String getAvgRatings(int movieID) throws SQLException, ClassNotFoundException, IOException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		Set<Movie> movies=new HashSet<Movie>();
		GenreDAO genreDAO=new GenreDAOImpl(); 
		try{
			H2DBConfigurer config=H2DBConfigurer.getConfigurer();
			conn=config.getConnection();
			pstmt=conn.prepareStatement("SELECT avg(mrt.rating) avgrating FROM S_MOVIE_RATINGS mrt WHERE mrt.movie_id=?");
			pstmt.setInt(1, movieID);
			ResultSet rs=pstmt.executeQuery();			
			if(rs.next()){
				return rs.getString("avgrating");
			}
		}finally{
			conn.close();
		}		
		return "";
	}
	
	
	
}
