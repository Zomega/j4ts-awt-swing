package javax.swing;

public class UIManager {

  public static String getString(String key) {
    switch (key) {
      case "AbstractUndoableEdit.redoText":
        return "Redo";
      case "AbstractUndoableEdit.undoText":
        return "Undo";
      default:
        return "<undefided>";
    }
  }

  static /*Border*/ Object getBorder(Object key) {
    return null; // TODO: Implement? Mock?
  }
}
