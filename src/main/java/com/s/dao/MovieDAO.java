package com.s.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import com.s.entities.Genre;
import com.s.entities.Movie;
import com.s.entities.User;

public interface MovieDAO {
	/*public boolean createMovie(Movie movie);
	/*
	public boolean deleteMovie(int movieID);
	public Set<Movie> getAllMovies();*/
	public Movie getMovie(int movieID) throws ClassNotFoundException, SQLException, IOException;
	public Set<Movie> getMoviesByUser(int userID) throws ClassNotFoundException, IOException, SQLException;
	public Set<Movie> getMoviesByGenre(String genre,int userID) throws ClassNotFoundException, IOException, SQLException;
	public String getAvgRatings(int movieID) throws SQLException, ClassNotFoundException, IOException;
}
