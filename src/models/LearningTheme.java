package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LearningTheme {
	StringProperty title = new SimpleStringProperty();

	public String getTitle() {return title.get();}
    public void setTitle(String title) {this.title.set(title);}
    public StringProperty titleProperty() {return title;}
    
	private LevelStatus[] levels;
	// Status
	Status status;
	
	// LearningCards
	private List<LearningCard> allCards;
	private List<LearningCard> selectedCards;
	private List<Integer> randomIndexes;

	// currentCard index
	private int currentIndex = 0;
	private int currentRandomIndex = 0;
	// currentCard
	private LearningCard currentCard;
	
  
	
	public LearningTheme(List<LearningCard> cards) {
		this.allCards = cards;
		this.status = new Status();
		
		levels =  new LevelStatus[LearningCard.LEVEL.values().length];
		for (LearningCard.LEVEL level : LearningCard.LEVEL.values()) {
			levels[level.ordinal()] = new LevelStatus(); 
		}
		
		selectLearningCards();
		selectCurrentCard();
	}
	
	/**
	 * SubClass: Status
	 * @author Natali
	 *
	 */
	private class Status {
		
		private int correctCardCount = 0;
		private int learningCardsCount = 0;
		private int wrongCardCount = 0;
		private boolean frontSide = true;
		
		@Override
		public String toString() {
			return "Status [correctCardCount=" + correctCardCount + ", learningCardsCount=" + learningCardsCount
					+ ", wrongCardCount=" + wrongCardCount + ", frontSide=" + frontSide + "]";
		}
	}
	
	/**
	 * SubClass: LevelStatus
	 * @author Natali
	 *
	 */
	public class LevelStatus {
		
		private List<LearningCard> cards;
		private boolean show;
		
		public LevelStatus() {
			show = true;
			cards = new ArrayList<LearningCard>(); 
		}
		
		@Override
		public String toString() {
			return "LevelStatus [show=" + show + ", cards=" + cards + "]";
		}

		public boolean isShowing() {
			return show;
		}
		
		public void setShow(boolean show) {
			this.show = show;
		}
		
		public int count() {
			return cards.size();
		}
	}
		
	
	/**
	 * get/set functions
	 * */
	
	public List<LearningCard> getAllCards() {return allCards;}
	  
	public LevelStatus getLevelStatus(LearningCard.LEVEL level) {
		
		LevelStatus levelStatus = this.levels[level.ordinal()];
		//System.out.println("LevelStatus " + level.name() + " " + levelStatus.toString());
		return levelStatus;
	}

	public int getCardCount() {
		return this.selectedCards.size();
	}
	public int getCurrentIndex() {
		return this.currentIndex;
	}
	public List<LearningCard> getCurrentLearningCards() {
		return this.selectedCards;
	}
	public void setCurrentLearningCards(List<LearningCard> cards) {
		this.selectedCards = cards;
	}
	public LearningCard getCurrentCard() {
		return this.currentCard;
	}
	public void setCurrentCard(LearningCard currentCard) {
		this.currentCard = currentCard;
	}
	public int getCorrectCardCount() {
		return status.correctCardCount;
	}
	public void setCorrectCardCount(int correctCardCount) {
		status.correctCardCount = correctCardCount;
	}
	public int getLearningCardsCount() {
		return status.learningCardsCount;
	}
	public void setLearningCardsCount(int learningCardsCount) {
		status.learningCardsCount = learningCardsCount;
	}
	public int getWrongCardCount() {
		return status.wrongCardCount;
	}
	public void setWrongCardCount(int wrongCount) {
		status.wrongCardCount = wrongCount;
	}
	public void setFrontSide(boolean frontSide) {
		status.frontSide = frontSide;
	}
	public boolean isFrontSide() {
		return status.frontSide;
	}
	
	/**
	 * getRandomIndex
	 * */
	public int getRandomIndex(int i) {
	    if (i >= 0 && i < randomIndexes.size()) {
	    	return randomIndexes.get(i);
	    }
	    return -1;
	}
	
	public boolean removeCard(LearningCard card) {
		if(selectedCards.size() == 0)
			return false;
		
		if (getAllCards().contains(card)) {
			
			if (selectedCards.contains(card)) {
				selectedCards.remove(card);
	 		}
				
			getAllCards().remove(card);
			
			return true;
 		}
		return false;
	}
	
	public void selectCurrentCard() {
		if(selectedCards.size() == 0)
			return;
		
		if (currentIndex < 0 || currentIndex >= selectedCards.size())
			currentIndex = 0;
		
		currentRandomIndex = getRandomIndex(currentIndex);
		
		LearningCard card = selectedCards.get(currentRandomIndex);
		setCurrentCard(card);
	}
	
	public void selectCurrentCard(int index) {
		if(selectedCards.size() == 0)
			return;
		
		currentIndex = index;
		
		currentRandomIndex = getRandomIndex(currentIndex);
		setCurrentCard(selectedCards.get(currentRandomIndex));
	}
	
	/**
	 * LearningCard: get text
	 * */
	public String getLearningCardText() {
		assert (currentCard != null);
		if (currentCard == null)
			return "not defined";
		
		return isFrontSide() 
				? currentCard.getAsk()
				: currentCard.getAnswer();
	}
	
	/**
	 * LearningCard: get status
	 * */
	public LearningCard.STATUS getLearningCardStatus() {
		assert (currentCard != null);
		if (currentCard == null)
			return LearningCard.STATUS.NOT_TESTED;
		
		return currentCard.getStatus();
	}
	
	/**
	 * LearningCard: get style
	 * */
	public String getLearningCardStyle() {
	
		final String ASK_DEFAULT_STYLE = "-fx-border-color: rgba(130, 130, 130, 0.5);-fx-border-width: 10px";
		final String ASK_STYLE = "-fx-border-color: #4682b4;-fx-border-width: 10px";
		final String ANSWER_STYLE = "-fx-border-color: #ffd700;-fx-border-width: 10px";
		
		assert (currentCard != null);
		if (currentCard == null)
			return ASK_DEFAULT_STYLE;
	
		if (isFrontSide()) {
			return ASK_STYLE;
		}
		else {
			return ANSWER_STYLE;
		}
	}
	
	/**
	 * changeCardSide
	 * */
	public void changeCardSide() {
		setFrontSide(!status.frontSide) ;
	}
	
	/**
	 * toNextLearningCard
	 * */
	public void toNextLearningCard() {
		if (selectedCards.size() > 0) {
			currentIndex ++;
			selectCurrentCard();
		}
	}
	
	/**
	 * updateLearningCardStatus
	 * */
	public void updateLearningCardStatus(LearningCard.STATUS status) {
		
		assert (currentCard != null);
		if (currentCard == null)
			return;
		
		if (status == LearningCard.STATUS.CORRECT) {
			// Karten zum nächsten Level verschieben
			currentCard.goToNextLevel();	
			
			this.status.learningCardsCount--;
			this.status.correctCardCount ++;
		}
		else if (status == LearningCard.STATUS.WRONG) {
			//currentCard.goToPrevLevel();
			
			this.status.learningCardsCount--;
			this.status.wrongCardCount ++;
		}
	}
	
	/**
	 * show
	 * */
	public void init() {
	
		// Vorderseite anzeigen
		setFrontSide(isFrontSide());
		
		// Karten auswählen
		selectLearningCards();

		// die erste Karte bestimmen
		currentIndex = 0;
		selectCurrentCard();
		
		// Nichtgelernte-Kartenanzahl auf 'max' setzen
		int learningCard = selectedCards.size();
		if(learningCard < 0) {
			learningCard= 0;
		}
		
		setLearningCardsCount(learningCard);
		// Richtigbeantworte-Kartenanzahl auf '0' setzen
		setCorrectCardCount(0);
		// Falschbeantwortete-Kartenanzahl auf '0' setzen
		setWrongCardCount(0);
	}
	
	/**
	 * getResultInProzent
	 * */
	public double getResultInProzent() {
		double result = 0.0;
		
		if (selectedCards.size() != 0)
			result = (double)getCorrectCardCount() * 100.0 / (double)selectedCards.size();
		
	//	System.out.println("Erreicht " + result + "%");
		return result;
	}
	
	/**
	 * getCardsOfLevel
	 * */
	public List<LearningCard> getCardsOfLevel(LearningCard.LEVEL level){
		List<LearningCard> cards = new ArrayList<LearningCard>();
		
		for(LearningCard card: this.selectedCards) {
			if (card.getLevel() == level) {
				cards.add(card);
			}
		}
		
		return cards;
	}
	
	/**
	 * selectLearningCards
	 * */
	public void BuildRandomIndexes(int size){
		randomIndexes = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			randomIndexes.add(i);
		}
		Collections.shuffle(randomIndexes);
		
		//System.out.println(randomIndexes);
	}
	
	/**
	 * selectLearningCards
	 * */
	public void selectLearningCards(){
		
		selectedCards = getCardsOfSelectedLevels();
		
		BuildRandomIndexes(selectedCards.size());
			
		currentIndex = 0;
		currentRandomIndex = getRandomIndex(currentIndex);
	}
	
	/**
	 * getLevelCardsMap
	 * */
	public Map<LearningCard.LEVEL, List<LearningCard>> getLevelCardsMap(){
		Map<LearningCard.LEVEL, List<LearningCard>> mapLevelCards = new LinkedHashMap<>();
		
		for(LearningCard card: this.allCards) {
			if (mapLevelCards.containsKey(card.getLevel())) {
				// key vorhanden, neue Karte einfügen
				mapLevelCards.get(card.getLevel()).add(card);
			}
			else {
				// key erstellen
				List<LearningCard> arr = new ArrayList<LearningCard>();
				arr.add(card);
				mapLevelCards.put(card.getLevel(), arr);
			}
		}
		
		return mapLevelCards;
	}
	
	/**
	 * getCardsOfSelectedLevels
	 * */
	public List<LearningCard> getCardsOfSelectedLevels(){
		List<LearningCard> cards = new ArrayList<>();
		
		Map<LearningCard.LEVEL, List<LearningCard>> map = getLevelCardsMap();
		
		for (LearningCard.LEVEL level : LearningCard.LEVEL.values()) {
			
			int index = level.ordinal();
			
			if (map.containsKey(level)) {
				levels[index].cards = map.get(level);
				if (levels[index].show) {
					cards.addAll(levels[index].cards);
				}
			}
			else {
				levels[index].cards.clear();
			}
			
		}
		return cards;
	}
}
