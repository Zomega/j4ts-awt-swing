package javax.swing;

import static def.dom.Globals.document;
import static def.dom.Globals.window;

import def.js.Promise;
import def.dom.Response;
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

    window.fetch(page.toString())
        .then((Response response) -> {
            if (!response.ok) {
                throw new Error("Network response was not ok.");
            }
            return response.text();
        })
        .then((String text) -> {
            setText(text);
            return null;
        })
        .Catch((error) -> {
            setText("<html><body><h1>Error loading page: " + error.toString() + "</h1></body></html>");
            return null;
        });
  }
}
