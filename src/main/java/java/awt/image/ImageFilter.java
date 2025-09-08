package java.awt.image;

import java.util.Hashtable;

public class ImageFilter implements ImageConsumer, Cloneable {
  protected ImageConsumer consumer;

  public ImageFilter getFilterInstance(ImageConsumer ic) {
    ImageFilter f = null;
    try {
      f = (ImageFilter) clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    f.consumer = ic;
    return f;
  }

  @Override
  public void setDimensions(int width, int height) {
    consumer.setDimensions(width, height);
  }

  @Override
  public void setProperties(Hashtable<?, ?> props) {
    consumer.setProperties(props);
  }

  @Override
  public void setColorModel(ColorModel model) {
    consumer.setColorModel(model);
  }

  @Override
  public void setHints(int hintflags) {
    consumer.setHints(hintflags);
  }

  @Override
  public void setPixels(
      int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize) {
    consumer.setPixels(x, y, w, h, model, pixels, off, scansize);
  }

  @Override
  public void setPixels(
      int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {
    consumer.setPixels(x, y, w, h, model, pixels, off, scansize);
  }

  @Override
  public void imageComplete(int status) {
    consumer.imageComplete(status);
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
