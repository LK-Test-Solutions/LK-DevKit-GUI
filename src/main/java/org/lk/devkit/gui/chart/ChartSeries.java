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

import java.util.ArrayList;
import java.util.List;

import com.kostikiadis.charts.MultiAxisChart;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class has all properties to define a series (chart data) object for the
 * chart creation.<br>
 * <br>
 * Sample usage:
 * 
 * <pre>
 * ChartProperties props = new ChartProperties();
 * List seriesValues = new ArrayList();
 * ChartSeries series = new ChartSeries();
 * series.setSeriesName("Name");
 * series.setSeriesColor(Color.STEELBLUE);
 * series.setSeriesLabelVisible(false);
 * ...
 * seriesValues.add(series);
 * cp.setSeriesValues(seriesValues);
 * </pre>
 * 
 * @author FME (LK Test Solutions)
 *
 */
public final class ChartSeries {

	private String seriesName = "";
	private List<String> seriesValues = new ArrayList<>();
	private Shape seriesSymbol = new Circle(5, Color.STEELBLUE);
	private boolean valuesConnected = true;
	private Color seriesColor = Color.STEELBLUE;
	private int seriesFontSize = 12;
	private int seriesSymbolSize = 5;
	private boolean seriesLabelVisible = true;
	private Shape seriesLegendNode = new Rectangle(10, 10, 10, 10);
	private int belongingAxis = MultiAxisChart.Y1_AXIS;

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String name) {
		this.seriesName = name;
	}

	public List<String> getSeriesValues() {
		return seriesValues;
	}

	public void setSeriesValues(List<String> values) {
		this.seriesValues = values;
	}

	public Shape getSeriesSymbol() {
		return seriesSymbol;
	}

	public void setSeriesSymbol(Shape symbol) {
		this.seriesSymbol = symbol;
	}

	public boolean isValuesConnected() {
		return valuesConnected;
	}

	public void setValuesConnected(boolean valuesConnected) {
		this.valuesConnected = valuesConnected;
	}

	public Color getSeriesColor() {
		return seriesColor;
	}

	public void setSeriesColor(Color color) {
		this.seriesColor = color;
	}

	public int getSeriesFontSize() {
		return seriesFontSize;
	}

	public void setSeriesFontSize(int size) {
		this.seriesFontSize = size;
	}

	public int getSeriesSymbolSize() {
		return seriesSymbolSize;
	}

	public void setSeriesSymbolSize(int size) {
		this.seriesSymbolSize = size;
	}

	public boolean isSeriesLabelVisible() {
		return seriesLabelVisible;
	}

	public void setSeriesLabelVisible(boolean seriesLabelVisible) {
		this.seriesLabelVisible = seriesLabelVisible;
	}

	public Shape getSeriesLegendNode() {
		return seriesLegendNode;
	}

	public void setSeriesLegendNode(Shape node) {
		this.seriesLegendNode = node;
	}

	public int getBelongingAxis() {
		return belongingAxis;
	}

	public void setBelongingAxis(int type) {
		this.belongingAxis = type;
	}

}
