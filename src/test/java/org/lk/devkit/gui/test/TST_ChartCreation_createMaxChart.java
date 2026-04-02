package org.lk.devkit.gui.test;

import org.lk.devkit.gui.chart.ChartCreatorPlugin;
import org.lk.devkit.gui.chart.ChartProperties;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class TST_ChartCreation_createMaxChart extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage arg0) {
		ChartProperties cp = new ChartProperties();
		
		StringBuilder title = new StringBuilder();
		title.append("-".repeat(10000));
		
		cp.setTitle(title.toString());
		cp.setWidth(10000);
		cp.setHeight(10000);

		ChartCreatorPlugin chartPlugin = new ChartCreatorPlugin("BAR", "./output/MaxBarChart.png", cp);
		chartPlugin.run();
		if (!chartPlugin.isSuccess()) {
			System.err.println("Chart creation finished with error ==> " + getClass().getSimpleName());
		}
		// Stop JavaFX Thread
		Platform.exit();
	}

}
