package ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import application.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import models.LearningCard;
import models.LearningTheme;

public class DialogThemeController {

	public LearningTheme theme;

	@FXML
	private TextField ctrlTitle;

	@FXML
	private TableColumn<LearningCard, String> colAnswer;
	@FXML
	private TableColumn<LearningCard, String> colDesc;
	@FXML
	private TableColumn<LearningCard, Integer> colId;
	@FXML
	private TableColumn<LearningCard, String> colQuestion;
	@FXML
	private TableView<LearningCard> tableCard;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	
	static Logger log = LogManager.getLogger(DialogThemeController.class);


	@FXML
	void initialize() {

		// ContextMenu -> Delete
		ContextMenu rowMenu = new ContextMenu();

		MenuItem newItem = new MenuItem("New");
		Image newIcon = new Image(getClass().getResourceAsStream("../../resources/add.png"));
		ImageView newView = new ImageView(newIcon);
		newView.setFitWidth(31);
		newView.setFitHeight(31);
		newItem.setGraphic(newView);
		// editItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE,
		// KeyCode.DELETE));
		rowMenu.getItems().add(newItem);

		MenuItem editItem = new MenuItem("Edit");
		Image editIcon = new Image(getClass().getResourceAsStream("../../resources/edit.png"));
		ImageView editView = new ImageView(editIcon);
		editView.setFitWidth(31);
		editView.setFitHeight(31);
		editItem.setGraphic(editView);
		// editItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE,
		// KeyCode.DELETE));
		rowMenu.getItems().add(editItem);

		MenuItem removeItem = new MenuItem("Delete");
		Image deleteIcon = new Image(getClass().getResourceAsStream("../../resources/trash.png"));
		ImageView deleteView = new ImageView(deleteIcon);
		deleteView.setFitWidth(31);
		deleteView.setFitHeight(31);
		removeItem.setGraphic(deleteView);
		// removeItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE,
		// KeyCode.DELETE));
		rowMenu.getItems().add(removeItem);

		tableCard.setContextMenu(rowMenu);

		removeItem.setOnAction(e -> DeleteCard());
	}

	private void setupTable() {

		colId.setPrefWidth(40);
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colQuestion.setCellValueFactory(new PropertyValueFactory<>("ask"));
		colDesc.setCellValueFactory(new PropertyValueFactory<>("askDesc"));
		colAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));

		colQuestion.setCellFactory(TextFieldTableCell.forTableColumn());
		colDesc.setCellFactory(TextFieldTableCell.forTableColumn());
		colAnswer.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	private void tableRefresh() {
		ObservableList<LearningCard> oList = FXCollections.observableArrayList(theme.getAllCards());

		tableCard.setItems(oList);
	}

	public void setTheme(LearningTheme theme) {
		this.theme = theme;

		ctrlTitle.textProperty().bindBidirectional(theme.titleProperty());
		setupTable();
		tableRefresh();
	}

	@FXML
	void onEditCard() {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource(View.DIALOG_CARD.getFileName());
		fxmlLoader.setLocation(obj);
		try {
			Pane cardDialogPane = fxmlLoader.load();
			DialogCardController controller = fxmlLoader.getController();

			LearningCard card = tableCard.getSelectionModel().getSelectedItem();
			if (card == null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Please selest a card");
				alert.show();
			}
			else {
				controller.setCard(card);
	
				Dialog<ButtonType> dialog = new Dialog<>();
				dialog.setDialogPane((DialogPane) cardDialogPane);
				dialog.setTitle("Edit card");
	
				Optional<ButtonType> clickedButton = dialog.showAndWait();
				if (clickedButton.get() == ButtonType.OK) {
					tableCard.refresh();
				}
			}

		} catch (IOException e) {
			log.error(e + "" + e.getLocalizedMessage());
		}
	}

	@FXML
	void onRemoveCard(ActionEvent event) {
		DeleteCard();
	}

	@FXML
	void onAddCard(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource(View.DIALOG_CARD.getFileName() /*"../fxml/dialogCard.fxml"*/);
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
				if (theme.getAllCards().add(card))
					tableRefresh();
			}

		} catch (IOException e) {
			log.error(e + "" + e.getLocalizedMessage());
		}
	}

	public void OnMouseClicked(MouseEvent click) {
		if (click.getClickCount() == 2) {
			onEditCard();
		}
	}

	void DeleteCard() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remove Card");
		alert.setHeaderText("Do you realy want to delete this Card?");

		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {

				int selItem = tableCard.getSelectionModel().getSelectedIndex();
				LearningCard card = tableCard.getSelectionModel().getSelectedItem();
				if (selItem >= 0) {
					if (theme.removeCard(card)) {
						tableCard.getItems().remove(selItem);
					} else {
						assert false;
						log.warn("Remove the card failed");
					}
				}
			}
		});
	}

	@FXML
	void onKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.DELETE) {
			DeleteCard();
		}
	}

	@FXML
	public void onTableMouseClick(MouseEvent click) {
		if (click.getClickCount() == 2) {
			onEditCard();
		}
	}

}
