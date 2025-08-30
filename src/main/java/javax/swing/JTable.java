package javax.swing;

import static def.dom.Globals.document;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import jsweet.util.StringTypes;

class JTable extends JComponent {
  // TODO: Implement rough out.

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jtable";
  }

  public TableModel getModel() {
    return null; // TODO: Implement
  }

  public int getColumnCount() {
    return 0; // TODO: Implement
  }

  public TableColumn getColumn(Object identifier) {
    return null; // TODO: Implement
  }

  public Object /*JTableHeader*/ getTableHeader() {
    return null; // TODO: Implement
  }

  public /*TableColumnModel*/ Object getColumnModel() {
    return null; // TODO: Implement
  }

  public void setColumnSelectionAllowed(boolean columnSelectionAllowed) {
    // TODO: Implement
  }

  public void setRowSelectionAllowed(boolean rowSelectionAllowed) {
    // TODO: Implement
  }

  public int getRowCount() {
    return 0; // TODO: Implement
  }

  public int getRowHeight() {
    return 0; // TODO: Implement
  }

  public int getRowHeight(int row) {
    return getRowHeight(); // TODO: Implement
  }

  public int getSelectedRow() {
    return -1; // TODO: Implement
  }

  public void setRowHeight(int rowHeight) {
    // TODO: Implement
  }

  public void clearSelection() {
    // TODO: Implement
  }

  public Object getValueAt(int row, int column) {
    return null; // TODO: Implement
  }

  public void setValueAt(Object aValue, int row, int column) {
    // TODO: Implement
  }

  public void setAutoResizeMode(int mode) {
    // mode - One of 5 legal values: AUTO_RESIZE_OFF, AUTO_RESIZE_NEXT_COLUMN,
    // AUTO_RESIZE_SUBSEQUENT_COLUMNS, AUTO_RESIZE_LAST_COLUMN, AUTO_RESIZE_ALL_COLUMNS
    // TODO: Implement.
  }

  public int getAutoResizeMode() {
    return 0; // TODO: Implement
  }

  public void setGridColor(Color gridColor) {
    // TODO: Implement
  }

  public void setIntercellSpacing(Dimension intercellSpacing) {
    // TODO: Implement
  }
}
