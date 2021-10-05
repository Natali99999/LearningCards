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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;

import javafx.util.Duration;

import models.LearningCard;
import models.LearningTheme;
import models.LearningThemes;


public class AppController implements Initializable{
		
	public static final String START_FILE = "start.fxml";
	public static final String LEARN_FILE = "learn.fxml";
	
	@FXML
	private ListView<String> themeList;
	@FXML
	private BorderPane borderPane;

    
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
    
    @FXML
    private ToggleButton btnLevel1;
    @FXML
    private ImageView imageLevel1;
    @FXML
    private Label labelLevel1;

    @FXML
    private ToggleButton btnLevel2;
    @FXML
    private ImageView imageLevel2;
    @FXML
    private Label labelLevel2;

    @FXML
    private ToggleButton btnLevel3;
    @FXML
    private ImageView imageLevel3;
    @FXML
    private Label labelLevel3;

    @FXML
    private ToggleButton btnLevel4;
    @FXML
    private ImageView imageLevel4;
    @FXML
    private Label labelLevel4;

    @FXML
    private Label learnCardCount;
    @FXML
    private Label correctCardCount;
    @FXML
    private Label wrongCardCount;
   
    @FXML
    private Button btnStart;
    

    private Node showCard(int pageIndex) {
    	LearningThemes.theme().showLearningCard(pageIndex);
    	updateLearningCardText();
        return textArea;

    }  
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	LearningThemes.initialize();
    		
		initListView();
		
		pagination.setPageCount(LearningThemes.theme().getCardCount());
		//pagination.setPageFactory(this::showCard);
		
