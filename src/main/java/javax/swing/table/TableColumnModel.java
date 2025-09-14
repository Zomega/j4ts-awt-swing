package javax.swing.table;

public interface TableColumnModel {
  void addColumn(TableColumn aColumn);

  void removeColumn(TableColumn aColumn);

  TableColumn getColumn(int columnIndex);

  int getColumnCount();
}
