package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class TargetControl extends Group {
	private double windowWidth;
	private double windowHeight;
	private Target target;
	double xCenter;
	double yCenter;
	double radius = 30.0;
	double radiusOffset = 30.0;
	double countL1, countL2, countL3, countL4;
	Text textL1, textL2, textL3, textL4;

	public TargetControl(double width, double height) {
		//VBox 
		this.windowWidth = width;
		this.windowHeight = height;
		xCenter = windowWidth / 2.0;
		yCenter = windowHeight / 2.0;
		
		target = new Target(xCenter, yCenter, radius, radiusOffset);
		textL1 = new Text();
		textL2 = new Text();
		textL3 = new Text();
		textL4 = new Text();
		
		getChildren().add(textL1);
		getChildren().add(textL2);
		getChildren().add(textL3);
		getChildren().add(textL4);
		getChildren().add(target);
	}

	void createBullettTrans(double count, double min, double max, List<Transition> listBullettTrans) {

		double r, theta, x, y;

		for (int i = 0; i < count; i++) {

			r = min + (max - min) * Math.sqrt(Math.random());

			theta = Math.random() * 2 * Math.PI;
			x = xCenter + r * Math.cos(theta);
			y = yCenter + r * Math.sin(theta);

			Bullet p = new Bullet(5, 0, windowHeight);
			target.getChildren().add(p);
			p.draw();

			TranslateTransition translate = new TranslateTransition();
			translate.setNode(p);
			translate.setDelay(Duration.millis(200 + i * 100));
			translate.setDuration(Duration.millis(200));
			translate.setCycleCount(1);
			translate.setByX(x);
			translate.setByY(y - windowHeight);
			translate.setAutoReverse(false);

			listBullettTrans.add(translate);
		}
	}

	public void draw(double countL1, double countL2, double countL3, double countL4, double countMax) {
		if (countMax <= 0) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("No card was selected!");
			alert.setContentText("Please check the level selection.");
			alert.show();
		}
	
		this.countL1 = countL1;
		this.countL2 = countL2;
		this.countL3 = countL3;
		this.countL4 = countL4;

		double l1 = countMax > 0 ? countL1 * 100 / countMax : 0;
		double l2 = countMax > 0 ? countL2 * 100 / countMax : 0;
		double l3 = countMax > 0 ? countL3 * 100 / countMax : 0;
		double l4 = countMax > 0 ? countL4 * 100 / countMax : 0;

		double radiusL4 = radius;
		double radiusL3 = radius + radiusOffset;
		double radiusL2 = radius + 2 * radiusOffset;
		double radiusL1 = radius + 3 * radiusOffset;
		
		// Level 1
		textL1.setText(String.format("%.2f %%", l1));
		textL1.setTextAlignment(TextAlignment.CENTER);
		textL1.setX(xCenter + radiusL4 / 2 + radiusL1);
		textL1.setY(yCenter - radiusL2 - radiusOffset / 4);
		textL1.setFont(Font.font("Verdana", 20));
		textL1.setFill(new Color(0, 0, 0, 0.7));

		// Level 2
		textL2.setText(String.format("%.2f %%", l2));
		textL2.setTextAlignment(TextAlignment.CENTER);
		textL2.setX(xCenter + radiusL4 / 2 + radiusL1);
		textL2.setY(yCenter - radiusL3 - radiusOffset / 4);
		textL2.setFont(Font.font("Verdana", 20));
		textL2.setFill(Color.web("#ffdf00"));

		// Level 3
		textL3.setText(String.format("%.2f %%", l3));
		textL3.setTextAlignment(TextAlignment.CENTER);
		textL3.setX(xCenter + radiusL4 / 2 + radiusL1);
		textL3.setY(yCenter - radiusL4 - radiusOffset / 4);
		textL3.setFont(Font.font("Verdana", 20));
		textL3.setFill(new Color(0, 0, 1, 0.9));

		// Level 4
		textL4.setText(String.format("%.2f %%", l4));
		textL4.setTextAlignment(TextAlignment.CENTER);
		textL4.setX(xCenter + radiusL4 / 2 + radiusL1);
		textL4.setY(yCenter + radiusOffset / 6);
		textL4.setFont(Font.font("Verdana", 20));
		textL4.setFill(Color.ORANGERED);

		target.draw();
	}

	public void play() {

		ArrayList<Transition> listBullettTrans = new ArrayList<>();

		createBullettTrans(countL1, target.getRadiusL2() - 5, target.getRadiusL1() - 5, listBullettTrans);
		createBullettTrans(countL2, target.getRadiusL3() - 5, target.getRadiusL2() - 5, listBullettTrans);
		createBullettTrans(countL3, target.getRadiusL4() - 5, target.getRadiusL3() - 5, listBullettTrans);
		createBullettTrans(countL4, 0, target.getRadiusL4() - 5, listBullettTrans);

		RotateTransition rotate = new RotateTransition();
		rotate.setDelay(Duration.millis(3000));
		rotate.setAxis(Rotate.Y_AXIS);
		rotate.setByAngle(180);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setCycleCount(1);
		rotate.setDuration(Duration.millis(1000));
		rotate.setNode(target);
		listBullettTrans.add(rotate);

		for (Transition t : listBullettTrans) {
			t.play();
		}

		String path = getClass().getResource("/resources/files/shutting.mp3").toString();
		Media media = new Media(path);
		MediaPlayer mp = new MediaPlayer(media);

		mp.play();
	}
	
	public void playWin() {
		String path = getClass().getResource("/resources/files/game-win-sound-effect.mp3").toString();
		Media media = new Media(path);
		MediaPlayer mp = new MediaPlayer(media);
		mp.play();
	}

}
