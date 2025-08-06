package javax.swing;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;

import def.dom.HTMLButtonElement;
import def.js.Array;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jsweet.util.StringTypes;

@SuppressWarnings("serial")
public class JButton extends AbstractButton {

  Array<ActionListener> actionListeners = new Array<>();
  String actionCommand;
  String label;
  Color background;
  ImageIcon icon;

  public JButton(String label) {
    this(label, null);
  }

  public JButton(String label, ImageIcon icon) {
    this.label = label;
    this.actionCommand = label;
    this.icon = icon;

    this.actionListener =
        actionEvent -> {
          for (ActionListener listener : actionListeners) {
            listener.actionPerformed(actionEvent);
          }
        };
  }

  @Override
  public HTMLButtonElement getHTMLElement() {
    return any(super.getHTMLElement());
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.button);
  }

  @Override
  public void initHTML() {
    super.initHTML();
    if (icon != null) getHTMLElement().appendChild(icon.getInternalHTMLImageElement());

    if (!label.isEmpty()) htmlElement.innerHTML = label;

    initActionListener();
  }

  private void initActionListener() {
    htmlElement.onclick =
        e -> {
          this.actionListener.actionPerformed(new ActionEvent(this, 0, actionCommand));
          return e;
        };
  }

  public void addActionListener(ActionListener actionListener) {
    actionListeners.unshift(actionListener);
  }

  public void removeActionListener(ActionListener actionListener) {
    int index = actionListeners.indexOf(actionListener);
    if (index > -1) {
      actionListeners = actionListeners.splice(index, 1);
    }
  }

  public final void setBackground(Color background) {
    this.background = background;
  }
}
