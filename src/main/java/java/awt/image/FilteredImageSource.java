package java.awt.image;

import java.util.Hashtable;

public class FilteredImageSource implements ImageProducer {
  ImageProducer src;
  ImageFilter filter;

  public FilteredImageSource(ImageProducer orig, ImageFilter imgf) {
    src = orig;
    filter = imgf;
  }

  private Hashtable consumers = new Hashtable();

  @Override
  public synchronized void addConsumer(ImageConsumer ic) {
    if (consumers.get(ic) == null) {
      consumers.put(ic, filter.getFilterInstance(ic));
    }
  }

  @Override
  public synchronized boolean isConsumer(ImageConsumer ic) {
    return consumers.get(ic) != null;
  }

  @Override
  public synchronized void removeConsumer(ImageConsumer ic) {
    consumers.remove(ic);
  }

  @Override
  public void startProduction(ImageConsumer ic) {
    addConsumer(ic);
    src.startProduction((ImageConsumer) consumers.get(ic));
  }

  @Override
  public void requestTopDownLeftRightResend(ImageConsumer ic) {
    addConsumer(ic);
    src.requestTopDownLeftRightResend((ImageConsumer) consumers.get(ic));
  }
}
