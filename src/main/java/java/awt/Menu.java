package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLUListElement;
import java.util.Vector;
import jsweet.util.StringTypes;

public class Menu extends MenuItem {
  private Vector<MenuItem> items = new Vector<>();
  protected HTMLUListElement ulElement;

  public Menu() {
    this("");
  }

  public Menu(String label) {
    super(label);
    ulElement = (HTMLUListElement) document.createElement(StringTypes.ul);
    htmlElement.appendChild(ulElement);
  }

  public Menu(String label, boolean tearOff) {
    this(label);
    // tearOff is not supported in this implementation
  }

  public MenuItem add(MenuItem mi) {
    items.add(mi);
    ulElement.appendChild(mi.getHTMLElement());
    return mi;
  }

  public void add(String label) {
    add(new MenuItem(label));
  }
}
