package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import Starfighter2.Ammo;
import Starfighter2.Ammo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets {

    private List<Ammo> ammo;

    public Bullets() {
        ammo = new ArrayList();
    }

    public void add(Ammo am) {
        ammo.add(am);
    }

    //post - draw each Ammo
    public void drawEmAll(Graphics window) {
        for (Ammo b : ammo) {
            b.draw(window);
        }
    }

    public void moveEmAll() {
        for (Ammo b : ammo) {
            b.setY(b.getY() - b.getSpeed());
        }
    }

    public void cleanEmUp() {
        for (int i = 0; i < ammo.size(); i++) {
            if (ammo.get(i).getY() < -100) {
                ammo.remove(i);
            }
        }
    }

    public List<Ammo> getList() {
        return ammo;
    }

    public String toString() {
        return "";
    }
}
