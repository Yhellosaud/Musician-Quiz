/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author :Group Toxic Sushi
 * @version 1.0
 */

public class Level {
    /*Attributes:
    * number of choices as int 
    * index Of Answer as int 
    * time as int 
    * audio Variants as Audio []
    * visual Variants as Visual []
    */
    private final int numChoices;
    private final int indexOfAnswer;
    private final int time;
    private final Audio[] audioVariants;
    private final Visual[] visualVariants;
    /*
    * @Param : int numChoices, int time, int indexOfAnswer, Audio[] audioVariants, Visual[] visualVariants 
    * Initializes the attributes    
    */
    public Level (int numChoices, int time, int indexOfAnswer, Audio[] audioVariants, Visual[] visualVariants) {
        
        this.numChoices = numChoices;
        this.time = time;
        this.indexOfAnswer = indexOfAnswer;
        this.audioVariants = audioVariants;
        this.visualVariants = visualVariants;
    }
    /*
    * @Param : Default
    * @return: int
    * gets Number Of Choices
    */
    public int getNumberOfChoices () {
        
        return numChoices;
    }
    /*
    * @Param : Default
    * @return: int
    * gets time
    */
    public int getTime () {
        
        return time;
    }
    /*
    * @Param : Default
    * @return: int
    * gets Index of Answer
    */
    public int getIndexOfAnswer () {
        
        return indexOfAnswer;
    }
    /*
    * @Param : Default
    * @return: Audio []
    * gets audio Variants   
    */
    public Audio[] getVariants () {
        
        return audioVariants;
    }
    /*
    * @Param : Default
    * @return: Visual
    * gets Visual variants
    */
    public Visual getVisual(int index) {
        
        return visualVariants[index];
    }
       /*
    * @Param : Default
    * @return: Audio
    * gets selected song
    */
    public Audio getSelectedSong (int index) {
        
        return audioVariants[index];
    }
    /* @Param : Default
    * @return: String
    * gets selected song
    */
    public String getArtistName () {
        
        return audioVariants[indexOfAnswer].getSoundName();
    }
}
