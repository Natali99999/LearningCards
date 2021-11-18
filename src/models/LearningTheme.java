package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author natal

 * 25.10.2021
 */
public class LearningTheme {
	/**
	 * 
	 * @author natal
	
	 * 25.10.2021
	 */
	public static enum LEVEL{
		LEVEL_1(true),
		LEVEL_2(true),
		LEVEL_3(true),
		LEVEL_4(true);
		
		LEVEL(boolean show){
			this.show = show;
			this.cards = new ArrayList<LearningCard>(); 
		}
		
		@Override
		public String toString() {
			return "LevelStatus [show=" + show + ", cards=" + cards + "]";
		}
		
		private boolean show = true;
		private List<LearningCard> cards;
		
		public boolean isShowing() {
			return show;
		}
		public void setShow(boolean show) {
			this.show = show;
		}
		public int cardCount() {
			return cards.size();
		}
		public List<LearningCard> getCards() {
			return cards;
		}
		/**
		 * 
		 * @param cards
		 */
		public void setCards(List<LearningCard> cards) {
			this.cards = cards;
		}
	}

	/**
	 * 
	 */
	StringProperty title = new SimpleStringProperty();

	public String getTitle() {return title.get();}
    public void setTitle(String title) {this.title.set(title);}
    public StringProperty titleProperty() {return title;}
    
	private int id;
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
	
	/**
	 * 
	 */
	public void initialize() {
		this.status = new Status();
	
		selectLearningCards();
		selectCurrentCard();
	}

	/**
	 * 
	 * @param id Themen-Id
	 * @param title Thementitel
	 * @param cards Lernkarten
	 */
	public LearningTheme(int id, String title, List<LearningCard> cards) {
		setId(id);
		setTitle(title);
		this.allCards = cards;
		this.status = new Status();
		
		initialize();
	}
	
