package ui.controllers;

import java.net.URL;


import java.util.ResourceBundle;

import application.View;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import models.LearningCard;
import service.LearningThemeService;
import ui.utils.ViewSwitcher;

/**
 * 
 * @author natal

 * 25.10.2021
 */
public class CardController implements Initializable{
		
	@FXML
	public BorderPane borderPane;
    
	@FXML
	private Label labelTheme;
	@FXML
	private ImageView labelImage;
	
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
   
   /* für pagination*/
   /* private Node showCard(int cardIndex) {
    	LearningThemes.theme().selectCurrentCard(cardIndex);
    	updateLearningCardText();
        return null;
    }  */
  
   // Card cardView;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	//cardView = new Card(300, 300);
    	updateView();
	}
    
    public void updateView() {

    	LearningThemeService.theme().init();
		  
    	labelTheme.setText(LearningThemeService.getCurrentTheme());
	
		updateLearningCardText();
		updateThemeStatus();
		
		pagination.setPageCount(LearningThemeService.theme().getCardCount());
		pagination.setStyle("-fx-border-color:#4682b4;");
      /*  pagination.setPageFactory((Callback<Integer, Node>) new Callback<Integer, Node>() {
            @Override
            public Node call(Integer cardIndex) {
            	 if (cardIndex >= LearningThemes.theme().getCardCount()) {
                     return null;
                 } else {
                	 return showCard(cardIndex);
                 }
            }
        });*/
	}
    
    public void updateThemeStatus() {
    	
    	if (LearningThemeService.theme() == null){
    		assert(false);
    		return;
    	}

		// Anzeige: learning card count
		learnCardCount.setText(String.valueOf(LearningThemeService.theme().getLearningCardsCount()));
		//  Anzeige: wrong card count
		wrongCardCount.setText(String.valueOf(LearningThemeService.theme().getWrongCardCount()));
		//  Anzeige: correct card count
		correctCardCount.setText(String.valueOf(LearningThemeService.theme().getCorrectCardCount()));
	}
    

	@FXML
    void onMouseClicked( ){
	 changeCardSide();
	}
	
	/**
	 * updateLearningCardText
	 * */
	void updateLearningCardText() {
		
		pagination.setPageCount(LearningThemeService.theme().getCardCount());
		pagination.setCurrentPageIndex(LearningThemeService.theme().getCurrentIndex());
		
		String cardText = LearningThemeService.theme().getLearningCardText();
		String cardStyle = LearningThemeService.theme().getLearningCardStyle();
			
		if (LearningThemeService.theme().isFrontSide()) {
			textArea.setText(cardText + "\n\n" + LearningThemeService.theme().getCurrentCard().getAskDesc());
			labelImage.setImage(new Image(getClass().getResourceAsStream("../../resources/help.png")));
		}
		else {
			textArea.setText(cardText);
			labelImage.setImage(new Image(getClass().getResourceAsStream("../../resources/answer.png")));
		}
		
		//System.out.println( LearningThemes.theme().getCurrentCard().toString());
		
		textArea.setStyle(cardStyle);
	}	
	
    void changeCardSide() {
    	// Karte drehen
    	LearningThemeService.theme().changeCardSide();
    	
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
	void onBtnResultClicked(ActionEvent event) {
		if (LearningThemeService.theme() == null) {
			assert(false);
			return;
		}
		
		Button btn = (Button)event.getSource();
		// Kartenstatus festlegen: Richtig/Falsch
		LearningThemeService.theme().updateLearningCardStatus(
				btn == btnCardResultCorrect
				? LearningCard.STATUS.CORRECT
				: LearningCard.STATUS.WRONG);
	
		updateThemeStatus();
		
		int cardCount = LearningThemeService.theme().getLearningCardsCount();
		if (cardCount == 0) {
	
			ViewSwitcher.switchAndUpdateTo(View.THEME, true);
			return;
		}
		
		// nächste Karten anzeigen
		LearningThemeService.theme().toNextLearningCard();
		
		// Kartentext aktualisieren
		updateLearningCardText();
	}
	
	@FXML
    void onBtnBack(ActionEvent event) {
		 ViewSwitcher.switchAndUpdateTo(View.THEME, false);
    }
}
