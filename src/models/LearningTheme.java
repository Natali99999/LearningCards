package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LearningTheme {
	
	public LearningTheme(List<LearningCard> cards) {
		this.allCards = cards;
		//System.out.println(cards.toString());
		this.status = new Status();
		
		levels =  new LevelStatus[LearningCard.LEVEL.values().length];
		for (LearningCard.LEVEL level : LearningCard.LEVEL.values()) {
			levels[level.ordinal()] = new LevelStatus(); 
		}
	
		this.selectedCards = getCardsOfSelectedLevels();
		//System.out.println("selectedCards " + selectedCards.toString());
		
		this.currentIndex = getStartIndex();
		if (this.currentIndex >= 0) {
			this.currentCard = cards.get(currentIndex);
		}
	}
	
	private class Status {
		
		int correctCardCount = 0;
		int learningCardsCount = 0;
		int wrongCardCount = 0;
		boolean frontSide = true;
		
		@Override
		public String toString() {
			return "Status [correctCardCount=" + correctCardCount + ", learningCardsCount=" + learningCardsCount
					+ ", wrongCardCount=" + wrongCardCount + ", frontSide=" + frontSide + "]";
		}
	}
	
	public class LevelStatus {
		public LevelStatus() {
			show = true;
			cards = new ArrayList<LearningCard>(); 
		}
		
		@Override
		public String toString() {
			return "LevelStatus [show=" + show + ", cards=" + cards + "]";
		}

		private boolean show;
		public boolean isShowing() {
			return show;
		}
		
		public void setShow(boolean show) {
			this.show = show;
		}
		
		public int count() {
			return cards.size();
		}
		List<LearningCard> cards;
	}
	
//	Map<LearningCard.LEVEL, List<LearningCard>> mapLevelToCards;
	
	private LevelStatus[] levels;
	// Status
	Status status;
	// LearningCards
	private List<LearningCard> allCards;
	private List<LearningCard> selectedCards;
	
	// currentCard index
	private int currentIndex = 0;
	// currentCard
	private LearningCard currentCard;
	
	/**
	 * get/set functions
	 * */

	public LevelStatus getLevelStatus(LearningCard.LEVEL level) {
		
		LevelStatus levelStatus = this.levels[level.ordinal()];
		System.out.println("LevelStatus " + level.name() + " " + levelStatus.toString());
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
	 * */
	public boolean checkIndex(int index) {
		return index >= 0 && index < selectedCards.size();
	}
	
	public int getStartIndex() {
		if (checkIndex(currentIndex)) {
			return currentIndex;
		}
		
		return selectedCards.size() > 0 ? 0 : -1;
	}
	
	public void selectCurrentCard() {
		currentIndex = getStartIndex();
		
		if (currentIndex >= 0) {
			setCurrentCard(selectedCards.get(currentIndex));
		}
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
		// todo
		final String ASK_DEFAULT_STYLE = "-fx-border-color: rgba(130, 130, 130, 0.5);-fx-border-width: 10px";
		final String ask_wrong_style = "-fx-border-color: rgba(0, 255, 0, 0.5);-fx-border-width: 10px";
		final String ask_correct_style = "-fx-border-color: rgba(0, 255, 0, 0.5);-fx-border-width: 10px";
		final String answer_style = "-fx-border-color: rgba(255, 255, 0, 0.5);-fx-border-width: 10px";
		
		assert (currentCard != null);
		if (currentCard == null)
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
			if (currentIndex < 0 || currentIndex >= selectedCards.size())
				currentIndex = 0;
			else if (currentIndex < selectedCards.size()) {
				currentIndex ++;
			}
			else if (currentIndex >= selectedCards.size()) {
				currentIndex = 0;
			}
			
			System.out.println("currentIndex " + currentIndex);
			selectCurrentCard();
		}
		
	}
	
	/**
	 * toNextLearningCard
	 * */
	public void showLearningCard(int ind) {
		if (ind < 0)
			ind = 0;
		else if (ind > selectedCards.size()) {
			ind = 0;
		}
		
		currentIndex = ind;
		
		selectCurrentCard();
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
			
			/*if (currentCard.getStatus() == LearningCard.STATUS.NOT_TESTED) {
				this.status.learningCardsCount--;
			}
			else if (currentCard.getStatus() == LearningCard.STATUS.WRONG) {
				this.status.wrongCardCount--;
			}
			else if (currentCard.getStatus() == LearningCard.STATUS.CORRECT) {
				// überspringen 
				return;
			}
			
			currentCard.setStatus(LearningCard.STATUS.CORRECT);*/
			this.status.correctCardCount ++;
		}
		else if (status == LearningCard.STATUS.WRONG) {
			//currentCard.goToPrevLevel();
			
			this.status.learningCardsCount--;
			
			/*if (currentCard.getStatus() == LearningCard.STATUS.NOT_TESTED) {
				this.status.learningCardsCount--;
			}
			else if (currentCard.getStatus() == LearningCard.STATUS.CORRECT) {
				this.status.correctCardCount--;
			}
			else if (currentCard.getStatus() == LearningCard.STATUS.WRONG) {
				// überspringen 
				return;
			}
			
			currentCard.setStatus(LearningCard.STATUS.WRONG);*/
			this.status.wrongCardCount ++;
			
		}
	}
	
	/**
	 * show
	 * */
	public void show() {
	
		//index = cards.size() > 0 ? 0 : -1;
		
		// Karte auswählen
	//	toNextLearningCard();
		
		// Vorderseite abzeigen
		setFrontSide(isFrontSide());
		
		// Nichtgelernte-Kartenanzahl auf 'max' setzen
		int learningCard = selectedCards.size()/*-getCorrectCardCount()-getWrongCardCount()*/;
		if(learningCard < 0) {
			learningCard= 0;
		}
		setLearningCardsCount(learningCard);
		// Richtigbeantworte-Kartenanzahl auf '0' setzen
		setCorrectCardCount(0/*getCorrectCardCount()*/);
		// Falschbeantwortete-Kartenanzahl auf '0' setzen
		setWrongCardCount(0/*getWrongCardCount()*/);
	}
	
	/**
	 * getResultInProzent
	 * */
	public double getResultInProzent() {
	
		double result = (double)getCorrectCardCount() * 100.0 / (double)selectedCards.size();
		System.out.println("Erreicht " + result + "%");
		return result;
		
	}
	
	
	public List<LearningCard> getCardsOfLevel(LearningCard.LEVEL level){
		List<LearningCard> cards = new ArrayList<LearningCard>();
		
		for(LearningCard card: this.selectedCards) {
			if (card.getLevel() == level) {
				cards.add(card);
			}
		}
		
		return cards;
		
	}
	
	public void updateLevels(){
		
		selectedCards = getCardsOfSelectedLevels();
			
	}
	
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
	
	public List<LearningCard> getCardsOfSelectedLevels(){
		List<LearningCard> cards = new ArrayList<>();
		
		Map<LearningCard.LEVEL, List<LearningCard>> map = getLevelCardsMap();
		//System.out.println("Map<LearningCard.LEVEL, List<LearningCard>> mapSize = " + map.size());
		
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
		
		//System.out.println("selectedCards " + cards.toString());
		return cards;
	}
	
	 
}
