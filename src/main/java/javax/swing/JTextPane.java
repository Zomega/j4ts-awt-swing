package javax.swing;

import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import java.net.URL;

class JTextPane extends JComponent {
	// TODO

	@Override
	public void createHTML() {
		if (htmlElement != null) {
			return;
		}
		htmlElement = document.createElement(StringTypes.div);
	}

	public void setPage(URL page) {
		// TODO: Load the URL into the div.
	}
}