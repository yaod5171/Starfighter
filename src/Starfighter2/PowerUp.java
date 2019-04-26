package Starfighter2;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.net.URL;

/**
 *
 * @author yaod5171
 */
public class PowerUp extends MovingThing {
    
    private Image image;
    
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

    @Override
    public void draw(Graphics window) {
        
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

}
