package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LearningCard {
	
	public static enum STATUS{
		NOT_TESTED, 
		CORRECT,
		WRONG;
	}
	
	public static enum LEVEL{
		LEVEL_1(),
		LEVEL_2(),
		LEVEL_3(),
		LEVEL_4();
	}
	
	StringProperty askProperty = new SimpleStringProperty();
	StringProperty askDescProperty = new SimpleStringProperty();
	StringProperty answerProperty = new SimpleStringProperty();
	
	public String getAsk() {return askProperty.get();}
    public void setAsk(String text) {this.askProperty.set(text);}
    public StringProperty askProperty() {return askProperty;}
    public String getAskDesc() {return askDescProperty.get();}
    public void setAskDesc(String text) {this.askDescProperty.set(text);}
    public StringProperty askDescProperty() {return askDescProperty;}
    public String getAnswer() {return answerProperty.get();}
    public void setAnswer(String text) {this.answerProperty.set(text);}
    public StringProperty answerProperty() {return answerProperty;}
	
	// Intern 
	private STATUS status = STATUS.NOT_TESTED;
	private LEVEL level = LEVEL.LEVEL_1;
	
	public LearningCard(String ask, String answer, String askDesc) {
		this.setAsk(ask);
		this.setAnswer(answer);
		this.setAskDesc(askDesc);
	}
	
	public LearningCard(String ask, String answer) {
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
	
	public LEVEL getLevel() {
		return level;
	}
	public void setLevel(LEVEL level) {
		this.level = level;
	}
	public void goToNextLevel() {
		
		int index = this.level.ordinal();
		if (index <  LEVEL.values().length-1) {
			index ++;
		}
		
		setLevel(LEVEL.values()[index]);
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
		
		setLevel(LEVEL.values()[index]);
	}
}
