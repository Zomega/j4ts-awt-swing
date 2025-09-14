package javax.swing.table;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;

public class DefaultTableCellRenderer extends JLabel implements TableCellRenderer {

  public DefaultTableCellRenderer() {
    super();
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    setText((value == null) ? "" : value.toString());
    return this;
  }
}
