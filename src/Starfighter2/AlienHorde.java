package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class AlienHorde {

    private List<Alien> aliens;

    private int killCount;
    
    private List<PowerUp> pu;

    public AlienHorde(int size) {
        aliens = new ArrayList();
        killCount = 0;
        pu = new ArrayList();
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawEmAll(Graphics window) {
        for (Alien a : aliens) {
            a.draw(window);
        }
    }

    public void moveEmAll() {
        for (Alien a : aliens) {
            a.move(a.getMovement());
            if (a.getX() < 0) {
                a.setMovement("RIGHT");
                a.setY(a.getY() + a.getDown());
            } else if (a.getX() + a.getWidth() > 800) {
                a.setMovement("LEFT");
                a.setY(a.getY() + a.getDown());
            }
        }
    }

    public void removeDeadOnes(List<Ammo> shots) {
        for (int i = 0; i < aliens.size(); i++) {
            for (Ammo b : shots) {
                if (b.collide(aliens.get(i))) {
                    if (Math.random() < 0.04) {
                        //spawn a powerup
                        pu.add(new PowerUp(b.getX(), b.getY()));
                    }
                    aliens.remove(i);
                    b.setY(-200);
                    killCount++;
                    
                    break;
                }
            }
        }
    }
    
    

    public int getKillCount() {
        return killCount;
    }

    public List<Alien> getList() {
        return aliens;
    }
    
    public List<PowerUp> getPowerups() {
        List<PowerUp> out = pu;
        pu = new ArrayList();
        return out;
    }
    public String toString() {
        return "";
    }
}
