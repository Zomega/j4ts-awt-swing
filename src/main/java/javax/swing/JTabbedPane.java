package javax.swing;

import static def.dom.Globals.document;

import def.dom.HTMLDivElement;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import jsweet.util.StringTypes;

public class JTabbedPane extends JComponent implements SwingConstants {

  private int tabPlacement = TOP;
  private List<Tab> tabs = new ArrayList<>();
  private HTMLDivElement tabContainer;
  private HTMLDivElement contentContainer;

  private static class Tab {
    String title;
    Icon icon;
    Component component;
    String tip;
    HTMLDivElement tabButton;

    Tab(String title, Icon icon, Component component, String tip) {
        this.title = title;
        this.icon = icon;
        this.component = component;
        this.tip = tip;
    }
  }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jtabbedpane";
    htmlElement.style.display = "flex";

    tabContainer = (HTMLDivElement) document.createElement(StringTypes.div);
    tabContainer.className = "applet-jtabbedpane-tabs";

    contentContainer = (HTMLDivElement) document.createElement(StringTypes.div);
    contentContainer.className = "applet-jtabbedpane-content";

    updateLayout();
  }

  public void setTabPlacement(int tabPlacement) {
    if (tabPlacement != TOP && tabPlacement != BOTTOM && tabPlacement != LEFT && tabPlacement != RIGHT) {
        throw new IllegalArgumentException("invalid tab placement");
    }
    this.tabPlacement = tabPlacement;
    updateLayout();
  }

  private void updateLayout() {
      if (htmlElement == null) {
          return;
      }
      // Clear and re-add children in the correct order
      while(htmlElement.firstChild != null) {
          htmlElement.removeChild(htmlElement.firstChild);
      }

      if (tabPlacement == TOP || tabPlacement == LEFT) {
          htmlElement.appendChild(tabContainer);
          htmlElement.appendChild(contentContainer);
      } else {
          htmlElement.appendChild(contentContainer);
          htmlElement.appendChild(tabContainer);
      }

      if (tabPlacement == TOP || tabPlacement == BOTTOM) {
          htmlElement.style.flexDirection = "column";
          tabContainer.style.flexDirection = "row";
      } else {
          htmlElement.style.flexDirection = "row";
          tabContainer.style.flexDirection = "column";
      }
  }

  public void addTab(String title, Icon icon, Component component, String tip) {
    Tab tab = new Tab(title, icon, component, tip);
    tabs.add(tab);

    tab.tabButton = (HTMLDivElement) document.createElement(StringTypes.div);
    tab.tabButton.className = "applet-jtabbedpane-tab";
    tab.tabButton.innerText = title;
    tab.tabButton.title = tip;

    tab.tabButton.onclick = (e) -> {
        setSelectedTab(tab);
        return e;
    };

    tabContainer.appendChild(tab.tabButton);
    contentContainer.appendChild(component.getHTMLElement());

    if (tabs.size() == 1) {
        setSelectedTab(tab);
    } else {
        component.getHTMLElement().style.display = "none";
    }
  }

  private void setSelectedTab(Tab selectedTab) {
      for (Tab tab : tabs) {
          boolean isSelected = tab == selectedTab;
          tab.component.getHTMLElement().style.display = isSelected ? "block" : "none";
          if (isSelected) {
              tab.tabButton.classList.add("active");
          } else {
              tab.tabButton.classList.remove("active");
          }
      }
  }

  // TODO: Get model.
  public SingleSelectionModel getModel() {
    return null;
  }
}
