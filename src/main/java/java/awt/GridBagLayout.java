package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;
import static jsweet.util.Lang.object;

import def.dom.*;
import jsweet.util.StringTypes;

public class GridBagLayout implements LayoutManager2 {
  boolean created = false;

  Container parent;
  public HTMLDivElement gridContainer;
  int rows, cols;
  int hgap, vgap;

  public GridBagLayout() {
    this(1, 1, 0, 0);
  }

  public GridBagLayout(int rows, int cols) {
    this(rows, cols, 0, 0); // Default to no gaps
  }

  public GridBagLayout(int rows, int cols, int hgap, int vgap) {
    this.rows = rows;
    this.cols = cols;
    this.hgap = hgap;
    this.vgap = vgap;
  }

  @Override
  public void addLayoutComponent(String name, Component component) {
    if (gridContainer != null) {
      gridContainer.appendChild(component.getHTMLElement());
    }
  }

  @Override
  public void removeLayoutComponent(Component component) {
    HTMLElement componentElement = component.getHTMLElement();
    if (gridContainer.contains(componentElement)) {
      gridContainer.removeChild(componentElement);
    }
  }

  @Override
  public void layoutContainer(Container parent) {
    if (!created) {
      this.parent = parent;
      created = true;
      HTMLDivElement parentElement = any(parent.getHTMLElement());

      gridContainer = document.createElement(StringTypes.div);
      gridContainer.className = "applet-grid-layout";

      // Set the CSS properties for the grid layout dynamically.
      object(gridContainer.style).$set("display", "grid");
      object(gridContainer.style)
          .$set("grid-template-rows", "repeat(" + this.rows + ", 1fr)");
      object(gridContainer.style)
          .$set("grid-template-columns", "repeat(" + this.cols + ", 1fr)");
      object(gridContainer.style).$set("gap", this.vgap + "px " + this.hgap + "px");

      gridContainer.style.width = "100%";
      gridContainer.style.height = "100%";

      // Append the new grid container to the parent element.
      parentElement.appendChild(gridContainer);
    }
  }

  @Override
  public void addLayoutComponent(Component component, Object o) {
    if (o instanceof GridBagConstraints) {
      setConstraints(component, (GridBagConstraints) o);
    }
    addLayoutComponent((String) null, component);
  }

  @Override
  public float getLayoutAlignmentX(Container container) {
    return 0;
  }

  @Override
  public float getLayoutAlignmentY(Container container) {
    return 0;
  }

  @Override
  public void invalidateLayout(Container container) {}

  public void setConstraints(Component comp, GridBagConstraints constraints) {
    HTMLElement element = comp.getHTMLElement();
    object(element.style).$set("grid-column-start", "" + (constraints.gridx + 1));
    object(element.style).$set("grid-column-end", "span " + constraints.gridwidth);
    object(element.style).$set("grid-row-start", "" + (constraints.gridy + 1));
    object(element.style).$set("grid-row-end", "span " + constraints.gridheight);
  }
}
