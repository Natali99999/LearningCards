package dao;

import java.sql.SQLException;
import java.util.List;

import db.DBException;
import models.LearningTheme;

public interface ThemeDAO {
	List<LearningTheme> findAll() throws DBException, SQLException;
	boolean save(LearningTheme theme) throws DBException, SQLException;
	boolean delete(int id) throws DBException, SQLException;
	int getCount() throws DBException, SQLException;
}