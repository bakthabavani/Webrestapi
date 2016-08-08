package com.s.service;

import java.util.Set;

import com.s.entities.Movie;

public interface MovieService {
	public Movie getMovie(int movieID);
	public Set<Movie> getMoviesByUser(int userID);
	public Set<Movie> getMoviesByGenre(String genre,int userID);
	public String getAvgRatings(int movieID);
}
