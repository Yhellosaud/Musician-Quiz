/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmet
 */

import java.awt.Dimension;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.Box;

public class GamePanel extends JPanel {
    
    private Game gm;
    private GameProcessor gp;
    private Scanner reader;
    private Image gif1, gif2;
    private boolean entered;
    private JPanel menuPanel, gamePanel, actionPanel, EMPTY;
    private URL u;
    private Image walking, highscore, credits, options, howtoplay;
    private Image gif, temp1, temp2, status;
    private Dimension screenSize;
    private int count, menuSelection;
    private int songSelected = -1;
    private AudioInputStream ais;
    private ImageIcon level1, letsstartNormal, letsstartOutlined, three, two, one, go, space, lock, lockOutlined, nextLevel, answerBlank, blank;
    private ImageIcon quitNormal, quitOutlined, gameNameNormal, gameNameOutlined, playNormal, playOutlined, emptySpace, highscoreNormal, highscoreOutlined;
    private ImageIcon creditsNormal, creditsOutlined, howtoplayNormal, howtoplayOutlined, backNormal, backOutlined, optionsNormal, optionsOutlined;
    private JLabel levelOne, start, locke, duration, artistName, highScore, levelNumber, blanky;
    private JLabel gameLabel, quitLabel, playLabel, creditsLabel, howtoplayLabel, optionsLabel, emptyLabel, backLabel, highscoreLabel;
    private ButtonListener bl;
    private TimeListener tl;
    private KeyboardListener kl;
    private Timer slideEffect1, slideEffect2;
    private String songName = "";
    private int slide1, slide2, changeWay = 0;
    private Clip clip1, clip2, enteredSound;
    private boolean gate1, gate2, gate3 = false;
    private boolean gate4 = true;
    private final int waitBound = 250;
    private boolean ready = false;
    private boolean mainMenu = true;
    private boolean pressed = false;
    private int time = -1;
    private int seconds;
    private boolean timeStarted = false; 
    private boolean clickedToStart = false;
    private char keyTyped = '-';
    private int isChanged = 0;
    private Visual visual;
    private ArrayList<Visual> gifs;
    private int next = 0;
    private boolean started = false;
    private boolean response1, response2;
    private int formerLevel = 0;
    private int levelNum = 1;
    
