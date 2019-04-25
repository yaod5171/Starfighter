package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import Starfighter2.MovingThing;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {

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
        super(x, y, w, h, s);
        this.movement = "LEFT";
        try {
            URL url = getClass().getResource("images/alien.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("alright who deleted the aliens");
        }
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
                setX(getX() - getSpeed());
                break;
            case "RIGHT":
                setX(getX() + getSpeed());
                break;
            case "UP":
                setY(getY() - getSpeed());
                break;
            case "DOWN":
                setY(getY() + getSpeed());
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
