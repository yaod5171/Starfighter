package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {


    public Ammo() {
        this(0, 0, 0);
    }

    public Ammo(int x, int y) {
        this(x, y, 0);
    }

    public Ammo(int x, int y, int s) {
        this(x, y, 10, 10, s);
    }

    public Ammo(int x, int y, int w, int h, int s) {
        super(x, y, w, h, s);
    }

    public void draw(Graphics window) {
        window.setColor(Color.YELLOW);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
