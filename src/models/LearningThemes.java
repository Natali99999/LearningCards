package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LearningThemes {
	public static Map<String, LearningTheme> themeMap = new LinkedHashMap<>();
	private static String currentTheme;
	private static LearningTheme theme;
	
	public static String getCurrentTheme() {
		return currentTheme;
	}
	public static LearningTheme theme() {
		return LearningThemes.theme;
	}
	
	public static void setCurrentTheme(String currentTheme) {
		LearningThemes.currentTheme = currentTheme;
	}
	
	public static void initialize() {
		themeMap.put("Java Basics", new LearningTheme(LearningCards.itemsJavaBasics));
		themeMap.put("Working with Java Data Types", new LearningTheme(LearningCards.itemsWorkingWithJavaDataTypes));
		themeMap.put("Using Operators and Decision Constructs", new LearningTheme(LearningCards.usingOperatorsAndDecisionConstructs));
		themeMap.put("Creating and Using Arrays", new LearningTheme(LearningCards.creatingAndUsingArrays));
		themeMap.put("Using Loop Constructs", new LearningTheme(LearningCards.usingLoopConstructs));
		themeMap.put("Working with Methods and Encapsulation", new LearningTheme(LearningCards.workingWithMethodAndEncapsulation));
		themeMap.put("Working with Inheritance", new LearningTheme(LearningCards.workingWithInheritance));
		themeMap.put("Handling Exceptions", new LearningTheme(LearningCards.handlingExceptions));
		themeMap.put("Working with Selected Classes from Java API", new LearningTheme(LearningCards.workingWithSelectedClassesFromAPI));
	}
	
	/**
	 * changeTheme
	 * */
	public static void changeTheme(String currentTheme) {
		// Theme auswählen
		setCurrentTheme(currentTheme);
		// Themenkartenliste auswählen
		theme = themeMap.get(currentTheme);
		
		theme.updateLevels();
		theme.selectCurrentCard();
		theme.show();
	}
}
