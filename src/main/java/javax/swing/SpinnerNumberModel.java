package javax.swing;

import java.util.Vector;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    if (value instanceof Number) {
      Double oldValue = this.value;
      this.value = ((Number) value).doubleValue();
      if (oldValue.doubleValue() != this.value.doubleValue()) {
        fireStateChanged();
      }
    } else {
      throw new IllegalArgumentException("Invalid value type");
    }
  }

  @Override
  public Object getNextValue() {
    if (this.maximum != null && this.value != null && this.maximum.compareTo(this.value) <= 0) {
      return null;
    }
    if (this.value == null || this.stepSize == null) {
      return null;
    }
    return this.value + this.stepSize;
  }

  @Override
  public Object getPreviousValue() {
    if (this.minimum != null && this.value != null && this.minimum.compareTo(this.value) >= 0) {
      return null;
    }
    if (this.value == null || this.stepSize == null) {
      return null;
    }
    return this.value - this.stepSize;
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
