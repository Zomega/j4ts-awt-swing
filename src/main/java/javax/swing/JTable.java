package javax.swing;

import static def.dom.Globals.document;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import jsweet.util.StringTypes;
import def.dom.HTMLTableElement;
import def.dom.HTMLTableSectionElement;

public class JTable extends JComponent {

  protected TableModel dataModel;
  protected HTMLTableElement tableElement;
  protected HTMLTableSectionElement tableHead;
  protected HTMLTableSectionElement tableBody;

  public JTable(TableModel dm) {
      setModel(dm);
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
              def.dom.HTMLTableCellElement td = (def.dom.HTMLTableCellElement) document.createElement(StringTypes.td);
              Object value = dataModel.getValueAt(i, j);
              td.innerText = (value == null) ? "" : value.toString();
              tr.appendChild(td);
          }
          tableBody.appendChild(tr);
      }
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
