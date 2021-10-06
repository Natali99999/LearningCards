package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.LearningCard;

public class DialogCardController{
	public LearningCard card;  
	
    @FXML
    private TextArea textAsk;

    @FXML
    private TextArea textAskDescription;

    @FXML
    private TextArea textAnswer;
	
	public void setCard(LearningCard card) {
 		this.card = card;
 		
 		textAsk.textProperty().bindBidirectional(card.askProperty());
 		textAskDescription.textProperty().bindBidirectional(card.askDescProperty());
 		textAnswer.textProperty().bindBidirectional(card.answerProperty());
 
 	}
}
