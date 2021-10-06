package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import models.LearningCard;
import models.LearningTheme;

public class DialogThemeController{
		
	public LearningTheme theme;   
	
	@FXML
    private TextField ctrlTitle;
    @FXML
    private ListView<LearningCard> listCards;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    
  
 	public void setTheme(LearningTheme theme) {
 		this.theme = theme;
 		
 		ctrlTitle.textProperty().bindBidirectional(theme.titleProperty());
 		
 	// ThemenList füllen
		for(LearningCard card : theme.getAllCards() ){
			// System.out.println(entry.getKey() + " --> " + entry.getValue());
			listCards.getItems().add(card);
		}	
		
		listCards.setCellFactory(listView -> new ListCell<LearningCard>() {
		    ImageView imageView = new ImageView();
		   
		    @Override
		    public void updateItem(LearningCard card, boolean empty) {
		        super.updateItem(card, empty);
		        if (empty) {
		            setText(null);
		            setGraphic(null);
		        } else {
		            Image image = new Image(getClass().getResourceAsStream("/resources/ask2.png"));
		            imageView.setFitHeight(32.0);
		            imageView.setFitWidth(32.0);
		            imageView.setImage(image);
		            setText(card.getAsk());
		            setGraphic(imageView);
		        }
		    }
		});
		
		listCards.getSelectionModel().selectedItemProperty().addListener(
				 new ChangeListener<LearningCard>(){

					@Override
					public void changed(ObservableValue<? extends LearningCard> observable, LearningCard oldValue,
							LearningCard newValue) {
						// TODO Auto-generated method stub
						
					}
				 });
		
			 // Den ersten Listeneintrag selektieren
		listCards.getSelectionModel().select(0/*LearningThemes.getSelectedItem()*/);
 	}
 	

	@FXML
    void onEditCard(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource("dialogCard.fxml");
		fxmlLoader.setLocation(obj);
		try {
			Pane cardDialogPane = fxmlLoader.load();
			DialogCardController controller = fxmlLoader.getController();
			
			LearningCard card = listCards.getSelectionModel().getSelectedItem();
		 	
			controller.setCard(card);
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane((DialogPane) cardDialogPane);
			dialog.setTitle("Edit card");
			
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
				//System.out.println("User selected OK");
				listCards.refresh();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@FXML
    void onRemoveCard(ActionEvent event) {
	 	int selItem = listCards.getSelectionModel().getSelectedIndex();
		LearningCard card = listCards.getSelectionModel().getSelectedItem();
	 	if (selItem >= 0) {
	 		
	 		if (theme.removeCard(card)) {
	 			listCards.getItems().remove(selItem);
	 		}
	 		else {
	 			assert false;
	 		}
	 	}
    }
	
	@FXML
    void onAddCard(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource("dialogCard.fxml");
		fxmlLoader.setLocation(obj);
		try {
			Pane cardDialogPane = fxmlLoader.load();
			DialogCardController controller = fxmlLoader.getController();
			
			LearningCard card = new LearningCard("Test Ask", "Test Answer", "Test Description");
		 	
			controller.setCard(card);
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane((DialogPane) cardDialogPane);
			dialog.setTitle("Add new Card");
			
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
				//System.out.println("User selected OK");
				listCards.getItems().add(card);
				theme.getAllCards().add(card);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}
