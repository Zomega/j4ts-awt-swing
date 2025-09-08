package java.applet;

import def.dom.HTMLAudioElement;
import java.net.URL;

/**
 * The <code>AudioClip</code> class is a simple abstraction for playing a sound clip. Multiple
 * <code>AudioClip</code> items can be playing at the same time, and the resulting sound is mixed
 * together to produce a composite sound.
 *
 * @author Arthur van Hoff
 * @since 1.0
 */
public class AudioClip {

  private final HTMLAudioElement audio;

  public AudioClip(URL url) {
    this.audio = new HTMLAudioElement();
    this.audio.src = url.toString();
  }
  /** Starts playing this audio clip in a loop. */
  public void loop() {
    audio.loop = true;
    audio.play();
  }

  /** Starts playing this audio clip. Each time this method is called, the clip is restarted from */
  public void play() {
    audio.play();
  }

  /** Stops playing this audio clip. */
  public void stop() {
    audio.pause();
    audio.currentTime = 0;
  }
}
