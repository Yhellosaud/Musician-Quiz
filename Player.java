/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author :Group Toxic Sushi
 * @version 1.0
 */
public class Player {
    /*
    * Attributes
    * player name as String
    * hig score as int
    */
    private String playerName;
    private int highScore;
    /*
    * Constructor
    * @Param: Default
    * Initializes attributes
    */
    public Player () {
        
        playerName = "";
        highScore = 0;
    }
    /*
    * Constructor
    * @Param: String playerName, int highScore
    * Initializes attributes
    */
    public Player (String playerName, int highScore) {
        
        this.playerName = playerName;
        this.highScore = highScore;
    }
    /*
    * @Param : Default
    * @return : int
    * gets score
    */
    public int getScore () {
        
        return highScore;
    }
    /*
    * @Param : int score
    * void
    * sets score
    */
    public void setScore (int score) {
        
        highScore = score;
    }
}
