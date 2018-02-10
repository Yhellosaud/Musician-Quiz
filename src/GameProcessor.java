/**
 *
 * @author Toxic Sushi
 * @version 1.0
 */

/*Attributes:
 * variants as character []
 * former as int
 * level as Level
 * gm as Game
 * answer as boolean 
 * response1 and response 2 as boolean
 * 
 */
import java.util.Arrays;
import java.util.Comparator;

public class GameProcessor {
    
    private final Character[] variants = {'-', 'q', 'w', 'e', 'r', 't', 'y', 'u'};
    private int former = 0;
    private Level level;
    private Game gm;
    private boolean answer = false;
    private boolean response1, response2 = false;
    private int wrongAnswerCount = 0;
    private int score;
    
    /*
    * Constructor
    * @Param Game gm
    * Initializes attributes
    */
    
    public GameProcessor (Game gm) {
        
        this.gm = gm;
        level = gm.getCurrentLevel();
        gm.setCurrentIcon (gm.getPlayLogos().get(4).getImageIcon());
        gm.setCurrentEffect (gm.getSoundEffects().get(0));
    }
    
    /*
    * @Param char typed, boolean entered
    * void
    * processes sound action
    */
    
    public void processTheAction (char typed, boolean entered) {
        
        int index = Arrays.asList(variants).indexOf(typed);
        
        if (former != index-1) {

            level.getSelectedSong(former).stopSound();
        }
        
        if (index != 0) {
            
            level.getSelectedSong(index-1).playSound();
            former = index-1;
        }
            
        if (entered == false) {
            
            gm.setCurrentImage(level.getVisual(index).getImage());
        }

        else {
            
            level.getSelectedSong(former).stopSound();

            if (response1 == true) {

                if (level.getIndexOfAnswer() == index-1) {
                    
                    gm.getSoundEffects().get(3).playSound();
                    gm.setCurrentIcon (gm.getPlayLogos().get(5).getImageIcon());
                }

                else {

                    gm.getSoundEffects().get(2).playSound();
                    gm.setCurrentIcon (gm.getPlayLogos().get(4).getImageIcon());
                }   
            }
            
            if (response2 == true) {
                
                if (level.getIndexOfAnswer() == index-1) {

                    gm.setCurrentIcon(gm.getPlayLogos().get(6).getImageIcon());
                    answer = true;
                }

                else {
                    
                    wrongAnswerCount++;
                    gm.setCurrentIcon(gm.getPlayLogos().get(7).getImageIcon());
                    answer = false;
                }
            }
            
            gm.setCurrentImage(level.getVisual(level.getNumberOfChoices() + 1).getImage());
        }
        

    }
    
    /*
    * @Param: Default
    * void
    * iteration of the level
    */
    
    public void iterateLevel () {
        
        if (answer == true) {
            
            level = gm.getNextLevel();
            answer = false;
        }
    }
    
    public boolean isAnswerCorrect () {
        
        return this.answer;
    }
    
    /*
    * @Param: boolean response1, response2
    * void
    * sets the responses
    */
    
    public void setResponses (boolean response1, boolean response2) {
        
        this.response1 = response1;
        this.response2 = response2;
    }
    
    /*public void calculateScore () {
        
        score = score + level.getTime()*10 + gm.getIndexOfCurrentLevel()*5 - wrongAnswerCount*5;
    }
    
    public int compareTo (Player p) {
        
        return Comparators.SCORE.compare(this, p);
    }*/
    
}