		//LoadContent(START_FILE);
	}
    
    public void updateThemeStatus() {

		// Anzeige: learning card count
		learnCardCount.setText(String.valueOf(LearningThemes.theme().getLearningCardsCount()));
		//  Anzeige: wrong card count
		wrongCardCount.setText(String.valueOf(LearningThemes.theme().getWrongCardCount()));
		//  Anzeige: correct card count
		correctCardCount.setText(String.valueOf(LearningThemes.theme().getCorrectCardCount()));
	}
    
    public void updateLevelStatus() {

    	LearningThemes.theme().updateLevels();
    	
        int count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).count();
        labelLevel1.setText(String.valueOf(count));
        btnLevel1.setSelected(LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).isShowing());
        		//count > 0);
        
        count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_2).count();
        labelLevel2.setText(String.valueOf(count));
        btnLevel2.setSelected(LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_2).isShowing());
        
        count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_3).count();
        labelLevel3.setText(String.valueOf(count));
        btnLevel3.setSelected(LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_3).isShowing());
        
        count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_4).count();
        labelLevel4.setText(String.valueOf(count));
        btnLevel4.setSelected(LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_4).isShowing());
        
        updateBtnLevel(btnLevel1);
        updateBtnLevel(btnLevel2);
        updateBtnLevel(btnLevel3);
        updateBtnLevel(btnLevel4);
  	}
	
	private void initListView() {
		
		// ThemenList füllen
		for(Entry<String, LearningTheme> entry : LearningThemes.themeMap.entrySet()){
			// System.out.println(entry.getKey() + " --> " + entry.getValue());
			 themeList.getItems().add(entry.getKey());
		}	
		
		themeList.getSelectionModel().selectedItemProperty().addListener(
			 new ChangeListener<String>(){

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue,
						String newValue) {
					
					// Theme auswählen
					LearningThemes.changeTheme(themeList.getSelectionModel().getSelectedItem());
						
					// Thementitel aktualisieren
					labelTheme.setText(LearningThemes.getCurrentTheme());
				
					//LoadContent(START_FILE);
					
					Stop();
					
					// Karte aktualisieren
					updateLearningCardText();
					updateThemeStatus();
					updateLevelStatus();
				}
				 
			 });
		
		 // Den ersten Listeneintrag selektieren
		 themeList.getSelectionModel().select(0);
	}
	
	void updateBtnLevel(ToggleButton btn) {
		
		ImageView image;
		String fileName;
		Label label;
		
		if (btn == btnLevel1) {
			fileName = btn.isSelected() ? "src/resources/level2.png" : "src/resources/level1.png";
			image = imageLevel1;
	    	label = labelLevel1;
		}
		else if (btn == btnLevel2) {
			fileName = btn.isSelected() ? "src/resources/level3.png" : "src/resources/level1.png";
			image = imageLevel2;
	    	label = labelLevel2;
		}
		else if (btn == btnLevel3) {
			fileName = btn.isSelected() ? "src/resources/level4.png" : "src/resources/level1.png";
			image = imageLevel3;
	    	label = labelLevel3;
		}
		else if (btn == btnLevel4) {
			fileName = btn.isSelected() ? "src/resources/level5.png" : "src/resources/level1.png";
			image = imageLevel4;
	    	label = labelLevel4;
		}
		else
			return;
		
		File file = new File(fileName);
		image.setImage(new Image(file.toURI().toString()));
		
		if (btn.isSelected() ) {
			image.setDisable(false);
    		label.setStyle("-fx-text-fill:  rgba(0, 0, 0, 1.0);-fx-background-color: rgba(0, 255, 0, 0.5);");
    		btn.setStyle("-fx-text-fill:  rgb(0, 0, 0);");
    	}
    	else {
    		label.setStyle("-fx-text-fill:  rgba(0, 0, 0, 0.3);-fx-background-color: rgb(255, 255, 255);");
    		btn.setStyle("-fx-text-fill:  rgba(0, 0, 0, 0.3);");
    		image.setDisable(true);
    	}
	}
	
	@FXML
	void onBtnLevelClicked(ActionEvent event) {
		ToggleButton btn = (ToggleButton)event.getSource();
		
		boolean selected = btn.isSelected();
		if (btn == btnLevel1) {
			LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).setShow(selected);
		}
		else if (btn == btnLevel2) {
			LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_2).setShow(selected);
		}
		else if (btn == btnLevel3) {
			LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_3).setShow(selected);
		}
		else if (btn == btnLevel4) {
			LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_4).setShow(selected);
		}
		
		updateBtnLevel(btn);
		
	//	LearningThemes.theme().updateLevels();
	//	updateThemeStatus();
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
        rotate.setDuration(Duration.millis(1000));  
        rotate.setAutoReverse(true);  
      	rotate.setNode(textArea/*borderPane.getCenter()*/);
	    rotate.play();  
	}
 
	
	 public void LoadContent(String fxmlFileName) {

		FXMLLoader fxmlLLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
	
		try {
			borderPane.setCenter(fxmlLLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
	        Scene scene = button.getScene();
	        root.translateYProperty().set(scene.getHeight());

	        parentContainer.getChildren().add(root);

	        Timeline timeline = new Timeline();
	        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
	        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	        timeline.getKeyFrames().add(kf);
	        timeline.setOnFinished(t -> {
	            parentContainer.getChildren().remove(anchorRoot);
	        });
	        timeline.play();*/
		
	}
	 
	 @FXML
    void onBtnStart(ActionEvent event) {
		// LoadContent(LEARN_FILE);
		 
		 if (btnStart.getText().equals("Start")) {
			 Start();
		 }
		 else {
			 Stop();
		 }
    }
	 
	 void Start() {
		 LearningThemes.theme().setCorrectCardCount(0);
		LearningThemes.theme().setWrongCardCount(0);
		 
		 btnStart.setText("Stop");
		 
		 btnCardResultCorrect.setDisable(false);
		 btnCardResultWrong.setDisable(false);
		 
		 btnLevel1.setDisable(true);
		 btnLevel2.setDisable(true);
		 btnLevel3.setDisable(true);
		 btnLevel4.setDisable(true);
		 
		  LearningThemes.theme().updateLevels();
		  LearningThemes.theme().show();
		  updateThemeStatus();
	}
	 
	 
	 void Stop() {
		 btnStart.setText("Start");
		 
		 updateLearningCardText();
		 
		  btnCardResultCorrect.setDisable(true);
		  btnCardResultWrong.setDisable(true);
		 
		 btnLevel1.setDisable(false);
		 btnLevel2.setDisable(false);
		 btnLevel3.setDisable(false);
		 btnLevel4.setDisable(false);
		 
		 btnLevel1.setSelected(!LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).isShowing());
		 btnLevel2.setSelected(!LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_2).isShowing());
		 btnLevel3.setSelected(!LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_3).isShowing());
		 btnLevel4.setSelected(!LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_4).isShowing());
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
			
			if (LearningThemes.theme().getLearningCardsCount() == 0) {
				double r = LearningThemes.theme().getResultInProzent();
				textArea.setText(r + "% erreicht");
				
				Stop();
				updateLevelStatus();
				// zurück setzen todo
				//LearningThemes.theme().show();
				
				//LearningThemes.theme().setCorrectCardCount(0);
				//LearningThemes.theme().setWrongCardCount(0);
				
				updateThemeStatus();
				return;
			}
		//	updateLevelStatus();
			
			// nächste Karten anzeigen
			LearningThemes.theme().toNextLearningCard();
			
			// Kartentext aktualisieren
			updateLearningCardText();
		}


}
