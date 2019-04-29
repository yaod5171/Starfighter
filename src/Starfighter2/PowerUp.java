package Starfighter2;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.Random;

/**
 *
 * @author yaod5171
 */
public class PowerUp extends MovingThing {
    //instance variables
    private Image image;
    //static variables
    private static boolean hasPowerUp = false;
    private static String PowerUp = "";
    private final static String[] POWERUPS = {"SHIELD"};
    
    public PowerUp(int x, int y) {
        super(x, y, 50, 50, 3);
        try {
            URL url = getClass().getResource("images/pu.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
            System.out.println("invisible powerups");
        }
    }
    //instance methods
    @Override
    public void draw(Graphics window) {
        
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    //static methods
    public static boolean has() {
        return hasPowerUp;
    }
    public static String powerup() {
        return PowerUp;
    }
    
    public static void getPU() {
        hasPowerUp = true;
        PowerUp = POWERUPS[(int) (Math.random() * POWERUPS.length)];
        
    }
    public static void losePU() {
        hasPowerUp = false;
        PowerUp = "";
    }
}
