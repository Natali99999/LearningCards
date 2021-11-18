package dao;

import static db.DBConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.DBConnection;
import db.DBException;
import models.LearningCard;


public class CardsMySQLDAO implements CardsDAO{
	private Connection dbConnection;
	static Logger log = LogManager.getLogger(CardsMySQLDAO.class);


	public CardsMySQLDAO() throws DBException {
		dbConnection = DBConnection.getInstance().connection();
	}
	
	@Override
	public List<LearningCard> findAll() {
		
		ArrayList<LearningCard> list = new ArrayList<>();
		
		String query = String.format("SELECT * FROM %s", TABLE_NAME);
		try (PreparedStatement statement = dbConnection.prepareStatement(query)) {

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String ask = rs.getString("ask");
				String askDesc = rs.getString("askDesc");
				String answer = rs.getString("answer");
			//	String answerDesc = rs.getString("answerkDesc");
			//	int cardgroup_id = rs.getInt("group_id");
				
				list.add(
					new LearningCard(id, ask, answer, askDesc)
				);
			}
		//	System.out.println(list);
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<LearningCard> findAll(int themeId) {
		
		ArrayList<LearningCard> list = new ArrayList<>();
		//user WHERE todo.id_user= user.id  AND user.id=1
				
		String query = String.format("SELECT * FROM %s WHERE group_id = ?", TABLE_NAME );
		try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
			statement.setInt(1, themeId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String ask = rs.getString("ask");
				String askDesc = rs.getString("ask_desc");
				String answer = rs.getString("answer");
				//String answerDesc = rs.getString("answer_desc");
				//int cardgroup_id = rs.getInt("group_id");
				
				list.add(
					new LearningCard(id, ask, answer, askDesc)
				);
			}
		//	System.out.println(list);
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean save(LearningCard card, int themeId) {
		if (card.getId()> 0){	
			return update(card, themeId);
		}

		String query = String.format("INSERT INTO %s (ask, ask_desc, answer, answer_desc, group_id ) VALUE ( ?, ?, ?, ?, ?)", TABLE_NAME);
		
		try (PreparedStatement statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, card.getAsk()); 
			statement.setString(2, card.getAskDesc()); 
			statement.setString(3, card.getAnswer()); 
			statement.setString(4, ""); //todo?
			statement.setInt(5, themeId); 
			
			int id = 0;
			int r = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()){
				id= rs.getInt(1);
			}
			log.debug("card {} created ", id);
			
			assert(id > 0);
			card.setId(id);
			return r > 0;

		} catch (SQLException e) {
			log.error(e + "" + e.getLocalizedMessage());
		} catch (NullPointerException e) {
			log.error(e + "" + e.getLocalizedMessage());
		} catch (Exception e) {
			log.error(e + "" + e.getLocalizedMessage());
		}
		return false;
	}
	
	public boolean update(LearningCard card, int themeId) {
		
		if (card.getId() <= 0){	
			log.debug("card id {} kann not be updated", card.getId());
			return false;
		}
		
		String query = String.format("UPDATE %s SET ask=?, ask_desc=?, answer=?, answer_desc=?, group_id=? WHERE id=?", TABLE_NAME);

		try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
			statement.setString(1, card.getAsk()); 
			statement.setString(2, card.getAskDesc()); 
			statement.setString(3, card.getAnswer()); 
			statement.setString(4, ""); //todo?
			statement.setInt(5, themeId); 
			statement.setInt(6, card.getId()); 
			int r = statement.executeUpdate();
			log.debug("card id {} updated {}", card.getId(), r);
			return r == 1;

		} catch (SQLException e) {
			log.error(e + "" + e.getLocalizedMessage());
		} catch (NullPointerException e) {
			log.error(e + "" + e.getLocalizedMessage());
		} catch (Exception e) {
			log.error(e + "" + e.getLocalizedMessage());
		}
		return false;
	}
	
	public boolean save(int themeId, List<LearningCard> cards) {
		for (LearningCard card : cards) {
			save(card, themeId);
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		String query = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);
		
		try (PreparedStatement deleteStatement = dbConnection.prepareStatement(query)) {
			deleteStatement.setInt(1, id);
			return deleteStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	
	@Override
	public boolean deleteAllCards(int themeId) {
		String query = String.format("DELETE FROM %s WHERE group_id=?", TABLE_NAME);
		
		try (PreparedStatement deleteStatement = dbConnection.prepareStatement(query)) {
			deleteStatement.setInt(1, themeId);
			return deleteStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
