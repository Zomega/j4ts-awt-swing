package javax.swing;

import static def.dom.Globals.document;
import java.awt.*;
import jsweet.util.StringTypes;

public class JOptionPane extends JComponent {
  // TODO: Implement this stub.

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-joptionpane";
  }

  public static void showMessageDialog(Component parentComponent, Object message)
      throws HeadlessException {
    // TODO: Implement
  }

  public static void showMessageDialog(
      Component parentComponent, Object message, String title, int messageType)
      throws HeadlessException {
    // TODO: Implement
  }

  public static void showMessageDialog(
      Component parentComponent, Object message, String title, int messageType, Icon icon)
      throws HeadlessException {
    // TODO: Implement
  }
}
