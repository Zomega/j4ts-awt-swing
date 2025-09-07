package javax.swing.table;

import static def.dom.Globals.document;

import javax.swing.JComponent;
import jsweet.util.StringTypes;

public class JTableHeader extends JComponent {

    protected TableColumnModel columnModel;

    public JTableHeader(TableColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    @Override
    public void createHTML() {
        if (htmlElement != null) {
            return;
        }
        htmlElement = document.createElement(StringTypes.thead);
        htmlElement.className = "applet-jtable-header";

        def.dom.HTMLTableRowElement headerRow = (def.dom.HTMLTableRowElement) document.createElement(StringTypes.tr);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            def.dom.HTMLTableCellElement th = (def.dom.HTMLTableCellElement) document.createElement(StringTypes.th);
            th.innerText = (String) column.getHeaderValue();
            headerRow.appendChild(th);
        }
        htmlElement.appendChild(headerRow);
    }
}
