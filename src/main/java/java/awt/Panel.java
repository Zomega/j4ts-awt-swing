package java.awt;

import static def.dom.Globals.document;
import static def.dom.Globals.window;

import def.dom.HTMLCanvasElement;
import jsweet.util.StringTypes;

public class Panel extends Container {

  private HTMLCanvasElement htmlCanvas;

  public Panel() {
    this(new FlowLayout());
  }

  public Panel(LayoutManager layout) {
    setLayout(layout);
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-panel";
  }

  @Override
  public Graphics getGraphics() {
    return new WebGraphics2D(htmlCanvas);
  }

  @Override
  public void setBackground(Color background) {
    super.setBackground(background);
    if (htmlElement != null) {
      if (background != null) {
        htmlElement.style.backgroundColor = background.toHTML();
      }
    }
    if (htmlCanvas != null) {
      if (background != null) {
        htmlCanvas.style.backgroundColor = background.toHTML();
      }
    }
  }

  @Override
  public void doPaintInternal() {
    if (htmlCanvas.width == 0 && htmlCanvas.height == 0) {
      htmlCanvas.width = htmlElement.offsetWidth;
      htmlCanvas.height = htmlElement.offsetHeight;
    }
    super.doPaintInternal();
  }

  @Override
  public void initHTML() {
    super.initHTML();
    if (htmlCanvas == null) {
      htmlCanvas = document.createElement(StringTypes.canvas);
      htmlElement.className = "applet-panel-canvas";
      htmlElement.appendChild(htmlCanvas);
      window.onresize =
          e -> {
            if ((htmlCanvas.width != htmlElement.offsetWidth)
                || (htmlCanvas.height != htmlElement.offsetHeight)) {
              htmlCanvas.width = htmlElement.offsetWidth;
              htmlCanvas.height = htmlElement.offsetHeight;
              repaint();
            }
            return e;
          };
    }
    if (background != null) {
      htmlElement.style.backgroundColor = background.toHTML();
      htmlCanvas.style.backgroundColor = background.toHTML();
    }
    htmlCanvas.width = htmlElement.offsetWidth;
    htmlCanvas.height = htmlElement.offsetHeight;
    htmlCanvas.style.position = "absolute";
    htmlCanvas.style.zIndex = "-1";
  }
}
