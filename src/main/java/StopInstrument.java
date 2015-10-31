package org.nlogo.extensions.sound;

/**
 * NetLogo command stops all notes played by an instrument.
 */
public class StopInstrument
    implements org.nlogo.api.Command {
  public String getAgentClassString() {
    return "OTP";
  }

  public org.nlogo.core.Syntax getSyntax() {
    int[] right = {org.nlogo.core.Syntax.StringType()};
    return org.nlogo.core.Syntax.commandSyntax(right);
  }

  public boolean getSwitchesBoolean() {
    return false;
  }

  public org.nlogo.api.Command newInstance(String name) {
    return new StopInstrument();
  }

  public void perform(org.nlogo.api.Argument args[], org.nlogo.api.Context context)
      throws org.nlogo.api.ExtensionException, org.nlogo.api.LogoException {
    int instrument = SoundExtension.getInstrument(args[0].getString());
    SoundExtension.stopNotes(instrument);
  }
}
