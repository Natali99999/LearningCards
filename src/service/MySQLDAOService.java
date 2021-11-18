package service;

import java.sql.SQLException;

import java.util.List;

import dao.CardsMySQLDAO;
import dao.ThemesMySQLDAO;
import db.DBException;
import models.LearningCard;
import models.DefaultLearningCardsDefs;
import models.LearningTheme;

public final class MySQLDAOService {
	private static ThemesMySQLDAO themesDao;
	private static CardsMySQLDAO cardsDao;
	private static MySQLDAOService instance;

	private MySQLDAOService(ThemesMySQLDAO themesDao, CardsMySQLDAO cardsDao) {
		MySQLDAOService.themesDao = themesDao;
		MySQLDAOService.cardsDao = cardsDao;
	}

	public List<LearningTheme> findAll() throws DBException, SQLException {
			int count = themesDao.getCount();

			if (count == 0) { // Defaulttabellen laden, wenn keine vorhanden
				saveDefaults();
			}

			List<LearningTheme> list = themesDao.findAll();
			//System.out.println(list);
			for (LearningTheme theme : list) {
				List<LearningCard> cards = theme.getAllCards();
				cards = cardsDao.findAll(theme.getId());
				theme.setAllCards(cards);
				LearningThemeService.addThemeToMap(theme.getId(), theme.getTitle(), cards);
			}

			//System.out.println(list);
			return list;
	}

	public void saveDefaults() throws DBException, SQLException {
		saveDefaultTheme("Java Basics", DefaultLearningCardsDefs.itemsJavaBasics);
		saveDefaultTheme("Working with Java Data Types", DefaultLearningCardsDefs.itemsWorkingWithJavaDataTypes);
		saveDefaultTheme("Using Operators and Decision Constructs", DefaultLearningCardsDefs.usingOperatorsAndDecisionConstructs);
		saveDefaultTheme("Creating and Using Arrays", DefaultLearningCardsDefs.creatingAndUsingArrays);
		saveDefaultTheme("Using Loop Constructs", DefaultLearningCardsDefs.usingLoopConstructs);
		saveDefaultTheme("Working with Methods and Encapsulation", DefaultLearningCardsDefs.workingWithMethodAndEncapsulation);
		saveDefaultTheme("Working with Inheritance", DefaultLearningCardsDefs.workingWithInheritance);
		saveDefaultTheme("Handling Exceptions", DefaultLearningCardsDefs.handlingExceptions);
		saveDefaultTheme("Working with Selected Classes from Java API",
				DefaultLearningCardsDefs.workingWithSelectedClassesFromAPI);
		saveDefaultTheme("Practice questions", DefaultLearningCardsDefs.tests);
	}

	public void saveDefaultTheme(String themeTitle, List<LearningCard> list)  throws DBException, SQLException{
		int themeId = themesDao.save(themeTitle);
		if (themeId > 0) {
			cardsDao.save(themeId, list);
		}
		assert(themeId > 0);
	
		findAll();
	}

	public static MySQLDAOService getInstance() throws DBException  {
		if (instance == null) {
			themesDao = new ThemesMySQLDAO();
			cardsDao = new CardsMySQLDAO();
			instance = new MySQLDAOService(themesDao, cardsDao);
		}
	
		return instance;
	}

	public boolean save(LearningTheme theme) throws DBException, SQLException {
		boolean result = themesDao.save(theme);
		return result;
	}

	public boolean saveComplete(LearningTheme theme) throws DBException, SQLException {
		 if (themesDao.save(theme)) {
			return cardsDao.save(theme.getId(), theme.getAllCards());
		 }
		 
		 return false;
	}

	public boolean delete(int themeId) throws DBException, SQLException {
		cardsDao.deleteAllCards(themeId);
		return themesDao.delete(themeId);
	}

	public boolean saveCard(LearningCard card, int themeId) {
		return cardsDao.save(card, themeId);
	}

	public boolean deleteCard(int cardId) {
		return cardsDao.delete(cardId);
	}

}
