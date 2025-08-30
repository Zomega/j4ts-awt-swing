package javax.swing;

import static def.dom.Globals.document;

import java.awt.Component;
import jsweet.util.StringTypes;
import javax.swing.table.TableModel;

class JTable extends JComponent {
  // TODO

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jtable";
  }

  public TableModel getSelectionModel() {
    return null;   // TODO: Get model.
  }
}
