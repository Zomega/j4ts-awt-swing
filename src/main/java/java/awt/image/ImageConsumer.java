package java.awt.image;

import java.util.Hashtable;

public interface ImageConsumer {
  int IMAGEERROR = 1;
  int SINGLEFRAMEDONE = 2;
  int STATICIMAGEDONE = 3;
  int IMAGEABORTED = 4;
  int RANDOMPIXELORDER = 1;
  int TOPDOWNLEFTRIGHT = 2;
  int COMPLETESCANLINES = 4;
  int SINGLEPASS = 8;
  int SINGLEFRAME = 16;

  void imageComplete(int status);

  void setColorModel(ColorModel model);

  void setDimensions(int width, int height);

  void setHints(int hintflags);

  void setPixels(
      int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize);

  void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize);

  void setProperties(Hashtable<?, ?> props);
}
