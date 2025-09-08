package javax.swing;

import java.text.Format;
import java.text.NumberFormat;

public class JFormattedTextField extends JTextField {
  private Format format;

  public JFormattedTextField() {
    super();
  }

  public JFormattedTextField(Object value) {
    this();
    setValue(value);
  }

  public void setValue(Object value) {
    if (format == null) {
      setText(value == null ? "" : value.toString());
    } else {
      try {
        setText(format.format(value));
      } catch (IllegalArgumentException e) {
        // ignore
      }
    }
  }

  public Object getValue() {
    String text = getText();
    if (format == null) {
      return text;
    }
    try {
      return format.parseObject(text);
    } catch (Exception e) {
      return getText();
    }
  }
}
