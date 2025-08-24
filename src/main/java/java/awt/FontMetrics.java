package java.awt;

import def.dom.CanvasRenderingContext2D;
import def.dom.Globals; // For console and document
import def.dom.HTMLCanvasElement;
import java.io.Serializable;

/**
 * The FontMetrics class contains information about the rendering of a particular font on a
 * particular screen. This is a JSweet port, implementing the abstract class as a concrete one with
 * default behaviors.
 *
 * @author Ilya S. Okomin (original), Gemini (JSweet port)
 */
public class FontMetrics implements Serializable {

  private static final long serialVersionUID = 1681126225205050147L;

  protected Font font;

  // A default/dummy CanvasRenderingContext2D for basic measurements
  // This will be used when a Graphics context is not explicitly provided.
  // In a real scenario, this should be initialized with a valid context
  // from an actual HTML canvas.
  private transient CanvasRenderingContext2D defaultContext2D;

  /**
   * Instantiates a new font metrics from the specified Font.
   *
   * @param fnt the Font.
   */
  public FontMetrics(Font fnt) {
    this.font = fnt;
    // Lazily initialize defaultContext2D when first needed.
    // This avoids creating a canvas element if FontMetrics is only used for its font field.
  }

  /**
   * Returns the String representation of this FontMetrics.
   *
   * @return the string.
   */
  @Override
  public String toString() {
    return this.getClass().getName()
        + "[font="
        + this.getFont()
        + "ascent="
        + this.getAscent()
        + ", descent="
        + this.getDescent()
        + ", height="
        + this.getHeight()
        + "]";
  }

  /**
   * Gets the font associated with this FontMetrics.
   *
   * @return the font associated with this FontMetrics.
   */
  public Font getFont() {
    return font;
  }

  /**
   * Gets the height of the text line in this Font.
   *
   * @return the height of the text line in this Font.
   */
  public int getHeight() {
    // Default implementation: sum of ascent, descent, and leading.
    // These will also return default values.
    return this.getAscent() + this.getDescent() + this.getLeading();
  }

  /**
   * Gets the font ascent of the Font associated with this FontMetrics. For a JSweet port, this is a
   * default/stub value. A more accurate implementation would require measuring text on a canvas.
   *
   * @return the ascent of the Font associated with this FontMetrics.
   */
  public int getAscent() {
    // Default/stub value. Real value depends on font and context.
    // A common default for web fonts might be around 12-16px for a 16px font.
    // This is a rough estimate without actual font measurement.
    return 15; // Example default
  }

  /**
   * Gets the font descent of the Font associated with this FontMetrics. For a JSweet port, this is
   * a default/stub value.
   *
   * @return the descent of the Font associated with this FontMetrics.
   */
  public int getDescent() {
    // Default/stub value. Real value depends on font and context.
    return 5; // Example default
  }

  /**
   * Gets the leading of the Font associated with this FontMetrics. For a JSweet port, this is a
   * default/stub value.
   *
   * @return the leading of the Font associated with this FontMetrics.
   */
  public int getLeading() {
    // Default/stub value. Line spacing.
    return 0; // Example default
  }

  /**
   * Checks if the Font has uniform line metrics or not.
   *
   * @return true, if the Font has uniform line metrics, false otherwise.
   */
  public boolean hasUniformLineMetrics() {
    // Default to true for simplicity in a JSweet port.
    return true;
  }

  /**
   * Returns the distance from the leftmost point to the rightmost point on the string's baseline
   * showing the specified array of bytes in this Font.
   *
   * @param data the array of bytes to be measured.
   * @param off the start offset.
   * @param len the number of bytes to be measured.
   * @return the advance width of the array.
   */
  public int bytesWidth(byte[] data, int off, int len) {
    if ((off >= data.length) || (off < 0)) {
      throw new IllegalArgumentException("offset off is out of range");
    }
    if ((off + len > data.length)) {
      throw new IllegalArgumentException("number of elements len is out of range");
    }
    int width = 0;
    for (int i = off; i < off + len; i++) {
      // Assuming charWidth can handle byte values as characters
      width += charWidth((char) (data[i] & 0xFF)); // Convert byte to char
    }
    return width;
  }

