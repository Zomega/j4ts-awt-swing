package javax.swing;

import static def.dom.Globals.document;

import java.net.URL;
import jsweet.util.StringTypes;

class JTextPane extends JComponent {
  // TODO

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
  }

  public void setPage(URL page) {
    // TODO: Load the URL into the div.
  }
}
