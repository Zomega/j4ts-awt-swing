package javax.swing.table;

import java.awt.Component;
import javax.swing.JTable;

public interface TableCellEditor {
  Component getTableCellEditorComponent(
      JTable table, Object value, boolean isSelected, int row, int column);

  Object getCellEditorValue();
}
