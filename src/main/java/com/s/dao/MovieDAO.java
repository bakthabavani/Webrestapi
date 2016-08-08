package com.s.dao;

import java.util.Set;

import com.s.entities.Genre;
import com.s.entities.Movie;

public interface MovieDAO {
	public boolean createMovie(Movie movie);
	/*public Movie getMovie(int movieID);
	public boolean deleteMovie(int movieID);*/
	public Set<Movie> getAllMovies();
	public Set<Movie> getMoviesByGenre(Genre genre);
}
