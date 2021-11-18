package ui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Target extends Group {
	private double xCenter;
	private double yCenter;
	private double radiusL4;
	private double radiusL3;
	private double radiusL2;
	private double radiusL1;
//	private double radiusOffset;
	
	Target(double xCenter, double yCenter, double radius, double radiusOffset) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.radiusL4 = radius;
		this.radiusL3 = radius + radiusOffset;
		this.radiusL2 = radius + 2 * radiusOffset;
		this.radiusL1 = radius + 3 * radiusOffset;
		//this.radiusOffset = radiusOffset;
	}
	
	public double getRadiusL4() {
		return radiusL4;
	}

	public double getRadiusL3() {
		return radiusL3;
	}

	public double getRadiusL2() {
		return radiusL2;
	}

	public double getRadiusL1() {
		return radiusL1;
	}
	
	public void deleteBullets()
	{
		for (Node node: getChildren()) {
			if( node instanceof Bullet) {
				getChildren().remove(node);
			}
		}
	}
	
	void draw() {
		// Level 1
		Circle circleL1 = new Circle();
		circleL1.setCenterX(xCenter);
		circleL1.setCenterY(yCenter);
		circleL1.setRadius(radiusL1);
		circleL1.setFill(Color.web("#000000"));

		// Level 2
		Circle circleL2 = new Circle();
		circleL2.setCenterX(xCenter);
		circleL2.setCenterY(yCenter);
		circleL2.setRadius(radiusL2);
		circleL2.setFill(Color.web("#ffdf00"));// #7fffd4 #66cdaa yallow "#ffd700"

		// Level 3
		Circle circleL3 = new Circle();
		circleL3.setCenterX(xCenter);
		circleL3.setCenterY(yCenter);
		circleL3.setRadius(radiusL3);
		circleL3.setFill(Color.web("#4682b4"));

		// Level 4
		Circle circleL4 = new Circle();
		circleL4.setCenterX(xCenter);
		circleL4.setCenterY(yCenter);
		circleL4.setRadius(radiusL4);
		circleL4.setFill(Color.ORANGERED);

		getChildren().add(circleL1);
		getChildren().add(circleL2);
		getChildren().add(circleL3);
		getChildren().add(circleL4);
	}
	
	/*public void drawText(double l1, double l2, double l3, double l4) {
		// Level 1
		Text textL1 = new Text();
		textL1.setText(String.valueOf(l1) + "%");
		textL1.setTextAlignment(TextAlignment.CENTER);
		textL1.setX(xCenter+radiusL4/2 + radiusL1);
		textL1.setY(yCenter-radiusL2 - radiusOffset/4 );
		textL1.setFont(Font.font("Verdana", 20));
		textL1.setFill(new Color(0, 0, 0, 0.7));
		
		// Level 2
		Text textL2 = new Text();
		textL2.setText(String.valueOf(l2) + "%");
		textL2.setTextAlignment(TextAlignment.CENTER);
		textL2.setX(xCenter+radiusL4/2 + radiusL1);
		textL2.setY(yCenter-radiusL3 - radiusOffset/4  );
		textL2.setFont(Font.font("Verdana", 20));
		textL2.setFill(Color.YELLOW);
		
		// Level 3
		Text textL3 = new Text();
		textL3.setText(String.valueOf(l3) + "%");
		textL3.setTextAlignment(TextAlignment.CENTER);
		textL3.setX(xCenter+radiusL4/2 + radiusL1);
		textL3.setY(yCenter-radiusL4- radiusOffset/4 );
		textL3.setFont(Font.font("Verdana", 20));
		textL3.setFill(new Color(0, 0, 1, 0.9));

		// Level 4
		Text textL4 = new Text();
		textL4.setText(String.valueOf(l4) + "%");
		textL4.setTextAlignment(TextAlignment.CENTER);
		textL4.setX(xCenter +radiusL4/2 + radiusL1);
		textL4.setY(yCenter + radiusOffset/6  );
		textL4.setFont(Font.font("Verdana", 20));
		textL4.setFill(Color.ORANGERED);

		getChildren().add(textL1);
		getChildren().add(textL2);
		getChildren().add(textL3);
		getChildren().add(textL4);
	}*/
}
