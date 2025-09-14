package javax.swing;

import java.awt.Component;
import javax.swing.table.TableCellEditor;

public class DefaultCellEditor implements TableCellEditor {

  protected JComponent editorComponent;

  public DefaultCellEditor(final JTextField textField) {
    this.editorComponent = textField;
  }

  public DefaultCellEditor(final JCheckBox checkBox) {
    this.editorComponent = checkBox;
  }

  public DefaultCellEditor(final JComboBox comboBox) {
    this.editorComponent = comboBox;
  }

  public Component getTableCellEditorComponent(
      JTable table, Object value, boolean isSelected, int row, int column) {
    if (editorComponent instanceof JTextField) {
      ((JTextField) editorComponent).setText((value != null) ? value.toString() : "");
    } else if (editorComponent instanceof JCheckBox) {
      ((JCheckBox) editorComponent).setState((Boolean) value);
    } else if (editorComponent instanceof JComboBox) {
      ((JComboBox) editorComponent).setSelectedItem(value);
    }
    return editorComponent;
  }

  public Object getCellEditorValue() {
    if (editorComponent instanceof JTextField) {
      return ((JTextField) editorComponent).getText();
    } else if (editorComponent instanceof JCheckBox) {
      return ((JCheckBox) editorComponent).getState();
    } else if (editorComponent instanceof JComboBox) {
      return ((JComboBox) editorComponent).getSelectedItem();
    }
    return null;
  }
}
