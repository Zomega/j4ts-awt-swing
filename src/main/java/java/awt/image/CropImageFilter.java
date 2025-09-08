package java.awt.image;

import java.awt.Rectangle;
import java.util.Hashtable;

public class CropImageFilter extends ImageFilter {
  int cropX;
  int cropY;
  int cropW;
  int cropH;

  public CropImageFilter(int x, int y, int w, int h) {
    this.cropX = x;
    this.cropY = y;
    this.cropW = w;
    this.cropH = h;
  }

  @Override
  public void setDimensions(int w, int h) {
    consumer.setDimensions(cropW, cropH);
  }

  @Override
  public void setPixels(
      int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize) {
    int x1 = Math.max(x, cropX);
    int y1 = Math.max(y, cropY);
    int x2 = Math.min(x + w, cropX + cropW);
    int y2 = Math.min(y + h, cropY + cropH);
    if (x1 >= x2 || y1 >= y2) {
      return;
    }
    consumer.setPixels(
        x1 - cropX,
        y1 - cropY,
        (x2 - x1),
        (y2 - y1),
        model,
        pixels,
        off + (y1 - y) * scansize + (x1 - x),
        scansize);
  }

  @Override
  public void setPixels(
      int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {
    int x1 = Math.max(x, cropX);
    int y1 = Math.max(y, cropY);
    int x2 = Math.min(x + w, cropX + cropW);
    int y2 = Math.min(y + h, cropY + cropH);
    if (x1 >= x2 || y1 >= y2) {
      return;
    }
    consumer.setPixels(
        x1 - cropX,
        y1 - cropY,
        (x2 - x1),
        (y2 - y1),
        model,
        pixels,
        off + (y1 - y) * scansize + (x1 - x),
        scansize);
  }

  @Override
  public void setProperties(Hashtable<?, ?> props) {
    Hashtable<Object, Object> p = (Hashtable<Object, Object>) props.clone();
    p.put("croprect", new Rectangle(cropX, cropY, cropW, cropH));
    super.setProperties(p);
  }
}
