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

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * This class gets used to store border information for the chart creation. The
 * <code>ChartBorder.border(..)</code> methods simply create a new border to use
 * it for further operations. Constructor initialization is possible, too, to
 * change the full number of properties.
 * 
 * @author FME (LK Test Solutions)
 *
 */
public final class ChartBorder {

	private Color colorTop = Color.TRANSPARENT;
	private Color colorRight = Color.TRANSPARENT;
	private Color colorBottom = Color.TRANSPARENT;
	private Color colorLeft = Color.TRANSPARENT;

	private BorderStrokeStyle styleTop = BorderStrokeStyle.SOLID;
	private BorderStrokeStyle styleRight = BorderStrokeStyle.SOLID;
	private BorderStrokeStyle styleBottom = BorderStrokeStyle.SOLID;
	private BorderStrokeStyle styleLeft = BorderStrokeStyle.SOLID;

	private CornerRadii radii = CornerRadii.EMPTY;
	private BorderWidths widths = BorderWidths.DEFAULT;
	private Insets insets = Insets.EMPTY;

	private ChartBorder() {
		super();
	}

	private ChartBorder(Color colorTop, Color colorRight, Color colorBottom, Color colorLeft) {
		setColorTop(colorTop);
		setColorRight(colorRight);
		setColorBottom(colorBottom);
		setColorLeft(colorLeft);
	}

	public static ChartBorder border() {
		return new ChartBorder();
	}

	public static ChartBorder border(Color colorTop, Color colorRight, Color colorBottom, Color colorLeft) {
		return new ChartBorder(colorTop, colorRight, colorBottom, colorLeft);
	}

	public Border getBorder() {
		return new Border(new BorderStroke(colorTop, colorRight, colorBottom, colorLeft, styleTop, styleRight, styleBottom, styleLeft, radii, widths, insets));
	}

	public void setColorTop(Color color) {
		this.colorTop = color;
	}

	public void setColorRight(Color color) {
		this.colorRight = color;
	}

	public void setColorBottom(Color color) {
		this.colorBottom = color;
	}

	public void setColorLeft(Color color) {
		this.colorLeft = color;
	}

	public void setStyleTop(BorderStrokeStyle style) {
		this.styleTop = style;
	}

	public void setStyleRight(BorderStrokeStyle style) {
		this.styleRight = style;
	}

	public void setStyleBottom(BorderStrokeStyle style) {
		this.styleBottom = style;
	}

	public void setStyleLeft(BorderStrokeStyle style) {
		this.styleLeft = style;
	}

	public void setRadii(CornerRadii value) {
		this.radii = value;
	}

	public void setWidths(BorderWidths value) {
		this.widths = value;
	}

	public void setInsets(Insets value) {
		this.insets = value;
	}

}
