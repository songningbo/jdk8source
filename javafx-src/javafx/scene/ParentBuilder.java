/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javafx.scene;

/**
Builder class for javafx.scene.Parent
@see javafx.scene.Parent
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.0
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public abstract class ParentBuilder<B extends javafx.scene.ParentBuilder<B>> extends javafx.scene.NodeBuilder<B> {
    protected ParentBuilder() {
    }


    private int __set;
    public void applyTo(javafx.scene.Parent x) {
        super.applyTo(x);
        int set = __set;
        if ((set & (1 << 1)) != 0) x.getStylesheets().addAll(this.stylesheets);
    }

    private java.util.Collection<? extends java.lang.String> stylesheets;
    /**
    Add the given items to the List of items in the {@link javafx.scene.Parent#getStylesheets() stylesheets} property for the instance constructed by this builder.
    * @since JavaFX 2.1
    */
    @SuppressWarnings("unchecked")
    public B stylesheets(java.util.Collection<? extends java.lang.String> x) {
        this.stylesheets = x;
        __set |= 1 << 1;
        return (B) this;
    }

    /**
    Add the given items to the List of items in the {@link javafx.scene.Parent#getStylesheets() stylesheets} property for the instance constructed by this builder.
    * @since JavaFX 2.1
    */
    public B stylesheets(java.lang.String... x) {
        return stylesheets(java.util.Arrays.asList(x));
    }

}
