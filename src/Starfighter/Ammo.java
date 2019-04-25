package Starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {

    private int speed;

    public Ammo() {
        this(0, 0, 0);
    }

    public Ammo(int x, int y) {
        this(x, y, 0);
    }

    public Ammo(int x, int y, int s) {
        super(x, y);
        speed = s;
    }
    
    public Ammo(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
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
        window.setColor(Color.YELLOW);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
