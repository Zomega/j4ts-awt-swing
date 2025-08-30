package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLDivElement;
import jsweet.util.StringTypes;

public class List extends Component {
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
}