    public GamePanel () throws UnsupportedAudioFileException, IOException {
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
        setPreferredSize (screenSize);
        //setLayout(null);
        setLayout (new BoxLayout (this, BoxLayout.X_AXIS));
        setBackground (Color.BLACK);

        gamePanel = new JPanel ();
        menuPanel = new JPanel ();
        actionPanel = new JPanel ();
        EMPTY = new JPanel ();
     
       	menuPanel.setOpaque (false);
       	menuPanel.setLayout (new BoxLayout (menuPanel, BoxLayout.Y_AXIS));
       	menuPanel.setSize (new Dimension (800, (int)screenSize.getHeight()));
       	menuPanel.setLocation ((int)((screenSize.getWidth()/2) - (menuPanel.getWidth()/2)), 0);
       	
       	gamePanel.setOpaque (false);
       	gamePanel.setLayout (new BoxLayout (gamePanel, BoxLayout.Y_AXIS));
       	gamePanel.setSize (new Dimension (800, (int)screenSize.getHeight()));
       	gamePanel.setLocation ((int)((screenSize.getWidth()/2) - (gamePanel.getWidth()/2)), 0);
       	gamePanel.setVisible (false);
       	
       	actionPanel.setOpaque (false);
       	actionPanel.setLayout (new BoxLayout (actionPanel, BoxLayout.Y_AXIS));
       	actionPanel.setLocation ((int)((screenSize.getWidth()/2) - (gamePanel.getWidth()/2)), 0);
       	actionPanel.setVisible (false);
       	
       	EMPTY.setOpaque (false);
       	EMPTY.setLayout (new BoxLayout (EMPTY, BoxLayout.Y_AXIS));
       	EMPTY.setLocation ((int)((screenSize.getWidth()/2) - (gamePanel.getWidth()/2)), 0);
        
        try {

            answerBlank = new ImageIcon (getClass().getResource("Resource/Visual/Png/answerBlank.png"));
            level1 = new ImageIcon (getClass().getResource("Resource/Visual/Png/level1.png"));
            letsstartNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/letsstartNORMAL.png"));
            letsstartOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/letsstartOUTLINED.png"));
            playNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/playNORMAL.png"));
            playOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/playOUTLINED.png"));
            quitNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/quitNORMAL.png"));
            quitOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/quitOUTLINED.png"));
            gameNameNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/musicquizNORMAL.png"));
            gameNameOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/musicquizOUTLINED.png"));
            creditsNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/creditsNORMAL.png"));
            creditsOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/creditsOUTLINED.png"));
            howtoplayNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/howtoplayNORMAL.png"));
            howtoplayOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/howtoplayOUTLINED.png"));
            backNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/backNORMAL.png"));
            backOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/backOUTLINED.png"));
            optionsNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/optionsNORMAL.png"));
            optionsOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/optionsOUTLINED.png"));
            highscoreNormal = new ImageIcon (getClass().getResource("Resource/Visual/Png/highscoreNORMAL.png"));
            highscoreOutlined = new ImageIcon (getClass().getResource("Resource/Visual/Png/highscoreOUTLINED.png"));
            emptySpace = new ImageIcon (getClass().getResource("Resource/Visual/Png/empty.png"));
            credits = new ImageIcon (getClass().getResource("Resource/Visual/Png/creditsINFO.png")).getImage();
            options = new ImageIcon (getClass().getResource("Resource/Visual/Png/optionsINFO.png")).getImage();
            highscore = new ImageIcon (getClass().getResource("Resource/Visual/Png/highscoreINFO.png")).getImage();
            howtoplay = new ImageIcon (getClass().getResource("Resource/Visual/Png/howtoplayINFO.png")).getImage();
            lock = new ImageIcon (getClass().getResource("Resource/Visual/Png/Blank.png"));
            nextLevel = new ImageIcon (getClass().getResource("Resource/Visual/Png/nextlevel.png"));
            
            walking = new ImageIcon (getClass().getResource("Resource/Visual/Jpeg/walking.jpg")).getImage();

            ais = AudioSystem.getAudioInputStream (getClass().getResource ("Resource/Audio/Sound Effects/buttonsoundeffect2.wav"));
            clip1 = AudioSystem.getClip ();
            clip1.open (ais);

            ais = AudioSystem.getAudioInputStream (getClass().getResource ("Resource/Audio/Sound Effects/gamemusic.wav"));
            clip2 = AudioSystem.getClip ();
            clip2.open (ais);

            ais = AudioSystem.getAudioInputStream (getClass().getResource ("Resource/Audio/Sound Effects/entered.wav"));
            enteredSound = AudioSystem.getClip ();
            enteredSound.open (ais);
        }

        catch (IOException | NullPointerException | LineUnavailableException i) {
            
        }

        start = new JLabel (letsstartNormal);
        locke = new JLabel (lock);
        gameLabel = new JLabel (gameNameNormal);
        quitLabel = new JLabel (quitNormal);
        playLabel = new JLabel (playNormal);
        creditsLabel = new JLabel (creditsNormal);
        howtoplayLabel = new JLabel (howtoplayNormal);
        optionsLabel = new JLabel (optionsNormal);
        highscoreLabel = new JLabel (highscoreNormal);
        backLabel = new JLabel (emptySpace);
        emptyLabel = new JLabel (emptySpace);
        duration = new JLabel ("" + seconds);
        highScore = new JLabel ("");
        artistName = new JLabel (" ");
        levelNumber = new JLabel (" ");
        
        duration.setFont(new Font(duration.getFont().getName(), duration.getFont().getStyle(), 100));
        highScore.setFont(new Font(duration.getFont().getName(), duration.getFont().getStyle(), 100));
        artistName.setFont(new Font(artistName.getFont().getName(), artistName.getFont().getStyle(), 75));
        levelNumber.setFont(new Font(artistName.getFont().getName(), artistName.getFont().getStyle(), 100));

        actionPanel.setAlignmentX (Component.LEFT_ALIGNMENT);
        gamePanel.setAlignmentX (Component.CENTER_ALIGNMENT);
        menuPanel.setAlignmentX (Component.CENTER_ALIGNMENT);
        //EMPTY.setAlignmentX (Component.RIGHT_ALIGNMENT);
        levelNumber.setAlignmentX (Component.CENTER_ALIGNMENT);
        start.setAlignmentX (Component.CENTER_ALIGNMENT);
        locke.setAlignmentX (Component.LEFT_ALIGNMENT);
        playLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        gameLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        quitLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        creditsLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        howtoplayLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        optionsLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        highscoreLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        backLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
        emptyLabel.setAlignmentX (Component.CENTER_ALIGNMENT);

        //EMPTY.add (Box.createRigidArea (new Dimension (500, 0)));

        actionPanel.add (artistName);
        actionPanel.add (duration);
        actionPanel.add (locke);
        //actionPanel.add (Box.createRigidArea (new Dimension (400, 0)));
        gamePanel.add (levelNumber);
        gamePanel.add (Box.createRigidArea (new Dimension (0, 350)));
        gamePanel.add (start);
        gamePanel.add (Box.createRigidArea (new Dimension (0, 400)));
        menuPanel.add (Box.createRigidArea (new Dimension (1925, 40)));
        menuPanel.add (playLabel);
        menuPanel.add (optionsLabel);
        menuPanel.add (highscoreLabel);
        menuPanel.add (emptyLabel);
        menuPanel.add (Box.createRigidArea (new Dimension (0, 200)));
        menuPanel.add (gameLabel);
        menuPanel.add (Box.createRigidArea (new Dimension (0, 310)));
        menuPanel.add (backLabel);
        menuPanel.add (howtoplayLabel);
        menuPanel.add (creditsLabel);
        menuPanel.add (quitLabel);

        backLabel.setVisible (false);					

        kl = new KeyboardListener();
        bl = new ButtonListener ();
        tl = new TimeListener ();
		
        addKeyListener (kl);
	addMouseMotionListener (bl);
	gameLabel.addMouseListener (bl);
	quitLabel.addMouseListener (bl);
	playLabel.addMouseListener (bl);
	creditsLabel.addMouseListener (bl);
	howtoplayLabel.addMouseListener (bl);
	optionsLabel.addMouseListener (bl);
	highscoreLabel.addMouseListener (bl);
	backLabel.addMouseListener (bl);
	start.addMouseListener (bl);
	//locke.addMouseListener (bl);
	setFocusable (true);

	slideEffect1 = new Timer (20, tl);
	slideEffect1.start();
		
        //clip2.loop(Clip.LOOP_CONTINUOUSLY);
 
        add (actionPanel);
        add (menuPanel);
        add (gamePanel);
        add (EMPTY);
        
        gm = new Game ();
        gp = new GameProcessor (gm);
    }

