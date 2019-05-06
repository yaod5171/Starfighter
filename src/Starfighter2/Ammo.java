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

    private int dir;
    
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
        this(x, y, w, h, s, 90);
    }
    
    public Ammo(int x, int y, int w, int h, int s, int d) {
        super(x, y, w, h, s);
        dir = d;
    }
    
    public int getDir() {
        return dir;
    }
    public void setDir(int d) {
        dir = d;
    }

    public void draw(Graphics window) {
        window.setColor(Color.YELLOW);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
