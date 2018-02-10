/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.sound.sampled.*;

public class Game {

/*
* Attributes 
* Audio music,effect
* Visual visual
* AudioInputStram ais
* ArrayList<Audio> audios,effects
*  ArrayList<Visual> visuals, gifs, logos
*  Image im, currentImage
*  ImageIcon io, currentIcon
*  int curent level
*  Scanner reader
*  ArrayList<Player> highScoreTable
*  Audio currentEffect
*/
    private Audio music, effect;
    private Visual visual;
    private AudioInputStream ais;
    private ArrayList<Audio> audios;
    private ArrayList<Visual> visuals;
    private ArrayList<Audio> effects;
    private ArrayList<Visual> gifs;
    private ArrayList<Visual> logos;
    private Image im;
    private ImageIcon io;
    private ImageIcon currentIcon;
    private Image currentImage;
    private Audio currentEffect;
    private int currentLevel;
    private final Level[] levels;
    private final LevelGenerator lg;
    private Scanner reader;
    private ArrayList<Player> highScoreTable;
    
    /*
    * Constructor
    * @Param: Default
    * @exception: UnsupportedAudioFileException, IOException
    * Initializes  attributes
    */
    
    public Game () throws UnsupportedAudioFileException, IOException {
        
        try {
            
            // Initializes audios, visuals, gifs, logos, effects
            audios = new ArrayList<>();
            visuals = new ArrayList<>();
            gifs = new ArrayList<>();
            logos = new ArrayList<>();
            effects = new ArrayList<>();
            highScoreTable = new ArrayList<>();
            
            for (int i = 0; i < 10; i++) {
                
                highScoreTable.add(new Player());
            }

            /*Initializes ais
            * Creates artist Name and fills it using reader
            * Creates song as clip
            * audios_@param : artistname, song 
            * adds music to audios arrayList
            * does these using for loop
            */
            reader = new Scanner (new File("src/Resource/Text/Male Artists.txt"));
            
            for (int i = 0; reader.hasNext(); i++) {

                String artistName = reader.nextLine();
                
                ais = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("Resource/Audio/Tracks/Male/" + artistName + ".wav"));
                Clip song = AudioSystem.getClip ();
                song.open (ais);

                music = new Audio (artistName, song);

                audios.add(music);
            }
             
            /* Changes ais
            * Creates artist Name and fills it using reader
            * Creates song as clip
            * audios_@param : artistname, song 
            * adds music to audios arrayList
            * does these using for loop
            */
            
            reader = new Scanner (new File("src/Resource/Text/Female Artists.txt"));
            
            for (int i = 0; reader.hasNext(); i++) {

                String artistName = reader.nextLine();
                
                ais = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("Resource/Audio/Tracks/Female/" + artistName + ".wav"));
                Clip song = AudioSystem.getClip ();
                song.open (ais);

                music = new Audio (artistName, song);

                audios.add(music);
            }
            
            reader = new Scanner (new File("src/Resource/Text/Visual Variants.txt"));
            
            for (int i = 0; reader.hasNext(); i++) {
                
                String visualName = reader.nextLine();
                im = new ImageIcon (getClass().getResource("Resource/Visual/Jpeg/" + visualName + ".jpg")).getImage();
                visual = new Visual (visualName, im);
                
                visuals.add(visual);
            }
            
            reader = new Scanner (new File("src/Resource/Text/Sound Effects.txt"));
            
            for (int i = 0; reader.hasNext(); i++) {

                String effectName = reader.nextLine();
                ais = AudioSystem.getAudioInputStream (getClass().getResource ("Resource/Audio/Sound Effects/" + effectName + ".wav"));
                Clip song = AudioSystem.getClip ();
                song.open (ais);
                
                effect = new Audio (effectName, song);
                
                effects.add(effect);
            }
            
            reader = new Scanner (new File("src/Resource/Text/Animations.txt"));
           
            for (int i = 0; reader.hasNext(); i++) {
                
                String visualName = reader.nextLine();
                im = new ImageIcon (getClass().getResource("Resource/Visual/Gif/" + visualName + ".gif")).getImage();
                visual = new Visual (visualName, im);

                gifs.add(visual);
            }
            
            reader = new Scanner (new File("src/Resource/Text/Play Logos.txt"));
           
            for (int i = 0; reader.hasNext(); i++) {
                
                String visualName = reader.nextLine();
                io = new ImageIcon (getClass().getResource("Resource/Visual/Png/" + visualName + ".png"));
                visual = new Visual (visualName, io);

                logos.add(visual);
            }           
        }
        
        catch (LineUnavailableException | IOException | UnsupportedOperationException e) {
            
            
        }
        
        currentLevel = 0;
        currentImage = gifs.get(0).getImage();
        lg = new LevelGenerator(audios, visuals);
        levels = lg.getLevels();
    }
    
    public Level getNextLevel () {
        
        return levels[++currentLevel];
    }
    
    public int getTotalLevelNumber () {
        
        return levels.length;
    }
    
    public void setCurrentEffect (Audio effect) {
        
        currentEffect = effect;
    }
    
    public void setCurrentImage (Image i) {
        
        currentImage = i;
    }
    
    public void setCurrentIcon (ImageIcon io) {
         
        currentIcon = io;
    }
    
    public Audio getCurrentEffect () {
        
        return currentEffect;
    }
    
    public Image getCurrentImage() {
        
        return currentImage;
    }
    
    public ImageIcon getCurrentIcon() {
        
        return currentIcon;
    }
    
    public Level getCurrentLevel() {
        
        return levels[currentLevel];
    }
    
    public int getIndexOfCurrentLevel() {
        
        return currentLevel;
    }
    
    public ArrayList<Audio> getSoundEffects() {
        
        return effects;
    }
    
    public ArrayList<Visual> getPlayLogos() {
        
        return logos;
    }
    
    /*public ArrayList<Player> setHighScoreTable (Player p) {
        
        highScoreTable.add(p);
        
        if (highScoreTable.size() == 11) {
            
            Collections.sort(highScoreTable);
            highScoreTable.remove(11);
        }
        
        else {
            
            Collections.sort(highScoreTable);
        }
        
        return highScoreTable;
    }*/
}
