package com.s.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import com.s.dao.MovieDAO;
import com.s.daoimpl.MovieDAOImpl;
import com.s.entities.Movie;
import com.s.service.MovieService;

public class MovieServiceImpl implements MovieService{
	MovieDAO movieDAO=new MovieDAOImpl();	
	@Override
	public Movie getMovie(int movieID) {
		try {
			movieDAO.getMovie(movieID);
		} catch (ClassNotFoundException | SQLException | IOException e) {		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Movie> getMoviesByUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Movie> getMoviesByGenre(String genre, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
