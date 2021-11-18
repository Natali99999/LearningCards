package ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * 
 * @author natal

 * 25.10.2021
 */
public class Card extends Group{
	 
	private double windowWidth;
	private double windowHeight;
	
	Card( double width, double height){
		this.windowWidth = width;
		this.windowHeight = height;
	}
	
	void draw() {
	  double width_long = windowWidth - 80;
	  double width_small = width_long - 130;
	  double height = windowHeight/4;
	  double y = 10;
	  double x = 0;
	  
	  Polygon triangle = new Polygon();
	  triangle.getPoints().setAll(
	    x,y+height,
	    width_small ,y+height,//unten
	    width_long, y,
	    x, y//oben
	    );
	  triangle.setFill(Color.LIGHTSTEELBLUE);
	//  triangle.setStroke(Color.BEIGE);
	//  triangle.setStrokeWidth(6);
	  
	  double width_long2 = width_long + 50;
	  double width_small2 = width_long2 - 70;
	  double height2 = height/2;
	  double y2 = y+50;
	  double x2 = 0;
	  
	  Polygon triangle2 = new Polygon();
	  triangle2.getPoints().setAll(
		x2, y2 + height2,
		width_small2, y2 + height2,
	    width_long2, y2,
	    x2, y2
	    );
	  triangle2.setFill(Color.LIGHTSKYBLUE);
	  
	  Polygon triangle3 = new Polygon();
	  triangle3.getPoints().setAll(
	    width_small2+10, y2-35,
	    width_small2-20, y2,
	    width_long2, y2
	    );
	  triangle3.setFill(Color.BLACK);
	  
	  double imageWidth = height2 - height2/5;
	  double imageOffset = (height2-imageWidth)/2;
	  
	  Text text = new Text();
		  text.setText("CARD 1");
		  text.setX(x2 +  imageWidth + 10+ imageOffset);
		  text.setY(y2 + height2-3*imageOffset);
		  text.setFont(Font.font("Verdana",50));
		  text.setFill(Color.BLACK);
		  
	  Text ask = new Text();
	  ask.setText("What is the name of the method that must be invoked to guarantee that the garbage collector will run?");
	  ask.setX(x2 + 50);
	  ask.setY(y2 + height2 + 70 );
	  ask.setFont(Font.font("Verdana",20));
	  ask.setFill(Color.BLACK);
	  ask.setTextAlignment(TextAlignment.LEFT);
	  ask.setWrappingWidth(width_small2);
	  
	  Image image = new Image("help.png");
	  ImageView imageView = new ImageView(image);
	  imageView.setFitWidth(imageWidth);
	  imageView.setFitHeight(imageWidth);
	  imageView.setX(10);
	  imageView.setY(y2 + imageOffset);
	  
	  double width_longR = windowWidth-width_long;
	  double width_smallR= windowWidth-width_small;
	  double heightR = 1.5*height;
	  double yR = windowHeight -20 ;
	  double xR = windowWidth /*- width_longR*/;
	  
	  Polygon triangleR = new Polygon();
	  triangleR.getPoints().setAll(
			  width_longR, yR,
			  xR ,yR,//unten
			    xR, yR-heightR,
			    width_smallR, yR-heightR//oben
	    );
	  triangleR.setFill(Color.LEMONCHIFFON);
	  triangleR.setStroke(Color.BEIGE);
	  triangleR.setStrokeWidth(6);
	  
	  Text askDesc = new Text();
	  askDesc.setText("Wie heißt die Methode, die aufgerufen werden muss, um zu garantieren, dass der Garbage Collector ausgeführt wird?");
	  askDesc.setX(width_smallR+imageOffset);
	  askDesc.setY(yR - heightR + imageWidth  );
	  askDesc.setFont(Font.font("Verdana",18));
	  askDesc.setFill(Color.BLACK);
	  askDesc.setTextAlignment(TextAlignment.LEFT);
	  askDesc.setWrappingWidth(xR- width_smallR-20);
	  
	  
	  Image imageDesc = new Image("about.png");
	  ImageView imageDescView = new ImageView(imageDesc);
	  imageDescView.setFitWidth(imageWidth);
	  imageDescView.setFitHeight(imageWidth);
	  imageDescView.setX(width_longR+imageWidth/*width_smallR-imageOffset*/);
	  imageDescView.setY(yR /*- heightR + 10*/ - imageWidth-imageOffset);
	  
	  
	  getChildren().add(triangle);
	  getChildren().add(triangle2);
	  getChildren().add(triangle3);
	  getChildren().add(text);
	  getChildren().add(ask);
	  getChildren().add(triangleR);
	  getChildren().add(imageDescView);
	  getChildren().add(askDesc);
	  getChildren().add(imageView);
	}
}
