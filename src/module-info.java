module opentdk.gui {
	
	exports org.lk.devkit.gui.application;
	exports org.lk.devkit.gui.controls;
	exports org.lk.devkit.gui.chart;
	exports com.kostikiadis.charts;

	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive javafx.graphics;	
	requires transitive javafx.swing;
	
	requires transitive java.logging;
    requires static lombok;
}