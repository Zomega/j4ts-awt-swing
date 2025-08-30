package javax.swing;

import def.dom.Globals;
import java.awt.Point;
import java.awt.Component;

public class SwingUtilities implements SwingConstants {
  private SwingUtilities() {
    // do nothing
  }

  public static void invokeLater(Runnable doRun) {
    Globals.window.requestAnimationFrame(v -> doRun.run());
  }

  public static void invokeAndWait(Runnable doRun) {
    doRun.run();
  }

  public static void convertPointToScreen(Point p,
                                        Component c) {
    // TODO: Implement
  }
}
