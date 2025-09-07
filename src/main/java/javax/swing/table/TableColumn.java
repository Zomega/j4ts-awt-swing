package javax.swing.table;

import java.io.Serializable;

public class TableColumn implements Serializable {

    protected Object headerValue;
    protected int modelIndex;
    protected TableCellRenderer cellRenderer;

    public TableColumn(int modelIndex) {
        this.modelIndex = modelIndex;
    }

    public int getModelIndex() {
        return modelIndex;
    }

    public void setHeaderValue(Object headerValue) {
        this.headerValue = headerValue;
    }

    public Object getHeaderValue() {
        return headerValue;
    }

    public void setCellRenderer(TableCellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
    }

    public TableCellRenderer getCellRenderer() {
        return cellRenderer;
    }

    protected TableCellEditor cellEditor;

    public void setCellEditor(TableCellEditor cellEditor) {
        this.cellEditor = cellEditor;
    }

    public TableCellEditor getCellEditor() {
        return cellEditor;
    }
}
