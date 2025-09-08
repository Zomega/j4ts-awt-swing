package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;
import static jsweet.util.Lang.object;

import def.dom.*;
import jsweet.util.StringTypes;

/**
 * A simplified implementation of `GridBagLayout` that uses CSS Grid. NOTE: This implementation is
 * not a full `GridBagLayout`. It creates a basic grid and respects `gridx`, `gridy`, `gridwidth`,
 * and `gridheight` from `GridBagConstraints`. It does not support weights, anchoring, or other
 * advanced features. It is intended to provide a "good enough" layout for semi-working
 * transpilation.
 */
public class GridBagLayout implements LayoutManager2 {
  boolean created = false;
  Container parent;
  public HTMLDivElement gridContainer;

  @Override
  public void addLayoutComponent(String name, Component component) {
    if (gridContainer != null) {
      gridContainer.appendChild(component.getHTMLElement());
    }
  }

  @Override
  public void removeLayoutComponent(Component component) {
    if (gridContainer != null && component.getHTMLElement() != null) {
      HTMLElement componentElement = component.getHTMLElement();
      if (gridContainer.contains(componentElement)) {
        gridContainer.removeChild(componentElement);
      }
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
      object(gridContainer.style).$set("display", "grid");
      gridContainer.style.width = "100%";
      gridContainer.style.height = "100%";
      parentElement.appendChild(gridContainer);
    }
  }

  @Override
  public void addLayoutComponent(Component component, Object constraints) {
    if (constraints instanceof GridBagConstraints) {
      setConstraints(component, (GridBagConstraints) constraints);
    }
    addLayoutComponent((String) null, component);
  }

  @Override
  public float getLayoutAlignmentX(Container container) {
    return 0.5f;
  }

  @Override
  public float getLayoutAlignmentY(Container container) {
    return 0.5f;
  }

  @Override
  public void invalidateLayout(Container container) {
    created = false;
  }

  public void setConstraints(Component comp, GridBagConstraints constraints) {
    HTMLElement element = comp.getHTMLElement();
    if (element != null) {
      object(element.style).$set("grid-column-start", "" + (constraints.gridx + 1));
      object(element.style).$set("grid-column-end", "span " + constraints.gridwidth);
      object(element.style).$set("grid-row-start", "" + (constraints.gridy + 1));
      object(element.style).$set("grid-row-end", "span " + constraints.gridheight);
    }
  }

  // Stubs for other LayoutManager methods
  public Dimension preferredLayoutSize(Container parent) {
    return new Dimension(0, 0);
  }

  public Dimension minimumLayoutSize(Container parent) {
    return new Dimension(0, 0);
  }

  public Dimension maximumLayoutSize(Container target) {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }
}
