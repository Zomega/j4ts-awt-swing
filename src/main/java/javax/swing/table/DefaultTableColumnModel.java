package javax.swing.table;

import java.util.ArrayList;
import java.util.List;

public class DefaultTableColumnModel implements TableColumnModel {

    protected List<TableColumn> tableColumns;

    public DefaultTableColumnModel() {
        tableColumns = new ArrayList<>();
    }

    @Override
    public void addColumn(TableColumn aColumn) {
        tableColumns.add(aColumn);
    }

    @Override
    public void removeColumn(TableColumn aColumn) {
        tableColumns.remove(aColumn);
    }

    @Override
    public TableColumn getColumn(int columnIndex) {
        return tableColumns.get(columnIndex);
    }

    @Override
    public int getColumnCount() {
        return tableColumns.size();
    }
}
