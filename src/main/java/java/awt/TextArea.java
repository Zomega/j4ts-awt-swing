package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLTextAreaElement;
import jsweet.util.StringTypes;

public class TextArea extends Component {
  private HTMLTextAreaElement textAreaElement;

  public TextArea() {
    this("", 0, 0, SCROLLBARS_BOTH);
  }

  public TextArea(int rows, int columns) {
    this("", rows, columns, SCROLLBARS_BOTH);
  }

  public TextArea(String text) {
    this(text, 0, 0, SCROLLBARS_BOTH);
  }

  public TextArea(String text, int rows, int columns) {
    this(text, rows, columns, SCROLLBARS_BOTH);
  }

  public TextArea(String text, int rows, int columns, int scrollbars) {
    super();
    if (textAreaElement == null) {
      createHTML();
    }
    textAreaElement.value = text;
    if (rows > 0) {
      textAreaElement.rows = rows;
    }
    if (columns > 0) {
      textAreaElement.cols = columns;
    }
    setScrollbars(scrollbars);
  }

  public void append(String str) {
    textAreaElement.value += str;
  }

  public void setText(String t) {
    textAreaElement.value = t;
  }

  public String getText() {
    return textAreaElement.value;
  }

  private void setScrollbars(int scrollbars) {
    switch (scrollbars) {
      case SCROLLBARS_BOTH:
        textAreaElement.style.overflow = "auto";
        break;
      case SCROLLBARS_VERTICAL_ONLY:
        textAreaElement.style.overflowY = "scroll";
        textAreaElement.style.overflowX = "hidden";
        break;
      case SCROLLBARS_HORIZONTAL_ONLY:
        textAreaElement.style.overflowX = "scroll";
        textAreaElement.style.overflowY = "hidden";
        break;
      case SCROLLBARS_NONE:
        textAreaElement.style.overflow = "hidden";
        break;
    }
  }

  public FontMetrics getFontMetrics() {
    return null; // TODO: Implement
  }

  public FontMetrics getFontMetrics(Font font) {
    return null; // TODO: Implement
  }

  @Override
  public void createHTML() {
    textAreaElement = (HTMLTextAreaElement) document.createElement(StringTypes.textarea);
    htmlElement = textAreaElement;
    htmlElement.className = "applet-text-area";
  }

  public static final int SCROLLBARS_BOTH = 0;
  public static final int SCROLLBARS_VERTICAL_ONLY = 1;
  public static final int SCROLLBARS_HORIZONTAL_ONLY = 2;
  public static final int SCROLLBARS_NONE = 3;
}
