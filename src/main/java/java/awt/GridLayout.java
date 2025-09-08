package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;
import static jsweet.util.Lang.object;

import def.dom.*;
import jsweet.util.StringTypes;

public class GridLayout implements LayoutManager2 {

  boolean created = false;

  Container parent;
  public HTMLDivElement gridContainer;
  int rows, cols;
  int hgap, vgap;

  public GridLayout(int rows, int cols) {
    this(rows, cols, 0, 0); // Default to no gaps
  }

  public GridLayout(int rows, int cols, int hgap, int vgap) {
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

      // In AWT, a value of 0 for rows or cols means "any number".
      // For CSS Grid, not setting the 'grid-template-rows' or 'grid-template-columns'
      // property achieves a similar auto-placement behavior.
      if (rows == 0 && cols == 0) {
        // AWT would throw an IllegalArgumentException here. We'll default to a single column.
        object(gridContainer.style).$set("grid-template-columns", "repeat(1, 1fr)");
      } else {
        if (rows > 0) {
          object(gridContainer.style)
              .$set("grid-template-rows", "repeat(" + this.rows + ", 1fr)");
        }
        if (cols > 0) {
          object(gridContainer.style)
              .$set("grid-template-columns", "repeat(" + this.cols + ", 1fr)");
        }
      }

      object(gridContainer.style).$set("gap", this.vgap + "px " + this.hgap + "px");

      gridContainer.style.width = "100%";
      gridContainer.style.height = "100%";

      // Append the new grid container to the parent element.
      parentElement.appendChild(gridContainer);
    }
  }

  @Override
  public void addLayoutComponent(Component component, Object o) {
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
}
