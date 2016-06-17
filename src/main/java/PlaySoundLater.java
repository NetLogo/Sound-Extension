package org.nlogo.extensions.sound;

import java.net.URL;
import org.nlogo.core.SyntaxJ;

/**
 * NetLogo command plays a sound file
 */
public class PlaySoundLater
    implements org.nlogo.api.Command {
  public String getAgentClassString() {
    return "OTP";
  }

  public org.nlogo.core.Syntax getSyntax() {
    int[] right =
        {
            org.nlogo.core.Syntax.StringType(), // sample URL
            org.nlogo.core.Syntax.NumberType(), // delay
        };
    return SyntaxJ.commandSyntax(right);
  }

  public boolean getSwitchesBoolean() {
    return false;
  }

  public org.nlogo.api.Command newInstance(String name) {
    return new PlaySoundLater();
  }


  public void perform(org.nlogo.api.Argument args[], org.nlogo.api.Context context)
      throws org.nlogo.api.ExtensionException, org.nlogo.api.LogoException {
    try {
      String soundpath = args[0].getString();
      int delay = Math.round((float) args[1].getDoubleValue() * (float) 1000.0);
      URL soundurl;
      soundpath = context.attachCurrentDirectory(soundpath);

      try {
        soundurl = new URL(context.attachCurrentDirectory(soundpath));
      } catch (java.net.MalformedURLException ex) {
        soundurl = new URL("file", "", soundpath);
      }
      SoundExtension.playSoundLater(soundurl, delay);

    } catch (java.net.MalformedURLException ex) {
      throw new org.nlogo.api.ExtensionException
          ("Unable to open sound sample: " + ex.getMessage());
    }
  }
}