	public LearningTheme(List<LearningCard> cards, String title) {
		setTitle(title);
		
		this.allCards = cards;
		initialize();
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
	 * get/set functions
	 * */
	public int getId() { return id;}
	public void setId(int id) { this.id = id;} 
	public List<LearningCard> getAllCards() {
		return allCards;
	}
	
	/**
	 * 
	 * @param cards
	 */
	public void setAllCards(List<LearningCard> cards) {
		 allCards = cards;
	}
	/**
	 *   
	 */
	@Override
	public String toString() {
		return "LearningTheme [title=" + title + ", id=" + id + ", status=" + status + ", allCards=" + allCards + "]";
	}
	/**
	 * 
	 * @return
	 */
	public int getCardCount() {
		return this.selectedCards.size();
	}
	/**
	 * 
	 * @return
	 */
	public int getCurrentIndex() {
		return this.currentIndex;
	}
	/**
	 * 
	 * @return
	 */
	public List<LearningCard> getCurrentLearningCards() {
		return this.selectedCards;
	}
	/**
	 * 
	 * @param cards
	 */
	public void setCurrentLearningCards(List<LearningCard> cards) {
		this.selectedCards = cards;
	}
	/**
	 * 
	 * @return
	 */
	public LearningCard getCurrentCard() {
		return this.currentCard;
	}
	/**
	 * 
	 * @param currentCard
	 */
	public void setCurrentCard(LearningCard currentCard) {
		this.currentCard = currentCard;
	}
	/**
	 * 
	 * @return
	 */
	public int getCorrectCardCount() {
		return status.correctCardCount;
	}
	/**
	 * 
	 * @param correctCardCount
	 */
	public void setCorrectCardCount(int correctCardCount) {
		status.correctCardCount = correctCardCount;
	}
	/**
	 * 
	 * @return
	 */
	public int getLearningCardsCount() {
		return status.learningCardsCount;
	}
	/**
	 * 
	 * @param learningCardsCount
	 */
	public void setLearningCardsCount(int learningCardsCount) {
		status.learningCardsCount = learningCardsCount;
	}
	/**
	 * 
	 * @return
	 */
	public int getWrongCardCount() {
		return status.wrongCardCount;
	}
	
	/**
	 * 
	 * @param wrongCount
	 */
	public void setWrongCardCount(int wrongCount) {
		status.wrongCardCount = wrongCount;
	}
	
	/**
	 * 
	 * @param frontSide Kartenseite: Frage/Antwort
	 */
	
	public void setFrontSide(boolean frontSide) {
		status.frontSide = frontSide;
	}
	
	/**
	 * 
	 * @return Kartenseite: Frage/Antwort
	 */
	public boolean isFrontSide() {
		return status.frontSide;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public int getRandomIndex(int i) {
	    if (i >= 0 && i < randomIndexes.size()) {
	    	return randomIndexes.get(i);
	    }
	    return -1;
	}
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	public boolean removeCard(LearningCard card) {
		/*if(selectedCards.size() == 0)
			return false;*/
		
		if (getAllCards().contains(card)) {
			
			if (selectedCards.contains(card)) {
				selectedCards.remove(card);
	 		}
				
			getAllCards().remove(card);
			
			return true;
 		}
		return false;
	}
	
	/**
	 * 
	 */
	public void selectCurrentCard() {
		if(selectedCards.size() == 0)
			return;
		
		if (currentIndex < 0 || currentIndex >= selectedCards.size())
			currentIndex = 0;
		
		currentRandomIndex = getRandomIndex(currentIndex);
		
		LearningCard card = selectedCards.get(currentRandomIndex);
		setCurrentCard(card);
	}
	
	/**
	 * 
	 * @param index
	 */
	public void selectCurrentCard(int index) {
		if(selectedCards.size() == 0)
			return;
		
		currentIndex = index;
		
		currentRandomIndex = getRandomIndex(currentIndex);
		setCurrentCard(selectedCards.get(currentRandomIndex));
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLearningCardText() {
		assert (currentCard != null);
		if (currentCard == null)
			return "not defined";
		
		return isFrontSide() 
				? currentCard.getAsk()
				: currentCard.getAnswer();
	}
	
	/**
	 * 
	 * @return LearningCard.STATUS
	 */
	public LearningCard.STATUS getLearningCardStatus() {
		assert (currentCard != null);
		if (currentCard == null) {
			return LearningCard.STATUS.NOT_TESTED;
		}
		
		return currentCard.getStatus();
	}
	
	/**
	 * 
	 * @return css style
	 */
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
	 * 
	 */
	public void changeCardSide() {
		setFrontSide(!status.frontSide) ;
	}
	
	/**
	 * 
	 */
	public void toNextLearningCard() {
		if (selectedCards.size() > 0) {
			currentIndex ++;
			selectCurrentCard();
		}
	}
	
	/**
	 * 
	 * @param status
	 */
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
	 * 
	 */
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
		if (learningCard < 0) {
			learningCard= 0;
		}
		
		setLearningCardsCount(learningCard);
		// Richtigbeantworte-Kartenanzahl auf '0' setzen
		setCorrectCardCount(0);
		// Falschbeantwortete-Kartenanzahl auf '0' setzen
		setWrongCardCount(0);
	}
	
	/**
	 * 
	 * @return
	 */
	public double getResultInProzent() {
		double result = 0.0;
		
		if (selectedCards.size() != 0)
			result = (double)getCorrectCardCount() * 100.0 / (double)selectedCards.size();
		
	//	System.out.println("Erreicht " + result + "%");
		
		return result;
	}
	
	/**
	 * 
	 * @param level Level
	 * @return
	 */
	public List<LearningCard> getCardsOfLevel(LearningTheme.LEVEL level){
		
		/*List<LearningCard> cards = new ArrayList<LearningCard>();
		
		for(LearningCard card: this.selectedCards) {
			if (card.getLevel() == level) {
				cards.add(card);
			}
		}*/
		
		List<LearningCard> cards = selectedCards.stream()
				.filter(card -> card.getLevel() == level)
				.collect(Collectors.toList());
		
		return cards;
	}
	
	/**
	 * 
	 * @param size
	 */
	public void BuildRandomIndexes(int size){
		/*randomIndexes = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			randomIndexes.add(i);
		}*/
		
		randomIndexes = IntStream.range(0, size).boxed().collect(Collectors.toList());
		Collections.shuffle(randomIndexes);
	}
	
	/**
	 * 
	 */
	public void selectLearningCards(){
		
		selectedCards = getCardsOfSelectedLevels();
		
		BuildRandomIndexes(selectedCards.size());
			
		currentIndex = 0;
		currentRandomIndex = getRandomIndex(currentIndex);
	}
	
	/**
	 * 
	 * @return Kartenzuordnung zum Level
	 */
	public Map<LearningTheme.LEVEL, List<LearningCard>> getLevelCardsMap(){
		Map<LearningTheme.LEVEL, List<LearningCard>> mapLevelCards = new LinkedHashMap<>();
		
		for(LearningCard card: allCards) {
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
	 * 
	 * @return
	 */
	public List<LearningCard> getCardsOfSelectedLevels(){
		List<LearningCard> cards = new ArrayList<>();
		
		Map<LearningTheme.LEVEL, List<LearningCard>> map = getLevelCardsMap();
		
		for (LearningTheme.LEVEL level : LearningTheme.LEVEL.values()) {
			
			if (map.containsKey(level)) {
				level.setCards(map.get(level));
				if (level.isShowing()) {
					cards.addAll(level.getCards());
				}
			}
			else {
				level.getCards().clear();
			}
		}
		return cards;
	}
}
