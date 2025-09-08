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
      JList<? extends E> list,
      E value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {

    setText((value == null) ? "" : value.toString());

    Color bg = null;
    Color fg = null;

    if (isSelected) {
      bg = (Color) UIManager.get("List.selectionBackground");
      fg = (Color) UIManager.get("List.selectionForeground");
    } else {
      bg = (Color) UIManager.get("List.background");
      fg = (Color) UIManager.get("List.foreground");
    }

    setBackground(bg);
    setForeground(fg);

    setEnabled(list == null ? true : list.isEnabled());
    setFont(list == null ? null : list.getFont());

    return this;
  }
}
