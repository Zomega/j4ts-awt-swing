package javax.swing;

import def.dom.HTMLElement;
import java.awt.Adjustable;
import java.awt.event.AdjustmentListener;

/**
 * An implementation of a scrollbar. The user positions the knob in the scrollbar to determine the
 * contents of the viewing area.
 *
 * <p>This is a minimal stub implementation based on the JScrollBar API documentation.
 *
 * <p>TODO: AI Implemented Stub. Finish it.
 */
public class JScrollBar extends JComponent implements Adjustable {

  /**
   * The model that represents the scrollbar's minimum, maximum, extent (aka
   * [cite_start]"visibleAmount") and current value. [cite: 309, 310, 427, 428]
   */
  protected BoundedRangeModel model;

  /** [cite_start]The component's orientation (horizontal or vertical). [cite: 314, 377, 432] */
  protected int orientation;

  /**
   * [cite_start]The amount to change the scrollbar's value by, given a unit up/down request. [cite:
   * 315, 384, 436]
   */
  protected int unitIncrement;

  /**
   * [cite_start]The amount to change the scrollbar's value by, given a block up/down request.
   * [cite: 308, 383, 443]
   */
  protected int blockIncrement;

  /**
   * Creates a vertical scrollbar with the following initial values: minimum=0,
   * [cite_start]maximum=100, value=0, extent=10. [cite: 471, 475-480]
   */
  public JScrollBar() {
    this(Adjustable.VERTICAL, 0, 10, 0, 100);
  }

  /**
   * Creates a scrollbar with the specified orientation and the following initial values:
   * [cite_start]minimum=0, maximum=100, value=0, extent=10. [cite: 465-468]
   *
   * @param orientation either {@link #VERTICAL} or {@link #HORIZONTAL}
   */
  public JScrollBar(int orientation) {
    this(orientation, 0, 10, 0, 100);
  }

  /**
   * [cite_start]Creates a scrollbar with the specified orientation, value, extent, minimum, and
   * maximum. [cite: 454]
   *
   * @param orientation either {@link #VERTICAL} or {@link #HORIZONTAL}
   * @param value the initial value of the scrollbar [cite_start]@param extent the size of the
   *     viewable area, also known as the visible amount [cite: 455]
   * @param min the minimum value
   * @param max the maximum value
   */
  public JScrollBar(int orientation, int value, int extent, int min, int max) {
    this.orientation = orientation;
    this.model = new DefaultBoundedRangeModel(value, extent, min, max);
  }

  @Override
  public void createHTML() {
    // TODO: Create a scrollbar?
  }

  @Override
  public HTMLElement getHTMLElement() {
    return null;
  }

  //
  // Implementation of the Adjustable interface
  //

  public int getOrientation() {
    return orientation;
  }

  public void setOrientation(int orientation) {
    if (orientation != Adjustable.HORIZONTAL && orientation != Adjustable.VERTICAL) {
      throw new IllegalArgumentException("invalid orientation");
    }
    this.orientation = orientation;
  }

  public int getValue() {
    return model.getValue();
  }

  public void setValue(int value) {
    model.setValue(value);
  }

  public int getVisibleAmount() {
    return model.getExtent();
  }

  public void setVisibleAmount(int newExtent) {
    model.setExtent(newExtent);
  }

  public int getMinimum() {
    return model.getMinimum();
  }

  public void setMinimum(int newMinimum) {
    model.setMinimum(newMinimum);
  }

  public int getMaximum() {
    return model.getMaximum();
  }

  public void setMaximum(int newMaximum) {
    model.setMaximum(newMaximum);
  }

  public int getUnitIncrement() {
    return unitIncrement;
  }

  public void setUnitIncrement(int unitIncrement) {
    this.unitIncrement = unitIncrement;
  }

  public int getBlockIncrement() {
    return blockIncrement;
  }

  public void setBlockIncrement(int blockIncrement) {
    this.blockIncrement = blockIncrement;
  }

  public void addAdjustmentListener(AdjustmentListener l) {
    // TODO Not implemented for this minimal stub
  }

  public void removeAdjustmentListener(AdjustmentListener l) {
    // TODO Not implemented for this minimal stub
  }

  public void setValues(int newValue, int newExtent, int newMin, int newMax) {
    model.setRangeProperties(newValue, newExtent, newMin, newMax, false);
  }
}
