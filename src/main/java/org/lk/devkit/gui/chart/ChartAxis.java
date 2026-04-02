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

/**
 * This class has all properties to define an axis object for the chart creation.<br>
 * <br>
 * Sample usage:
 * 
 * <pre>
 * ChartProperties props = new ChartProperties();
 * ChartAxis xAxis = new ChartAxis();
 * xAxis.setLabel(..);
 * ..
 * props.setxAxis(xAxis);
 * 
 * ChartAxis y1Axis = new ChartAxis();
 * y1Axis.setLabel(..);
 * ..
 * props.setY1Axis(y1Axis);
 *
 * </pre>
 * 
 * @author FME (LK Test Solutions)
 *
 */
public final class ChartAxis {

	private static final int maxChars = 10000;
	private static final int maxFontSize = 1638;
	private static final int maxMargin = 10000;
	private static final int maxMinorTickCount = 1000;
	private static final int maxTickLength = 10000;

	/** Axis values if the axis is categorical and not numeric. */
	private transient List<String> categories = new ArrayList<>();
	/** Space between the axis and the plot area end. For categorical axis only. */
	private int endMargin = 30;
	/** Displays the zero on the axis even if it is not part of the values. For numerical axis only. */
	private boolean forceZeroInRange = false;
	/** Label text of the axis. */
	private String label = "";
	/** Object to define font, font size and family of the axis label. */
	private ChartFont labelFont = ChartFont.font(16);
	/** Padding left, right, top and bottom of the axis label. */
	private int labelPadding = 10;
	/** First value to display on the axis. */
	private double lowestValue = 0;
	/** Number of ticks between two axis values. */
	private int minorTickCount = 0;
	/** Size in pixel of the minor ticks. */
	private double minorTickLength = 10;
	/** Show or disable the minor tick marks. */
	private boolean minorTickMarksVisible = true;
	/** Space between the axis and the plot area start. For categorical axis only. */
	private int startMargin = 10;
	/** Define the color of the axis tick label (e.g. <code>Color.BLACK</code>). <code>Color.valueOf(..)</code> takes color strings (like 'white'), RGB values (like 'rgb(255,255,255)') or HEX strings. */
	private String tickLabelColor = "BLACK";
	/** Object to define the font of the axis tick label. Use <code>Font.font(..)</code> to define one. */
	private ChartFont tickLabelFont = ChartFont.font(16);
	/** The space between to tick labels in pixel. */
	private int tickLabelGap = 5;
	/** Degree value to define the axis label rotation. Default value is 0 (horizontal). */
	private int tickLabelRotation = 0;
	/** Possibility to show or disable the tick label. */
	private boolean tickLabelsVisible = true;
	/** Size in pixel of the axis tick marks. */
	private double tickLength = 10;
	/** Define the color of the axis tick mark (e.g. <code>Color.BLACK</code>). <code>Color.valueOf(..)</code> takes color strings (like 'white'), RGB values (like 'rgb(255,255,255)') or HEX strings. */
	private String tickMarkColor = "BLACK";
	/** Define the color of the minor tick mark (e.g. <code>Color.BLACK</code>). <code>Color.valueOf(..)</code> takes color strings (like 'white'), RGB values (like 'rgb(255,255,255)') or HEX strings. */
	private String minorTickMarkColor = "BLACK";
	/** Possibility to show or disable the tick marks. */
	private boolean tickMarksVisible = true;
	/** Maximum value of the axis. */
	private transient double valueRange = 0;
	/** Step size of each tick mark. This value should be a divisor of {@link #valueRange} to avoid the last tick to be smaller than the rest. */
	private transient double valueStep = 0;
	/** If true one additional tick mark will be shown. */
	private boolean leaveOneStepSpace = false;

	/**
	 * @return {@link #categories}
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * @return {@link #endMargin}
	 */
	public int getEndMargin() {
		return endMargin;
	}

	/**
	 * @return {@link #forceZeroInRange}
	 */
	public boolean getForceZeroInRange() {
		return forceZeroInRange;
	}

	/**
	 * @return {@link #label}
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return {@link #labelFont}
	 */
	public ChartFont getLabelFont() {
		return labelFont;
	}

	/**
	 * @return {@link #labelPadding}
	 */
	public int getLabelPadding() {
		return labelPadding;
	}

	/**
	 * @return {@link #lowestValue}
	 */
	public double getLowestValue() {
		return lowestValue;
	}

	/**
	 * @return {@link #minorTickCount}
	 */
	public int getMinorTickCount() {
		return minorTickCount;
	}

	/**
	 * @return {@link #minorTickLength}
	 */
	public double getMinorTickLength() {
		return minorTickLength;
	}

	/**
	 * @return {@link #minorTickMarksVisible}
	 */
	public boolean getMinorTickMarksVisible() {
		return minorTickMarksVisible;
	}

	/**
	 * @return {@link #startMargin}
	 */
	public int getStartMargin() {
		return startMargin;
	}

	/**
	 * @return {@link #tickLabelColor}
	 */
	public String getTickLabelColor() {
		return tickLabelColor;
	}

