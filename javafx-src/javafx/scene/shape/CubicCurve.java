/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
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

package javafx.scene.shape;

import com.sun.javafx.geom.CubicCurve2D;
import com.sun.javafx.scene.DirtyBits;
import com.sun.javafx.sg.prism.NGCubicCurve;
import com.sun.javafx.sg.prism.NGNode;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.paint.Paint;


/**
 * <p>The {@code CubiCurve} class defines a cubic B&eacute;zier parametric curve segment
 * in (x,y) coordinate space. Drawing a curve that intersects both the specified
 * coordinates {@code (startX, startY)} and {@code (endX, enfY)}, using the
 * specified points {@code (controlX1, controlY1)} and {@code (controlX2, controlY2)}
 * as B&eacute;zier control points.
 * Example:</p>
 *
<PRE>
import javafx.scene.shape.*;

CubicCurve cubic = new CubicCurve();
cubic.setStartX(0.0f);
cubic.setStartY(50.0f);
cubic.setControlX1(25.0f);
cubic.setControlY1(0.0f);
cubic.setControlX2(75.0f);
cubic.setControlY2(100.0f);
cubic.setEndX(100.0f);
cubic.setEndY(50.0f);
}
</PRE>
 * @since JavaFX 2.0
 */
public class CubicCurve extends Shape {

    private final CubicCurve2D shape = new CubicCurve2D();
    /**
     * Defines the X coordinate of the start point of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty startX;

    /**
     * Creates an empty instance of CubicCurve.
     */
    public CubicCurve() {
    }

    /**
     * Creates a new instance of CubicCurve.
     * @param startX the X coordinate of the start point
     * @param startY the Y coordinate of the start point
     * @param controlX1 the X coordinate of the first control point
     * @param controlY1 the Y coordinate of the first control point
     * @param controlX2 the X coordinate of the second control point
     * @param controlY2 the Y coordinate of the second control point
     * @param endX the X coordinate of the end point
     * @param endY the Y coordinate of the end point
     * @since JavaFX 2.1
     */
    public CubicCurve(double startX, double startY, double controlX1,
            double controlY1, double controlX2, double controlY2,
            double endX, double endY) {
        setStartX(startX);
        setStartY(startY);
        setControlX1(controlX1);
        setControlY1(controlY1);
        setControlX2(controlX2);
        setControlY2(controlY2);
        setEndX(endX);
        setEndY(endY);
    }

    public final void setStartX(double value) {
        if (startX != null || value != 0.0) {
            startXProperty().set(value);
        }
    }

    public final double getStartX() {
        return startX == null ? 0.0 : startX.get();
    }

