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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.paint.Color;

/**
 * This class has all properties to trigger the chart creation.<br>
 * <br>
 * Sample usage:
 * 
 * <pre>
 * ChartProperties cp = new ChartProperties();
 * cp.setTitle("Title");
 * cp.setPlotAreaColor(Color.valueOf("rgb(188,222,255)"));
 * cp.setLegendColor(Color.valueOf("rgb(222,222,222)"));
 * cp.setTitleFont(ChartFont.font(18, FontWeight.BOLD));
 * cp.setHeight(741);
 * cp.setWidth(1286);
 * cp.setLegendSide(Side.BOTTOM);
 * ...
 * ChartCreatorPlugin chartPlugin = new ChartCreatorPlugin("LINE", "./output/LineChart.png", cp);
 * chartPlugin.run();
 * if (chartPlugin.isSuccess() == false) {
 * 	Assert.fail("Chart creation finished with error " + getClass().getSimpleName());
 * }
 * </pre>
 * 
 * @author FME (LK Test Solutions)
 *
 */
// TODO lombok getter
public final class ChartProperties {

	private static final int maxChartSize = 10000;
	private static final int maxFontSize = 1638;
//	private static final int maxChars = 10000;

	private int categoryGap = 10;
	private Color chartBackground = Color.WHITE;
	private int chartContentPadding = 10;
	private ChartFont chartFont = ChartFont.font(16);
	private List<ChartMarker> chartMarkers = new ArrayList<>();
	private int height = 435;
	private boolean horizontalGridLinesVisible = true;
	private boolean horizontalZeroLineVisible = true;
	private Color legendColor = Color.WHITE;
	private ChartFont legendFont = ChartFont.font(16);
	private Side legendSide = Side.BOTTOM;
	private boolean legendVisible = true;
	private Color plotAreaColor = Color.WHITE;
	private List<ChartSeries> seriesValues = new ArrayList<>();
	private String title = "";
	private ChartFont titleFont = ChartFont.font(16);
	private int titlePadding = 10;
	private Side titleSide = Side.TOP;
	private boolean verticalGridLinesVisible = true;
	private boolean verticalZeroLineVisible = true;
	private int width = 768;
	private boolean wrapTitleText = true;
	private boolean horizontalGridLinesToFront = false;
	private Color horizontalGridLinesColor = Color.GAINSBORO;
	private boolean verticalGridLinesToFront = true;
	private Color verticalGridLinesColor = Color.GAINSBORO;
	private Insets chartPadding = Insets.EMPTY;
	private NumberFormat numberAxisFormat = new DecimalFormat();

	private ChartAxis xAxis = new ChartAxis();
	private ChartAxis y1Axis = new ChartAxis();
	private ChartAxis y2Axis = new ChartAxis();

