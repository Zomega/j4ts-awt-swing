package java.awt;

import static def.dom.Globals.document;

import jsweet.util.StringTypes;

public class TextArea extends Component {
  public TextArea() {
    // TODO: Implement
  }

  public TextArea(int rows, int columns) {
    // TODO: Implement
  }

  public TextArea(String text) {
    // TODO: Implement
  }

  public TextArea(String text, int rows, int columns) {
    // TODO: Implement
  }

  public TextArea(String text, int rows, int columns, int scrollbars) {
    // TODO: Implement
  }

  public void append(String str) {
    // TODO: Implement
  }

  public void setText(String t) {
    // TODO: Implement
  }

  public FontMetrics getFontMetrics() {
    return null; // TODO: Implement
  }

  public FontMetrics getFontMetrics(Font font) {
    return null; // TODO: Implement
  }

  @Override
  public void createHTML() {
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-text-area";
  }

  // TODO: Implement the rest.
}
