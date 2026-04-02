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
package org.lk.devkit.gui.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Whenever the items of a control of javafx.scene.controls is in editing mode this class helps to 
 * display the items correctly. This is a typical JavaFX bean object.<br>
 * <br>
 * E.g. if a list view should be editable it gets initialized like this:
 * <pre>
 * {@literal ListView<ObjectPropertyWrapper<String>>} view = ...
 * </pre>
 * 
 * @author FME (LK Test Solutions)
 *
 */
public class ObjectPropertyWrapper<T> {

	/**
	 * The wrapped object stored as a JavaFX {@link ObjectProperty}, enabling
	 * property binding and change listeners on the underlying value.
	 */
	private final ObjectProperty<T> object = new SimpleObjectProperty<>();

	/**
	 * A JavaFX {@link BooleanProperty} indicating whether this item is currently
	 * filtered (i.e. hidden from view in a filtered list or control).
	 */
	private final BooleanProperty filtered = new SimpleBooleanProperty();

	/**
	 * Constructs a new {@code ObjectPropertyWrapper} and initializes it with the
	 * given object value.
	 *
	 * @param object the initial value to wrap; may be {@code null}
	 */
	public ObjectPropertyWrapper(T object) {
		setObject(object);
	}

	/**
	 * Returns the underlying {@link ObjectProperty} holding the wrapped value.
	 * Used internally to support JavaFX property binding conventions.
	 *
	 * @return the {@link ObjectProperty} for the wrapped object
	 */
	private ObjectProperty<T> objectProperty() {
		return this.object;
	}

	/**
	 * Returns the current value of the wrapped object.
	 *
	 * @return the wrapped object value, or {@code null} if none has been set
	 */
	public T getObject() {
		return this.objectProperty().get();
	}

	/**
	 * Sets the value of the wrapped object.
	 *
	 * @param object the new value to wrap; may be {@code null}
	 */
	public void setObject(T object) {
		this.objectProperty().set(object);
	}

	/**
	 * Returns the underlying {@link BooleanProperty} for the filtered state.
	 * Used internally to support JavaFX property binding conventions.
	 *
	 * @return the {@link BooleanProperty} representing the filtered state
	 */
	BooleanProperty filterProperty() {
		return this.filtered;
	}

	/**
	 * Returns whether this item is currently marked as filtered.
	 *
	 * @return {@code true} if this item is filtered (hidden); {@code false} otherwise
	 */
	public boolean isFiltered() {
		return this.filterProperty().get();
	}

	/**
	 * Sets the filtered state of this item. When set to {@code true}, the item
	 * is considered hidden or excluded from the active view or list.
	 *
	 * @param filter {@code true} to mark this item as filtered; {@code false} to make it visible
	 */
	public void setFiltered(boolean filter) {
		this.filterProperty().set(filter);
	}

	/**
	 * Returns the string representation of the wrapped object by delegating to
	 * its own {@code toString()} method.
	 *
	 * @return the string representation of the wrapped object, or {@code null} if the object is {@code null}
	 */
	@Override
	public String toString() {
		return getObject() == null ? null : getObject().toString();
	}
}
