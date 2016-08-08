package com.s.dao;

import com.s.entities.Movie;
import com.s.entities.Review;
import com.s.entities.User;

public interface ReviewDAO {
	public Review getReview(int reviewID);
	public boolean addReview(User user,Movie movie,int review);
}
