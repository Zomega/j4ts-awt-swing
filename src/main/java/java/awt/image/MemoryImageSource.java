package java.awt.image;

import java.util.Hashtable;
import java.util.Vector;

public class MemoryImageSource implements ImageProducer {
  int width;
  int height;
  ColorModel model;
  int[] pixels;
  int offset;
  int scan;
  Hashtable properties;
  Vector consumers = new Vector();
  boolean animated;

  public MemoryImageSource(int w, int h, int[] pix, int off, int scan) {
    this.width = w;
    this.height = h;
    this.model = ColorModel.getRGBdefault();
    this.pixels = pix;
    this.offset = off;
    this.scan = scan;
    this.properties = null;
  }

  public MemoryImageSource(int w, int h, int[] pix, int off, int scan, Hashtable<?, ?> props) {
    this.width = w;
    this.height = h;
    this.model = ColorModel.getRGBdefault();
    this.pixels = pix;
    this.offset = off;
    this.scan = scan;
    this.properties = props;
  }

  @Override
  public synchronized void addConsumer(ImageConsumer ic) {
    if (consumers.contains(ic)) {
      return;
    }
    consumers.addElement(ic);
  }

  @Override
  public synchronized boolean isConsumer(ImageConsumer ic) {
    return consumers.contains(ic);
  }

  @Override
  public synchronized void removeConsumer(ImageConsumer ic) {
    consumers.removeElement(ic);
  }

  @Override
  public void startProduction(ImageConsumer ic) {
    addConsumer(ic);
    if (ic != null) {
      ic.setDimensions(width, height);
      if (properties != null) {
        ic.setProperties(properties);
      }
      ic.setColorModel(model);
      ic.setHints(
          animated
              ? (ImageConsumer.SINGLEPASS)
              : (ImageConsumer.SINGLEPASS | ImageConsumer.STATICIMAGEDONE));
      sendPixels(ic, 0, 0, width, height);
      if (!animated) {
        ic.imageComplete(ImageConsumer.STATICIMAGEDONE);
        if (isConsumer(ic)) {
          removeConsumer(ic);
        }
      }
    }
  }

  private void sendPixels(ImageConsumer ic, int x, int y, int w, int h) {
    int off = offset + scan * y + x;
    ic.setPixels(x, y, w, h, model, pixels, off, scan);
  }

  @Override
  public void requestTopDownLeftRightResend(ImageConsumer ic) {
    // This method is not used by this class.
  }

  public synchronized void setAnimated(boolean animated) {
    this.animated = animated;
    if (!animated) {
      for (int i = 0; i < consumers.size(); i++) {
        ImageConsumer ic = (ImageConsumer) consumers.elementAt(i);
        ic.imageComplete(ImageConsumer.STATICIMAGEDONE);
      }
      consumers.removeAllElements();
    }
  }

  public synchronized void newPixels() {
    newPixels(0, 0, width, height, true);
  }

  public synchronized void newPixels(int x, int y, int w, int h) {
    newPixels(x, y, w, h, true);
  }

  public synchronized void newPixels(int x, int y, int w, int h, boolean framenotify) {
    if (animated) {
      if (consumers.size() > 0) {
        for (int i = 0; i < consumers.size(); i++) {
          ImageConsumer ic = (ImageConsumer) consumers.elementAt(i);
          sendPixels(ic, x, y, w, h);
          if (framenotify) {
            ic.imageComplete(ImageConsumer.SINGLEFRAMEDONE);
          }
        }
      }
    }
  }
}
