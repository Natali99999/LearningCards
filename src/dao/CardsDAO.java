package dao;

import java.util.List;

import models.LearningCard;


public interface CardsDAO {
	/**
	 * 
	 * @return
	 */
	List<LearningCard> findAll();
    List<LearningCard> findAll(int themeId);
	
	/**
	 * 
	 * @param card
	 * @param themeId
	 * @return
	 */
	boolean save(LearningCard card, int themeId);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(int id);
	boolean deleteAllCards(int themeId);
}