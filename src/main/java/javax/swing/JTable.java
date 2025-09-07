package javax.swing;

import static def.dom.Globals.document;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import jsweet.util.StringTypes;
import def.dom.HTMLTableElement;
import def.dom.HTMLTableSectionElement;

public class JTable extends JComponent {

  protected TableModel dataModel;
  protected TableColumnModel columnModel;
  protected TableCellRenderer defaultRenderer;
  protected TableCellEditor defaultCellEditor;
  protected HTMLTableElement tableElement;
  protected HTMLTableSectionElement tableHead;
  protected HTMLTableSectionElement tableBody;
  protected int editingRow = -1;
  protected int editingColumn = -1;

  public JTable(TableModel dm) {
      this.columnModel = new DefaultTableColumnModel();
      this.defaultRenderer = new DefaultTableCellRenderer();
      this.defaultCellEditor = new DefaultCellEditor(new JTextField());
      setModel(dm);
      createDefaultColumnsFromModel();
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jtable-container";
    htmlElement.style.overflow = "auto";

    tableElement = (HTMLTableElement) document.createElement(StringTypes.table);
    tableElement.className = "applet-jtable";
    htmlElement.appendChild(tableElement);

    tableHead = (HTMLTableSectionElement) document.createElement(StringTypes.thead);
    tableElement.appendChild(tableHead);

    tableBody = (HTMLTableSectionElement) document.createElement(StringTypes.tbody);
    tableElement.appendChild(tableBody);
  }

  public void setModel(TableModel dataModel) {
    if (dataModel == null) {
        throw new IllegalArgumentException("Cannot set a null TableModel");
    }
    if (this.dataModel != null) {
        this.dataModel.removeTableModelListener(this::tableChanged);
    }
    this.dataModel = dataModel;
    this.dataModel.addTableModelListener(this::tableChanged);
    refreshTable();
  }

  public TableModel getModel() {
    return dataModel;
  }

  private void tableChanged(javax.swing.event.TableModelEvent e) {
      refreshTable();
  }

  private void refreshTable() {
      if (tableHead == null || tableBody == null) {
          return;
      }

      // Clear existing content
      tableHead.innerHTML = "";
      tableBody.innerHTML = "";

      // Create header
      if (dataModel.getRowCount() > 0) {
          def.dom.HTMLTableRowElement headerRow = (def.dom.HTMLTableRowElement) document.createElement(StringTypes.tr);
          for (int i = 0; i < dataModel.getColumnCount(); i++) {
              def.dom.HTMLTableCellElement th = (def.dom.HTMLTableCellElement) document.createElement(StringTypes.th);
              th.innerText = dataModel.getColumnName(i);
              headerRow.appendChild(th);
          }
          tableHead.appendChild(headerRow);
      }

      // Create body
      for (int i = 0; i < dataModel.getRowCount(); i++) {
          def.dom.HTMLTableRowElement tr = (def.dom.HTMLTableRowElement) document.createElement(StringTypes.tr);
          for (int j = 0; j < dataModel.getColumnCount(); j++) {
              final int row = i;
              final int col = j;
              def.dom.HTMLTableCellElement td = (def.dom.HTMLTableCellElement) document.createElement(StringTypes.td);
              TableColumn column = getColumnModel().getColumn(j);
              TableCellRenderer renderer = column.getCellRenderer();
              if (renderer == null) {
                  renderer = defaultRenderer;
              }
              Component cellComponent = renderer.getTableCellRendererComponent(this, dataModel.getValueAt(i, j), false, false, i, j);
              td.appendChild(cellComponent.getHTMLElement());

              td.onclick = (e) -> {
                  if (getModel().isCellEditable(row, col)) {
                      editCellAt(row, col);
                  }
                  return e;
              };

              tr.appendChild(td);
          }
          tableBody.appendChild(tr);
      }
  }

  public void editCellAt(int row, int column) {
      if (editingRow != -1) {
          stopEditing();
      }

      editingRow = row;
      editingColumn = column;

      def.dom.HTMLTableRowElement tr = (def.dom.HTMLTableRowElement) tableBody.rows.item(row);
      def.dom.HTMLTableCellElement td = (def.dom.HTMLTableCellElement) tr.cells.item(column);

      TableColumn tableColumn = getColumnModel().getColumn(column);
      TableCellEditor editor = tableColumn.getCellEditor();
      if (editor == null) {
          editor = defaultCellEditor;
      }

      Component editorComponent = editor.getTableCellEditorComponent(this, getValueAt(row, column), true, true, row, column);
      td.innerHTML = "";
      td.appendChild(editorComponent.getHTMLElement());

      editorComponent.getHTMLElement().focus();
      editorComponent.getHTMLElement().onblur = (e) -> {
          stopEditing();
          return e;
      };
  }

  public void stopEditing() {
      if (editingRow == -1) {
          return;
      }

      TableColumn tableColumn = getColumnModel().getColumn(editingColumn);
      TableCellEditor editor = tableColumn.getCellEditor();
      if (editor == null) {
          editor = defaultCellEditor;
      }

      // This is a simplification. A real implementation would get the value from the editor.
      // For now, we assume the editor has modified the model directly or we don't capture the value.
      // To properly get the value, we would need a `getCellEditorValue()` method on TableCellEditor.

      editingRow = -1;
      editingColumn = -1;
      refreshTable();
  }

  public SingleSelectionModel getSelectionModel() {
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

  public TableColumnModel getColumnModel() {
    return columnModel;
  }

  public void createDefaultColumnsFromModel() {
      TableModel tm = getModel();
      if (tm != null) {
          // Remove any existing columns
          TableColumnModel cm = getColumnModel();
          while (cm.getColumnCount() > 0) {
              cm.removeColumn(cm.getColumn(0));
          }

          // Create new columns from the data model info
          for (int i = 0; i < tm.getColumnCount(); i++) {
              TableColumn newColumn = new TableColumn(i);
              addColumn(newColumn);
          }
      }
  }

  public void addColumn(TableColumn aColumn) {
      if (aColumn.getHeaderValue() == null) {
          int modelColumn = aColumn.modelIndex;
          String columnName = getModel().getColumnName(modelColumn);
          aColumn.setHeaderValue(columnName);
      }
      getColumnModel().addColumn(aColumn);
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
