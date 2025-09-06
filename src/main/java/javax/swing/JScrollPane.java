package javax.swing;

import static def.dom.Globals.document;

import java.awt.BorderLayout;
import java.awt.Component;
import jsweet.util.StringTypes;

public class JScrollPane extends JComponent {

  protected JViewport viewport;
  protected JScrollBar verticalScrollBar;

  public JScrollPane(Component view) {
    this.viewport = new JViewport();
    this.viewport.setView(view);
    this.verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
    setLayout(new BorderLayout());
    add(viewport, BorderLayout.CENTER);
    add(verticalScrollBar, BorderLayout.EAST);
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jscrollpane";
    htmlElement.style.overflow = "auto";
  }

  public Component getView() {
    return viewport.getView();
  }

  public JViewport getViewport() {
    return viewport;
  }

  public JScrollBar getVerticalScrollBar() {
    return verticalScrollBar;
  }
}
