package models;

public class LearningCard {
	
	public static enum STATUS{
		NOT_TESTED, 
		CORRECT,
		WRONG;
	}
	
	public static enum LEVEL{
		LEVEL_1("Level 1"),
		LEVEL_2("Level 2"),
		LEVEL_3("Level 3"),
		LEVEL_4("Level 4");
		
		private final String name;
		private LEVEL(String name) {
			this.name = name;
		}
	}
	
	private String ask;
	private String answer;
	private String askGerman;
	
	// Intern 
	private STATUS status = STATUS.NOT_TESTED;
	private LEVEL level = LEVEL.LEVEL_1;
	
	public LearningCard(String ask, String answer, String askGerman) {
		this.ask = ask;
		this.answer = answer;
		this.askGerman = askGerman;
	}
	
	public LearningCard(String ask, String answer) {
		this.ask = ask;
		this.answer = answer;
		this.askGerman = "";
	}
	
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	
	public String getAsk2() {
		return askGerman;
	}
	public void setAsk2(String ask) {
		this.askGerman = ask;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
		return "LearningCard [ask=" + ask +  ", status=" + status.name() + ", level=" + level.name() + "]";
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
