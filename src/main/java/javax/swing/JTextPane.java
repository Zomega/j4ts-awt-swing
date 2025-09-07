package javax.swing;

import static def.dom.Globals.document;

import def.dom.Event;
import def.dom.ProgressEvent;
import def.dom.XMLHttpRequest;
import java.net.URL;
import jsweet.util.StringTypes;

public class JTextPane extends JEditorPane {

    public JTextPane() {
        super();
    }

  @Override
  public void createHTML() {
    if (htmlElement != null) {
      return;
    }
    htmlElement = document.createElement(StringTypes.div);
    htmlElement.className = "applet-jtextpane";
  }

  public void setPage(URL page) {
    if (page == null) {
        throw new NullPointerException("page must be non-null.");
    }

    XMLHttpRequest xhr = new XMLHttpRequest();
    xhr.open("GET", page.toString(), true);

    xhr.onreadystatechange = (ProgressEvent e) -> {
        if (xhr.readyState == 4) { // DONE
            if (xhr.status >= 200 && xhr.status < 300) {
                setText(xhr.responseText);
            } else {
                setText("<html><body><h1>Error loading page: " + xhr.statusText + "</h1></body></html>");
            }
        }
        return null;
    };

    xhr.onerror = (Event e) -> {
        setText("<html><body><h1>Network error occurred.</h1></body></html>");
        return null;
    };

    xhr.send();
  }
}
