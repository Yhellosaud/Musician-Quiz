/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toxic Sushi
 */

import java.awt.Image;
import javax.swing.ImageIcon;

public class Visual {
    /*
    * Attributes
    * Visual Name as String
    * i as Image
    * io as ImageIcon
    * 
    */
    private final String visualName;
    private Image i;
    private ImageIcon io;
    /*
    * Constructor
    * @Param: String visualName, Image i
    * Initializes the attributes
    */
    public Visual (String visualName, Image i) {
        
        this.visualName = visualName;
        this.i = i;
    }
    /*
    * Constructor
    * @Param: String visualName, ImageIcon io
    * Initializes the attributes
    */
    public Visual (String visualName, ImageIcon io) {
        
        this.visualName = visualName;
        this.io = io;
    }
    /*
     * @Param:Default
     * @Return: Image
     * get Image
     */
    public Image getImage() {
        
        return i;
    }
    /*
    * @Param:Default
    * @Return: ImageIcon 
    * get Image Icon
    */
    public ImageIcon getImageIcon() {
        
        return io;
    }
    
    public String getVisualName() {
        
        return visualName;
    }
    
}
