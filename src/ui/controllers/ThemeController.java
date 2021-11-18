package ui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import application.View;
import db.DBException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import models.LearningCard;
import models.LearningTheme;
import service.LearningThemeService;
import service.MySQLDAOService;
import ui.TargetControl;
import ui.utils.ViewSwitcher;
import xml.XMLExport;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.Group;

public class ThemeController implements Initializable {
	
	static Logger log = LogManager.getLogger(ThemeController.class);

	@FXML
	private ListView<LearningTheme> themeList;
	@FXML
	public BorderPane borderPane;

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
	@FXML
	private MenuItem menuImport;
	@FXML
	private MenuItem menuExport;
	
	
	@FXML
	VBox ctrlVBOX;

	private TargetControl targetControl;
	@FXML
	Group groupTarget;
	@FXML
	Label labelStatusMessage;
	
	
	List<ToggleButton> levelButtons;
	List<Label> levelLabels;
	List<ImageView> levelImageView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			
			levelButtons = Arrays.asList(new ToggleButton[] {btnLevel1, btnLevel2, btnLevel3, btnLevel4});
			levelLabels = Arrays.asList(new Label[] {labelLevel1, labelLevel2, labelLevel3, labelLevel4});
			levelImageView = Arrays.asList(new ImageView[] {imageLevel1, imageLevel2, imageLevel3, imageLevel4});
			
			ObservableList<LearningTheme> oList = FXCollections.observableArrayList(MySQLDAOService.getInstance().findAll());
			themeList.setItems(oList);

			themeList.setCellFactory(listView -> new ListCell<LearningTheme>() {
				ImageView imageView = new ImageView();

				@Override
				public void updateItem(LearningTheme theme, boolean empty) {
					super.updateItem(theme, empty);
					if (empty) {
						setText(null);
						setGraphic(null);
					} else {
						Image image = new Image(getClass().getResourceAsStream("/resources/questions.png"));
						imageView.setFitHeight(32.0);
						imageView.setFitWidth(32.0);
						imageView.setImage(image);
						setText(theme.getTitle());
						setGraphic(imageView);
					}
				}
			});

			themeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LearningTheme>() {
				@Override
				public void changed(ObservableValue<? extends LearningTheme> observable, LearningTheme oldValue,
						LearningTheme newValue) {
					updateView(false);

				}
			});

			// Den ersten Listeneintrag selektieren
			themeList.getSelectionModel().select(0);
		} catch (DBException e1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(e1.getMessage());
			alert.setContentText("Please establish a DB connection and restart the program!");
			alert.showAndWait();
			Platform.exit(); // beendet FX-Platform
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("unknown error");
			alert.setContentText(e.toString());
			alert.show();
		}
	}
	
	/**
	 * updateView(boolean updateResults)
	 */
	public void updateView(boolean updateResults) {
		// Theme auswählen
		int selectedItem = themeList.getSelectionModel().getSelectedIndex();

		if (themeList.getSelectionModel().getSelectedItem() != null &&
			LearningThemeService.changeTheme(themeList.getSelectionModel().getSelectedItem().getId(), selectedItem)) {

			// Thementitel aktualisieren
			labelTheme.setText(LearningThemeService.getCurrentTheme());
			
			CreateTargetControl(true);
		}
		else {
			CreateTargetControl(false);
		}

		updateLevelStatus();

		if (updateResults) {
			
		//	new Thread(() -> {
				targetControl.play();

				if ( LearningThemeService.theme() != null &&
					LearningTheme.LEVEL.LEVEL_4.cardCount() == LearningThemeService.theme().getAllCards().size()) {
					targetControl.playWin();
				}
			//}
			//).start(); 
		}
	}

	/**
	 * CreateTargetControl(boolean reinsert)
	 */
	private void CreateTargetControl(boolean reinsert) {
		if (!reinsert && targetControl != null) {
			return;
		}
		
		if (reinsert && targetControl != null) {
			ctrlVBOX.getChildren().remove(targetControl);
		}
		
		targetControl = new TargetControl(600, 300);
		ctrlVBOX.getChildren().add(1, targetControl);
	}

	/**
	 * updateLevelStatus
	 */
	public void updateLevelStatus() {
		if (LearningThemeService.theme() == null) {
			log.error("theme() is null");
			assert(false);
			return;
		}
		
		// Lernkarten in zufähliger Reihenfolge holen
		LearningThemeService.theme().selectLearningCards();
		
		if (levelButtons.size() != levelLabels.size() ||
			levelLabels.size() != LearningTheme.LEVEL.values().length) {
			log.warn("Arraygrössen stimmen nicht !");
			return;
		}
	
		for (LearningTheme.LEVEL level : LearningTheme.LEVEL.values()) {
			int index = level.ordinal();
			levelLabels.get(index).setText(String.valueOf(level.cardCount()));  // Labeltext: Cardcount aktualisieren
			levelButtons.get(index).setSelected(level.isShowing()); 			// Buttons aktivieren/deaktivieren
		}

		// targetControl animieren
		targetControl.draw(LearningTheme.LEVEL.LEVEL_1.cardCount(),
				LearningTheme.LEVEL.LEVEL_2.cardCount(), 
				LearningTheme.LEVEL.LEVEL_3.cardCount(), 
				LearningTheme.LEVEL.LEVEL_4.cardCount(), 
				LearningThemeService.theme().getCardCount());
	}

	/**
	 * updateBtnLevel
	 */
	void updateBtnLevel(ToggleButton btn) {

		if (levelImageView.size() != levelLabels.size()) {
			log.warn("Arraygrössen stimmen nicht !");
			return;
		}
	
		int index = levelButtons.indexOf(btn);
		String fileName = btn.isSelected() ? "/resources/level_active.png" : "/resources/level_disactive.png";
		ImageView image = levelImageView.get(index);
		Label label = levelLabels.get(index);
		double opacity = 0.4 + index*20;
		
		image.setImage(new Image(getClass().getResourceAsStream(fileName)));
		image.setOpacity(opacity);
		if (btn.isSelected()) {
			image.setDisable(false);
			label.setStyle("-fx-text-fill:  rgba(0, 0, 0, 1.0);-fx-background-color:   #f5f5f5;");
			btn.setStyle("-fx-text-fill:  rgb(0, 0, 0);");
		} else {
			label.setStyle("-fx-text-fill:  rgba(0, 0, 0, 0.3);-fx-background-color: rgb(255, 255, 255);");
			btn.setStyle("-fx-text-fill:  rgba(0, 0, 0, 0.3);");
			image.setDisable(true);
		}
	}

	/**
	 * @FXML onBtnLevelClicked
	 */
	@FXML
	void onBtnLevelClicked(ActionEvent event) {
		ToggleButton btn = (ToggleButton) event.getSource();
	
		if (levelButtons.contains(btn)) {
			int index = levelButtons.indexOf(btn);
			boolean selected = btn.isSelected();
			LearningTheme.LEVEL.values()[index].setShow(selected);
		}
	
		updateBtnLevel(btn);
	}

	/**
	 * @FXML onAddNewTheme
	 */
	@FXML
	void onAddNewTheme(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource(View.DIALOG_THEME.getFileName());
		fxmlLoader.setLocation(obj);
		try {
			Pane themeDialogPane = fxmlLoader.load();
			DialogThemeController controller = fxmlLoader.getController();

			LearningTheme theme = new LearningTheme(new ArrayList<LearningCard>(), "No name");
			controller.setTheme(theme);

			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane((DialogPane) themeDialogPane);
			dialog.setTitle("Add new Theme");

			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
			
				try {
					if (MySQLDAOService.getInstance().saveComplete(theme)) {
						AddNewTheme(theme);
						setInfoMessage(String.format("The Theme '%s' was created", theme.getTitle()));
					}
				} catch (DBException | SQLException e) {
					log.error(e + "" + e.getLocalizedMessage());
				}
			}

		} catch (IOException e) {
			log.error(e + "" + e.getLocalizedMessage());
		}
	}

	/**
	 * @FXML onRemoveTheme
	 */
	@FXML
	void onRemoveTheme(ActionEvent event) {
		DeleteTheme();
	}
	
	/**
	 * @FXML onEditTheme
	 */
	@FXML
	void onEditTheme() {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL obj = getClass().getResource(View.DIALOG_THEME.getFileName());
		fxmlLoader.setLocation(obj);
		try {
			Pane themeDialogPane = fxmlLoader.load();
			DialogThemeController controller = fxmlLoader.getController();

			LearningTheme theme = themeList.getSelectionModel().getSelectedItem();

			controller.setTheme(theme);

			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane((DialogPane) themeDialogPane);
			dialog.setTitle("Edit Theme");

			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
				try {
					if (MySQLDAOService.getInstance().saveComplete(theme)) {
						setInfoMessage(String.format("The Theme '%s' was changed", theme.getTitle()));
					}
				} catch (DBException | SQLException e) {
					log.error(e + "" + e.getLocalizedMessage());
				}
				}

		} catch (IOException e) {
			log.error(e + "" + e.getLocalizedMessage());
		}
	 }

	/**
	 * @FXML onMenuSelected
	 */
	@FXML
	void onMenuSelected(ActionEvent event) {
		MenuItem menuItem = (MenuItem) event.getSource();

		if (menuItem.getId().equals("menuImport")) {
			importThemeFromFile();
		} else if (menuItem.getId().equals("menuExport")) {
			exportThemeFromFile();
		} 
	}

	/**
	 * @FXML importThemeFromFile
	 */
	void importThemeFromFile() {

		String filename = "";

		String currentDir = System.getProperty("user.dir");
	
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Export to XML");
		fileChooser.setInitialDirectory(new File(currentDir));
		
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.*)", "*.xslx"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
	
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			
			filename = selectedFile.getPath();
			LearningTheme theme = XMLExport.importTheme(filename);
			try {
				if (MySQLDAOService.getInstance().saveComplete(theme)) {
					AddNewTheme(theme);
					
					// das neue Theme auswählen
					selectListItem(themeList.getItems().size()-1);
					
					// InfoMessage
					setInfoMessage(String.format("The Theme title was to '%s'imported", theme.getTitle()));
				}
			} catch (DBException | SQLException e) {
				log.error(e + "" + e.getLocalizedMessage());
			}
		}
	}

	/**
	 * AddNewTheme(LearningTheme theme)
	 */
	private void AddNewTheme(LearningTheme theme) {
		// in die ListView hinzufügen
		themeList.getItems().add(theme);
		// in die ThemeMap hinzufügen
		LearningThemeService.addThemeToMap(theme.getId(), theme.getTitle(), theme.getAllCards());
	}
	
	/**
	 * void selectListItem(int sel)
	 */
	private void selectListItem(int sel) {
		themeList.scrollTo(sel);
		themeList.getSelectionModel().select(sel);
	}

	/**
	 * exportThemeFromExcel
	 */
	void exportThemeFromFile() {
		LearningTheme theme = themeList.getSelectionModel().getSelectedItem();

		String filename = theme.getTitle() + ".xml";

		String currentDir = System.getProperty("user.dir");
	
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Export to XML");
		fileChooser.setInitialDirectory(new File(currentDir));
		fileChooser.setInitialFileName(filename);
		
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.*)", "*.xslx"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
		
		File selectedFile = fileChooser.showSaveDialog(null);
		if (selectedFile != null) {
			filename = selectedFile.getPath();
			XMLExport.exportTheme(filename, theme);
		}
	}

	
	/**
	 * onAbout()
	 */
	public void onAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Learning Cards");
		alert.setHeaderText("Version 1.0");

		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
			}
		});
	}
	
	/**
	 * DeleteTheme()
	 */
	void DeleteTheme() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remove Theme");
		alert.setHeaderText("Do you realy want to delete this Theme?");

		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {

				int selItem = themeList.getSelectionModel().getSelectedIndex();
				LearningTheme theme = themeList.getSelectionModel().getSelectedItem();
			
				String msg = String.format("The Theme '%s' was deleted", theme.getTitle());
				try {
					if (MySQLDAOService.getInstance().delete(theme.getId())) {
						themeList.getItems().remove(selItem);
						if (themeList.getSelectionModel().getSelectedItem() != null &&
							selItem == LearningThemeService.getSelectedItem()) {
							selItem = themeList.getSelectionModel().getSelectedIndex();
							LearningThemeService.changeTheme(themeList.getSelectionModel().getSelectedItem().getId(),
									selItem);
						}
						setInfoMessage(msg);
					} else {
						System.out.printf("\nThemeController.DeleteTheme(): theme %s id %d not found\n", theme.getTitle(), theme.getId());
						assert false;
					}
				} catch (DBException | SQLException e) {
					log.error(e + "" + e.getLocalizedMessage());
				}
			}
		});
	}

	/**
	 *  OnMouseClicked(MouseEvent click)
	 */
	public void OnMouseClicked(MouseEvent click) {
		if (click.getClickCount() == 2) {
			onEditTheme();
		}
	}

	/**
	 *  onBtnStart(ActionEvent event)
	 */
	@FXML
	void onBtnStart(ActionEvent event) {
		LearningThemeService.theme().init();
		if (LearningThemeService.theme().getLearningCardsCount() == 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Non card was selected!");
			alert.show();
			return;
		}
		
		LearningThemeService.theme().setCorrectCardCount(0);
		LearningThemeService.theme().setWrongCardCount(0);

		ctrlVBOX.getChildren().remove(targetControl);
		targetControl = null;

		ViewSwitcher.switchAndUpdateTo(View.CARD, false);
	}

	/**
	 * setInfoMessage(String msg)
	 */
	private void setInfoMessage(String msg) {
		labelStatusMessage.setOpacity(1);
		FadeTransition fade = new FadeTransition(Duration.seconds(4), labelStatusMessage);
		fade.setDelay(Duration.seconds(1));
		fade.setFromValue(1); // 100%
		fade.setToValue(0);
		labelStatusMessage.setText(msg);
		fade.play();
	}
	
	/**
	 * onKeyRelease(KeyEvent event)
	 */
	@FXML
	public void onKeyRelease(KeyEvent event) {
		if (event.getCode() == KeyCode.DELETE) {
			DeleteTheme();
		}
	}

}
