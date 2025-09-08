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

    // Add a one-time event listener to hide the menu when the user clicks elsewhere
    def.dom.EventListener listener =
        (e) -> {
          htmlElement.style.display = "none";
        };
    document.addEventListener("click", listener, new def.js.Object() {
      {
        $set("once", true);
      }
    });
  }
}
