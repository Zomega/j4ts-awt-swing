package javax.swing;

import java.awt.Component;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;

class JTabbedPane extends JComponent {
	// TODO

	@Override
	public void createHTML() {
		if (htmlElement != null) {
			return;
		}
		htmlElement = document.createElement(StringTypes.div);
	}

	public void setTabPlacement(int tabPlacement) {
		// TODO
	}

	public void addTab(String title, Icon icon, Component component, String tip) {
		// TODO
	}
}