package com.s.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.s.dao.MovieDAO;
import com.s.daoimpl.MovieDAOImpl;
import com.s.entities.Movie;
import com.s.service.MovieService;

@Path("/movies")
public class MovieServiceImpl implements MovieService {
	private MovieDAO movieDAO=new MovieDAOImpl();
	@Override
	@GET
	@Produces("application/json")
	@Path("{id}")
	public Movie getMovie(@PathParam("id") int movieID) {
		try {
			return this.movieDAO.getMovie(movieID);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("byuser/deprecated")
	public Set<Movie> getMoviesByUser(@QueryParam("userID") int userID){
		try{
			return this.movieDAO.getMoviesByUser(userID);
		}catch(ClassNotFoundException | SQLException | IOException ex ){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("bygenre")
	public Set<Movie> getMoviesByGenre(@QueryParam("genre") String genre,@QueryParam("userID") int userID) {
		try{
			return this.movieDAO.getMoviesByGenre(genre, userID);
		}catch(ClassNotFoundException | SQLException | IOException ex ){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("ratings/{id}")
	public String getAvgRatings(@PathParam("id") int movieID) {
		try{
			return this.movieDAO.getAvgRatings(movieID)+"";
		}catch(ClassNotFoundException | SQLException | IOException ex ){
			ex.printStackTrace();
		}
		return "No Ratings";
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byuser")
	public String getMoviesByUserJSON(@QueryParam("userID") int userID) {
		try{
			return this.movieDAO.getMoviesByUserJSON(userID);
		}catch(ClassNotFoundException | SQLException | IOException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
