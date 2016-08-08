package com.s.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.s.entities.Genre;

public interface GenreDAO {
	public Genre getGenre(int genreID) throws SQLException, ClassNotFoundException, IOException;
	public Genre getGenre(String genre) throws SQLException, ClassNotFoundException, IOException;
}