	/**
	 * @return {@link #tickLabelFont}
	 */
	public ChartFont getTickLabelFont() {
		return tickLabelFont;
	}

	/**
	 * @return {@link #tickLabelGap}
	 */
	public int getTickLabelGap() {
		return tickLabelGap;
	}

	/**
	 * @return {@link #tickLabelRotation}
	 */
	public int getTickLabelRotation() {
		return tickLabelRotation;
	}

	/**
	 * @return {@link #tickLabelsVisible}
	 */
	public boolean getTickLabelsVisible() {
		return tickLabelsVisible;
	}

	/**
	 * @return {@link #tickLength}
	 */
	public double getTickLength() {
		return tickLength;
	}

	/**
	 * @return {@link #tickMarkColor}
	 */
	public String getTickMarkColor() {
		return tickMarkColor;
	}

	/**
	 * @return {@link #minorTickMarkColor}
	 */
	public String getMinorTickMarkColor() {
		return minorTickMarkColor;
	}

	/**
	 * @return {@link #tickMarksVisible}
	 */
	public boolean getTickMarksVisible() {
		return tickMarksVisible;
	}

	/**
	 * @return {@link #valueRange}
	 */
	public double getValueRange() {
		return valueRange;
	}

	/**
	 * @return {@link #valueStep}
	 */
	public double getValueStep() {
		return valueStep;
	}

	/**
	 * @return {@link #leaveOneStepSpace}
	 */
	public boolean isLeaveOneStepSpace() {
		return leaveOneStepSpace;
	}

	public void setCategories(List<String> values) {
		if (values == null || values.isEmpty()) {
			throw new IllegalArgumentException("Values are null, empty or out of range ==> No values to add");
		}
		this.categories = values;
	}

	public void setEndMargin(int size) {
		if (size < 0 || size > maxMargin) {
			throw new IllegalArgumentException("End margin out of range ==> Allowed: 0 to " + maxMargin);
		}
		this.endMargin = size;
	}

	public void setForceZeroInRange(boolean forceZeroInRange) {
		this.forceZeroInRange = forceZeroInRange;
	}

	public void setLabel(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Label is null");
		}
		this.label = name;
	}

	public void setLabelFont(ChartFont font) {
		this.labelFont = font;
	}

	public void setLabelPadding(int pixelSize) {
		if (pixelSize < 0 || pixelSize > maxFontSize) {
			throw new IllegalArgumentException("Label padding pixel size is out of range (< 0 or > " + maxFontSize + ")");
		}
		this.labelPadding = pixelSize;
	}

	public void setLowestValue(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Lowest axis value out of range");
		}
		this.lowestValue = value;
	}

	public void setMinorTickCount(int value) {
		if (value < 0 || value > maxMinorTickCount) {
			throw new IllegalArgumentException("Minor tick count out of range");
		}
		this.minorTickCount = value;
	}

	public void setMinorTickLength(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value) || value < 0 || value > maxTickLength) {
			throw new IllegalArgumentException("Minor tick length out of long range");
		}
		this.minorTickLength = value;
	}

	public void setMinorTickMarksVisible(boolean isVisible) {
		this.minorTickMarksVisible = isVisible;
	}

	public void setStartMargin(int size) {
		if (size < 0 || size > maxMargin) {
			throw new IllegalArgumentException("Start margin out of range ==> Allowed: 0 to " + maxMargin);
		}
		this.startMargin = size;
	}

	public void setTickLabelColor(String color) {
		this.tickLabelColor = color;
	}

	public void setTickLabelFont(ChartFont font) {
		this.tickLabelFont = font;
	}

	public void setTickLabelGap(int size) {
		if (size < 0 || size > maxFontSize) {
			throw new IllegalArgumentException("Tick label gap is out of range (< 0 or > " + maxFontSize + ")");
		}
		this.tickLabelGap = size;
	}

	public void setTickLabelRotation(int degree) {
		if (degree < 0 || degree > 360) {
			throw new IllegalArgumentException("Tick label rotation degree value is out of range (< 0 or > 360)");
		}
		this.tickLabelRotation = degree;
	}

	public void setTickLabelsVisible(boolean isVisible) {
		this.tickLabelsVisible = isVisible;
	}

	public void setTickLength(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value) || value < 0 || value > maxTickLength) {
			throw new IllegalArgumentException("Tick length out of range");
		}
		this.tickLength = value;
	}

	public void setTickMarkColor(String color) {
		this.tickMarkColor = color;
	}

	public void setMinorTickMarkColor(String color) {
		this.minorTickMarkColor = color;
	}

	public void setTickMarksVisible(boolean isVisible) {
		this.tickMarksVisible = isVisible;
	}

	public void setValueRange(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Value range out of range");
		}
		this.valueRange = value;
	}

	public void setValueStep(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Value step out of range");
		}
		this.valueStep = value;
	}

	public void setLeaveOneStepSpace(boolean leaveOneStepSpace) {
		this.leaveOneStepSpace = leaveOneStepSpace;
	}

}
