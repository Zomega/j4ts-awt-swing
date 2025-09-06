package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;

import def.dom.HTMLOptionElement;
import def.dom.HTMLSelectElement;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import jsweet.util.StringTypes;

public class List extends Component {

  Vector<String> items = new Vector<>();
  int rows;
  boolean multipleMode;
  ItemListener itemListener;

  public List() {
    this(0, false);
  }

  public List(int rows) {
    this(rows, false);
  }

  public List(int rows, boolean multipleMode) {
    this.rows = rows;
    this.multipleMode = multipleMode;
  }

  @Override
  public HTMLSelectElement getHTMLElement() {
    return any(super.getHTMLElement());
  }

  @Override
  public void createHTML() {
    htmlElement = document.createElement(StringTypes.select);
    htmlElement.className = "applet-list";
    if (multipleMode) {
      getHTMLElement().multiple = true;
    }
    if (rows > 0) {
      getHTMLElement().size = rows;
    }
  }

  @Override
  public void initHTML() {
    super.initHTML();
    getHTMLElement().onchange =
        (e) -> {
          if (itemListener != null) {
            itemListener.itemStateChanged(
                new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, null, 0));
          }
          return e;
        };
  }

  public void addItemListener(ItemListener l) {
    this.itemListener = l;
  }

  public int getSelectedIndex() {
    return getHTMLElement().selectedIndex;
  }

  public void add(String item) {
    add(item, -1);
  }

  public void add(String item, int index) {
    if (index < 0) {
      items.add(item);
    } else {
      items.add(index, item);
    }
    rebuildOptions();
  }

  public void remove(String item) {
    items.remove(item);
    rebuildOptions();
  }

  public void remove(int position) {
    items.remove(position);
    rebuildOptions();
  }

  public void removeAll() {
    items.clear();
    rebuildOptions();
  }

  public void replaceItem(String newValue, int index) {
    items.set(index, newValue);
    rebuildOptions();
  }

  private void rebuildOptions() {
    getHTMLElement().innerHTML = "";
    for (String item : items) {
      HTMLOptionElement option = any(document.createElement(StringTypes.option));
      option.text = item;
      getHTMLElement().add(option);
    }
  }

  public void makeVisible(int index) {
    // Not needed for web
  }

  public int getItemCount() {
    return items.size();
  }

  public String getItem(int index) {
    return items.get(index);
  }
}
