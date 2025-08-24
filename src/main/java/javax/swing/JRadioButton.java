package javax.swing;

import static def.dom.Globals.document;

import def.dom.HTMLElement;
import def.dom.HTMLInputElement;

/**
 * An implementation of a radio button. Used with a ButtonGroup object to create a group of buttons
 * in [cite_start]which only one button at a time can be selected. [cite: 563]
 *
 * <p>This class assumes that JToggleButton is already implemented.
 *
 * <p>TODO: AI Implemented Stub. Finish
 */
public class JRadioButton extends JToggleButton {

  // The name attribute is used for grouping radio buttons in HTML
  private String name;
  private HTMLElement element;
  private HTMLInputElement inputElement;

  /** [cite_start]Creates an initially unselected radio button with no set text. [cite: 630] */
  public JRadioButton() {
    this(null, null, false);
  }

  /**
   * [cite_start]Creates an unselected radio button with the specified text. [cite: 638]
   *
   * @param text the string displayed on the radio button
   */
  public JRadioButton(String text) {
    this(text, null, false);
  }

  /**
   * [cite_start]Creates a radio button with the specified text and selection state. [cite: 638]
   *
   * @param text the string displayed on the radio button
   * @param selected if true, the button is initially selected
   */
  public JRadioButton(String text, boolean selected) {
    this(text, null, selected);
  }

  /**
   * [cite_start]Creates a radio button with the specified text, image, and selection state. [cite:
   * 638]
   *
   * @param text the string displayed on the radio button
   * @param icon the image that the button should display
   * @param selected if true, the button is initially selected
   */
  public JRadioButton(String text, Icon icon, boolean selected) {
    super(text, icon, selected);
    // Additional JRadioButton-specific initialization
    createHTML();
  }

  /**
   * [cite_start]Creates an initially unselected radio button with the specified image but no text.
   * [cite: 632]
   *
   * @param icon the image that the button should display
   */
  public JRadioButton(Icon icon) {
    this(null, icon, false);
  }

  /**
   * [cite_start]Creates a radio button with the specified image and selection state, but no text.
   * [cite: 633]
   *
   * @param icon the image that the button should display
   * @param selected if true, the button is initially selected
   */
  public JRadioButton(Icon icon, boolean selected) {
    this(null, icon, selected);
  }

  @Override
  public void createHTML() {
    if (this.element != null) {
      return;
    }

    // Create a new container for the radio button and label
    HTMLElement container = (HTMLElement) document.createElement("div");
    container.className = "applet-jradiobutton";

    HTMLInputElement radioInput = (HTMLInputElement) document.createElement("input");
    radioInput.className = "applet-jradiobutton-input";
    radioInput.type = "radio";
    radioInput.checked = this.isSelected();
    container.appendChild(radioInput);

    HTMLElement label = (HTMLElement) document.createElement("label");
    label.className = "applet-jradiobutton-label";
    label.textContent = this.getText();
    container.appendChild(label);

    this.element = container; // Assuming a field `element` exists in JToggleButton
    this.inputElement = radioInput; // Store reference to the actual input

    // Add event listener to handle selection state changes
    radioInput.addEventListener(
        "click",
        e -> {
          if (this.isSelected() == false) {
            // If it's not selected, select it. The ButtonGroup will handle deselecting others.
            this.setSelected(true);
          }
        });
  }

  @Override
  public HTMLElement getHTMLElement() {
    return this.element;
  }

  //
  // Overrides of JToggleButton methods
  //
  @Override
  public boolean isSelected() {
    if (this.inputElement != null) {
      return this.inputElement.checked;
    }
    return super.isSelected();
  }

  @Override
  public void setSelected(boolean b) {
    if (this.inputElement != null) {
      this.inputElement.checked = b;
    }
    // This will fire an action event and state change events.
    super.setSelected(b);
  }

  //
  // JRadioButton-specific methods
  //
  public String getUIClassID() {
    return "RadioButtonUI";
  }
}