    @Override
    public void paintComponent (Graphics g) {
        
        super.paintComponent (g);
        
        if (menuSelection != 1) {


            if (menuSelection == 2) {
                
                temp2 = options;
            }
            else if (menuSelection == 4) {
                
                temp2 = howtoplay;
            }
        
            else if (menuSelection == 5) {
            
                temp2 = credits;
            }
            
            else {
                
                temp2 = gm.getCurrentImage();
            }
            
            g.drawImage (temp2, 500, 250, (int)screenSize.getWidth()-1000, (int)screenSize.getHeight()-500, this);
        }
        

        
        if (started == true) {
            
            g.drawImage (gm.getCurrentImage(), 0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight(), this);
        }
    }

    private class ButtonListener extends MouseAdapter implements MouseListener, MouseMotionListener {

        public void mouseClicked (MouseEvent event) {
			
            gm.getSoundEffects().get(1).getSound().stop();
            gm.getSoundEffects().get(1).getSound().setMicrosecondPosition(0);
            gm.getSoundEffects().get(1).getSound().start();

            mainMenu = false;

            if (event.getSource() == playLabel) {
                
                gamePanel.setVisible (true);
                actionPanel.setVisible (true);
                menuPanel.setVisible (false);
                gm.setCurrentImage(null);
                gameLabel.setVisible (false);
                menuSelection = 1;
            }

            else if (event.getSource() == optionsLabel) {

                backLabel.setVisible (true);
                emptyLabel.setVisible (false);
                menuSelection = 2;
            }

            else if (event.getSource()  == highscoreLabel) {

                backLabel.setVisible (true);
                emptyLabel.setVisible (false);
                menuSelection = 3;
            }

            else if (event.getSource() == howtoplayLabel) {

                backLabel.setVisible (true);
                emptyLabel.setVisible (false);
                menuSelection = 4;

            }

            else if (event.getSource() == creditsLabel) {

                backLabel.setVisible (true);
                emptyLabel.setVisible (false);
                menuSelection = 5;
            }

            else if (event.getSource() == quitLabel) {
                
                System.exit(0);
            }

            else if (event.getSource()  == backLabel) {

                mainMenu = true;
                gameLabel.setVisible (true);
                backLabel.setVisible (false);
                emptyLabel.setVisible (true);
                menuSelection = 0;
            }

            else if (event.getSource() == gameLabel && menuSelection == 0) {

                mainMenu = true;
            }

            else if (event.getSource() == start) {
                
                ready = true;
                entered = false;
                time = 1;
                next = 0;
                started = false;
                gp.iterateLevel();
    
                //start.setIcon(three);
            }

            else if (event.getSource() == locke) {

                //entered = true;
                //enteredSound.start();
            }

            if (event.getSource() != backLabel) {

                gate1 = true;
            }
            
        }
            
