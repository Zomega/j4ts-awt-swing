package java.awt;

import static def.dom.Globals.document;

import jsweet.util.StringTypes;

public class ScrollPane extends Component {
  // TODO: Implement.

  ScrollPane() {
    // TODO: Implement
  }

  ScrollPane(Component view) {
    // TODO: Implement
  }

  public void add(Component view) {
    // TODO: Implement
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-scrollpane";
  }

  public void doLayout() {
    // TODO: I think this is a no-op because we're handling this with CSS now.
  }
}
