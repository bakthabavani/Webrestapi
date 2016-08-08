package com.s.entities;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement
public class Review {
	@JsonIgnore
	private Movie movie;
	@JsonIgnore
	private User  user;
	private int   rating;
	public Review(Movie movie, User user, int rating) {
		super();
		this.movie = movie;
		this.user = user;
		this.rating = rating;
	}
	
	public Review(int rating) {
		super();
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
