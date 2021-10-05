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

public class StartController implements Initializable{
	
	//private static final String ASK_SIDE_FILE = "askSide.fxml";
	//private static final String ANSWER_SIDE_FILE = "answerSide.fxml";
				
	@FXML
    private ScrollPane pane;

    @FXML
    private Label labelTheme;

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
    private Button btnStart;


    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	labelTheme.setText(LearningThemes.getCurrentTheme());
    	updateLevelStatus();
	}

    public void updateLevelStatus() {

    	LearningThemes.theme().updateLevels();
    	
        int count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).count();
        labelLevel1.setText(String.valueOf(count));
        btnLevel1.setSelected(count > 0);
        
        count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_2).count();
        labelLevel2.setText(String.valueOf(count));
        btnLevel2.setSelected(count > 0);
        
        count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_3).count();
        labelLevel3.setText(String.valueOf(count));
        btnLevel3.setSelected(count > 0);
        
        count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_4).count();
        labelLevel4.setText(String.valueOf(count));
        btnLevel4.setSelected(count > 0);
        
        updateBtnLevel(btnLevel1);
        updateBtnLevel(btnLevel2);
        updateBtnLevel(btnLevel3);
        updateBtnLevel(btnLevel4);
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
		updateBtnLevel(btn);
	}
	
	@FXML
	void onStart(ActionEvent event) {
		
	//	Stage stage = (Stage)pane.getScene().getWindow();
		BorderPane borderPane = (BorderPane) pane.getScene().getRoot();
		//AppController.LoadContent(this, borderPane, AppController.LEARN_FILE);
		
		FXMLLoader fxmlLLoader = new FXMLLoader(getClass().getResource(AppController.LEARN_FILE));
		
		try {
			borderPane.setCenter(fxmlLLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	

}
