package java.awt.image;

import java.awt.Image;
import java.util.Hashtable;

public class PixelGrabber implements ImageConsumer {
  private ImageProducer producer;
  private int dstX;
  private int dstY;
  private int dstW;
  private int dstH;
  private int[] dstBuffer;
  private int dstOffset;
  private int dstScan;
  private boolean grabbing;
  private int flags;

  public PixelGrabber(Image img, int x, int y, int w, int h, boolean forceRGB) {
    this(img.getSource(), x, y, w, h, null, 0, w);
  }

  public PixelGrabber(Image img, int x, int y, int w, int h, int[] pix, int off, int scansize) {
    this(img.getSource(), x, y, w, h, pix, off, scansize);
  }

  public PixelGrabber(
      ImageProducer ip, int x, int y, int w, int h, int[] pix, int off, int scansize) {
    this.producer = ip;
    this.dstX = x;
    this.dstY = y;
    this.dstW = w;
    this.dstH = h;
    this.dstBuffer = pix;
    this.dstOffset = off;
    this.dstScan = scansize;
  }

  public synchronized boolean grabPixels() throws InterruptedException {
    return grabPixels(0);
  }

  public synchronized boolean grabPixels(long ms) throws InterruptedException {
    if ((flags & (ImageConsumer.IMAGEABORTED | ImageConsumer.IMAGEERROR)) != 0) {
      return false;
    }
    grabbing = true;
    producer.startProduction(this);
    return (flags & (ImageConsumer.IMAGEABORTED | ImageConsumer.IMAGEERROR)) == 0;
  }

  @Override
  public synchronized void imageComplete(int status) {
    grabbing = false;
    this.flags = status;
  }

  @Override
  public void setColorModel(ColorModel model) {}

  @Override
  public void setDimensions(int width, int height) {
    if (dstBuffer == null) {
      dstBuffer = new int[width * height];
      dstOffset = 0;
      dstScan = width;
      dstW = width;
      dstH = height;
    }
  }

  @Override
  public void setHints(int hintflags) {}

  @Override
  public void setPixels(
      int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize) {
    int[] intPixels = new int[w * h];
    for (int i = 0; i < w * h; i++) {
      intPixels[i] = model.getRGB(pixels[off + i] & 0xff);
    }
    setPixels(x, y, w, h, model, intPixels, 0, w);
  }

  @Override
  public void setPixels(
      int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {
    if (y < dstY) {
      int diff = dstY - y;
      h -= diff;
      y += diff;
      off += diff * scansize;
    }
    if (y + h > dstY + dstH) {
      h = dstY + dstH - y;
    }
    if (x < dstX) {
      int diff = dstX - x;
      w -= diff;
      x += diff;
      off += diff;
    }
    if (x + w > dstX + dstW) {
      w = dstX + dstW - x;
    }
    if (w <= 0 || h <= 0) {
      return;
    }
    if (dstBuffer == null) {
      return;
    }
    int dstPtr = dstOffset + (y - dstY) * dstScan + (x - dstX);
    for (int i = 0; i < h; i++) {
      System.arraycopy(pixels, off, dstBuffer, dstPtr, w);
      off += scansize;
      dstPtr += dstScan;
    }
  }

  @Override
  public void setProperties(Hashtable<?, ?> props) {}
}
