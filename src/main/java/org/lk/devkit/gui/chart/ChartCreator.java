/* 
 * BSD 2-Clause License
 * 
 * Copyright (c) 2022, LK Test Solutions GmbH
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package org.lk.devkit.gui.chart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.kostikiadis.charts.Legend;
import com.kostikiadis.charts.Legend.LegendItem;
import com.kostikiadis.charts.MultiAxisChart;
import com.kostikiadis.charts.MultiAxisChart.Data;
import com.kostikiadis.charts.MultiAxisChart.Series;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class ChartCreator {

	private final ChartProperties cp;
	private MultiAxisChart<Object, Object> chart;
	private Axis<?> xAxis;
	private Axis<?> y1Axis;
	private Axis<?> y2Axis;
	private List<String> xcategories = new ArrayList<>();

	protected ChartCreator(ChartProperties props) {
		cp = props;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void createChart(ChartType type) {
		xAxis = initAxis(cp.getxAxis(), cp.getxAxisBorder(), true);
		y1Axis = initAxis(cp.getY1Axis(), cp.getY1AxisBorder(), false);
		y2Axis = initAxis(cp.getY2Axis(), cp.getY2AxisBorder(), false);

		switch (type) {
			case BAR -> chart = new CustomBarChart(xAxis, y1Axis, null);
			case LINE -> chart = new CustomLineChart(xAxis, y1Axis, y2Axis);
			case NONE -> throw new RuntimeException("No chart type detected.");
		}

		configureChart();
		addChartData();
		addChartMarkers();
		setCssProperties();
	}

	private Axis<?> initAxis(ChartAxis properties, ChartBorder border, boolean saveCategories) {
		Axis<?> axis = null;

		double rng = properties.getValueRange();
		double stp = properties.getValueStep();
		List<String> categories = properties.getCategories();

		boolean isNumberAxis = (!Double.isNaN(rng) && !Double.isNaN(stp)) && (rng != 0.0 && stp != 0.0);
		boolean isCategoryAxis = !categories.isEmpty();

		if (isNumberAxis && isCategoryAxis) {
			throw new RuntimeException("Abscissa axis: Value range and step for the number axis AND categories for the category axis are defined. Only one axis type is possible.");
		} else if (isNumberAxis) {
			axis = new NumberAxis();
			((NumberAxis) axis).setAutoRanging(false);
			((NumberAxis) axis).setLowerBound(properties.getLowestValue());
			((NumberAxis) axis).setMinorTickCount(properties.getMinorTickCount());
			((NumberAxis) axis).setMinorTickLength(properties.getMinorTickLength());
			((NumberAxis) axis).setMinorTickVisible(properties.getMinorTickMarksVisible());
			((NumberAxis) axis).setTickUnit(stp);
			if (properties.isLeaveOneStepSpace()) {
				((NumberAxis) axis).setUpperBound(rng + stp);
			} else {
				((NumberAxis) axis).setUpperBound(rng);
			}
			((NumberAxis) axis).setTickLabelFormatter(new StringConverter<>() {
				@Override
				public String toString(Number object) {
					double i = object.doubleValue();
					String ret = null;
					if (i > rng) {
						ret = "";
					} else if (i == 0 && !properties.getForceZeroInRange()) {
						ret = "";
					} else {
						NumberFormat format = cp.getNumberAxisFormat();
						ret = format.format(i);
					}
					return ret;
				}

				@Override
				public Number fromString(String string) {
					return null;
				}
			});
			categories = new ArrayList<>();
			for (double d = stp; d <= rng; d++) {
				categories.add(String.valueOf(d));
				d = d + stp - 1;
			}
		} else {
			axis = new CategoryAxis(FXCollections.observableArrayList(categories));
			((CategoryAxis) axis).setGapStartAndEnd(false);
			((CategoryAxis) axis).setStartMargin(properties.getStartMargin());
			((CategoryAxis) axis).setEndMargin(properties.getEndMargin());
			((CategoryAxis) axis).setAutoRanging(false);
		}
		axis.setTickLabelRotation(properties.getTickLabelRotation());
		axis.setTickLabelFill(Color.valueOf(properties.getTickLabelColor()));
		axis.setTickLabelFont(properties.getTickLabelFont().toFont());
		axis.setTickLabelGap(properties.getTickLabelGap());
		axis.setLabel(properties.getLabel());
		axis.setTickLabelsVisible(properties.getTickLabelsVisible());
		axis.setTickLength(properties.getTickLength());
		axis.setTickMarkVisible(properties.getTickMarksVisible());
		axis.setBorder(border.getBorder());
		axis.setAnimated(false);

		String labelFont = this.getStyleFromFont(properties.getLabelFont());
		String labelPadding = String.format("-fx-padding: %dpx;", properties.getLabelPadding());
		axis.lookup(".axis-label").setStyle(labelFont + labelPadding);

		axis.lookup(".axis-tick-mark").setStyle(String.format("-fx-stroke: %s;", toRGB(Color.valueOf(properties.getTickMarkColor()))));
		if (axis.lookup(".axis-minor-tick-mark") != null) {
			axis.lookup(".axis-minor-tick-mark").setStyle(String.format("-fx-stroke: %s;", toRGB(Color.valueOf(properties.getMinorTickMarkColor()))));
		}

		if (saveCategories) {
			xcategories = categories;
		}
		return axis;
	}

	private void configureChart() {
		chart.setAnimated(false);
		chart.setVerticalGridLinesVisible(cp.isVerticalGridLinesVisible());
		chart.setVerticalZeroLineVisible(cp.isVerticalZeroLineVisible());		
		chart.setHorizontalGridLinesVisible(cp.isHorizontalGridLinesVisible());
		chart.setHorizontalZeroLineVisible(cp.isHorizontalZeroLineVisible());
		chart.setTitle(cp.getTitle());
		chart.setTitleSide(cp.getTitleSide());
		chart.setBackground(new Background(new BackgroundFill(cp.getChartBackground(), CornerRadii.EMPTY, Insets.EMPTY)));
		chart.setLegendSide(cp.getLegendSide());
		chart.setLegendVisible(cp.isLegendVisible());
		chart.setPadding(cp.getChartPadding());
	}

	private void addChartData() {
		ObservableList<LegendItem> legendItems = FXCollections.<LegendItem>observableArrayList();
		List<ChartSeries> series = cp.getSeriesValues();
		if (series == null) {
			throw new IllegalArgumentException("Series list is null ==> no data to add");
		} else {
			for (ChartSeries serie : series) {
				List<String> values = serie.getSeriesValues();
				if (values != null) {
					int belongingAxis = serie.getBelongingAxis();

					Series<Object, Object> nextSerie = new Series<>();
					nextSerie.setName(serie.getSeriesName());

					for (int i = 0; i < values.size(); i++) {
						String nextValue = values.get(i);
						if (nextValue == null || nextValue.isBlank()) {
							nextValue = "NaN";
						}
						if (nextValue.contains(",")) {
							nextValue = nextValue.replaceAll(",", "\\.");
						}

						// Position
						Object xVal = null;
						if (i < xcategories.size()) {
							xVal = xcategories.get(i);
						}
						Object yVal = null;

						if (belongingAxis == 0) {
							if (xAxis instanceof CategoryAxis && y1Axis instanceof NumberAxis) {
								yVal = Double.parseDouble(nextValue);
							} else if (xAxis instanceof CategoryAxis && y1Axis instanceof CategoryAxis) {
								yVal = nextValue;
							} else if (xAxis instanceof NumberAxis && y1Axis instanceof NumberAxis) {
								xVal = Double.parseDouble(String.valueOf(xVal));
								yVal = Double.parseDouble(nextValue);
							} else if (xAxis instanceof NumberAxis && y1Axis instanceof CategoryAxis) {
								xVal = Double.parseDouble(String.valueOf(xVal));
								yVal = nextValue;
							} else {
								throw new IllegalStateException("ChartCreator ==> xAxis and y1Axis are no instance of a valid axis type.");
							}
						} else if (belongingAxis == 1) {
							if (xAxis instanceof CategoryAxis && y2Axis instanceof NumberAxis) {
								yVal = Double.parseDouble(nextValue);
							} else if (xAxis instanceof CategoryAxis && y2Axis instanceof CategoryAxis) {
								yVal = nextValue;
							} else if (xAxis instanceof NumberAxis && y2Axis instanceof NumberAxis) {
								xVal = Double.parseDouble(String.valueOf(xVal));
								yVal = Double.parseDouble(nextValue);
							} else if (xAxis instanceof NumberAxis && y2Axis instanceof CategoryAxis) {
								xVal = Double.parseDouble(String.valueOf(xVal));
								yVal = nextValue;
							} else {
								throw new IllegalStateException("ChartCreator ==> xAxis and y2Axis are no instance of a valid axis type.");
							}
						} else {
							throw new IllegalArgumentException("Belonging axis is neither 0 (y1Axis) nore 1 (y2Axis)");
						}
						Data<Object, Object> nextData = new Data<>(xVal, yVal, belongingAxis);
						nextSerie.getData().add(nextData);
					}
					LegendItem legendItem = new LegendItem(serie.getSeriesName(), serie.getSeriesLegendNode());
					legendItem.getSymbol().setStyle(String.format("-fx-fill: %s;", toRGB(serie.getSeriesColor())));
					legendItems.add(legendItem);

					chart.getData().add(nextSerie);

					// Styling
					for (Data<Object, Object> data : nextSerie.getData()) {
						if (data.getNode() != null) {
							ObservableList<String> styleClass = data.getNode().getStyleClass();

							if (styleClass.contains("chart-line-symbol")) {
								String fxShape = "";
								if (!serie.isSeriesLabelVisible()) {
									fxShape = "-fx-background-color: transparent";
								}
								String color = toRGB(serie.getSeriesColor());
								int symbolSize = serie.getSeriesSymbolSize();
								String lineSymbolStyle = String.format("-fx-background-color: %s;-fx-background-radius: %dpx;-fx-padding: %dpx;%s;", color, symbolSize, symbolSize, fxShape);
								data.getNode().lookup(".chart-line-symbol").setStyle(lineSymbolStyle);
								data.getNode().lookup(".chart-line-symbol").setViewOrder(-1.0);

							} else if (styleClass.contains("chart-bar")) {
								String style = String.format("-fx-bar-fill: %s;", toRGB(serie.getSeriesColor()));
								((CustomBarChart<?, ?>) chart).setCustomCategoryGap(cp.getCategoryGap());
								data.getNode().setStyle(style);
							}
						}
					}
					if (nextSerie.getNode() != null) {
						ObservableList<String> styleClass = nextSerie.getNode().getStyleClass();
						if (styleClass.contains("chart-series-line")) {
							String lineStyle;
							if (!serie.isValuesConnected()) {
								lineStyle = "-fx-stroke: transparent;";
							} else {
								lineStyle = String.format("-fx-stroke: %s;", toRGB(serie.getSeriesColor()));
							}
							nextSerie.getNode().lookup(".chart-series-line").setStyle(lineStyle);
							nextSerie.getNode().lookup(".chart-series-line").setViewOrder(-1.0);
						}
					}
				}
			}
		}
		Legend legend = new Legend();
		if (cp.isLegendVisible()) {
			legend.setItems(legendItems);
		}
		if (chart instanceof CustomLineChart) {
			((CustomLineChart<?, ?>) chart).setLegend(legend);
		} else if (chart instanceof CustomBarChart) {
			((CustomBarChart<?, ?>) chart).setLegend(legend);
		} else {
			throw new IllegalStateException("Chart object is no instance of one of the supported types ==> addChartMarkers");
		}
	}

	private void addChartMarkers() {
		for (ChartMarker marker : cp.getChartMarkers()) {
			if (chart instanceof CustomLineChart) {
				((CustomLineChart<?, ?>) chart).addMarker(marker);
			} else if (chart instanceof CustomBarChart) {
				((CustomBarChart<?, ?>) chart).addMarker(marker);
			} else {
				throw new IllegalStateException("Chart object is no instance of one of the supported types ==> addChartMarkers");
			}
		}
	}

	private void setCssProperties() {
		// Chart general
		String chartFont = this.getStyleFromFont(cp.getChartFont());
		chart.lookup(".chart").setStyle(chartFont);

		// Title
		String titleFont = this.getStyleFromFont(cp.getTitleFont());
		String titleWrapText = String.format("-fx-wrap-text: %s;", String.valueOf(cp.isWrapTitleText()));
		String titlePadding = String.format("-fx-padding: %dpx;", cp.getTitlePadding());
		chart.lookup(".chart-title").setStyle(titleFont + titleWrapText + titlePadding);

		// Plot background
		chart.lookup(".chart-plot-background").setStyle(String.format("-fx-background-color: %s;", toRGB(cp.getPlotAreaColor())));
		// Content
		chart.lookup(".chart-content").setStyle(String.format("-fx-padding: %dpx;", cp.getChartContentPadding()));

		// Legend
		String legendColor = String.format("-fx-background-color: %s;", toRGB(cp.getLegendColor()));
		String legendFont = this.getStyleFromFont(cp.getLegendFont());
		chart.lookup(".chart-legend").setStyle(legendColor + legendFont);

		if (cp.isHorizontalGridLinesToFront()) {
			chart.lookup(".chart-horizontal-grid-lines").setViewOrder(-1.0);
		} else {
			chart.lookup(".chart-horizontal-grid-lines").setViewOrder(0.0);
		}

		if (cp.isVerticalGridLinesToFront()) {
			chart.lookup(".chart-vertical-grid-lines").setViewOrder(-1.0);
		} else {
			chart.lookup(".chart-vertical-grid-lines").setViewOrder(0.0);
		}

		chart.lookup(".chart-horizontal-grid-lines").setStyle(String.format("-fx-stroke: %s;", toRGB(cp.getHorizontalGridLinesColor())));
		chart.lookup(".chart-vertical-grid-lines").setStyle(String.format("-fx-stroke: %s;", toRGB(cp.getVerticalGridLinesColor())));

		chart.applyCss();
		chart.layout();
	}

	private String getStyleFromFont(ChartFont font) {
		String fontFamily = String.format("-fx-font-family: %s;", font.getFamily());
		String fontWeight = String.format("-fx-font-weight: %s;", font.getWeight().toLowerCase());
		String fontSize = String.format("-fx-font-size: %dpx;", font.getSize());
		return fontFamily + fontWeight + fontSize;
	}

	private String toRGB(Color color) {
		return new String("rgb(" + (int) (color.getRed() * 255) + "," + (int) (color.getGreen() * 255) + "," + (int) (color.getBlue() * 255) + ")");
	}

	protected MultiAxisChart<?, ?> getChart() {
		return chart;
	}

	protected double getWidth() {
		return cp.getWidth();
	}

	protected double getHeight() {
		return cp.getHeight();
	}

}
