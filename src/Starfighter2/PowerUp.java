package Starfighter2;

import java.util.ArrayList;
import java.util.List;
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
    //general powerup variables
    private static List<String> PowerUps = new ArrayList();
    private final static String[] POWERUPS = {"SHIELD", "MULTISHOT", "PIERCING"}; //add multishot, piercing, freeze?
    //specific powerup variables
    private static int multishot = 0;
    private static int piercing = 0;
    private static int freeze;
    
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
    
    //general methods
    public static boolean hasPowerUp(String pu) {
        return PowerUps.contains(pu);
    }
    
    public static List<String> powerups() {
        return PowerUps;
    }
    
    public static String getPU() {
        String pu = POWERUPS[(int) (Math.random() * POWERUPS.length)];
        if (!hasPowerUp(pu)) {
            getPU(pu);
        }
        //other setup:
        switch(pu) {
            case "SHIELD":
                break;
            case "MULTISHOT":
                initMultishot();
                break;
            case "PIERCING":
                initPiercing();
                break;
                
        }
        
        return pu;
    }
    public static void getPU(String pu) {
        PowerUps.add(pu);
    }
    
    public static void losePU(String pu) {
        PowerUps.remove(pu);
    }
    
    //specific methods
    public static void initMultishot() {
        multishot = 10;
    }
    public static void useMultishot() {
        multishot--;
        if (multishot == 0) {
            losePU("MULTISHOT");
        }
    }
    public static void initPiercing() {
        piercing = 10;
    }
    public static void usePiercing() {
        piercing--;
        if (piercing == 0) {
            losePU("PIERCING");
        }
    }
}
