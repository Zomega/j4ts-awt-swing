package java.applet;

import def.dom.Element;
import def.dom.HTMLDivElement;
import def.dom.NodeList;
import jsweet.util.StringTypes;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.awt.Image;

import static def.dom.Globals.console;
import static def.dom.Globals.document;
import static def.dom.Globals.window;

import static jsweet.util.Lang.$new;
import static jsweet.util.Lang.any;
import static jsweet.util.Lang.object;

public class Applet extends Panel {

	static {
		// TODO: Will this work with multiple applets?
		// May need to be changed.
		window.addEventListener(StringTypes.load, e -> {
			def.js.Object[] divList = any(document.getElementsByClassName("applet"));
			if (divList.length == 0) {
				return null;
			}
			HTMLDivElement div = (HTMLDivElement) divList[0];
			if (div.getAttribute("data-applet") != null) {
				String[] names = div.getAttribute("data-applet").split(".");
				Object constructor = window;
				for (String name : names) {
					constructor = object(constructor).$get(name);
				}
				Applet applet = $new(constructor);

				applet.setSize(Integer.parseInt(div.getAttribute("data-width")),
						Integer.parseInt(div.getAttribute("data-height")));

				applet.bindHTML(div);
				applet.init();
				applet.doPaintInternal();
			}
			return null;
		});
	}

	public Applet() {
	}

	public void init() {
	}

	public String getParameter(String param) {
		// 1. Try to get the parameter from a data-attribute first
		String dataValue = this.htmlElement.getAttribute("data-" + param);

		// If the data-attribute exists and has a value, return it
		if (dataValue != null && !dataValue.isEmpty()) {
			return dataValue;
		}

		// 2. If no data-attribute or it's empty, iterate through child <param> elements
		Element element;
		for (int i = 0; i < this.htmlElement.children.length; i++) {
			element = this.htmlElement.children.item(i);
			if (element.tagName == "PARAM") {
				if (element.getAttribute("name") == param) {
					return element.getAttribute("value");
				}
			}
		}

		// 3. If neither method finds the parameter, return null
		return null;
	}

	/**
	 * Gets the base URL. This is the URL of the directory which contains this applet.
	 * In a web environment, this typically corresponds to the base URL of the HTML page.
	 *
	 * @return The base URL of the applet.
	 */
	public URL getCodeBase() {
		try {
			// In a browser, the code base is typically the directory of the HTML document itself.
			// This might need adjustment if applets are loaded from different origins.
			String href = window.location.href;
			// Find the last slash to get the directory
			int lastSlash = href.lastIndexOf('/');
			if (lastSlash > -1) {
				return new URL(href.substring(0, lastSlash + 1));
			}
			return new URL(href); // If no slash, return the full URL
		} catch (MalformedURLException e) {
			console.error("Malformed URL Exception in getCodeBase: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Gets the URL of the document in which this applet is embedded.
	 * In a web environment, this is simply the current page's URL.
	 *
	 * @return The URL of the document containing this applet.
	 */
	public URL getDocumentBase() {
		try {
			return new URL(window.location.href);
		} catch (MalformedURLException e) {
			console.error("Malformed URL Exception in getDocumentBase: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Requests that the argument string be displayed in the "status window".
	 * In a browser, this attempts to update the browser's status bar.
	 * Note: Modern browsers severely restrict direct manipulation of the status bar
	 * for security reasons. For reliable status display in a web app, it's better
	 * to update a dedicated DOM element (e.g., a div with id="applet-status").
	 *
	 * @param msg A string to display in the status window.
	 */
	public void showStatus(String msg) {
		// Attempt to set the browser's status bar text.
		// This might not be visible in all modern browsers due to security restrictions.
		window.status = msg;

		// A more reliable approach for web applications would be to update a specific
		// HTML element on the page, like a <div> or <p> tag designated for status messages.
		// Example (requires an element with id="applet-status" in your HTML):
		// Element statusElement = document.getElementById("applet-status");
		// if (statusElement != null) {
		//     statusElement.textContent = msg;
		// }
	}

	public Image getImage(URL url) {
		// TODO: Confirm this is correct.
		return new Image(url.toString());
	}

	public AppletContext getAppletContext() {
		return new AppletContext(this);
	}
}
