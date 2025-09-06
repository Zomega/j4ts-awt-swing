package java.awt;

import static def.dom.Globals.document;

import def.dom.HTMLDivElement;
import def.dom.HTMLElement;
import jsweet.util.StringTypes;

public class Dialog extends Window {

  String title;
  boolean modal;
  HTMLDivElement titleBar;
  HTMLDivElement contentArea;
  HTMLElement modalOverlay;

  public Dialog(Frame owner, String title, boolean modal) {
    super(owner);
    this.title = title;
    this.modal = modal;
  }

  public Dialog(Frame owner, String title) {
    this(owner, title, false);
  }

  public Dialog(Frame owner, boolean modal) {
    this(owner, "", modal);
  }

  public Dialog(Frame owner) {
    this(owner, "", false);
  }

  @Override
  public void createHTML() {
    super.createHTML();
    htmlElement.className = "applet-dialog";
    htmlElement.style.position = "fixed";
    htmlElement.style.top = "50%";
    htmlElement.style.left = "50%";
    htmlElement.style.transform = "translate(-50%, -50%)";
    htmlElement.style.backgroundColor = "white";
    htmlElement.style.border = "1px solid black";
    htmlElement.style.padding = "10px";
    htmlElement.style.zIndex = "1000";

    titleBar = (HTMLDivElement) document.createElement(StringTypes.div);
    titleBar.className = "applet-dialog-titlebar";
    titleBar.innerText = title;
    htmlElement.appendChild(titleBar);

    contentArea = (HTMLDivElement) document.createElement(StringTypes.div);
    contentArea.className = "applet-dialog-content";
    htmlElement.appendChild(contentArea);

    if (modal) {
      modalOverlay = document.createElement(StringTypes.div);
      modalOverlay.className = "applet-dialog-overlay";
      modalOverlay.style.position = "fixed";
      modalOverlay.style.top = "0";
      modalOverlay.style.left = "0";
      modalOverlay.style.width = "100%";
      modalOverlay.style.height = "100%";
      modalOverlay.style.backgroundColor = "rgba(0,0,0,0.5)";
      modalOverlay.style.zIndex = "999";
      modalOverlay.style.display = "none";
      document.body.appendChild(modalOverlay);
    }
  }

  @Override
  public void add(Component comp, Object constraints) {
    if (contentArea == null) {
      // Component is added before createHTML is called.
      // We need to defer adding it to the DOM.
      super.add(comp, constraints);
      return;
    }
    contentArea.appendChild(comp.getHTMLElement());
  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b);
    if (modalOverlay != null) {
      modalOverlay.style.display = b ? "block" : "none";
    }
  }

  public void dispose() {
    setVisible(false);
    if (htmlElement != null) {
      htmlElement.remove();
    }
    if (modalOverlay != null) {
      modalOverlay.remove();
    }
  }

  public void setTitle(String title) {
    this.title = title;
    if (titleBar != null) {
      titleBar.innerText = title;
    }
  }
}
