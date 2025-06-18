package org.opentdk.gui.test;

import java.util.*;

import org.opentdk.gui.chart.ChartAxis;
import org.opentdk.gui.chart.ChartCreatorPlugin;
import org.opentdk.gui.chart.ChartFont;
import org.opentdk.gui.chart.ChartMarker;
import org.opentdk.gui.chart.ChartMarker.DataPoint;
import org.opentdk.gui.chart.ChartProperties;
import org.opentdk.gui.chart.ChartSeries;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TST_ChartCreation_createHistogram2 extends Application {

	private String[] xCategories = {"<= 0", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "1100", "1200", "> 1200"};
	private String outputFile = "output/Histogram_2.png";

	private String[] normalValues = {"0", "0.0", "0.0", "0.0", "0.0", "16.27907", "83.72093", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0"};
	private String[] invalidValues = {"0.0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
	private String[] highValues = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0.0"};

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		createHistogram();

		// Stop JavaFX Thread
		Platform.exit();
	}

	private void createHistogram() {
		ChartProperties cp = new ChartProperties();

		// General settings

		cp.setTitle("Title");
		cp.setTitleFont(ChartFont.font(18, FontWeight.BOLD));
		cp.setHeight(641);
		cp.setWidth(1135);
		cp.setLegendSide(Side.RIGHT);

		// Axis

		ChartAxis xaxis = new ChartAxis();
		xaxis.setLabel("Time range (ms)");
		xaxis.setLabelFont(ChartFont.font(14));
		xaxis.setCategories(Arrays.asList(xCategories));
		xaxis.setTickLabelFont(ChartFont.font(12));
		xaxis.setTickLabelRotation(270);
		cp.setxAxis(xaxis);

		ChartAxis y1axis = new ChartAxis();
		y1axis.setLabel("Response time (%)");
		y1axis.setLabelFont(ChartFont.font(14));
		y1axis.setValueRange(110);
		y1axis.setValueStep(10);
		cp.setY1Axis(y1axis);

		// Series values

		List<ChartSeries> seriesValues  = new ArrayList<>();

		ChartSeries normal = new ChartSeries();
		normal.setSeriesName("Series1");
		normal.setSeriesValues(Arrays.asList(normalValues));
		normal.setSeriesColor(Color.STEELBLUE);
		normal.setSeriesLabelVisible(false);
		normal.setSeriesFontSize(11);
		seriesValues.add(normal);

		ChartSeries invalid = new ChartSeries();
		invalid.setSeriesName("Series2");
		invalid.setSeriesValues(Arrays.asList(invalidValues));
		invalid.setSeriesColor(Color.RED);
		invalid.setSeriesLabelVisible(false);
		invalid.setSeriesFontSize(11);
//		seriesValues.add(invalid);

		ChartSeries high = new ChartSeries();
		high.setSeriesName("Series3");
		high.setSeriesValues(Arrays.asList(highValues));
		high.setSeriesColor(Color.ORANGE);
		high.setSeriesLabelVisible(false);
		high.setSeriesFontSize(11);
//		seriesValues.add(high);
		cp.setSeriesValues(seriesValues);

		// Markers

		List<ChartMarker> chartMarkers = new ArrayList<>();
		int i = 0;
		for(String value : normalValues) {
			float asFloat = Float.parseFloat(value);

			Text text = new Text(value);
			text.setRotate(270);
			text.setFill(Color.BLACK);
			text.setFont(Font.font(12));
			chartMarkers.add(ChartMarker.marker(new StackPane(), new Shape[] {text}, DataPoint.point(xCategories[i], asFloat + 10)));
			i++;
		}
		cp.setChartMarkers(chartMarkers);
		cp.setCategoryGap(30);
		
		cp.setVerticalGridLinesVisible(false);

		ChartCreatorPlugin chartPlugin = new ChartCreatorPlugin("BAR", outputFile, cp, 2);
		chartPlugin.run();
		if(!chartPlugin.isSuccess()) {
			System.err.println("Chart creation finished with error ==> " + getClass().getSimpleName());
		}
	}
}
