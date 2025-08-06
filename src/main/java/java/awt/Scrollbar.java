package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLDivElement;
import def.dom.HTMLInputElement;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import jsweet.util.Lang;

/**
 * An implementation of a scrollbar.
 *
 * <p>This is a minimal stub implementation based on the Scrollbar API documentation.
 */
public class Scrollbar extends Component implements Adjustable {

  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;

  private int orientation;
  private int value;
  private int visibleAmount;
  private int minimum;
  private int maximum;
  private int unitIncrement = 1;
  private int blockIncrement = 10;

  private HTMLInputElement inputElement;
  private List<AdjustmentListener> adjustmentListeners = new ArrayList<>();

  /**
   * Constructs a new vertical scroll bar. The default properties are: orientation=VERTICAL,
   * value=0, visibleAmount=10, minimum=0, maximum=100.
   */
  public Scrollbar() {
    this(VERTICAL, 0, 10, 0, 100);
  }

  /**
   * Constructs a new scroll bar with the specified orientation. The other properties are set to
   * their default values.
   *
   * @param orientation either {@link #VERTICAL} or {@link #HORIZONTAL}
   */
  public Scrollbar(int orientation) {
    this(orientation, 0, 10, 0, 100);
  }

  /**
   * Constructs a new scroll bar with the specified orientation, initial value, visible amount, and
   * minimum and maximum values.
   *
   * @param orientation either {@link #VERTICAL} or {@link #HORIZONTAL}
   * @param value the initial value of the scroll bar
   * @param visible the visible amount of the scroll bar
   * @param minimum the minimum value of the scroll bar
   * @param maximum the maximum value of the scroll bar
   */
  public Scrollbar(int orientation, int value, int visible, int minimum, int maximum) {
    if (orientation != HORIZONTAL && orientation != VERTICAL) {
      throw new IllegalArgumentException("invalid orientation");
    }
    this.orientation = orientation;
    setValues(value, visible, minimum, maximum);
  }

  @Override
  public void createHTML() {
    HTMLDivElement container = (HTMLDivElement) document.createElement("div");
    container.style.position = "relative";

    inputElement = (HTMLInputElement) document.createElement("input");
    inputElement.type = "range";

    if (orientation == HORIZONTAL) {
      inputElement.style.width = "100%";
      inputElement.style.height = "100%";
    } else {
      inputElement.style.transformOrigin = "7px 50%";
      inputElement.style.transform = "rotate(270deg)";
      inputElement.style.width = "100%"; // Will be height after rotation
      inputElement.style.height = "100%"; // Will be width after rotation
    }

    container.appendChild(inputElement);
    this.htmlElement = container;

    // Add event listener to capture changes
    inputElement.addEventListener(
        "input",
        e -> {
          int oldValue = this.value;
          this.value = Lang.any(inputElement.valueAsNumber);
          fireAdjustmentEvent(
              new AdjustmentEvent(
                  this,
                  AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED,
                  AdjustmentEvent.TRACK,
                  this.value,
                  false));
        });
  }

  private void fireAdjustmentEvent(AdjustmentEvent e) {
    for (AdjustmentListener l : adjustmentListeners) {
      l.adjustmentValueChanged(e);
    }
  }

  //
  // Implementation of the Adjustable interface
  //

  public int getOrientation() {
    return this.orientation;
  }

  public void setOrientation(int orientation) {
    if (orientation != HORIZONTAL && orientation != VERTICAL) {
      throw new IllegalArgumentException("invalid orientation");
    }
    this.orientation = orientation;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int newValue) {
    // Handle value being outside the valid range.
    if (newValue < minimum) newValue = minimum;
    int maxVal = maximum - visibleAmount;
    if (newValue > maxVal) newValue = maxVal;

    if (this.value != newValue) {
      int oldValue = this.value;
      this.value = newValue;
      if (inputElement != null) {
        inputElement.value = "" + this.value;
      }
    }
  }

  public int getVisibleAmount() {
    return this.visibleAmount;
  }

  public void setVisibleAmount(int newExtent) {
    this.visibleAmount = newExtent;
  }

  public int getMinimum() {
    return this.minimum;
  }

  public void setMinimum(int newMinimum) {
    this.minimum = newMinimum;
    // The Java docs state other values are adjusted to be consistent.
    // For a minimal stub, we just update the min value.
  }

  public int getMaximum() {
    return this.maximum;
  }

  public void setMaximum(int newMaximum) {
    this.maximum = newMaximum;
    // The Java docs state other values are adjusted to be consistent.
    // For a minimal stub, we just update the max value.
  }

  public int getUnitIncrement() {
    return this.unitIncrement;
  }

  public void setUnitIncrement(int v) {
    this.unitIncrement = v;
  }

  public int getBlockIncrement() {
    return this.blockIncrement;
  }

  public void setBlockIncrement(int v) {
    this.blockIncrement = v;
  }

  public boolean getValueIsAdjusting() {
    // For this simple stub, we assume the value is always adjusting
    // during a range input change.
    return true;
  }

  public void setValueIsAdjusting(boolean b) {
    // Not implemented for this stub
  }

  public void addAdjustmentListener(AdjustmentListener l) {
    if (l != null) {
      adjustmentListeners.add(l);
    }
  }

  public void removeAdjustmentListener(AdjustmentListener l) {
    if (l != null) {
      adjustmentListeners.remove(l);
    }
  }

  public void setValues(int newValue, int newVisible, int newMinimum, int newMaximum) {
    this.value = newValue;
    this.visibleAmount = newVisible;
    this.minimum = newMinimum;
    this.maximum = newMaximum;
    if (inputElement != null) {
      initHTML(); // Update the HTML element
    }
  }
}
