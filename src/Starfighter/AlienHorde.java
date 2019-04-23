package Starfighter;

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

public class AlienHorde
{
	private List<Alien> aliens;

	public AlienHorde(int size)
	{
            aliens = new ArrayList();
	}

	public void add(Alien al)
	{
            aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
            for (Alien a: aliens) {
                a.draw(window);
            }
	}

	public void moveEmAll()
	{
            for (Alien a: aliens) {
                a.move(a.getMovement());
                if (a.getX() < 0) {
                    a.setMovement("RIGHT");
                    a.setY(a.getY()+4);
                } else if (a.getX() + a.getWidth() > 800) {
                    a.setMovement("LEFT");
                    a.setY(a.getY()+4);
                }
            }
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
            for (Alien a: aliens) {
                for (Ammo b: shots) {
                    if (b.collide(a)) {
                        aliens.remove(a);
                        b.setY(-200);
                        break;
                    }
                }
            }
	}

	public String toString()
	{
		return "";
	}
}
