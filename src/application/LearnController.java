package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.LearningCard;
import models.LearningTheme;
import models.LearningThemes;

public class LearnController implements Initializable{
	
	//private static final String ASK_SIDE_FILE = "askSide.fxml";
	//private static final String ANSWER_SIDE_FILE = "answerSide.fxml";
				
	@FXML
	private Label labelTheme;
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

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	labelTheme.setText(LearningThemes.getCurrentTheme());
    	pagination.setPageCount(LearningThemes.theme().getCardCount());
    	
    	updateLearningCardText();
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
			textArea.setText("Ask:\n\n" + cardText);
		}
		else {
			textArea.setText("Answer:\n\n" + cardText);
		}
		
		textArea.setStyle(cardStyle);
	}
	
    void changeCardSide() {
    	
    	LearningThemes.theme().changeCardSide();
		
		RotateTransition rotate = new RotateTransition();  
        rotate.setAxis(Rotate.Y_AXIS);  
        rotate.setByAngle(360);  
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(1);  
        rotate.setDuration(Duration.millis(1000));  
        rotate.setAutoReverse(true);  
      	rotate.setNode(textArea/*borderPane.getCenter()*/);
	    rotate.play();  
	}
    
	@FXML
	void onBtnResultClicked(ActionEvent event) {
		Button btn = (Button)event.getSource();
		
		// Kartenstatus festlegen: Richtig/Falsch
		LearningThemes.theme().updateLearningCardStatus(
				btn == btnCardResultCorrect
				? LearningCard.STATUS.CORRECT
				: LearningCard.STATUS.WRONG);
	
		//updateThemeStatus();
		//updateLevelStatus();
		
		// nächste Karten anzeigen
		LearningThemes.theme().toNextLearningCard();
		
		// Kartentext aktualisieren
		updateLearningCardText();
	}

}
