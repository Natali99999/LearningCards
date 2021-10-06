package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import application.Main;


public class ViewSwitcher {

    private static Map<View, Parent> cache = new HashMap<>();
    private static Scene scene;
    private static Class<? extends Main> class1;
    
    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }
    public static void setClass(Class<? extends Main> _class) {
        ViewSwitcher.class1 = _class;
    }
 
    public static void switchTo(View view) {
        if (scene == null) {
          //  System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;

            if (cache.containsKey(view)) {
               // System.out.println("Loading from cache");

                root = cache.get(view);
                scene.setRoot(root);
            } 
            else {
             //   System.out.println("Loading from FXML");
                
                Object ob = ViewSwitcher.class1.getResource(view.getFileName());
                if(ob != null) {
	                root = FXMLLoader.load(
	                		ViewSwitcher.class1.getResource(view.getFileName())
	                );
	              //  cache.put(view, root);
	                scene.setRoot(root);
                }
                else
                {
                	 System.out.println("Loading from FXML failed " + view.getFileName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}