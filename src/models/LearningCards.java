package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.LearningCard.STATUS;

public class LearningCards {

	public static List<LearningCard> itemsJavaBasics = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("What components can a class body contain?",
	    			"\n* fields\n* methods\n* constructors\n* initializer blocks\n* nested datatypes"),
		    		new LearningCard("What is the scope of a class variable, a.k.a. static field?",
		        			"A static field is available to any object of the class it is defined in."),
		    		
		    		new LearningCard("Ask 1.3?", "Answer 1.3"),
		    		new LearningCard("Ask 1.4", "Answer 1.4"),
		    		new LearningCard("Ask 1.5?", "Answer 1.5"),
		    		new LearningCard("Ask 1.6?", "Answer 1.6"),
		    		new LearningCard("Ask 1.7", "Answer 1.7"),
		    		new LearningCard("Ask 1.8?", "Answer 1.8"),
		    		new LearningCard("Ask 1.9?", "Answer 1.9"),
		    		new LearningCard("Ask 1.10", "Answer 1.10")
			));

	public static List<LearningCard> itemsWorkingWithJavaDataTypes = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("\"What are the eight primitive types in Java?",
	    			"byte, short, int, long, float, double, char, and boolean."),
		    		new LearningCard("What’s the name of the process when the compiler\\r\\nautomatically converts an int to an Integer?",
		        			"Autoboxing."),
		    		new LearningCard("Ask 2.3?", "Answer 2.3"),
		    		new LearningCard("Ask 2.4", "Answer 2.4"),
		    		new LearningCard("Ask 2.5?", "Answer 2.5"),
		    		new LearningCard("Ask 2.6?", "Answer 2.6"),
		    		new LearningCard("Ask 2.7?", "Answer 2.7"),
		    		new LearningCard("Ask 2.8?", "Answer 2.8"),
		    		new LearningCard("Ask 2.9?", "Answer 2.9"),
		    		new LearningCard("Ask 2.10", "Answer 2.10")
		    		
			));
	
	public static List<LearningCard> usingOperatorsAndDecisionConstructs = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 3.1?", "Answer 3.1"),
		    		new LearningCard("Ask 3.2", "Answer 3.2"),
		    		new LearningCard("Ask 3.3?", "Answer 3.3"),
		    		new LearningCard("Ask 3.4", "Answer 3.4"),
		    		new LearningCard("Ask 3.5?", "Answer 3.5"),
		    		new LearningCard("Ask 3.6?", "Answer 3.6"),
		    		new LearningCard("Ask 3.7?", "Answer 3.7"),
		    		new LearningCard("Ask 3.8?", "Answer 3.8"),
		    		new LearningCard("Ask 3.9?", "Answer 3.9"),
		    		new LearningCard("Ask 3.10", "Answer 3.10"),
		    		new LearningCard("Ask 3.1?", "Answer 3.11"),
		    		new LearningCard("Ask 3.2", "Answer 3.12"),
		    		new LearningCard("Ask 3.3?", "Answer 3.13"),
		    		new LearningCard("Ask 3.4", "Answer 3.14"),
		    		new LearningCard("Ask 3.5?", "Answer 3.15"),
		    		new LearningCard("Ask 3.6?", "Answer 3.16"),
		    		new LearningCard("Ask 3.7?", "Answer 3.17"),
		    		new LearningCard("Ask 3.8?", "Answer 3.18"),
		    		new LearningCard("Ask 3.9?", "Answer 3.19"),
		    		new LearningCard("Ask 3.10", "Answer 3.20")
			));
	
	public static List<LearningCard> creatingAndUsingArrays = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 4.1?", "Answer 4.1"),
		    		new LearningCard("Ask 4.2", "Answer 4.2"),
		    		new LearningCard("Ask 4.3?", "Answer 4.3"),
		    		new LearningCard("Ask 4.4", "Answer 4.4"),
		    		new LearningCard("Ask 4.5?", "Answer 4.5"),
		    		new LearningCard("Ask 4.6?", "Answer 4.6"),
		    		new LearningCard("Ask 4.7?", "Answer 4.7"),
		    		new LearningCard("Ask 4.8?", "Answer 4.8"),
		    		new LearningCard("Ask 4.9?", "Answer 4.9"),
		    		new LearningCard("Ask 4.10", "Answer 4.10")
			));
	
	public static List<LearningCard> usingLoopConstructs = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 5.1?", "Answer 5.1"),
		    		new LearningCard("Ask 5.2", "Answer 5.2"),
		    		new LearningCard("Ask 5.3?", "Answer 5.3"),
		    		new LearningCard("Ask 5.4", "Answer 5.4"),
		    		new LearningCard("Ask 5.5?", "Answer 5.5"),
		    		new LearningCard("Ask 5.6?", "Answer 5.6"),
		    		new LearningCard("Ask 5.7?", "Answer 5.7"),
		    		new LearningCard("Ask 5.8?", "Answer 5.8"),
		    		new LearningCard("Ask 5.9?", "Answer 5.9"),
		    		new LearningCard("Ask 5.10", "Answer 5.10")
			));
	
	public static List<LearningCard> workingWithMethodAndEncapsulation = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 6.1?", "Answer 6.1"),
		    		new LearningCard("Ask 6.2", "Answer 6.2"),
		    		new LearningCard("Ask 6.3?", "Answer 6.3"),
		    		new LearningCard("Ask 6.4", "Answer 6.4"),
		    		new LearningCard("Ask 6.5?", "Answer 6.5"),
		    		new LearningCard("Ask 6.6?", "Answer 6.6"),
		    		new LearningCard("Ask 6.7?", "Answer 6.7"),
		    		new LearningCard("Ask 6.8?", "Answer 6.8"),
		    		new LearningCard("Ask 6.9?", "Answer 6.9"),
		    		new LearningCard("Ask 6.10", "Answer 6.10")
			));
	
	public static List<LearningCard> workingWithInheritance = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 7.1?", "Answer 7.1"),
		    		new LearningCard("Ask 7.2", "Answer 7.2"),
		    		new LearningCard("Ask 7.3?", "Answer 7.3"),
		    		new LearningCard("Ask 7.4", "Answer 7.4"),
		    		new LearningCard("Ask 7.5?", "Answer 7.5"),
		    		new LearningCard("Ask 7.6?", "Answer 7.6"),
		    		new LearningCard("Ask 7.7?", "Answer 7.7"),
		    		new LearningCard("Ask 7.8?", "Answer 7.8"),
		    		new LearningCard("Ask 7.9?", "Answer 7.9"),
		    		new LearningCard("Ask 7.10", "Answer 7.10")
			));
	
	public static List<LearningCard> handlingExceptions = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 8.1?", "Answer 8.1"),
		    		new LearningCard("Ask 8.2", "Answer 8.2"),
		    		new LearningCard("Ask 8.3?", "Answer 8.3"),
		    		new LearningCard("Ask 8.4", "Answer 8.4"),
		    		new LearningCard("Ask 8.5?", "Answer 8.5"),
		    		new LearningCard("Ask 8.6?", "Answer 8.6"),
		    		new LearningCard("Ask 8.7?", "Answer 8.7"),
		    		new LearningCard("Ask 8.8?", "Answer 8.8"),
		    		new LearningCard("Ask 8.9?", "Answer 8.9"),
		    		new LearningCard("Ask 8.10", "Answer 8.10")
			));
	
	public static List<LearningCard> workingWithSelectedClassesFromAPI = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 9.1?", "Answer 9.1"),
		    		new LearningCard("Ask 9.2", "Answer 9.2"),
		    		new LearningCard("Ask 9.3?", "Answer 9.3"),
		    		new LearningCard("Ask 9.4", "Answer 9.4"),
		    		new LearningCard("Ask 9.5?", "Answer 9.5"),
		    		new LearningCard("Ask 9.6?", "Answer 9.6"),
		    		new LearningCard("Ask 9.7?", "Answer 9.7"),
		    		new LearningCard("Ask 9.8?", "Answer 9.8"),
		    		new LearningCard("Ask 9.9?", "Answer 9.9"),
		    		new LearningCard("Ask 9.10", "Answer 9.10")
			));


	/*public static int index = 0;
	private static int correctCardCount = 0;
	private static int learningCardsCount = 0;
	private static int wrongCardCount = 0;
	private static boolean frontSide = true;
	private static List<LearningCard> currentLearningCards;
	private static LearningCard currentLearningCard;
	

	public static void SetCurrentLearningCards(List<LearningCard> learningCards) {
		currentLearningCards = learningCards;
	}
	public static int getCorrectCardCount() {
		return correctCardCount;
	}
	public static void setCorrectCardCount(int correctCardCount) {
		LearningCards.correctCardCount = correctCardCount;
	}
	public static int getLearningCardsCount() {
		return learningCardsCount;
	}
	public static void setLearningCardsCount(int learningCardsCount) {
		LearningCards.learningCardsCount = learningCardsCount;
	}
	public static int getWrongCardCount() {
		return wrongCardCount;
	}
	public static void setWrongCardCount(int wrongCount) {
		LearningCards.wrongCardCount = wrongCount;
	}
	public static void setFrontSide(boolean frontSide) {
		LearningCards.frontSide = frontSide;
	}
	public static boolean isFrontSide() {
		return LearningCards.frontSide;
	}*/
	
	/**
	 * updateLearningCardStatus
	 * */
	/*public static void updateLearningCardStatus(LearningCard.STATUS status) {
		
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return;
		
		if (status == LearningCard.STATUS.CORRECT) {
			// Karten zum nächsten Level verschieben
			currentLearningCard.goToNextLevel();	
			currentLearningCard.setStatus(LearningCard.STATUS.CORRECT);
			correctCardCount ++;
			learningCardsCount--;
			
		}
		else if (status == LearningCard.STATUS.WRONG) {
			currentLearningCard.setStatus(LearningCard.STATUS.WRONG);
			wrongCardCount ++;
			learningCardsCount--;
		}
	}*/
	
	/**
	 * toNextLearningCard
	 * */
	/*public static void toNextLearningCard() {
		if (index < 0)
			index = 0;
		else if (index < currentLearningCards.size()-1) {
			index ++;
		}
		
		currentLearningCard = LearningCards.currentLearningCards.get(LearningCards.index);
	}*/
	
	/**
	 * changeTheme
	 * */
	/*public static void initialize() {
	
		index = currentLearningCards.size() > 0 ? 0 : -1;
		
		// Karte auswählen
		toNextLearningCard();
		// Vorderseite abzeigen
		setFrontSide(true);
		
		// Nichtgelernte-Kartenanzahl auf 'max' setzen
		setLearningCardsCount(currentLearningCards.size());
		// Richtigbeantworte-Kartenanzahl auf '0' setzen
		setCorrectCardCount(0);
		// Falschbeantwortete-Kartenanzahl auf '0' setzen
		setWrongCardCount(0);
	}*/
	
	/**
	 * changeCardSide
	 * */
	/*public static void changeCardSide() {
		LearningCards.frontSide = !LearningCards.frontSide;
	}*/
	
	/**
	 * LearningCard: get text
	 * */
	/*public static String getLearningCardText() {
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return "not defined";
		
		return isFrontSide() 
				? currentLearningCard.getAsk()
				: currentLearningCard.getAnswer();
	}*/
	
	/**
	 * LearningCard: get status
	 * */
	/*public static LearningCard.STATUS getLearningCardStatus() {
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return LearningCard.STATUS.NOT_TESTED;
		
		return currentLearningCard.getStatus();
	}*/
	
	/**
	 * LearningCard: get style
	 * */
	/*public static String getLearningCardStyle() {
		// todo
		final String ASK_DEFAULT_STYLE = "-fx-border-color: rgba(130, 130, 130, 0.5);-fx-border-width: 10px";
		final String ask_wrong_style = "-fx-border-color: rgba(0, 255, 0, 0.5);-fx-border-width: 10px";
		final String ask_correct_style = "-fx-border-color: rgba(0, 255, 0, 0.5);-fx-border-width: 10px";
		final String answer_style = "-fx-border-color: rgba(255, 255, 0, 0.5);-fx-border-width: 10px";
		
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return ASK_DEFAULT_STYLE;
	
		if (isFrontSide()) {
		
			if(getLearningCardStatus() == LearningCard.STATUS.WRONG) {
				return ask_wrong_style;
			}
			else if (getLearningCardStatus() == LearningCard.STATUS.CORRECT) {
				return ask_correct_style;
			}
			else {
				return ASK_DEFAULT_STYLE;
			}
		}
		else {
			return answer_style;
		}
	}*/
	
}
