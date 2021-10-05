module LearningCards {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

	//requires jdk.incubator.vector;
	requires junit;


	
	opens application to javafx.graphics, javafx.fxml;
}
