/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
*
* @author Toxic Sushi
*/
import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameExecutive {
	
    public static void main (String[] args) throws IOException, UnsupportedAudioFileException {
        // Creates the game panel
        GamePanel gp = new GamePanel ();
        //Make game panel get the focus whenever frame is activated.
        gp.requestFocusInWindow();

        SwingUtilities.invokeLater (() -> {
            
            JFrame gameFrame = new JFrame ();
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            gameFrame.setSize(screenSize);
            gameFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            gameFrame.getContentPane().add(gp);
            gameFrame.setVisible(true);
            gameFrame.removeNotify();
            gameFrame.setUndecorated(true);
            gameFrame.addNotify();
            gameFrame.setResizable(false);
        });	
    }
}
