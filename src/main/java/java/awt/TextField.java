package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;

import def.dom.HTMLInputElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jsweet.util.StringTypes;

public class TextField extends Component {

  ActionListener actionListener;

  public TextField(int cols) {
    // TODO: Implement?
  }

  public TextField(String text, int columns) {
    // TODO: Implement?
  }

  @Override
  public HTMLInputElement getHTMLElement() {
    return any(htmlElement);
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.input);
    htmlElement.className = "applet-textfield";
    htmlElement.setAttribute("type", "text");
  }

  @Override
  public void initHTML() {
    super.initHTML();
    initActionListener();
  }

  private void initActionListener() {
    if (actionListener != null) {
      htmlElement.onclick =
          e -> {
            this.actionListener.actionPerformed(new ActionEvent(this, 0, null));
            return e;
          };
    }
  }

  public void addActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
    if (htmlElement != null) {
      initActionListener();
    }
  }

  public void removeActionListener(ActionListener actionListener) {
    // TODO: Confirm?
    this.actionListener = null;
    if (htmlElement != null) {
      initActionListener();
    }
  }

  public void setText(String text) {
    getHTMLElement().value = text;
  }

  public String getText() {
    return getHTMLElement().value;
  }
}
