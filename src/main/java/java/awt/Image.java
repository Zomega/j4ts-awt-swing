/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.awt;

import static def.dom.Globals.document;

import def.dom.CanvasRenderingContext2D;
import def.dom.HTMLCanvasElement;
import def.dom.HTMLImageElement;
import def.dom.ImageData;
import def.js.Uint8Array;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Vector;
import jsweet.util.StringTypes;

public class Image implements ImageProducer {

  // @Interface
  // class ImageSource {
  // public double width;
  // public double height;
  // }

  // public Image(ImageSource source) {
  // this.source = source;
  // }

  public Image(String src) {
    source = document.createElement(StringTypes.img);
    source.className = "applet-image";
    source.src = src;
    setScale(SCALE_DEFAULT);
  }

  private void setScale(int scale) {
    switch (scale) {
      case SCALE_DEFAULT:
      case SCALE_FAST:
      case SCALE_REPLICATE:
        source.style.$set("imageRendering", "pixelated");
        break;
      case SCALE_SMOOTH:
      case SCALE_AREA_AVERAGING:
        source.style.$set("imageRendering", "");
    }
  }

  public int getWidth(ImageObserver observer) {
    return (int) source.width;
  }

  public int getHeight(ImageObserver observer) {
    return (int) source.height;
  }

  public final HTMLImageElement source;

  // public abstract ImageProducer getSource();

  // TODO: Implement?
  public Graphics getGraphics() {
    return null;
  }

  public Image getScaledInstance(int width, int height, int scaleType) {
    Image image = new Image(source.src);
    image.source.width = width;
    image.source.height = height;
    image.setScale(scaleType);
    return image;
  }

  /**
   * Use the default image-scaling algorithm.
   *
   * @since JDK1.1
   */
  public static final int SCALE_DEFAULT = 1;

  /**
   * Choose an image-scaling algorithm that gives higher priority to scaling speed than smoothness
   * of the scaled image.
   *
   * @since JDK1.1
   */
  public static final int SCALE_FAST = 2;

  public static final int SCALE_SMOOTH = 4;

  public static final int SCALE_REPLICATE = 8;

  public static final int SCALE_AREA_AVERAGING = 16;

  public void flush() {
    // do nothing
  }

  private Vector<ImageConsumer> consumers = new Vector<>();

  public ImageProducer getSource() {
    return this;
  }

  @Override
  public synchronized void addConsumer(ImageConsumer ic) {
    if (!consumers.contains(ic)) {
      consumers.addElement(ic);
    }
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
    if (source.complete) {
      imageLoaded();
    } else {
      source.onload =
          (e) -> {
            imageLoaded();
            return null;
          };
    }
  }

  private void imageLoaded() {
    int w = (int) source.width;
    int h = (int) source.height;

    HTMLCanvasElement canvas = (HTMLCanvasElement) document.createElement("canvas");
    canvas.width = w;
    canvas.height = h;
    CanvasRenderingContext2D ctx = (CanvasRenderingContext2D) canvas.getContext("2d");
    ctx.drawImage(source, 0, 0);
    ImageData imageData = ctx.getImageData(0, 0, w, h);
    Uint8Array data = imageData.data;
    int[] pixels = new int[w * h];
    for (int i = 0; i < pixels.length; i++) {
      double r = data.$get(i * 4);
      double g = data.$get(i * 4 + 1);
      double b = data.$get(i * 4 + 2);
      double a = data.$get(i * 4 + 3);
      pixels[i] = ((int) a << 24) | ((int) r << 16) | ((int) g << 8) | (int) b;
    }

    for (ImageConsumer consumer : consumers) {
      consumer.setDimensions(w, h);
      consumer.setPixels(0, 0, w, h, ColorModel.getRGBdefault(), pixels, 0, w);
      consumer.imageComplete(ImageConsumer.STATICIMAGEDONE);
    }
    consumers.clear();
  }

  @Override
  public void requestTopDownLeftRightResend(ImageConsumer ic) {
    // not implemented
  }
}
