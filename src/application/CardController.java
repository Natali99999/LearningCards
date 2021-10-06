package application;

import java.net.URL;


import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;

import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.util.Callback;
import javafx.util.Duration;

import models.LearningCard;
//import models.LearningTheme;
import models.LearningThemes;
import models.View;
import models.ViewSwitcher;


public class CardController implements Initializable{
		
	@FXML
	private BorderPane borderPane;
    
	@FXML
	private Label labelTheme;
	@FXML
	private Label sideTitle;
	
	@FXML
	private Pagination pagination;
	@FXML
    private Button btnCardResultCorrect;
    @FXML
    private Button btnCardResultWrong;
    @FXML
    private Button btnChangeSide;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btnBack;
  

    @FXML
    private Label learnCardCount;
    @FXML
    private Label correctCardCount;
    @FXML
    private Label wrongCardCount;
   
    private Node showCard(int cardIndex) {
    	LearningThemes.theme().selectCurrentCard(cardIndex);
    	updateLearningCardText();
        return null;
    }  
  
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	LearningThemes.theme().init();
		  
    	labelTheme.setText(LearningThemes.getCurrentTheme());
	
		updateLearningCardText();
		updateThemeStatus();
		
		pagination.setPageCount(LearningThemes.theme().getCardCount());
		pagination.setStyle("-fx-border-color:#4682b4;");
        pagination.setPageFactory((Callback<Integer, Node>) new Callback<Integer, Node>() {
            @Override
            public Node call(Integer cardIndex) {
            	 if (cardIndex >= LearningThemes.theme().getCardCount()) {
                     return null;
                 } else {
                	 return showCard(cardIndex);
                 }
            }
        });
	}
    
    public void updateThemeStatus() {

		// Anzeige: learning card count
		learnCardCount.setText(String.valueOf(LearningThemes.theme().getLearningCardsCount()));
		//  Anzeige: wrong card count
		wrongCardCount.setText(String.valueOf(LearningThemes.theme().getWrongCardCount()));
		//  Anzeige: correct card count
		correctCardCount.setText(String.valueOf(LearningThemes.theme().getCorrectCardCount()));
	}
    

	@FXML
    void onMouseClicked( ){
	 changeCardSide();
	}
	
	/**
	 * updateLearningCardText
	 * */
	void updateLearningCardText() {
		
		pagination.setPageCount(LearningThemes.theme().getCardCount());
		pagination.setCurrentPageIndex(LearningThemes.theme().getCurrentIndex());
		
		String cardText = LearningThemes.theme().getLearningCardText();
		String cardStyle = LearningThemes.theme().getLearningCardStyle();
			
		if (LearningThemes.theme().isFrontSide()) {
			sideTitle.setText("Ask:");
			textArea.setText(cardText + "\n\n" + LearningThemes.theme().getCurrentCard().getAsk2());
		}
		else {
			sideTitle.setText("Answer:");
			textArea.setText(cardText);
		}
		
		System.out.println( LearningThemes.theme().getCurrentCard().toString());
		
		textArea.setStyle(cardStyle);
	}
	
    void changeCardSide() {
    	// Karte drehen
    	LearningThemes.theme().changeCardSide();
    	
    	updateLearningCardText();
		
		RotateTransition rotate = new RotateTransition();  
        rotate.setAxis(Rotate.Y_AXIS);  
        rotate.setByAngle(360);  
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(1);  
        rotate.setDuration(Duration.millis(500));  
        rotate.setAutoReverse(true);  
      	rotate.setNode(textArea);
	    rotate.play();  
	}
	 
	 @FXML
    void onBtnBack(ActionEvent event) {
		 ViewSwitcher.switchTo(View.THEME);
    }
	   
	@FXML
	void onBtnResultClicked(ActionEvent event) {
		
		Button btn = (Button)event.getSource();
		// Kartenstatus festlegen: Richtig/Falsch
		LearningThemes.theme().updateLearningCardStatus(
				btn == btnCardResultCorrect
				? LearningCard.STATUS.CORRECT
				: LearningCard.STATUS.WRONG);
	
		updateThemeStatus();
		
		int cardCount = LearningThemes.theme().getLearningCardsCount();
		if (cardCount == 0) {
			ViewSwitcher.switchTo(View.THEME);
			return;
		}
		
		// nächste Karten anzeigen
		LearningThemes.theme().toNextLearningCard();
		
		// Kartentext aktualisieren
		updateLearningCardText();
	}
}
