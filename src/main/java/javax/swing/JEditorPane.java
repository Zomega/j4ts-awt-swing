package javax.swing;

import static def.dom.Globals.document;

import javax.swing.text.JTextComponent;
import jsweet.util.StringTypes;

public class JEditorPane extends JTextComponent {

  public JEditorPane() {
    super();
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jeditorpane";
    htmlElement.style.border = "1px solid #C0C0C0";
    htmlElement.style.overflow = "auto";
  }

  @Override
  public void setText(String t) {
    super.setText(t);
    if (htmlElement != null) {
      htmlElement.innerHTML = t;
    }
  }
}
