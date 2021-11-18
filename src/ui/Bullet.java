package ui;

import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author natal

 * 25.10.2021
 */
public class Bullet  extends Group{
	private double radius;
	private double  posX =20;
	private double  posY =20;
	
	Bullet( double radius, double  posX, double posY){
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
	} 
	
	void draw() {
		  
		  Circle circle = new Circle();
		  circle.setCenterX(posX);
		  circle.setCenterY(posY);
		  circle.setRadius(radius);
		  circle.setFill(Color.BLACK);
		  circle.setStrokeWidth(10);
		  circle.setStroke(Color.BEIGE);
		  circle.setStrokeWidth(2);
		  
		  Circle circle2 = new Circle();
		  circle2.setCenterX(posX+2);
		  circle2.setCenterY(posY+1);
		  circle2.setRadius(radius);
		  //circle.setFill(Color.DARKGRAY);
		  circle2.setStrokeWidth(10);
		  circle2.setStroke(Color.DARKGRAY);
		  circle2.setStrokeWidth(2);
		  getChildren().add(circle2);
		  
		 
	}
	
}
