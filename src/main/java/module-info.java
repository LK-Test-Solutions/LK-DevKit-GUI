module lk.devkit.gui {

	opens org.lk.devkit.gui.application to javafx.fxml;
	//opens org.lk.devkit.gui.test to javafx.fxml;

	exports org.lk.devkit.gui.application;
	exports org.lk.devkit.gui.controls;
	exports org.lk.devkit.gui.chart;
	//exports org.lk.devkit.gui.test;
	exports com.kostikiadis.charts;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.swing;
	
	requires java.logging;
    requires static lombok;
}