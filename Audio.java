/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author Toxic Sushi
*/

import javax.sound.sampled.Clip;

public class Audio {
    /*Attributes:
     * Sound name as String 
     * Sound as Clip
    */
    private final String soundName;
    private final Clip sound;
    /*
    * Constructor
    * @Param: String sound name Clip sound
    * Initializes the attributes
    */
    public Audio (String soundName, Clip sound) {
        
        this.soundName = soundName;
        this.sound = sound;
    }
    /*
    * @Param : Default
    * @return : String 
    * gets sound name
    */
    public String getSoundName () {
        
        return soundName;
    }
    /*
    * @Param : Default
    * @return: Clip
    * gets sound
    */
    public Clip getSound () {
        
        return sound;
    }
    /*
    * @Param : Default
    * void
    * to start sound
    */
    public void playSound () {
        
        sound.start();
    }
    /*
    * @Param : Default
    * void
    *  to stop sound
    */
    public void stopSound () {
        
        sound.stop();
        sound.setMicrosecondPosition(0);
    }
}
