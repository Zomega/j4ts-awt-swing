package java.awt;

import java.util.Vector;

public class MediaTracker {
  private Component target;
  private Vector<TrackedImage> images = new Vector<>();

  public static final int LOADING = 1;
  public static final int ABORTED = 2;
  public static final int ERRORED = 4;
  public static final int COMPLETE = 8;

  public MediaTracker(Component comp) {
    this.target = comp;
  }

  public void addImage(Image image, int id) {
    addImage(image, id, -1, -1);
  }

  public synchronized void addImage(Image image, int id, int w, int h) {
    images.add(new TrackedImage(image, id, w, h));
  }

  public boolean checkAll() {
    return checkAll(false);
  }

  public synchronized boolean checkAll(boolean load) {
    for (TrackedImage ti : images) {
      if ((ti.status & COMPLETE) == 0) {
        return false;
      }
    }
    return true;
  }

  public synchronized boolean isErrorAny() {
    for (TrackedImage ti : images) {
      if ((ti.status & ERRORED) != 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method is a no-op in this implementation because blocking is not feasible in a
   * single-threaded JavaScript environment.
   */
  public void waitForAll() throws InterruptedException {
    // No-op
  }

  /**
   * This method is a no-op in this implementation because blocking is not feasible in a
   * single-threaded JavaScript environment.
   *
   * @return always returns true
   */
  public boolean waitForAll(long ms) throws InterruptedException {
    return true;
  }

  public int statusID(int id, boolean load) {
    int status = 0;
    for (TrackedImage ti : images) {
      if (ti.id == id) {
        status |= ti.status;
      }
    }
    return status;
  }

  private class TrackedImage {
    Image image;
    int id;
    int width;
    int height;
    int status;

    TrackedImage(Image image, int id, int w, int h) {
      this.image = image;
      this.id = id;
      this.width = w;
      this.height = h;
      this.status = 0;
      // In a real implementation, we'd listen for image loading events
      // and update the status. For this stub, we'll assume it completes instantly.
      this.status = COMPLETE;
    }
  }
}
