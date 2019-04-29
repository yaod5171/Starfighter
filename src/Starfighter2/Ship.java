package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {

    private Image ship;
    private Image puShip;

    public Ship() {
        this(10, 10, 10, 10, 5);
    }

    public Ship(int x, int y) {
        this(x, y, 10, 10, 5);
    }

    public Ship(int x, int y, int s) {
        this(x, y, 10, 10, s);
    }

    public Ship(int x, int y, int w, int h, int s) {
        super(x, y, w, h, s);
        try {
            URL url = getClass().getResource("images/ship.jpg");
            ship = ImageIO.read(url);
            URL url2 = getClass().getResource("images/ship.jpgWithShield.jpg");
            puShip = ImageIO.read(url2);
        } catch (Exception e) {
            //feel free to do something here
            System.out.println("This is an error");
        }
    }


    public void draw(Graphics window) {
        if (PowerUp.powerup() == "SHIELD") {
            window.drawImage(puShip, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            window.drawImage(ship, getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
