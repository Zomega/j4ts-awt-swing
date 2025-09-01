package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLDivElement;
import jsweet.util.StringTypes;
import java.awt.event.ItemListener;

public class List extends Component {
  public List() {
    this(0, false);
  }

  public List(int rows) {
    this(rows, false);
  }

  public List(int rows,
            boolean multipleMode) {
    // TODO: Implement
  }

  @Override
  public HTMLDivElement getHTMLElement() {
    return (HTMLDivElement) super.getHTMLElement();
  }

  @Override
  public void createHTML() {
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-list";
  }

  @Override
  public void initHTML() {
    super.initHTML();
  }

  public void addItemListener(ItemListener l) {
    // TODO: Implement
  }

  public int getSelectedIndex() {
    return -1; // TODO: Implement
  }

  public void add(String item) {
    // TODO: Implement
  }

  public void add(String item,
                int index) {
    // TODO: Implement
  }

  // TODO: Also public void remove(String item)
  public void remove(int position) {
    // TODO: Implement
  }

  public void removeAll() {
    // TODO: Implement
  }

  public void replaceItem(String newValue,
                        int index) {
    // TODO: Implement
  }

  public void makeVisible(int index) {
    // TODO: Implement
  }

  public int getItemCount() {
    return 0; // TODO: Implement
  }

  public String getItem(int index) {
    return ""; // TODO: Implement
  }
}
