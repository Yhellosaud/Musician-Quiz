/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toxic Sushi
 */

import java.util.ArrayList;
import java.util.Random;

public final class LevelGenerator {
    /*
    * Attributes:numChoices, time, period, audios, males, females, visuals
    */
    private int numChoices, period, start, end;
    private int time = 10;
    private final Level[] levels = new Level[17];
    private final ArrayList<Audio> audios, males, females;
    private final ArrayList<Visual> visuals;
    private final Random rndm = new Random();
    /*
     * Constructor
     * @Param: ArrayList<Audio> audios, ArrayList<Visual> visuals
     * Initializes the attributes
     */
    public LevelGenerator (ArrayList<Audio> audios, ArrayList<Visual> visuals) {
        
        this.audios = audios;
        this.visuals = visuals;
        
        period = 1;
        numChoices = 2;
        start = 0;
        end = 3;
        
        males = new ArrayList<>();
        females = new ArrayList<>();
        //sets audios in arraylists accoring to their sexes
        for (int i = 0; i < audios.size(); i++) {
            
            if (i < 32) {
                
                males.add(audios.get(i));
            }
            
            else {
                
                females.add(audios.get(i));
            }
        }
        /*
        * Generates the time
        * and creates levels array
        */
        for (int i = 0; i < levels.length; i++) {
            
            levels[i] = generateNewLevel();
        }
    }
    /*
    * @Param : Default
    * @return : Level
    * generates nwew level  
    */
    public Level generateNewLevel () {
        
        int random;
        
        //Checks perioods: In every period difficulty changes so number of choices increase 
        
        if (period%3 == 0) {
            
            time = time + 10;
            numChoices++;
            end = end + numChoices + 2;
            start = start + numChoices + 1;
        }
        
        int visualNumber = numChoices + 2;
        // Creates and initializes audioVariants and visualVariants
        Audio[] audioVariants = new Audio[numChoices];
        Visual[] visualVariants = new Visual[visualNumber];
        
        // Creates and initializes indexOfAnswer randomly
        int indexOfAnswer = (int)(Math.random()*numChoices);
        // Chooses songs from females or maales
        for (int i = 0; i < numChoices; i++) {
            
            if (period%2 == 0) {
                
                random = (int)(Math.random()*males.size());
                audioVariants[i] = males.get(random);
                males.remove(audioVariants[indexOfAnswer]);
            }

            else {

                random = (int)(Math.random()*females.size());
                audioVariants[i] = females.get(random);
                females.remove(audioVariants[indexOfAnswer]);
            }  
            
        }

        int index = 0;
        // gets visuals from Visual Variants
        for (int i = start; i <= end; i++) {
            
            visualVariants[index] = visuals.get(i);
            index++;
            
        }

        period++;
       
        return new Level (numChoices, time, indexOfAnswer, audioVariants, visualVariants);
    }
    /*
    * @Param: Default
    * @return: Level []
    * gets levels
    */
    public Level[] getLevels () {
        
        return levels;
    }
}
