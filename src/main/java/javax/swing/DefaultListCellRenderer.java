package javax.swing;

import java.awt.Color;
import java.awt.Component;

@SuppressWarnings("serial")
public class DefaultListCellRenderer<E> extends JLabel implements ListCellRenderer<E> {

  public DefaultListCellRenderer() {
    super();
    setOpaque(true);
  }

  @Override
  public Component getListCellRendererComponent(
      JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {

    setText((value == null) ? "" : value.toString());

    if (isSelected) {
      setBackground(new Color(0, 120, 215)); // Standard blue selection color
      setForeground(Color.white);
    } else {
      setBackground(Color.white);
      setForeground(Color.black);
    }

    setEnabled(list == null ? true : list.isEnabled());
    setFont(list == null ? null : list.getFont());

    return this;
  }
}
