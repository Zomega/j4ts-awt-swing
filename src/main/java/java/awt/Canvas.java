package java.awt;

import def.dom.CanvasRenderingContext2D; // Import for 2D rendering context
import def.dom.Globals; // Import Globals for document and console
import def.dom.HTMLCanvasElement;
import jsweet.util.StringTypes;

// Assuming you have a WebGraphics2D class that wraps CanvasRenderingContext2D
// You might need to adjust this import based on your actual project structure
// For example: import java.awt.WebGraphics2D;
// For this example, I'll assume a simple Graphics class that can be instantiated
// with a CanvasRenderingContext2D.
// You will need to ensure your 'Graphics' class can wrap a CanvasRenderingContext2D.

/**
 * A <code>Canvas</code> component represents a blank rectangular area of the screen onto which the
 * application can draw or from which the application can trap input events from the user.
 *
 * <p>This is a JSweet port of the Java AWT Canvas, mapping to an HTML5 &lt;canvas&gt; element.
 *
 * @author Sami Shaio (original), Gemini (JSweet port)
 * @since JDK1.0 (original)
 */
public class Canvas extends Component {

  private static final String base = "canvas";
  private static int nameCounter = 0;

  // The underlying HTMLCanvasElement that this AWT Canvas wraps
  protected HTMLCanvasElement htmlCanvasElement;
  // The 2D rendering context for drawing operations
  protected CanvasRenderingContext2D context2D;

  /** Constructs a new Canvas. */
  public Canvas() {
    // In JSweet, the actual HTML element is created in addNotify()
    // or when explicitly bound.
  }

  /**
   * Constructs a new Canvas given a GraphicsConfiguration object. This constructor is largely
   * ignored in a typical JSweet browser context as GraphicsConfiguration is a native AWT concept.
   *
   * @param config a reference to a GraphicsConfiguration object.
   */
  public Canvas(/*GraphicsConfiguration*/ Object config) {
    this();
    // setGraphicsConfiguration(config); // Not directly applicable in JSweet
  }

  // @Override // This method from Component is highly AWT-specific and often not ported directly
  // void setGraphicsConfiguration(GraphicsConfiguration gc) {
  //     // Synchronization and peer concepts are not directly applicable in JSweet
  //     // super.setGraphicsConfiguration(gc);
  // }

  /** Construct a name for this component. Called by getName() when the name is null. */
  String constructComponentName() {
    // Synchronization is ignored by JSweet, but keeping the structure
    // for conceptual consistency with Java AWT.
    // In a single-threaded JS environment, this is less critical.
    return base + nameCounter++;
  }

  /**
   * Creates the peer of the canvas. In the JSweet context, this means creating the underlying HTML
   * &lt;canvas&gt; element and appending it to the component's HTML element. It also initializes
   * the 2D rendering context.
   */
  public void addNotify() {
    // In AWT, this creates the native peer. In JSweet, we create the HTML element.
    if (this.htmlElement == null) {
      // This Canvas needs a parent HTML element to attach to.
      // In JSweet, components typically have an htmlElement field.
      // If the parent htmlElement is not set, we can't attach the canvas.
      Globals.console.error(
          "Canvas: htmlElement is null. Cannot addNotify without a parent HTML element.");
      return;
    }

    if (this.htmlCanvasElement == null) {
      this.htmlCanvasElement =
          (HTMLCanvasElement) Globals.document.createElement(StringTypes.canvas);
      this.htmlCanvasElement.className = "applet-canvas";
      // Set initial size (can be overridden by setSize later)
      this.htmlCanvasElement.width = this.width;
      this.htmlCanvasElement.height = this.height;
      this.htmlElement.appendChild(this.htmlCanvasElement);
      this.context2D = (CanvasRenderingContext2D) this.htmlCanvasElement.getContext("2d");
    }

    // super.addNotify(); // Call superclass addNotify if it has JSweet-specific logic
  }

  /**
   * Sets the size of this component. Overrides Component's setSize to also update the underlying
   * HTML canvas element's width and height.
   *
   * @param width The new width.
   * @param height The new height.
   */
  @Override
  public void setSize(int width, int height) {
    super.setSize(width, height);
    if (this.htmlCanvasElement != null) {
      this.htmlCanvasElement.width = width;
      this.htmlCanvasElement.height = height;
    }
  }

