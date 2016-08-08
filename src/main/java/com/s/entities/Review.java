package com.s.entities;

public class Review {
	private Movie movie;
	private User  user;
	private int   rating;
	public Review(Movie movie, User user, int rating) {
		super();
		this.movie = movie;
		this.user = user;
		this.rating = rating;
	}
	public Review() {
		super();
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}