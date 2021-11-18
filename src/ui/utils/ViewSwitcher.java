package ui.utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ui.controllers.CardController;
import ui.controllers.ThemeController;

import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import application.Main;
import application.View;

public class ViewSwitcher {

	private static Scene scene;
	private static Class<? extends Main> class1;

	public static void setScene(Scene scene) {
		ViewSwitcher.scene = scene;
	}

	public static void setClass(Class<? extends Main> _class) {
		ViewSwitcher.class1 = _class;
	}

	private static Map<View, Object> cacheController = new HashMap<>();

	
	public static void addToCacheController(View view, Object controller) {
		cacheController.put(view, controller);
	}
	
	public static void switchAndUpdateTo(View view, boolean updateResults) {
		if (scene == null) {
			// System.out.println("No scene was set");
			return;
		}

		try {
			if (cacheController.containsKey(view)) {
				// System.out.println("Loading from cache");

				Object controller = cacheController.get(view);
				if (controller != null) {
					if (controller instanceof ThemeController) {
						((ThemeController) controller).updateView(updateResults);
						scene.setRoot(((ThemeController) controller).borderPane);
					} else if (controller instanceof CardController) {
						((CardController) controller).updateView();
						scene.setRoot(((CardController) controller).borderPane);
					}
				}
			} else {
				// System.out.println("Loading from FXML");
				FXMLLoader fxmlLoader = new FXMLLoader();
				URL ob = ViewSwitcher.class1.getResource(view.getFileName());

				if (ob != null) {

					fxmlLoader.setLocation(ob);
					scene.setRoot(fxmlLoader.load());
				
					Object controller = fxmlLoader.getController();
					
					cacheController.put(view, controller);
				
				} else {
					System.out.println("Loading from FXML failed " + view.getFileName());
				}
			}
		} catch (IOException e) {
			System.out.println("IOException: Loading from FXML failed " + view.getFileName());
			e.printStackTrace();
		}
	}
	
	/*
	 * private static Map<View, Parent> cache = new HashMap<>();
	 * public static void switchTo2(View view) {
		if (scene == null) {
			// System.out.println("No scene was set");
			return;
		}

		try {
			Parent root;

			if (cache.containsKey(view)) {
				// System.out.println("Loading from cache");

				root = cache.get(view);
				scene.setRoot(root);
			} else {
				// System.out.println("Loading from FXML");

				Object ob = ViewSwitcher.class1.getResource(view.getFileName());
				if (ob != null) {
					root = FXMLLoader.load(ViewSwitcher.class1.getResource(view.getFileName()));
					// cache.put(view, root);
					scene.setRoot(root);
				} else {
					System.out.println("Loading from FXML failed " + view.getFileName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}