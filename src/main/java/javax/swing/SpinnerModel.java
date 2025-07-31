package javax.swing;

import javax.swing.event.ChangeListener;

/**
 * Defines the interface for a mutable sequence of values.
 *
 * TODO: AI Implemented Stub. Finish
 */
public interface SpinnerModel {
    Object getValue();
    void setValue(Object value);
    Object getNextValue();
    Object getPreviousValue();
    void addChangeListener(ChangeListener listener);
    void removeChangeListener(ChangeListener listener);
}
