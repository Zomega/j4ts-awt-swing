package java.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class AWTEventMulticaster extends Object implements ActionListener {

  protected final EventListener a, b;

  public AWTEventMulticaster(EventListener a, EventListener b) {
    this.a = a;
    this.b = b;
  }

  public void actionPerformed(ActionEvent e) {
    if (a != null) ((ActionListener) a).actionPerformed(e);
    if (b != null) ((ActionListener) b).actionPerformed(e);
  }

  public static ActionListener add(ActionListener a, ActionListener b) {
    return (ActionListener) addInternal(a, b);
  }

  public static ActionListener remove(ActionListener l, ActionListener oldl) {
    return (ActionListener) removeInternal(l, oldl);
  }

  protected static EventListener addInternal(EventListener a, EventListener b) {
    if (a == null) return b;
    if (b == null) return a;
    return new AWTEventMulticaster(a, b);
  }

  protected static EventListener removeInternal(EventListener l, EventListener oldl) {
    if (l == oldl || l == null) {
      return null;
    } else if (l instanceof AWTEventMulticaster) {
      return ((AWTEventMulticaster) l).remove(oldl);
    } else {
      return l;
    }
  }

  public EventListener remove(EventListener oldl) {
    if (oldl == a) return b;
    if (oldl == b) return a;
    EventListener a2 = removeInternal(a, oldl);
    EventListener b2 = removeInternal(b, oldl);
    if (a2 == a && b2 == b) {
      return this;
    }
    return addInternal(a2, b2);
  }
}
