package org.opentdk.gui.test;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opentdk.gui.chart.ChartAxis;
import org.opentdk.gui.chart.ChartCreatorPlugin;
import org.opentdk.gui.chart.ChartFont;
import org.opentdk.gui.chart.ChartMarker;
import org.opentdk.gui.chart.ChartProperties;
import org.opentdk.gui.chart.ChartSeries;
import org.opentdk.gui.chart.ChartMarker.DataPoint;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TST_ChartCreation_createLineChart extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage arg0) {
		createLineChartFull_1();
		createLineChartFull_2();

		// Stop JavaFX Thread
		Platform.exit();
	}

	public void createLineChartFull_1() {

		ChartProperties cp = new ChartProperties();
		cp.setTitle("Title");
		cp.setTitleFont(ChartFont.font(24, FontWeight.BOLD));
		cp.setHeight(831);
		cp.setWidth(2230);
		cp.setLegendFont(ChartFont.font(18, FontWeight.NORMAL));
		cp.setLegendSide(Side.TOP);
		cp.setVerticalGridLinesVisible(false);
		cp.setHorizontalGridLinesVisible(true);
		cp.setHorizontalGridLinesColor(Color.GREY);

		ChartAxis xAxis = new ChartAxis();
		xAxis.setLabel("X Axis Label");
		xAxis.setValueRange(31);
		xAxis.setValueStep(1);
		xAxis.setLabelFont(ChartFont.font(18, FontWeight.BOLD));
		xAxis.setTickLabelFont(ChartFont.font(14));
		xAxis.setLeaveOneStepSpace(true);
		cp.setxAxis(xAxis);

		ChartAxis y1Axis = new ChartAxis();
		y1Axis.setLabel("Y1 Axis Label");
		y1Axis.setValueRange(100);
		y1Axis.setValueStep(5);
		y1Axis.setLabelFont(ChartFont.font(18));
		y1Axis.setTickLabelFont(ChartFont.font(14));
		y1Axis.setMinorTickMarksVisible(false);
		y1Axis.setForceZeroInRange(true);
		cp.setY1Axis(y1Axis);

		ChartAxis y2Axis = new ChartAxis();
		y2Axis.setLabel("Y2 Axis Label");
		y2Axis.setValueRange(7000);
		y2Axis.setValueStep(1000);
		y2Axis.setLabelFont(ChartFont.font(18));
		y2Axis.setTickLabelFont(ChartFont.font(14));
		y2Axis.setMinorTickMarksVisible(false);
		y2Axis.setForceZeroInRange(true);
		cp.setY2Axis(y2Axis);

		ChartSeries series1 = new ChartSeries();
		series1.setSeriesName("Series1");
		List<String> series1Values = new ArrayList<>(Collections.nCopies(31, (String) null));
		series1Values.set(4, "50");
		series1Values.set(5, "55");
		series1.setSeriesValues(series1Values);
		series1.setSeriesColor(Color.STEELBLUE);
		series1.setBelongingAxis(0);

		ChartSeries series2 = new ChartSeries();
		series2.setSeriesName("Series2");
		List<String> series2Values = new ArrayList<>(Collections.nCopies(31, (String) null));
		series2Values.set(4, "3800");
		series2Values.set(5, "4000");
		series2.setSeriesValues(series2Values);
		series2.setSeriesColor(Color.DARKRED);
		series2.setBelongingAxis(1);

		List<ChartSeries> series = new ArrayList<>();
		series.add(series1);
		series.add(series2);
		cp.setSeriesValues(series);

		List<ChartMarker> chartMarkers = new ArrayList<>();

		StackPane pane = new StackPane();
		Text text = new Text("Text");
		text.setRotate(270);
		text.setFill(Color.GREY);
		text.setFont(Font.font(24));
		Rectangle rectangle = new Rectangle();
		rectangle.setWidth(50);
		rectangle.setHeight(200);
		rectangle.setFill(Color.WHITE);
		Shape[] children = { text, rectangle };
		chartMarkers.add(ChartMarker.marker(pane, children, DataPoint.point(1, 35), -1.0));

		Color color = Color.rgb((int) (Color.GAINSBORO.getRed() * 255), (int) (Color.GAINSBORO.getGreen() * 255), (int) (Color.GAINSBORO.getBlue() * 255), 0.5);
		
		Rectangle rangeRectangle1 = new Rectangle();
		rangeRectangle1.setHeight(650);
		rangeRectangle1.setWidth(95);
		rangeRectangle1.setFill(color);
		chartMarkers.add(ChartMarker.marker(rangeRectangle1, DataPoint.point(0.75, 100), 1.0));
		
		Rectangle rangeRectangle2 = new Rectangle();
		rangeRectangle2.setHeight(650);
		rangeRectangle2.setWidth(62.5 * 5);
		rangeRectangle2.setFill(color);
		chartMarkers.add(ChartMarker.marker(rangeRectangle2, DataPoint.point(6.0, 100), 1.0));

		cp.setChartMarkers(chartMarkers);

		ChartCreatorPlugin chartPlugin = new ChartCreatorPlugin("LINE", "./output/LineChart_1.png", cp, 2);

		chartPlugin.run();
		if (!chartPlugin.isSuccess()) {
			System.err.println("Chart creation finished with error ==> " + getClass().getSimpleName());
		}
	}

	public void createLineChartFull_2() {
		ChartProperties cp = new ChartProperties();
		cp.setTitle("Title");
		cp.setPlotAreaColor(Color.valueOf("rgb(188,222,255)"));
		cp.setLegendColor(Color.valueOf("rgb(222,222,222)"));
		cp.setTitleFont(ChartFont.font(18, FontWeight.BOLD));
		cp.setHeight(741);
		cp.setWidth(1286);
		cp.setLegendSide(Side.BOTTOM);
		cp.setVerticalGridLinesVisible(false);
//		cp.setHorizontalGridLinesToFront(false);
		cp.setHorizontalGridLinesColor(Color.GREY);
		cp.setLegendFont(ChartFont.font(16, FontWeight.NORMAL));
		cp.setChartPadding(new Insets(10, 10, 10, 10));

		ChartAxis xAxis = new ChartAxis();
		List<String> xCategories = new ArrayList<>();
//		String sCurDate = DateUtil.get("27.12.2021", "dd.MM.yyyy");
		int daysToThePast = 94;
		for (int i = daysToThePast; i >= 0; i--) {
//			xCategories.add(DateUtil.get(sCurDate, -i, "dd.MM.yyyy", ChronoUnit.DAYS));
			xCategories.add(String.valueOf(i));
		}
		xAxis.setCategories(xCategories);
		xAxis.setTickLabelRotation(270);
		xAxis.setTickLabelFont(ChartFont.font(13));
		xAxis.setEndMargin(30);
		xAxis.setTickLength(8);
		cp.setxAxis(xAxis);
		
		ChartAxis y1Axis = new ChartAxis();
		y1Axis.setLabel("Y1 Axis Label");
		y1Axis.setLabelFont(ChartFont.font(16));
		y1Axis.setValueRange(15);
		y1Axis.setValueStep(1);
		y1Axis.setMinorTickMarksVisible(true);
		y1Axis.setForceZeroInRange(true);
		y1Axis.setMinorTickLength(5);
		y1Axis.setMinorTickMarkColor("black");
		y1Axis.setMinorTickCount(5);
		cp.setY1Axis(y1Axis);
		
		ChartAxis y2Axis = new ChartAxis();
		y2Axis.setLabel("Y2 Axis Label");
		y2Axis.setLabelFont(ChartFont.font(16));
		y2Axis.setValueRange(7000);
		y2Axis.setValueStep(1000);
		y2Axis.setForceZeroInRange(true);
		y2Axis.setMinorTickCount(5);
		y2Axis.setMinorTickLength(5);
		y2Axis.setMinorTickMarkColor("black");
		y2Axis.setMinorTickMarksVisible(true);
		cp.setY2Axis(y2Axis);
		
		List<ChartSeries> series = new ArrayList<>();
		
		ChartSeries counts = new ChartSeries();
		counts.setSeriesName("Series1");
		counts.setSeriesLegendNode(new Circle(5));
		counts.setSeriesColor(Color.WHITE);
		series.add(counts);
		
		List<ChartMarker> chartMarkers = new ArrayList<>();
		Rectangle rectangle = new Rectangle();
		rectangle.setWidth(4);
		rectangle.setFill(Color.WHITE);
		chartMarkers.add(ChartMarker.marker(rectangle, DataPoint.point("26.12.2021", 3250), -0.5, 1));
				
		StackPane pane = new StackPane();
		Rectangle line = new Rectangle();
		line.setStroke(Color.GREEN);
		line.setWidth(cp.getWidth() * 2);
		line.getStrokeDashArray().addAll(10d, 10d);
		line.setStrokeWidth(3);
		Shape[] children = { line };
		chartMarkers.add(ChartMarker.marker(pane, children, DataPoint.point(xCategories.get(0), 7.5), 0));
		
		StackPane releasePane = new StackPane();
		Text text = new Text("Upd. 50/21");
		text.setRotate(270);
		text.setFill(Color.GREY);
		text.setFont(Font.font(16));
		Shape[] relChildren = { text };
		chartMarkers.add(ChartMarker.marker(releasePane, relChildren, DataPoint.point("26.12.2021", 2), -1.0));
		
		cp.setChartMarkers(chartMarkers);
		
		ChartSeries responseTimes = new ChartSeries();
		responseTimes.setBelongingAxis(0);
		responseTimes.setSeriesColor(Color.STEELBLUE);
		responseTimes.setSeriesName("Series2");
		responseTimes.setSeriesSymbolSize(3);
		responseTimes.setSeriesLegendNode(new Circle(5));
		List<String> averages = new ArrayList<>(Collections.nCopies(94, (String) null));
		averages.set(93, "5.1");
		responseTimes.setSeriesValues(averages);
		series.add(responseTimes);
		
		ChartSeries quantileValues = new ChartSeries();
		quantileValues.setBelongingAxis(0);
		quantileValues.setSeriesColor(Color.RED);
		quantileValues.setSeriesName("Series3");
		quantileValues.setSeriesSymbolSize(3);
		quantileValues.setSeriesLegendNode(new Circle(5));
		List<String> quantiles = new ArrayList<>(Collections.nCopies(94, (String) null));
		quantiles.set(93, "7.0");
		quantileValues.setSeriesValues(quantiles);
		series.add(quantileValues);
		
		ChartSeries references = new ChartSeries();
		references.setSeriesName("Text");
		references.setSeriesColor(Color.GREEN);
		series.add(references);
				
		cp.setSeriesValues(series);

		ChartCreatorPlugin chartPlugin = new ChartCreatorPlugin("LINE", "./output/LineChart_2.png", cp);
		chartPlugin.run();
		if (!chartPlugin.isSuccess()) {
			System.err.println("Chart creation finished with error ==> " + getClass().getSimpleName());
		}
		
	}
}
