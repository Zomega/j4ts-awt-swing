package javax.swing.event;

import java.util.EventObject;
import javax.swing.table.TableModel;

/**
 * TableModelEvent is used to notify listeners that a table model
 * has changed. The model event describes changes to a TableModel
 * and all references to rows and columns are in the co-ordinate
 * system of the model.
 */
public class TableModelEvent extends EventObject {

    public static final int INSERT =  1;
    public static final int UPDATE =  0;
    public static final int DELETE = -1;

    public static final int HEADER_ROW = -1;
    public static final int ALL_COLUMNS = -1;

    protected int      type;
    protected int      firstRow;
    protected int      lastRow;
    protected int      column;

    /**
     * All row data in the table has changed, listeners should discard any state
     * and re-query the <code>TableModel</code> to get the new row count and all
     * the appropriate values.
     */
    public TableModelEvent(TableModel source) {
        this(source, 0, Integer.MAX_VALUE, ALL_COLUMNS, UPDATE);
    }

    /**
     * This event notifies listeners that the data for rows in the range
     * [firstRow, lastRow] has been updated.
     */
    public TableModelEvent(TableModel source, int row) {
        this(source, row, row, ALL_COLUMNS, UPDATE);
    }

    /**
     * This event notifies listeners that the data for rows in the range
     * [firstRow, lastRow] has been updated.
     */
    public TableModelEvent(TableModel source, int firstRow, int lastRow) {
        this(source, firstRow, lastRow, ALL_COLUMNS, UPDATE);
    }

    /**
     * This event notifies listeners that the value of the cell at
     * [row, column] has been updated.
     */
    public TableModelEvent(TableModel source, int firstRow, int lastRow, int column) {
        this(source, firstRow, lastRow, column, UPDATE);
    }

    /**
     * This event notifies listeners that rows in the range [firstRow, lastRow]
     * have been inserted, updated or deleted.
     */
    public TableModelEvent(TableModel source, int firstRow, int lastRow, int column, int type) {
        super(source);
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.column = column;
        this.type = type;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public int getColumn() {
        return column;
    }

    public int getType() {
        return type;
    }
}
