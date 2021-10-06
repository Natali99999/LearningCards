package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import models.LearningCard;
import models.LearningTheme;
import models.LearningThemes;
import models.View;
import models.ViewSwitcher;


public class ThemeController implements Initializable{
		
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
    private TextArea ctrlText;
    
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
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {   		
		initListView();
	}
 
	private void initListView() {
		
		// ThemenList füllen
		for(Entry<String, LearningTheme> entry : LearningThemes.themeMap.entrySet()){
			// System.out.println(entry.getKey() + " --> " + entry.getValue());
			 themeList.getItems().add(entry.getKey());
		}	
		
		themeList.setCellFactory(listView -> new ListCell<String>() {
		    ImageView imageView = new ImageView();
		   
		    @Override
		    public void updateItem(String theme, boolean empty) {
		        super.updateItem(theme, empty);
		        if (empty) {
		            setText(null);
		            setGraphic(null);
		        } else {
		            Image image = new Image(getClass().getResourceAsStream("/resources/java_icon.png"));
		            imageView.setFitHeight(32.0);
		            imageView.setFitWidth(32.0);
		            imageView.setImage(image);
		            setText(theme);
		            setGraphic(imageView);
		        }
		    }
		});
		
		
		themeList.getSelectionModel().selectedItemProperty().addListener(
			 new ChangeListener<String>(){

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					
					// Theme auswählen
					int selectedItem = themeList.getSelectionModel().getSelectedIndex();
					LearningThemes.changeTheme(themeList.getSelectionModel().getSelectedItem(), selectedItem);
						
					// Thementitel aktualisieren
					labelTheme.setText(LearningThemes.getCurrentTheme());
				
					// Karte aktualisieren
					updateTextField();
					
					updateLevelStatus();
				}
			 });
	
		 // Den ersten Listeneintrag selektieren
		 themeList.getSelectionModel().select(LearningThemes.getSelectedItem());
	}
	  
    public void updateLevelStatus() {

    	LearningThemes.theme().selectLearningCards();
    	
        int count = LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).count();
        labelLevel1.setText(String.valueOf(count));
        btnLevel1.setSelected(LearningThemes.theme().getLevelStatus(LearningCard.LEVEL.LEVEL_1).isShowing());
        
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
    		label.setStyle("-fx-text-fill:  rgba(0, 0, 0, 1.0);-fx-background-color:   #f5f5f5;");
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
	}
	
	/**
	 * updateTextField
	 * */
	void updateTextField() {
		
		int cardCount = LearningThemes.theme().getLearningCardsCount();
		if (cardCount == 0) {
			double r = LearningThemes.theme().getResultInProzent();
			ctrlText.setText(String.format("Progress: %.2f%%", r));
		}
		else {
			ctrlText.setText("Select the card levels.");
		}
	}
	
	 @FXML
    void onBtnStart(ActionEvent event) {
		 LearningThemes.theme().setCorrectCardCount(0);
		 LearningThemes.theme().setWrongCardCount(0);
		 
		 ViewSwitcher.switchTo(View.CARD);
    }
	 
	@FXML
    void onAddNewTheme(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource("dialogTheme.fxml");
		fxmlLoader.setLocation(obj);
		try {
			Pane themeDialogPane = fxmlLoader.load();
			DialogThemeController controller = fxmlLoader.getController();
			
			LearningTheme theme = new LearningTheme(new ArrayList<LearningCard>());
			theme.setTitle("Test Theme");
			controller.setTheme(theme);
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane((DialogPane) themeDialogPane);
			dialog.setTitle("Add new Theme");
			
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
			//	System.out.println("User selected OK");
				//theme.setTitle(null);
				/*if (mode == DialogMode.ADD) {
					
				}*/
				
				LearningThemes.themeMap.put(theme.getTitle(), theme);
				themeList.getItems().add(theme.getTitle());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
	 
	@FXML
    void onRemoveTheme(ActionEvent event) {
		int selItem = themeList.getSelectionModel().getSelectedIndex();
		String themeTitle = themeList.getSelectionModel().getSelectedItem();
	 	if (selItem > 8) {
	 		if (LearningThemes.themeMap.containsKey(themeTitle)) {
	 			LearningThemes.themeMap.remove(themeTitle);
	 			themeList.getItems().remove(selItem);
	 		}
	 		else {
	 			assert false;
	 		}
	 	}
    }

	@FXML
    void onEditTheme(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource("dialogTheme.fxml");
		fxmlLoader.setLocation(obj);
		try {
			Pane themeDialogPane = fxmlLoader.load();
			DialogThemeController controller = fxmlLoader.getController();
			
			String themeTitle = themeList.getSelectionModel().getSelectedItem();
		 	
			controller.setTheme(LearningThemes.themeMap.get(themeTitle));
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane((DialogPane) themeDialogPane);
			dialog.setTitle("Edit Theme");
			
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
				//System.out.println("User selected OK");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
