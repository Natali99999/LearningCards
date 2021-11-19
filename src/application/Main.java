package application;
	


import javafx.application.Application;
import javafx.stage.Stage;
import models.Constants;
import ui.utils.ViewSwitcher;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// test
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/ui/fxml/theme.fxml"));
			BorderPane root = (BorderPane)fxmlLoader.load();
			Scene scene = new Scene(root);
		     
		    ViewSwitcher.setClass(getClass());
		    ViewSwitcher.setScene(scene);
			ViewSwitcher.addToCacheController(View.THEME, fxmlLoader.getController());
		 	
			// Icon ändern
			Image icon = new Image(getClass().getResourceAsStream("/resources/faq.png"));
			primaryStage.getIcons().add(icon);
			
			scene.getStylesheets().add(getClass().getResource("/ui/fxml/application.css").toExternalForm());
		
			primaryStage.setTitle(Constants.APPTITLE);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
