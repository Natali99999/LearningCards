package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LearningCard {
	
	public static enum STATUS{
		NOT_TESTED, 
		CORRECT,
		WRONG;
	}
	
	private int id;

	StringProperty askProperty = new SimpleStringProperty();
	StringProperty askDescProperty = new SimpleStringProperty();
	StringProperty answerProperty = new SimpleStringProperty();
	
	public int getId() { return id;}
	public void setId(int id) { this.id = id;} 
	public String getAsk() {return askProperty.get();}
    public void setAsk(String text) {this.askProperty.set(text);}
    public StringProperty askProperty() {return askProperty;}
    public String getAskDesc() {return askDescProperty.get();}
    public void setAskDesc(String text) {this.askDescProperty.set(text);}
    public StringProperty askDescProperty() {return askDescProperty;}
    public String getAnswer() {return answerProperty.get();}
    public StringProperty answerProperty() {return answerProperty;}
    public void setAnswer(String text) {this.answerProperty.set(text);}
    
	// Intern 
	private STATUS status = STATUS.NOT_TESTED;
	private LearningTheme.LEVEL level = LearningTheme.LEVEL.LEVEL_1;
	
	public LearningCard(String ask, String answer, String askDesc) {
		this.setId(0);
		this.setAsk(ask);
		this.setAnswer(answer);
		this.setAskDesc(askDesc);
	}
	
	public LearningCard(int id, String ask, String answer, String askDesc) {
		this.setId(id);
		this.setAsk(ask);
		this.setAnswer(answer);
		this.setAskDesc(askDesc);
	}
	
	public LearningCard(String ask, String answer) {
		this.setId(0);
		this.setAsk(ask);
		this.setAnswer(answer);
		this.setAskDesc("");
	}
	
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}
	
	public LearningTheme.LEVEL getLevel() {
		return level;
	}
	public void setLevel(LearningTheme.LEVEL level) {
		this.level = level;
	}
	public void goToNextLevel() {
		
		int index = this.level.ordinal();
		if (index <  LearningTheme.LEVEL.values().length-1) {
			index ++;
		}
		
		setLevel(LearningTheme.LEVEL.values()[index]);
	}
	
	@Override
	public String toString() {
		return "LearningCard [ask=" + getAsk() +  ", status=" + status.name() + ", level=" + level.name() + "]";
	}
	public void goToPrevLevel() {
		
		int index = this.level.ordinal();
		if (index >  0) {
			index --;
		}
		
		if (index <  0) {
			index = 0;
		}
		
		setLevel(LearningTheme.LEVEL.values()[index]);
	}
}
