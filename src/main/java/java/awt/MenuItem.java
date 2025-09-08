package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLLIElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import jsweet.util.StringTypes;

public class MenuItem extends MenuComponent implements HTMLComponent {
  private String label;
  private Vector<ActionListener> actionListeners = new Vector<>();
  protected HTMLLIElement htmlElement;

  public MenuItem() {
    this("");
  }

  public MenuItem(String label) {
    this.label = label;
    createHTML();
    initHTML();
  }

  public void addActionListener(ActionListener l) {
    actionListeners.add(l);
  }

  public void removeActionListener(ActionListener l) {
    actionListeners.remove(l);
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
    htmlElement.textContent = label;
  }

  @Override
  public void createHTML() {
    htmlElement = (HTMLLIElement) document.createElement(StringTypes.li);
    htmlElement.textContent = label;
    htmlElement.addEventListener(
        "click",
        (e) -> {
          ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, label);
          for (ActionListener listener : actionListeners) {
            listener.actionPerformed(event);
          }
        });
  }

  @Override
  public HTMLLIElement getHTMLElement() {
    return htmlElement;
  }

  @Override
  public void initHTML() {}

  @Override
  public void bindHTML(def.dom.HTMLElement htmlElement) {}
}
