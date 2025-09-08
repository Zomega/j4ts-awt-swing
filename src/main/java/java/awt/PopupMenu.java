package java.awt;

import static def.dom.Globals.document;

public class PopupMenu extends Menu {

  public PopupMenu() {
    this("");
  }

  public PopupMenu(String label) {
    super(label);
    // The superclass (Menu -> MenuItem) creates an <li> with a <ul> inside.
    // For a top-level popup, we just want the <ul>.
    // We will use the ulElement created by the Menu class.
    this.htmlElement = this.ulElement;
    htmlElement.style.position = "absolute";
    htmlElement.style.display = "none";
    document.body.appendChild(htmlElement);
  }

  public void show(Component origin, int x, int y) {
    htmlElement.style.left = x + "px";
    htmlElement.style.top = y + "px";
    htmlElement.style.display = "block";

    // Add a one-time event listener to hide the menu when the user clicks elsewhere.
    // We use setTimeout to delay the listener registration to the next event loop cycle.
    // This prevents the same click event that showed the menu from immediately
    // triggering the hide listener.
    def.dom.Globals.setTimeout(
        () -> {
          final def.dom.EventListener[] listenerHolder = new def.dom.EventListener[1];
          listenerHolder[0] =
              (e) -> {
                htmlElement.style.display = "none";
                document.removeEventListener("click", listenerHolder[0]);
              };
          document.addEventListener("click", listenerHolder[0]);
        },
        0);
  }
}
