/** (c) 2004-2011 Uri Wilensky. See README.txt for terms of use. **/

package org.nlogo.extensions.sound ;

/**
 * NetLogo command plays a note for a specified duration.
 **/
public class PlayNote
    implements org.nlogo.api.Command
{
    public String getAgentClassString()
    {
        return "OTP" ;
    }

    public org.nlogo.api.Syntax getSyntax()
    {
        int[] right = 
        { 
            org.nlogo.api.Syntax.TYPE_STRING, 
            org.nlogo.api.Syntax.TYPE_NUMBER, 
            org.nlogo.api.Syntax.TYPE_NUMBER, 
            org.nlogo.api.Syntax.TYPE_NUMBER
        };
        return org.nlogo.api.Syntax.commandSyntax( right ) ;
    }

    public boolean getSwitchesBoolean() 
    { 
        return false; 
    }

    public org.nlogo.api.Command newInstance( String name ) 
    {				
        return new PlayNote() ;
    }


    public void perform( org.nlogo.api.Argument args[] , org.nlogo.api.Context context  )
        throws org.nlogo.api.ExtensionException , org.nlogo.api.LogoException
    {
        int instrument = SoundExtension.getInstrument( args[ 0 ].getString() );
        int note = args[ 1 ].getIntValue();
        int velocity = args[ 2 ].getIntValue();
        
        // convert duration from seconds to milliseconds
        int duration = Math.round( (float) args[ 3 ].getDoubleValue() * (float) 1000.0 );
        
        SoundExtension.playNote( instrument, note, velocity, duration );
    }
}
