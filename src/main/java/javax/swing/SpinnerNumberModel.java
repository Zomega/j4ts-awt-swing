package javax.swing;

import javax.swing.event.ChangeListener;

/**
 * An example implementation of SpinnerModel that defines a sequence of numbers.
 *
 * <p>TODO: AI Implemented Stub. Finish
 */
public class SpinnerNumberModel implements SpinnerModel {
  private Double value;
  private Double minimum;
  private Double maximum;
  private Double stepSize;

  // A list of listeners to be notified of changes
  private ChangeListener[] listeners = new ChangeListener[0];

  public SpinnerNumberModel(Number value, Number minimum, Number maximum, Number stepSize) {
    // Convert all incoming Number types to Double for internal consistency
    this.value = value != null ? value.doubleValue() : null;
    this.minimum = minimum != null ? minimum.doubleValue() : null;
    this.maximum = maximum != null ? maximum.doubleValue() : null;
    this.stepSize = stepSize != null ? stepSize.doubleValue() : null;
  }

  @Override
  public Object getValue() {
    return this.value;
  }

  @Override
  public void setValue(Object value) {
    if (value instanceof Number) {
      Double oldValue = this.value;
      this.value = ((Number) value).doubleValue();
      fireStateChanged(oldValue, this.value);
    } else {
      throw new IllegalArgumentException("Invalid value type");
    }
  }

  @Override
  public Object getNextValue() {
    if (this.maximum != null && this.maximum.compareTo(this.value) <= 0) {
      return null;
    }
    return this.value + this.stepSize;
  }

  @Override
  public Object getPreviousValue() {
    if (this.minimum != null && this.minimum.compareTo(this.value) >= 0) {
      return null;
    }
    return this.value - this.stepSize;
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    // Not implemented for this simple example
  }

  @Override
  public void removeChangeListener(ChangeListener listener) {
    // Not implemented for this simple example
  }

  protected void fireStateChanged(Object oldValue, Object newValue) {
    // Notify all listeners
  }

  public void setMinimum(Number minimum) {
    // TODO: Implement
  }

  public void setMaximum(Number maximum) {
    // TODO: Implement
  }
}
