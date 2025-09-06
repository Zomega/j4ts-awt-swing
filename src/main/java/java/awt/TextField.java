package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;

import def.dom.HTMLInputElement;
import def.dom.KeyboardEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jsweet.util.StringTypes;

public class TextField extends Component {

  String text;
  int columns;
  ActionListener actionListener;

  public TextField() {
    this("", 0);
  }

  public TextField(String text) {
    this(text, 0);
  }

  public TextField(int columns) {
    this("", columns);
  }

  public TextField(String text, int columns) {
    this.text = text;
    this.columns = columns;
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
    if (text != null) {
      getHTMLElement().value = text;
    }
    if (columns > 0) {
      getHTMLElement().size = columns;
    }
    initActionListener();
  }

  private void initActionListener() {
    if (actionListener != null) {
      htmlElement.onkeydown =
          (e) -> {
            KeyboardEvent ke = (KeyboardEvent) e;
            if (ke.keyCode == 13) {
              this.actionListener.actionPerformed(new ActionEvent(this, 0, null));
            }
            return ke;
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
