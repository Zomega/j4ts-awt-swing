package java.awt.image;

public class ColorModel {
  private static final ColorModel aRGBmodel = new ColorModel();

  public static ColorModel getRGBdefault() {
    return aRGBmodel;
  }

  public int getRGB(int pixel) {
    return pixel;
  }
}