	private ChartBorder xAxisBorder = ChartBorder.border(Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
	private ChartBorder y1AxisBorder = ChartBorder.border(Color.TRANSPARENT, Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT);
	private ChartBorder y2AxisBorder = ChartBorder.border(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.BLACK);

	public int getCategoryGap() {
		return categoryGap;
	}

	public Color getChartBackground() {
		return chartBackground;
	}

	public int getChartContentPadding() {
		return chartContentPadding;
	}

	public ChartFont getChartFont() {
		return chartFont;
	}

	public List<ChartMarker> getChartMarkers() {
		return chartMarkers;
	}

	public int getHeight() {
		return height;
	}

	public boolean isHorizontalGridLinesVisible() {
		return horizontalGridLinesVisible;
	}

	public boolean isHorizontalZeroLineVisible() {
		return horizontalZeroLineVisible;
	}

	public Color getLegendColor() {
		return legendColor;
	}

	public ChartFont getLegendFont() {
		return legendFont;
	}

	public Side getLegendSide() {
		return legendSide;
	}

	public boolean isLegendVisible() {
		return legendVisible;
	}

	public Color getPlotAreaColor() {
		return plotAreaColor;
	}

	public List<ChartSeries> getSeriesValues() {
		return seriesValues;
	}

	public String getTitle() {
		return title;
	}

	public ChartFont getTitleFont() {
		return titleFont;
	}

	public int getTitlePadding() {
		return titlePadding;
	}

	public Side getTitleSide() {
		return titleSide;
	}

	public boolean isVerticalGridLinesVisible() {
		return verticalGridLinesVisible;
	}

	public boolean isVerticalZeroLineVisible() {
		return verticalZeroLineVisible;
	}

	public int getWidth() {
		return width;
	}

	public boolean isWrapTitleText() {
		return wrapTitleText;
	}

	public boolean isHorizontalGridLinesToFront() {
		return horizontalGridLinesToFront;
	}

	public Color getHorizontalGridLinesColor() {
		return horizontalGridLinesColor;
	}

	public boolean isVerticalGridLinesToFront() {
		return verticalGridLinesToFront;
	}

	public Color getVerticalGridLinesColor() {
		return verticalGridLinesColor;
	}

	public Insets getChartPadding() {
		return chartPadding;
	}

	public ChartAxis getxAxis() {
		return xAxis;
	}

	public ChartAxis getY1Axis() {
		return y1Axis;
	}

	public ChartAxis getY2Axis() {
		return y2Axis;
	}

	public ChartBorder getxAxisBorder() {
		return xAxisBorder;
	}

	public ChartBorder getY1AxisBorder() {
		return y1AxisBorder;
	}

	public ChartBorder getY2AxisBorder() {
		return y2AxisBorder;
	}
	
	public NumberFormat getNumberAxisFormat() {
		return numberAxisFormat;
	}

	public void setCategoryGap(int size) {
		if (size < 0 || size > maxFontSize) {
			throw new IllegalArgumentException("Out of range ==> Allowed: 0 to " + maxFontSize);
		}
		this.categoryGap = size;
	}

	public void setChartBackground(Color color) {
		this.chartBackground = color;
	}

	public void setChartContentPadding(int pixelSize) {
		if (pixelSize < 0 || pixelSize > maxFontSize) {
			throw new IllegalArgumentException("Out of range ==> Allowed 0 to " + maxFontSize);
		}
		this.chartContentPadding = pixelSize;
	}

	public void setChartFont(ChartFont font) {
		this.chartFont = font;
	}

	public void setChartMarkers(List<ChartMarker> markers) {
		if (markers == null || markers.isEmpty()) {
			throw new IllegalArgumentException("Markers are null or empty");
		}
		this.chartMarkers = markers;
	}

	public void setHeight(int pixelSize) {
		if (pixelSize < 0 || pixelSize > maxChartSize) {
			throw new IllegalArgumentException("Out of range ==> Allowed: 0 to " + maxChartSize);
		}
		this.height = pixelSize;
	}

	public void setHorizontalGridLinesVisible(boolean isVisible) {
		this.horizontalGridLinesVisible = isVisible;
	}

	public void setHorizontalZeroLineVisible(boolean isVisible) {
		this.horizontalZeroLineVisible = isVisible;
	}

	public void setLegendColor(Color color) {
		this.legendColor = color;
	}

	public void setLegendFont(ChartFont font) {
		this.legendFont = font;
	}

	public void setLegendSide(Side side) {
		this.legendSide = side;
	}

	public void setLegendVisible(boolean isVisible) {
		this.legendVisible = isVisible;
	}

	public void setPlotAreaColor(Color color) {
		this.plotAreaColor = color;
	}

	public void setSeriesValues(List<ChartSeries> values) {
		this.seriesValues = values;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleFont(ChartFont font) {
		this.titleFont = font;
	}

	public void setTitlePadding(int pixelSize) {
		if (pixelSize < 0 || pixelSize > maxFontSize) {
			throw new IllegalArgumentException("Out of range ==> Allowed: 0 to " + maxFontSize);
		}
		this.titlePadding = pixelSize;
	}

	public void setTitleSide(Side side) {
		this.titleSide = side;
	}

	public void setVerticalGridLinesVisible(boolean isVisible) {
		this.verticalGridLinesVisible = isVisible;
	}

	public void setVerticalZeroLineVisible(boolean isVisible) {
		this.verticalZeroLineVisible = isVisible;
	}

	public void setWidth(int pixelSize) {
		if (pixelSize < 0 || pixelSize > maxChartSize) {
			throw new IllegalArgumentException("Out of range ==> Allowed: 0 to " + maxChartSize);
		}
		this.width = pixelSize;
	}

	public void setWrapTitleText(boolean wrapTitleText) {
		this.wrapTitleText = wrapTitleText;
	}

	public void setxAxis(ChartAxis axis) {
		this.xAxis = axis;
	}

	public void setY1Axis(ChartAxis axis) {
		this.y1Axis = axis;
	}

	public void setY2Axis(ChartAxis axis) {
		this.y2Axis = axis;
	}

	public void setxAxisBorder(ChartBorder border) {
		this.xAxisBorder = border;
	}

	public void setY1AxisBorder(ChartBorder border) {
		this.y1AxisBorder = border;
	}

	public void setY2AxisBorder(ChartBorder border) {
		this.y2AxisBorder = border;
	}

	public void setHorizontalGridLinesToFront(boolean horizontalGridLinesToFront) {
		this.horizontalGridLinesToFront = horizontalGridLinesToFront;
	}

	public void setHorizontalGridLinesColor(Color horizontalGridLinesColor) {
		this.horizontalGridLinesColor = horizontalGridLinesColor;
	}

	public void setVerticalGridLinesToFront(boolean verticalGridLinesToFront) {
		this.verticalGridLinesToFront = verticalGridLinesToFront;
	}

	public void setVerticalGridLinesColor(Color color) {
		this.verticalGridLinesColor = color;
	}

	public void setChartPadding(Insets chartPadding) {
		this.chartPadding = chartPadding;
	}

	public void setNumberAxisFormat(NumberFormat format) {
		this.numberAxisFormat = format;
	}
}
