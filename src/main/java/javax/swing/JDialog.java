package javax.swing;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

public class JDialog extends Dialog {

  protected JRootPane rootPane;

  public JDialog(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    setRootPane(createRootPane());
  }

  public JDialog(Frame owner, String title) {
    this(owner, title, false);
  }

  public JDialog(Frame owner, boolean modal) {
    this(owner, "", modal);
  }

  public JDialog(Frame owner) {
    this(owner, "", false);
  }

  protected JRootPane createRootPane() {
    return new JRootPane();
  }

  protected void setRootPane(JRootPane root) {
    if (rootPane != null) {
      remove(rootPane);
    }
    rootPane = root;
    if (rootPane != null) {
      add(rootPane);
    }
  }

  public JRootPane getRootPane() {
    return rootPane;
  }

  public Container getContentPane() {
    return getRootPane().getContentPane();
  }

  public void setContentPane(Container contentPane) {
    getRootPane().setContentPane(contentPane);
  }
}
