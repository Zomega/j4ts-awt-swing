package java.awt.image;

import java.awt.Image;

public class PixelGrabber {
  public PixelGrabber(Image img, int x, int y, int w, int h, boolean forceRGB) {
    // TODO: Implement
  }

  public PixelGrabber(Image img, int x, int y, int w, int h, int[] pix, int off, int scansize) {
    // TODO: Implement
  }

  public PixelGrabber(
      ImageProducer ip, int x, int y, int w, int h, int[] pix, int off, int scansize) {
    // TODO: Implement
  }

  public boolean grabPixels() {
    // TODO: Implement
    return false;
  }

  public boolean grabPixels(long ms) {
    return grabPixels();
  }
}
