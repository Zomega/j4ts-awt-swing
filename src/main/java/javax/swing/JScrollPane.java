package javax.swing;

import static def.dom.Globals.document;

import jsweet.util.StringTypes;

class JScrollPane extends JComponent {
  // TODO

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
  }

  public Object /*JViewport*/ getViewport() {
    return null; // TODO
  }
}
