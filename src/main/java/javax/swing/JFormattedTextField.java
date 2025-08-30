package javax.swing;

import static def.dom.Globals.document;

import jsweet.util.StringTypes;

class JFormattedTextField extends JComponent {
  // TODO

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jformattedtextfield";
  }
}
