package service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.LearningCard;
import models.LearningTheme;
/**
 * 
 * @author natal

 * 25.10.2021
 */

public class LearningThemeService {
	private static Map<Integer, LearningTheme> mapThemes = new LinkedHashMap<>();

	private static int currentThemeId;
	private static LearningTheme theme;
	private static int selectedItem = -1; // für View
	
	public static Integer getCurrentThemeId() {
		return currentThemeId;
	}
	public static void setCurrentThemeId(Integer currentThemeId) {
		LearningThemeService.currentThemeId = currentThemeId;
	}
	
	public static String getCurrentTheme() {
		return theme().getTitle();
	}
	
	public static int getSelectedItem() {
		return selectedItem;
	}
	public static LearningTheme theme() {
		if(LearningThemeService.theme == null){
			assert (false);
		}
		return LearningThemeService.theme;
	}

	public static void addThemeToMap(Integer id, String title, List<LearningCard> cardList) {
		mapThemes.put(id, new LearningTheme(cardList, title));
	}
	
	/**
	 * changeTheme
	 * */
	public static boolean changeTheme(Integer currentThemeId, int selectedItem) {
		if (LearningThemeService.selectedItem == selectedItem)
			return false;
		
		LearningThemeService.selectedItem = selectedItem;
	
		// Theme auswählen
		setCurrentThemeId(currentThemeId);
		
		// Themenkartenliste auswählen
		theme = mapThemes.get(currentThemeId);
		if (theme != null) {
			theme.init();
			return true;
		}
		
		System.out.printf("\nLearningsThemes.changeTheme(): theme id %d not found, sel item %d\n", currentThemeId, selectedItem);
		assert (false);
		return false;
	}

}