    public final DoubleProperty startXProperty() {
        if (startX == null) {
            startX = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "startX";
                }
            };
        }
        return startX;
    }

    /**
     * Defines the Y coordinate of the start point of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty startY;

    public final void setStartY(double value) {
        if (startY != null || value != 0.0) {
            startYProperty().set(value);
        }
    }

    public final double getStartY() {
        return startY == null ? 0.0 : startY.get();
    }

    public final DoubleProperty startYProperty() {
        if (startY == null) {
            startY = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "startY";
                }
            };
        }
        return startY;
    }

    /**
     * Defines the X coordinate of the first control point
     * of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty controlX1;

    public final void setControlX1(double value) {
        if (controlX1 != null || value != 0.0) {
            controlX1Property().set(value);
        }
    }

    public final double getControlX1() {
        return controlX1 == null ? 0.0 : controlX1.get();
    }

    public final DoubleProperty controlX1Property() {
        if (controlX1 == null) {
            controlX1 = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "controlX1";
                }
            };
        }
        return controlX1;
    }

    /**
     * Defines the Y coordinate of the first control point
     * of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty controlY1;

    public final void setControlY1(double value) {
        if (controlY1 != null || value != 0.0) {
            controlY1Property().set(value);
        }
    }

    public final double getControlY1() {
        return controlY1 == null ? 0.0 : controlY1.get();
    }

    public final DoubleProperty controlY1Property() {
        if (controlY1 == null) {
            controlY1 = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "controlY1";
                }
            };
        }
        return controlY1;
    }

    /**
     * Defines the X coordinate of the second control point
     * of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty controlX2;

    public final void setControlX2(double value) {
        if (controlX2 != null || value != 0.0) {
            controlX2Property().set(value);
        }
    }

    public final double getControlX2() {
        return controlX2 == null ? 0.0 : controlX2.get();
    }

    public final DoubleProperty controlX2Property() {
        if (controlX2 == null) {
            controlX2 = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "controlX2";
                }
            };
        }
        return controlX2;
    }

    /**
     * Defines the Y coordinate of the second control point
     * of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty controlY2;

    public final void setControlY2(double value) {
        if (controlY2 != null || value != 0.0) {
            controlY2Property().set(value);
        }
    }

    public final double getControlY2() {
        return controlY2 == null ? 0.0 : controlY2.get();
    }

    public final DoubleProperty controlY2Property() {
        if (controlY2 == null) {
            controlY2 = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "controlY2";
                }
            };
        }
        return controlY2;
    }

    /**
     * Defines the X coordinate of the end point of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty endX;

    public final void setEndX(double value) {
        if (endX != null || value != 0.0) {
            endXProperty().set(value);
        }
    }

    public final double getEndX() {
        return endX == null ? 0.0 : endX.get();
    }

    public final DoubleProperty endXProperty() {
        if (endX == null) {
            endX = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "endX";
                }
            };
        }
        return endX;
    }

    /**
     * Defines the Y coordinate of the end point of the cubic curve segment.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty endY;

    public final void setEndY(double value) {
        if (endY != null || value != 0.0) {
            endYProperty().set(value);
        }
    }

    public final double getEndY() {
        return endY == null ? 0.0 : endY.get();
    }

    public final DoubleProperty endYProperty() {
        if (endY == null) {
            endY = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return CubicCurve.this;
                }

                @Override
                public String getName() {
                    return "endY";
                }
            };
        }
        return endY;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override public CubicCurve2D impl_configShape() {
        shape.x1 = (float)getStartX();
        shape.y1 = (float)getStartY();
        shape.ctrlx1 = (float)getControlX1();
        shape.ctrly1 = (float)getControlY1();
        shape.ctrlx2 = (float)getControlX2();
        shape.ctrly2 = (float)getControlY2();
        shape.x2 = (float)getEndX();
        shape.y2 = (float)getEndY();
        return shape;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override protected NGNode impl_createPeer() {
        return new NGCubicCurve();
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override public void impl_updatePeer() {
        super.impl_updatePeer();

        if (impl_isDirty(DirtyBits.NODE_GEOMETRY)) {
            final NGCubicCurve peer = impl_getPeer();
            peer.updateCubicCurve((float)getStartX(),
                (float)getStartY(),
                (float)getEndX(),
                (float)getEndY(),
                (float)getControlX1(),
                (float)getControlY1(),
                (float)getControlX2(),
                (float)getControlY2());
        }
    }

    /**
     * Returns a string representation of this {@code CubicCurve} object.
     * @return a string representation of this {@code CubicCurve} object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CubicCurve[");

        String id = getId();
        if (id != null) {
            sb.append("id=").append(id).append(", ");
        }

        sb.append("startX=").append(getStartX());
        sb.append(", startY=").append(getStartY());
        sb.append(", controlX1=").append(getControlX1());
        sb.append(", controlY1=").append(getControlY1());
        sb.append(", controlX2=").append(getControlX2());
        sb.append(", controlY2=").append(getControlY2());
        sb.append(", endX=").append(getEndX());
        sb.append(", endY=").append(getEndY());

        sb.append(", fill=").append(getFill());

        Paint stroke = getStroke();
        if (stroke != null) {
            sb.append(", stroke=").append(stroke);
            sb.append(", strokeWidth=").append(getStrokeWidth());
        }

        return sb.append("]").toString();
    }
}

