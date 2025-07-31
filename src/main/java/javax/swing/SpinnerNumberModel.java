package javax.swing;

import javax.swing.event.ChangeListener;

/**
 * An example implementation of SpinnerModel that defines a sequence of numbers.
 *
 * TODO: AI Implemented Stub. Finish
 */
public class SpinnerNumberModel implements SpinnerModel {
    private Number value;
    private Comparable minimum;
    private Comparable maximum;
    private Number stepSize;

    // A list of listeners to be notified of changes
    private ChangeListener[] listeners = new ChangeListener[0];

    public SpinnerNumberModel(Number value, Comparable minimum, Comparable maximum, Number stepSize) {
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
        this.stepSize = stepSize;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            Number oldValue = this.value;
            this.value = (Number) value;
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
        // This is a simplified increment. A full implementation would need to handle different Number types.
        return this.value.doubleValue() + this.stepSize.doubleValue();
    }

    @Override
    public Object getPreviousValue() {
        if (this.minimum != null && this.minimum.compareTo(this.value) >= 0) {
            return null;
        }
        // This is a simplified decrement. A full implementation would need to handle different Number types.
        return this.value.doubleValue() - this.stepSize.doubleValue();
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
}
