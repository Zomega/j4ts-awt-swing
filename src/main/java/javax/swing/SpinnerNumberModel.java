package javax.swing;

import java.util.Vector;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A `SpinnerModel` for a sequence of numbers. The `JSpinner` component that uses this model is
 * responsible for calling `setValue` with the value returned by `getNextValue` or
 * `getPreviousValue`. This model does not change its own state when `getNextValue` or
 * `getPreviousValue` are called.
 */
public class SpinnerNumberModel implements SpinnerModel {
  private Double value;
  private Double minimum;
  private Double maximum;
  private Double stepSize;
  private Vector<ChangeListener> listeners = new Vector<>();

  public SpinnerNumberModel(Number value, Number minimum, Number maximum, Number stepSize) {
    this.value = value != null ? value.doubleValue() : null;
    this.minimum = minimum != null ? minimum.doubleValue() : null;
    this.maximum = maximum != null ? maximum.doubleValue() : null;
    this.stepSize = stepSize != null ? stepSize.doubleValue() : null;
  }

  public SpinnerNumberModel() {
    this(0, null, null, 1);
  }

  @Override
  public Object getValue() {
    return this.value;
  }

  @Override
  public void setValue(Object value) {
    if (!(value instanceof Number)) {
      throw new IllegalArgumentException("Invalid value type");
    }
    Number num = (Number) value;
    if (this.value == null || this.value.doubleValue() != num.doubleValue()) {
      this.value = num.doubleValue();
      fireStateChanged();
    }
  }

  @Override
  public Object getNextValue() {
    if (this.value == null || this.stepSize == null) {
      return null;
    }
    double next = this.value + this.stepSize;
    if (this.maximum != null && next > this.maximum) {
      return null;
    }
    return next;
  }

  @Override
  public Object getPreviousValue() {
    if (this.value == null || this.stepSize == null) {
      return null;
    }
    double prev = this.value - this.stepSize;
    if (this.minimum != null && prev < this.minimum) {
      return null;
    }
    return prev;
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeChangeListener(ChangeListener listener) {
    listeners.remove(listener);
  }

  protected void fireStateChanged() {
    ChangeEvent event = new ChangeEvent(this);
    for (ChangeListener listener : listeners) {
      listener.stateChanged(event);
    }
  }

  public void setMinimum(Number minimum) {
    this.minimum = minimum != null ? minimum.doubleValue() : null;
  }

  public void setMaximum(Number maximum) {
    this.maximum = maximum != null ? maximum.doubleValue() : null;
  }

  public Number getStepSize() {
    return stepSize;
  }

  public Number getMinimum() {
    return minimum;
  }

  public Number getMaximum() {
    return maximum;
  }
}
