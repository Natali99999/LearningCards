package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import models.Constants;
import models.LearningThemes;
import models.ViewSwitcher;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LearningThemes.initialize();
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("theme.fxml"));
			Scene scene = new Scene(root, 1100,600);
		
		    ViewSwitcher.setScene(scene);
		    ViewSwitcher.setClass(getClass());
	
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Icon �ndern
			Image icon = new Image(getClass().getResourceAsStream("/resources/app.png"));
			primaryStage.getIcons().add(icon);
			 
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
