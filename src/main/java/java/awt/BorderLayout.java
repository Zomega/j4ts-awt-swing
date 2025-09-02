/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package java.awt;

import static def.dom.Globals.document;
import static jsweet.util.Lang.any;

import def.dom.HTMLDivElement;
import jsweet.util.StringTypes;

public class BorderLayout implements LayoutManager2, java.io.Serializable {
  boolean created = false;

  Container parent;
  public HTMLDivElement container;
  public HTMLDivElement middleRow;

  int hgap;
  int vgap;

  Component north;
  Component west;
  Component east;
  Component south;
  Component center;

  Component firstLine;
  Component lastLine;
  Component firstItem;
  Component lastItem;

  public static final String NORTH = "North";
  public static final String SOUTH = "South";
  public static final String EAST = "East";
  public static final String WEST = "West";
  public static final String CENTER = "Center";

  public static final String PAGE_START = "First";
  public static final String PAGE_END = "Last";
  public static final String LINE_START = "Before";
  public static final String LINE_END = "After";

  private static final long serialVersionUID = -8658291919501921765L;

  public BorderLayout() {
    this(0, 0);
  }

  public BorderLayout(int hgap, int vgap) {
    this.hgap = hgap;
    this.vgap = vgap;
  }

  public int getHgap() {
    return hgap;
  }

  public void setHgap(int hgap) {
    this.hgap = hgap;
  }

  public int getVgap() {
    return vgap;
  }

  public void setVgap(int vgap) {
    this.vgap = vgap;
  }

  public void addLayoutComponent(Component comp, Object constraints) {
    if ((constraints == null) || (constraints instanceof String)) {
      addLayoutComponent((String) constraints, comp);
    } else {
      throw new IllegalArgumentException(
          "cannot add to layout: constraint must be a string (or null)");
    }
  }

  @Deprecated
  public void addLayoutComponent(String name, Component comp) {
    if (name == null) {
      name = "Center";
    }

    Component old = getLayoutComponent(name);
    if (old != null && old.parent != null) {
      old.parent.remove(old);
      old.parent = null;
    }

    switch (name) {
      case "Center":
        center = comp;
        break;
      case "North":
        north = comp;
        break;
      case "South":
        south = comp;
        break;
      case "East":
        east = comp;
        break;
      case "West":
        west = comp;
        break;
      case PAGE_START:
        firstLine = comp;
        break;
      case PAGE_END:
        lastLine = comp;
        break;
      case LINE_START:
        firstItem = comp;
        break;
      case LINE_END:
        lastItem = comp;
        break;
      default:
        throw new IllegalArgumentException("cannot add to layout: unknown constraint: " + name);
    }
    add(comp, name);
  }

  public void removeLayoutComponent(Component comp) {
    comp.getHTMLElement().parentNode.removeChild(comp.getHTMLElement());

    if (comp == center) {
      center = null;
    } else if (comp == north) {
      north = null;
    } else if (comp == south) {
      south = null;
    } else if (comp == east) {
      east = null;
    } else if (comp == west) {
      west = null;
    }
    if (comp == firstLine) {
      firstLine = null;
    } else if (comp == lastLine) {
      lastLine = null;
    } else if (comp == firstItem) {
      firstItem = null;
    } else if (comp == lastItem) {
      lastItem = null;
    }
  }

  public Component getLayoutComponent(Object constraints) {
    if (CENTER.equals(constraints)) {
      return center;
    } else if (NORTH.equals(constraints)) {
      return north;
    } else if (SOUTH.equals(constraints)) {
      return south;
    } else if (WEST.equals(constraints)) {
      return west;
    } else if (EAST.equals(constraints)) {
      return east;
    } else if (PAGE_START.equals(constraints)) {
      return firstLine;
    } else if (PAGE_END.equals(constraints)) {
      return lastLine;
    } else if (LINE_START.equals(constraints)) {
      return firstItem;
    } else if (LINE_END.equals(constraints)) {
      return lastItem;
    } else {
      throw new IllegalArgumentException(
          "cannot get component: unknown constraint: " + constraints);
    }
  }

  public Component getLayoutComponent(Container target, Object constraints) {
    boolean ltr = true;
    Component result;

    if (NORTH.equals(constraints)) {
      result = (firstLine != null) ? firstLine : north;
    } else if (SOUTH.equals(constraints)) {
      result = (lastLine != null) ? lastLine : south;
    } else if (WEST.equals(constraints)) {
      result = ltr ? firstItem : lastItem;
      if (result == null) {
        result = west;
      }
    } else if (EAST.equals(constraints)) {
      result = ltr ? lastItem : firstItem;
      if (result == null) {
        result = east;
      }
    } else if (CENTER.equals(constraints)) {
      result = center;
    } else {
      throw new IllegalArgumentException(
          "cannot get component: invalid constraint: " + constraints);
    }

    return result;
  }

  public Object getConstraints(Component comp) {
    if (comp == null) {
      return null;
    }
    if (comp == center) {
      return CENTER;
    } else if (comp == north) {
      return NORTH;
    } else if (comp == south) {
      return SOUTH;
    } else if (comp == west) {
      return WEST;
    } else if (comp == east) {
      return EAST;
    } else if (comp == firstLine) {
      return PAGE_START;
    } else if (comp == lastLine) {
      return PAGE_END;
    } else if (comp == firstItem) {
      return LINE_START;
    } else if (comp == lastItem) {
      return LINE_END;
    }
    return null;
  }

  public float getLayoutAlignmentX(Container parent) {
    return 0.5f;
  }

  public float getLayoutAlignmentY(Container parent) {
    return 0.5f;
  }

  public void invalidateLayout(Container target) {}

  public void layoutContainer(Container parent) {
    if (!created) {
      this.parent = parent;
      created = true;
      HTMLDivElement div = any(parent.getHTMLElement());
      this.container = document.createElement(StringTypes.div);
      this.container.className = "applet-border-layout-container";
      div.appendChild(this.container);

      this.middleRow = document.createElement(StringTypes.div);
      this.middleRow.className = "applet-border-layout-middle-row";
      this.container.appendChild(this.middleRow);

      add(north, NORTH);
      add(south, SOUTH);
      add(west, WEST);
      add(east, EAST);
      add(center, CENTER);
    }
  }

  private void add(Component component, String position) {
    if (component == null) {
      return;
    }

    HTMLDivElement componentDiv = any(document.createElement(StringTypes.div));
    componentDiv.className = "applet-border-layout-item " + position.toLowerCase();
    componentDiv.appendChild(component.getHTMLElement());

    // Place components in the correct order
    switch (position) {
      case NORTH:
        container.insertBefore(componentDiv, container.firstChild);
        break;
      case SOUTH:
        container.appendChild(componentDiv);
        break;
      case WEST:
        middleRow.insertBefore(componentDiv, middleRow.firstChild);
        break;
      case EAST:
        middleRow.appendChild(componentDiv);
        break;
      case CENTER:
        middleRow.appendChild(componentDiv);
        break;
    }
  }

  public String toString() {
    return getClass().getName() + "[hgap=" + hgap + ",vgap=" + vgap + "]";
  }
}
