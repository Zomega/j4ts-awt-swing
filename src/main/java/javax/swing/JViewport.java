package javax.swing;

import static def.dom.Globals.document;

import java.awt.BorderLayout;
import java.awt.Component;
import jsweet.util.StringTypes;

public class JViewport extends JComponent {

  protected Component view;

  public JViewport() {
    setLayout(new BorderLayout());
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jviewport";
  }

  public void setView(Component view) {
    this.view = view;
    add(view, BorderLayout.CENTER);
  }

  public Component getView() {
    return view;
  }
}
