package dao;

import static db.DBConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import db.DBException;
import models.LearningCard;
import models.LearningTheme;

public class ThemesMySQLDAO implements ThemeDAO {

	private Connection dbConnection;

	public ThemesMySQLDAO() throws DBException  {
		dbConnection = DBConnection.getInstance().connection();
	}

	@Override
	public int getCount() throws DBException, SQLException{

		String query = String.format("SELECT count(*) FROM %s", TABLE_THEMES);
		PreparedStatement statement = dbConnection.prepareStatement(query);
		int count = 0;
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			 count = rs.getInt(1);
		}
		
		//System.out.println(count);
		statement.close();
		return count;
	}
	

	@Override
	public List<LearningTheme> findAll() throws DBException, SQLException {

		ArrayList<LearningTheme> list = new ArrayList<>();

		String query = String.format("SELECT * FROM %s", TABLE_THEMES);
		PreparedStatement statement = dbConnection.prepareStatement(query);

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");

			list.add(new LearningTheme(id, title, new ArrayList<LearningCard>()));
		}
		
		statement.close();
	//	System.out.println(list);
		return list;
	}
	
	//@Override
	public int getIdByTitle(String title) throws DBException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE title=?", TABLE_THEMES);
		
		PreparedStatement statement = dbConnection.prepareStatement(query);
		statement.setString(1, title);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			return id;
		}
		statement.close();	
		return 0;
	}


	@Override
	public boolean save(LearningTheme theme) throws DBException, SQLException {
		if (theme.getId() > 0) {
			return update(theme);
		}
		else {
			int themeId = save(theme.getTitle());
			if (themeId > 0) {
				theme.setId(themeId);
			}
			return themeId > 0;
		}
	}
	
	public int save(String title) throws DBException, SQLException {
		
		String query = String.format("INSERT INTO %s ( title ) VALUE ( ? )", TABLE_THEMES);

		PreparedStatement statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, title);
		int r = statement.executeUpdate();
		
		int id = 0;
		
		// id auslesen
		if (r == 1) {
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()){
				id= rs.getInt(1);
			}
		}
		
		statement.close();
		assert(id > 0);
		return id;
	}
	
	public boolean update(LearningTheme theme) throws DBException, SQLException{
		String query = String.format("UPDATE %s SET title=? WHERE id=?", TABLE_THEMES);

		PreparedStatement statement = dbConnection.prepareStatement(query);
		statement.setString(1, theme.getTitle());
		statement.setInt(2, theme.getId());
		int r = statement.executeUpdate();
		
		statement.close();
		return r == 1;
	}

	@Override
	public boolean delete(int id) throws DBException, SQLException{
		String query = String.format("DELETE FROM %s WHERE id=?", TABLE_THEMES);

		PreparedStatement deleteStatement = dbConnection.prepareStatement(query);
		deleteStatement.setInt(1, id);
		int r = deleteStatement.executeUpdate();
		deleteStatement.close();
		return r == 1;
	}

}
