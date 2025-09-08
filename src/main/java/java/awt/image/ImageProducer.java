package java.awt.image;

import java.util.Hashtable;

public interface ImageProducer {
  void addConsumer(ImageConsumer ic);

  boolean isConsumer(ImageConsumer ic);

  void removeConsumer(ImageConsumer ic);

  void requestTopDownLeftRightResend(ImageConsumer ic);

  void startProduction(ImageConsumer ic);
}
