package javax.swing;

import static def.dom.Globals.document;

import def.dom.HTMLElement;
import def.dom.HTMLInputElement;
import javax.swing.event.ChangeListener;

/**
 * A JSpinner class for JSweet, which simulates the functionality of the Java Swing JSpinner. It's a
 * compound component consisting of an editor and two buttons for stepping through a sequence.
 *
 * <p>TODO: AI Implemented Stub. Finish
 */
public class JSpinner extends JComponent {

  private SpinnerModel model;
  private HTMLElement spinnerElement;
  private HTMLInputElement valueEditor;
  private HTMLElement upButton;
  private HTMLElement downButton;

  /** Constructs a spinner with an Integer SpinnerNumberModel with initial value 0. */
  public JSpinner() {
    this(new SpinnerNumberModel(0, null, null, 1));
  }

  /**
   * Constructs a spinner for the given model. The spinner has a set of previous/next buttons, and
   * an editor appropriate for the model.
   *
   * @param model the SpinnerModel that defines the sequence of values.
   */
  public JSpinner(SpinnerModel model) {
    if (model == null) {
      throw new NullPointerException("model cannot be null");
    }
    this.model = model;
    createHTML(); // TODO: This might be in the wrong place.

    this.model.addChangeListener(
        e -> {
          updateValueInEditor();
        });
  }

  @Override
  public void createHTML() {
    if (this.spinnerElement != null) {
      return;
    }

    // Create the main container element for the spinner
    this.spinnerElement = (HTMLElement) document.createElement("div");
    this.spinnerElement.className = "applet-jspinner";

    // Create the editor field
    this.valueEditor = (HTMLInputElement) document.createElement("input");
    this.valueEditor.type = "text";
    this.valueEditor.className = "applet-jspinner-input";
    this.spinnerElement.appendChild(this.valueEditor);

    // Create the buttons container
    HTMLElement buttonsContainer = (HTMLElement) document.createElement("div");
    buttonsContainer.className = "applet-jspinner-buttons";

    // Create the up button
    this.upButton = (HTMLElement) document.createElement("button");
    this.upButton.className = "applet-jspinner-button-up";
    this.upButton.textContent = "▲";
    buttonsContainer.appendChild(this.upButton);

    // Create the down button
    this.downButton = (HTMLElement) document.createElement("button");
    this.downButton.className = "applet-jspinner-button-down";
    this.downButton.textContent = "▼";
    buttonsContainer.appendChild(this.downButton);

    this.spinnerElement.appendChild(buttonsContainer);

    // Add event listeners to the buttons
    this.upButton.addEventListener(
        "click",
        e -> {
          Object nextValue = model.getNextValue();
          if (nextValue != null) {
            model.setValue(nextValue);
          }
        });

    this.downButton.addEventListener(
        "click",
        e -> {
          Object previousValue = model.getPreviousValue();
          if (previousValue != null) {
            model.setValue(previousValue);
          }
        });
  }

  @Override
  public HTMLElement getHTMLElement() {
    return this.spinnerElement;
  }

  /**
   * Returns the SpinnerModel that defines this spinner's sequence of values.
   *
   * @return the SpinnerModel
   */
  public SpinnerModel getModel() {
    return this.model;
  }

  /**
   * Changes the model that represents the value of this spinner.
   *
   * @param model the new SpinnerModel
   */
  public void setModel(SpinnerModel model) {
    SpinnerModel oldModel = getModel();
    if (oldModel != null) {
      // Remove listeners from old model
      // Not implemented for this simple example
    }
    this.model = model;
    // Add listeners to new model
    // Not implemented for this simple example
  }

  /**
   * Returns the current value of the model.
   *
   * @return the current value
   */
  public Object getValue() {
    return this.model.getValue();
  }

  /**
   * Changes the current value of the model.
   *
   * @param value the new value
   */
  public void setValue(Object value) {
    this.model.setValue(value);
  }

  private void updateValueInEditor() {
    if (this.valueEditor != null && this.model != null) {
      this.valueEditor.value = this.model.getValue().toString();
    }
  }

  void addChangeListener(ChangeListener listener) {
    this.model.addChangeListener(listener);
  }
}
