package java.awt;

import static def.dom.Globals.document;

import jsweet.util.StringTypes;

public class ScrollPane extends Container {

  protected Component view;

  public ScrollPane() {
    this(null);
  }

  public ScrollPane(Component view) {
    this.view = view;
    if (view != null) {
        add(view);
    }
  }

  @Override
  public void add(Component comp, Object constraints) {
      this.view = comp;
      super.add(comp, constraints);
      if (htmlElement != null) {
          htmlElement.appendChild(comp.getHTMLElement());
      }
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-scrollpane";
    htmlElement.style.overflow = "auto";
  }

  public void doLayout() {
    // TODO: I think this is a no-op because we're handling this with CSS now.
  }
}
