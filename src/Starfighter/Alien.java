package Starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {

    private int speed;
    private Image image;
    private String movement;

    public Alien() {
        this(0, 0, 30, 30, 0);
        this.movement = "LEFT";
    }

    public Alien(int x, int y) {
        this(x, y, 30, 30, 0);
        this.movement = "LEFT";
    }

    public Alien(int x, int y, int s) {
        this(x, y, 30, 30, s);
        this.movement = "LEFT";
    }

    public Alien(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        this.movement = "LEFT";
        speed = s;
        try {
            URL url = getClass().getResource("images/alien.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("alright who deleted the aliens");
        }
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setMovement(String move) {
        movement = move;
    }
    
    public String getMovement() {
        return movement;
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
        return "";
    }
}