        public void mouseEntered (java.awt.event.MouseEvent event) {

            if (event.getSource() == gameLabel && mainMenu == true) {
                gameLabel.setIcon (gameNameOutlined);
            }

            else if (event.getSource() == quitLabel) {
                quitLabel.setIcon (quitOutlined);
            }

            else if (event.getSource() == playLabel) {
                playLabel.setIcon (playOutlined);	
            }

            else if (event.getSource() == howtoplayLabel) {
                howtoplayLabel.setIcon (howtoplayOutlined);
            }

            else if (event.getSource() == creditsLabel) {
                creditsLabel.setIcon (creditsOutlined);
            }

            else if (event.getSource() == backLabel) {
                backLabel.setIcon (backOutlined);
            }

            else if (event.getSource() == optionsLabel) {
                optionsLabel.setIcon (optionsOutlined);
            }

            else if (event.getSource() == highscoreLabel) {
                highscoreLabel.setIcon (highscoreOutlined);
            }

            else if (event.getSource() == locke) {
                locke.setIcon (lockOutlined);
            }
	}
		
        public void mouseExited (java.awt.event.MouseEvent evt) {

            gameLabel.setIcon (gameNameNormal);
            quitLabel.setIcon (quitNormal);
            playLabel.setIcon (playNormal);
            howtoplayLabel.setIcon (howtoplayNormal);
            creditsLabel.setIcon (creditsNormal);
            optionsLabel.setIcon (optionsNormal);
            highscoreLabel.setIcon (highscoreNormal);
            backLabel.setIcon (backNormal);
            locke.setIcon (lock);
        }
    }

    private class KeyboardListener implements KeyListener {

            @Override
            public void keyTyped (KeyEvent event) {
                
                if (entered == false) {
                    
                    keyTyped = event.getKeyChar();
                }
            }

            public void keyPressed (KeyEvent event) {
                
                if (keyTyped != '-' && event.getKeyCode() == KeyEvent.VK_ENTER) {
                    
                    time = 0;
                    entered = true;
                }
            }

            public void keyReleased (KeyEvent event) {
                
                if (entered == false) {
                    
                    keyTyped = '-';
                }
            }
    }

    private class TimeListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
                        
            if (menuSelection != 1) {
                
                if (gate1 == true && slide1 > -50) {
                    
                    gameLabel.setLocation (gameLabel.getX(), gameLabel.getY()+(slide1--));	
                    playLabel.setLocation (playLabel.getX(), playLabel.getY()+(slide1--));
                    optionsLabel.setLocation (optionsLabel.getX(), optionsLabel.getY()+(slide1--));
                    highscoreLabel.setLocation (highscoreLabel.getX(), highscoreLabel.getY()+(slide1--));
                    howtoplayLabel.setLocation (howtoplayLabel.getX(), howtoplayLabel.getY()+(slide2++));
                    creditsLabel.setLocation (creditsLabel.getX(), creditsLabel.getY()+(slide2++));
                    quitLabel.setLocation (quitLabel.getX(), quitLabel.getY()+(slide2++));
                }

                else if (slide1 < -50) {

                    gate1 = false;
                    slide1 = 0;
                    slide2 = 0;
                }
            }

            if (ready == true) {

                if (time%40 == 0) {

                    if (next == 4 || next == 5) {

                        gm.setCurrentIcon(gm.getPlayLogos().get(7).getImageIcon());
                        start.setIcon(gm.getCurrentIcon());
                        seconds = gm.getCurrentLevel().getTime();
                        started = true;
                        ready = false; 
                        levelNumber.setText("Level" + levelNum);
                        artistName.setText(gm.getCurrentLevel().getArtistName());
                    }
                    
                    start.setIcon(gm.getPlayLogos().get(next++).getImageIcon());
                } 
            }
            
            if (started == true) {
                
                start.setIcon(gm.getCurrentIcon());
                gp.processTheAction(keyTyped, entered);
                response2 = false;

                if (entered == true) {
                    
                    if (time == 0) {
                        
                        gm.getSoundEffects().get(0).getSound().stop();
                        gm.getSoundEffects().get(0).getSound().setMicrosecondPosition(0);
                        gm.getSoundEffects().get(0).getSound().start();
                    }
                                  
                    else if (time == 50) {
                           
                        gp.setResponses (true, false);
                    }
                    
                    
                    else if (time == 100) {
                        
                        gp.setResponses (false, true);
                    }
                    
                    else if (time == 120) {
                       
                        keyTyped = '-';
                        entered = false;
                    }                    
                }
                
                else {
                    
                    if (time%50 == 0 && gp.isAnswerCorrect() == false && seconds != 0) {
                        seconds--;
                        duration.setText(" " + seconds);  
                    }
                    
                    else if (gp.isAnswerCorrect() == true) {
                    
                        started = false;
                        levelNum++;
                    }
                    
                    formerLevel = gm.getIndexOfCurrentLevel();
                    
                    gp.setResponses (false, false);
                    gm.getSoundEffects().get(2).stopSound();
                    gm.getSoundEffects().get(3).stopSound();
                }    
            }
            
            time++;
        }	
    }                          
}
