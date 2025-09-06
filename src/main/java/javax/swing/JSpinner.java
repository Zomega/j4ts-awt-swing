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
import jsweet.util.StringTypes;

public class JSpinner extends JComponent {

  private SpinnerModel model;

  public JSpinner() {
    this(new SpinnerNumberModel());
  }

  public JSpinner(SpinnerModel model) {
    if (model == null) {
      throw new NullPointerException("model cannot be null");
    }
    this.model = model;
    this.model.addChangeListener(e -> updateValueInEditor());
  }

  @Override
  public HTMLInputElement getHTMLElement() {
    return (HTMLInputElement) super.getHTMLElement();
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.input);
    getHTMLElement().type = "number";
    getHTMLElement().className = "applet-jspinner";

    getHTMLElement().onchange = e -> {
      setValue(getHTMLElement().valueAsNumber);
      return e;
    };
  }

  @Override
  public void initHTML() {
    super.initHTML();
    updateValueInEditor();
    if (model instanceof SpinnerNumberModel) {
      SpinnerNumberModel numberModel = (SpinnerNumberModel) model;
      getHTMLElement().step = numberModel.getStepSize().toString();
      if (numberModel.getMinimum() != null) {
        getHTMLElement().min = numberModel.getMinimum().toString();
      }
      if (numberModel.getMaximum() != null) {
        getHTMLElement().max = numberModel.getMaximum().toString();
      }
    }
  }

  /**
   * Returns the SpinnerModel that defines this spinner's sequence of values.
   *
   * @return the SpinnerModel
   */
  public SpinnerModel getModel() {
    return this.model;
  }

  public void setModel(SpinnerModel model) {
    if (model == null) {
      throw new NullPointerException("model cannot be null");
    }
    if (this.model != null) {
      this.model.removeChangeListener(e -> updateValueInEditor());
    }
    this.model = model;
    this.model.addChangeListener(e -> updateValueInEditor());
    if (htmlElement != null) {
      initHTML();
    }
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
    if (getHTMLElement() != null && this.model != null) {
        Object value = this.model.getValue();
        if (value instanceof Number) {
            getHTMLElement().valueAsNumber = ((Number) value).doubleValue();
        } else {
            getHTMLElement().value = value.toString();
        }
    }
  }

  void addChangeListener(ChangeListener listener) {
    this.model.addChangeListener(listener);
  }

  void removeChangeListener(ChangeListener listener) {
    this.model.removeChangeListener(listener);
  }
}
