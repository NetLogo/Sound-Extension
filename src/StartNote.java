/** (c) 2004-2011 Uri Wilensky. See README.txt for terms of use. **/

package org.nlogo.extensions.sound;

/**
 * NetLogo command starts playing a note.
 */
public class StartNote
    implements org.nlogo.api.Command {
  public String getAgentClassString() {
    return "OTP";
  }

  public org.nlogo.api.Syntax getSyntax() {
    int[] right =
        {
            org.nlogo.api.Syntax.TYPE_STRING,
            org.nlogo.api.Syntax.TYPE_NUMBER,
            org.nlogo.api.Syntax.TYPE_NUMBER
        };
    return org.nlogo.api.Syntax.commandSyntax(right);
  }

  public boolean getSwitchesBoolean() {
    return false;
  }

  public org.nlogo.api.Command newInstance(String name) {
    return new StartNote();
  }

  public void perform(org.nlogo.api.Argument args[], org.nlogo.api.Context context)
      throws org.nlogo.api.ExtensionException, org.nlogo.api.LogoException {
    int instrument = SoundExtension.getInstrument(args[0].getString());
    int note = args[1].getIntValue();
    int velocity = args[2].getIntValue();
    SoundExtension.startNote(instrument, note, velocity);
  }
}
