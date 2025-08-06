package java.applet;

import java.net.URL;
import static def.dom.Globals.console;
import java.util.Enumeration;
import java.awt.Image;

class AppletContext {
	protected Applet applet;

	public AppletContext(Applet applet) {
		this.applet = applet;
	}

	public Applet getApplet(String name) {
		// TODO: Do anything with name?
		return applet;
	}

	public Enumeration<Applet> getApplets() {
		// TODO: Implement this? Is it used in CTK?
		return null;
	}

	public void showDocument(URL url) {
		showDocument(url, "_top");
	}

	public void showDocument(URL url, String target) {
		// TODO: Implement this.
		console.log("Attempted to showDocument.");
	}

	public Image getImage(URL url) {
		return applet.getImage(url);
	}
}