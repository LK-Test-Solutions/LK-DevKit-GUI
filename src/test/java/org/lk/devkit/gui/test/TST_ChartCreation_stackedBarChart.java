package org.lk.devkit.gui.test;

import org.lk.devkit.gui.chart.ChartFont;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TST_ChartCreation_stackedBarChart extends Application {

	public static void main(String[] args) {
		launch();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Stage Title");

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("X Label");
		xAxis.getCategories().addAll("27.02.2024", "05.03.2024", "12.03.2024", "19.03.2024", "26.03.2024", "02.04.2024");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Y Label");

		StackedBarChart stackedBarChart = new StackedBarChart(xAxis, yAxis);
		stackedBarChart.setLegendSide(Side.RIGHT);
		stackedBarChart.setTitle("Title");
		stackedBarChart.setPrefWidth(1000);
		stackedBarChart.setPrefHeight(600);
		stackedBarChart.setCategoryGap(70);
		stackedBarChart.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		stackedBarChart.setVerticalGridLinesVisible(false);
//		stackedBarChart.setHorizontalZeroLineVisible(false);
		
		stackedBarChart.lookup(".chart-plot-background").setStyle(String.format("-fx-background-color: %s;", toRGB(Color.WHITE)));
		String legendColor = String.format("-fx-background-color: %s;", toRGB(Color.WHITE));
		String legendFont = getStyleFromFont(ChartFont.font(10, FontWeight.NORMAL));
		stackedBarChart.lookup(".chart-legend").setStyle(legendColor + legendFont);
		
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("Series1");
		dataSeries1.getData().add(new XYChart.Data("27.02.2024", 37465));
		dataSeries1.getData().add(new XYChart.Data("05.03.2024", 34657));
		dataSeries1.getData().add(new XYChart.Data("12.03.2024", 32845));
		dataSeries1.getData().add(new XYChart.Data("19.03.2024", 35832));
		dataSeries1.getData().add(new XYChart.Data("26.03.2024", 38725));
		dataSeries1.getData().add(new XYChart.Data("02.04.2024", 36354));
		stackedBarChart.getData().add(dataSeries1);

		XYChart.Series dataSeries2 = new XYChart.Series();
		dataSeries2.setName("Series2");
		dataSeries2.getData().add(new XYChart.Data("27.02.2024", 17465));
		dataSeries2.getData().add(new XYChart.Data("05.03.2024", 14657));
		dataSeries2.getData().add(new XYChart.Data("12.03.2024", 12845));
		dataSeries2.getData().add(new XYChart.Data("19.03.2024", 15832));
		dataSeries2.getData().add(new XYChart.Data("26.03.2024", 18725));
		dataSeries2.getData().add(new XYChart.Data("02.04.2024", 16354));
		stackedBarChart.getData().add(dataSeries2);

		XYChart.Series dataSeries3 = new XYChart.Series();
		dataSeries3.setName("Series3");
		dataSeries3.getData().add(new XYChart.Data("27.02.2024", 7465));
		dataSeries3.getData().add(new XYChart.Data("05.03.2024", 4657));
		dataSeries3.getData().add(new XYChart.Data("12.03.2024", 2845));
		dataSeries3.getData().add(new XYChart.Data("19.03.2024", 5832));
		dataSeries3.getData().add(new XYChart.Data("26.03.2024", 8725));
		dataSeries3.getData().add(new XYChart.Data("02.04.2024", 6354));
		stackedBarChart.getData().add(dataSeries3);

		VBox vbox = new VBox(stackedBarChart);
		Scene scene = new Scene(vbox, 1000, 600);

		primaryStage.setScene(scene);
		primaryStage.setWidth(1000);
		primaryStage.setHeight(600);

		primaryStage.show();

		// Stop JavaFX Thread
		//Platform.exit();
	}
	
	private String toRGB(Color color) {
		return "rgb(" + (int) (color.getRed() * 255) + "," + (int) (color.getGreen() * 255) + "," + (int) (color.getBlue() * 255) + ")";
	}
	
	private String getStyleFromFont(ChartFont font) {
		String fontFamily = String.format("-fx-font-family: %s;", font.getFamily());
		String fontWeight = String.format("-fx-font-weight: %s;", font.getWeight().toLowerCase());
		String fontSize = String.format("-fx-font-size: %dpx;", font.getSize());
		return fontFamily + fontWeight + fontSize;
	}

}