  /**
   * Paints this canvas.
   *
   * <p>Most applications that subclass <code>Canvas</code> should override this method in order to
   * perform some useful operation (typically, custom painting of the canvas). The default operation
   * is simply to clear the canvas. Applications that override this method need not call
   * super.paint(g).
   *
   * @param g the specified Graphics context (expected to be WebGraphics2D or similar)
   */
  public void paint(Graphics g) {
    // Assuming 'g' is a WebGraphics2D or similar wrapper around CanvasRenderingContext2D
    if (g != null && this.context2D != null) {
      // Default behavior: clear the canvas
      g.clearRect(0, 0, this.width, this.height);
    }
  }

  /**
   * Updates this canvas.
   *
   * <p>This method is called in response to a call to <code>repaint</code>. The canvas is first
   * cleared by filling it with the background color, and then completely redrawn by calling this
   * canvas's <code>paint</code> method.
   *
   * @param g the specified Graphics context (expected to be WebGraphics2D or similar)
   */
  public void update(Graphics g) {
    if (g != null && this.context2D != null) {
      g.clearRect(0, 0, this.width, this.height);
      paint(g);
    }
  }

  // This method is AWT-specific for old mouse events and typically not ported.
  // boolean postsOldMouseEvents() {
  //     return true;
  // }

  /**
   * Creates a new strategy for multi-buffering on this component. In a browser environment, this is
   * often a no-op as HTML Canvas rendering is typically immediate or managed by the browser's
   * rendering loop.
   *
   * @param numBuffers number of buffers to create, including the front buffer
   * @exception IllegalArgumentException if numBuffers is less than 1.
   * @exception IllegalStateException if the component is not displayable
   */
  public void createBufferStrategy(int numBuffers) {
    if (numBuffers < 1) {
      throw new IllegalArgumentException("Number of buffers must be at least 1.");
    }
    // No-op for HTML Canvas in a simple port.
    // If more advanced rendering is needed (e.g., WebGL), this would be more complex.
    Globals.console.warn("createBufferStrategy is a no-op in JSweet Canvas port.");
  }

  /**
   * Creates a new strategy for multi-buffering on this component with the required buffer
   * capabilities. In a browser environment, this is often a no-op.
   *
   * @param numBuffers number of buffers to create
   * @param caps the required capabilities for creating the buffer strategy; cannot be <code>null
   *     </code>
   * @exception AWTException if the capabilities supplied could not be supported or met.
   * @exception IllegalArgumentException if numBuffers is less than 1, or if caps is <code>null
   *     </code>
   */
  public void createBufferStrategy(int numBuffers, /* BufferCapabilities caps */ Object caps)
      throws AWTException { // Changed type to Object as BufferCapabilities is AWT-specific
    if (numBuffers < 1) {
      throw new IllegalArgumentException("Number of buffers must be at least 1.");
    }
    if (caps == null) {
      throw new IllegalArgumentException("BufferCapabilities cannot be null.");
    }
    // No-op for HTML Canvas in a simple port.
    Globals.console.warn(
        "createBufferStrategy with capabilities is a no-op in JSweet Canvas port.");
  }

  /**
   * Returns the <code>BufferStrategy</code> used by this component. In a browser environment, this
   * will typically return null or a dummy object as direct buffer strategies are not applicable.
   *
   * @return the buffer strategy used by this component (or null)
   */
  public /* BufferStrategy */ Object getBufferStrategy() { // Changed return type to Object
    return null; // Not applicable for direct HTML Canvas rendering
  }

  /**
   * Creates the primary HTML element for this component. For a Canvas, this creates an HTML
   * &lt;canvas&gt; element. This method is typically called by the framework when the component is
   * added to a parent.
   */
  @Override // Assuming Component declares this or HTMLComponent is an interface
  public void createHTML() {
    this.htmlCanvasElement = (HTMLCanvasElement) Globals.document.createElement(StringTypes.canvas);
    this.htmlCanvasElement.className = "applet-canvas";
    // Set initial size (can be overridden by setSize later)
    this.htmlCanvasElement.width = this.width;
    this.htmlCanvasElement.height = this.height;
    this.htmlElement = this.htmlCanvasElement; // Set the component's main HTML element
    this.context2D = (CanvasRenderingContext2D) this.htmlCanvasElement.getContext("2d");
  }
}
