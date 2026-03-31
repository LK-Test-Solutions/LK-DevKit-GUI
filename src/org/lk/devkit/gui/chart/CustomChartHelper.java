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

import java.util.List;

import com.kostikiadis.charts.MultiAxisChart.Data;

import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 * A helper class for customizing charts by setting markers on the chart.
 * This class provides functionality to position and configure markers
 * on a chart based on their associated data points and axes.
 *
 * @param <X> The type of the X-axis values.
 * @param <Y> The type of the Y-axis values.
 */
public class CustomChartHelper<X, Y> {
	/**
	 * Sets the markers on the chart based on the provided list of {@link ChartMarker}.
	 * The method calculates the position of each marker based on its associated data point
	 * and the axes provided, and applies any offsets or view order settings.
	 *
	 * @param chartMarkers A list of {@link ChartMarker} objects to be added to the chart.
	 * @param xAxis The X-axis of the chart.
	 * @param y1Axis The primary Y-axis of the chart.
	 * @param y2Axis The secondary Y-axis of the chart.
	 * @throws IllegalArgumentException If a marker's data point is not set correctly
	 *                                  for the type of axis it belongs to.
	 * @throws UnsupportedOperationException If a marker contains a child shape that is not supported.
	 */
	@SuppressWarnings("unchecked")
	public void setMarkers(List<ChartMarker> chartMarkers, Axis<X> xAxis, Axis<Y> y1Axis, Axis<Y> y2Axis) {
			
		for (ChartMarker marker : chartMarkers) {
			String xsValue = marker.getDataPoint().getXsValue();
			Number xnValue = marker.getDataPoint().getXnValue();
			String ysValue = marker.getDataPoint().getYsValue();
			Number ynValue = marker.getDataPoint().getYnValue();
			int xOffset = marker.getDataPoint().getxOffset();
			int yOffset = marker.getDataPoint().getyOffset();

			// Set the coordinates of the data point according to the x and y value that the marker should have
			Data<X, Y> point = new Data<>();
			if (xAxis instanceof CategoryAxis) {
				if (xsValue == null) {
					throw new IllegalArgumentException("xAxis is of type category but marker xValue is not set");
				} else {
					point.setXValue((X) xsValue);
				}
			} else if (xAxis instanceof NumberAxis) {
				if (xnValue.intValue() == -1) {
					throw new IllegalArgumentException("xAxis is of type number but marker xValue is not set");
				} else {
					point.setXValue((X) xnValue);
				}
			}
			if (y1Axis instanceof CategoryAxis) {
				if (ysValue == null) {
					throw new IllegalArgumentException("y1Axis is of type category but marker yValue is not set. Marker gets not added to the chart");
				} else {
					point.setYValue((Y) ysValue);
				}
			} else if (y1Axis instanceof NumberAxis) {
				if (ynValue.intValue() == -1) {
					throw new IllegalArgumentException("y1Axis is of type number but marker yValue is not set. Marker gets not added to the chart");
				} else {
					point.setYValue((Y) ynValue);
				}
			}
			point.setExtraValue(marker.getBelongingAxis());

			// Retrieve the coordinates of the data point as number to position the marker
			double xOrientation = xAxis.getDisplayPosition(point.getXValue());
			double yOrientation;
			if (marker.getBelongingAxis() == 1) {
				yOrientation = y2Axis.getDisplayPosition(point.getYValue());
			} else {
				yOrientation = y1Axis.getDisplayPosition(point.getYValue());
			}
			// Set the markers coordinates with additional offset
			for (Shape child : marker.getChildren()) {
				if (child instanceof Text) {
					Text text = (Text) child;
					if (text.getText().contentEquals("0")) {
						text.setText("");
					}
					text.setX(xOrientation);
					text.setY(yOrientation);
					text.toFront();
				} else if(child instanceof Rectangle) {
					Rectangle rec = (Rectangle) child;
					rec.setX(xOrientation);
					rec.setY(yOrientation);
					rec.toFront();
				}
				else {
					throw new UnsupportedOperationException(child.getClass().getSimpleName() + " not supported as marker");
					// Arc, Circle, Line, Rectangle
				}
				child.setViewOrder(marker.getViewOrder());
			}
			// Position the container of the marker
			if (marker.getContainer() == null) {
				marker.getMarker().setLayoutX(xOrientation + xOffset);
				marker.getMarker().setLayoutY(yOrientation + yOffset);
				marker.getMarker().setViewOrder(marker.getViewOrder());

				if (marker.getMarker() instanceof Rectangle) {
					Rectangle rec = (Rectangle) marker.getMarker();
					rec.setX(rec.getX() - (rec.getWidth() / 4));
					if (rec.getHeight() == 0.0) {
						rec.setHeight(yOrientation);
					}
				} 
			} else {
				marker.getContainer().setLayoutX(xOrientation + xOffset);
				marker.getContainer().setLayoutY(yOrientation + yOffset);
				marker.getContainer().setViewOrder(marker.getViewOrder());
			}
		}
	}
}