  /**
   * Returns the distance from the leftmost point to the rightmost point on the string's baseline
   * showing the specified array of characters in this Font.
   *
   * @param data the array of characters to be measured.
   * @param off the start offset.
   * @param len the number of bytes to be measured.
   * @return the advance width of the array.
   */
  public int charsWidth(char[] data, int off, int len) {
    if ((off >= data.length) || (off < 0)) {
      throw new IllegalArgumentException("offset off is out of range");
    }
    if ((off + len > data.length)) {
      throw new IllegalArgumentException("number of elements len is out of range");
    }
    int width = 0;
    for (int i = off; i < off + len; i++) {
      width += charWidth(data[i]);
    }
    return width;
  }

  /**
   * Returns the distance from the leftmost point to the rightmost point of the specified character
   * in this Font.
   *
   * @param ch the specified Unicode point code of character to be measured.
   * @return the advance width of the character.
   */
  public int charWidth(int ch) {
    if (defaultContext2D == null) {
      // Lazily create a dummy context if not provided
      HTMLCanvasElement dummyCanvas = (HTMLCanvasElement) Globals.document.createElement("canvas");
      dummyCanvas.className = "applet-dummy-canvas";
      defaultContext2D = (CanvasRenderingContext2D) dummyCanvas.getContext("2d");
      // Set a default font for the dummy context for more meaningful measurements
      if (this.font != null) {
        // Assuming Font.getFontName() and Font.getSize() exist and are meaningful
        // This will depend on your Font class implementation.
        defaultContext2D.font = this.font.getSize() + "px " + this.font.getFontName();
      } else {
        defaultContext2D.font = "16px sans-serif"; // Fallback default
      }
    }
    return (int) defaultContext2D.measureText(String.valueOf(Character.toChars(ch))).width;
  }

  /**
   * Returns the distance from the leftmost point to the rightmost point of the specified character
   * in this Font.
   *
   * @param ch the specified character to be measured.
   * @return the advance width of the character.
   */
  public int charWidth(char ch) {
    // Delegates to the int version
    return charWidth((int) ch);
  }

  /**
   * Gets the maximum advance width of character in this Font. For a JSweet port, this is a
   * default/stub value or derived from `measureText`.
   *
   * @return the maximum advance width of character in this Font.
   */
  public int getMaxAdvance() {
    // This would ideally iterate through common characters or specific font data.
    // For a simple port, return a reasonable default or estimate.
    // A rough estimate might be the width of 'W' or 'M'.
    return charWidth('W'); // Example: max advance is roughly width of 'W'
  }

  /**
   * Gets the maximum font ascent of the Font associated with this FontMetrics.
   *
   * @return the maximum font ascent of the Font associated with this FontMetrics.
   */
  public int getMaxAscent() {
    // For simplicity, return the same as getAscent for now.
    return getAscent();
  }

  /**
   * Gets the maximum font descent of character in this Font.
   *
   * @return the maximum font descent of character in this Font.
   * @deprecated Replaced by getMaxDescent() method.
   */
  @Deprecated
  public int getMaxDecent() {
    return getMaxDescent();
  }

  /**
   * Gets the maximum font descent of character in this Font.
   *
   * @return the maximum font descent of character in this Font.
   */
  public int getMaxDescent() {
    // For simplicity, return the same as getDescent for now.
    return getDescent();
  }

  /**
   * Gets the advance widths of the characters in the Font. This is a complex method to port
   * accurately without full font data.
   *
   * @return the advance widths of the characters in the Font.
   */
  public int[] getWidths() {
    // This would typically return an array of 256 or more character widths.
    // For a simple port, return null or an empty array.
    Globals.console.warn("getWidths() is a stub in JSweet FontMetrics. Returns null.");
    return null;
  }

  /**
   * Returns the advance width for the specified String in this Font.
   *
   * @param str String to be measured.
   * @return the the advance width for the specified String in this Font.
   */
  public int stringWidth(String str) {
    if (str == null || str.isEmpty()) {
      return 0;
    }
    // Use the dummy context to measure the string width
    if (defaultContext2D == null) {
      // Lazily create a dummy context if not provided
      HTMLCanvasElement dummyCanvas = (HTMLCanvasElement) Globals.document.createElement("canvas");
      dummyCanvas.className = "applet-dummy-canvas";
      defaultContext2D = (CanvasRenderingContext2D) dummyCanvas.getContext("2d");
      if (this.font != null) {
        defaultContext2D.font = this.font.getSize() + "px " + this.font.getFontName();
      } else {
        defaultContext2D.font = "16px sans-serif"; // Fallback default
      }
    }
    return (int) defaultContext2D.measureText(str).width;
  }
}
