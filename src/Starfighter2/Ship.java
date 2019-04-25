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

    private int speed;
    private Image image;

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
        super(x, y, w, h);
        speed = s;
        try {
            URL url = getClass().getResource("images/ship.jpg");
            System.out.println(url);
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
            System.out.println("This is an error");
        }
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(String dir) {
        switch (dir) {
            case "LEFT":
                setX(getX() - speed);
                break;
            case "RIGHT":
                setX(getX() + speed);
                break;
            case "UP":
                setY(getY() - speed);
                break;
            case "DOWN":
                setY(getY() + speed);
                break;
        }
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
