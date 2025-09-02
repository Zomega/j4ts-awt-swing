package javax.swing;

import static def.dom.Globals.document;

import java.awt.Component;
import jsweet.util.StringTypes;

class JScrollPane extends JComponent {
  // TODO: Implement.

  JScrollPane(Component view) {
    // TODO: Implement
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jscrollpane";
  }

  public Object /*JViewport*/ getViewport() {
    return null; // TODO
  }

  public JScrollBar getVerticalScrollBar() {
    return null; // TODO
  }
}
