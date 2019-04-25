package Starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
    
    private Ship ship;
    /* uncomment once you are ready for this part
     */
    private AlienHorde horde;
    private Bullets shots;
    
    private boolean gameOver;
    
    private boolean[] keys;
    private BufferedImage back;
    private boolean createdBullet;
    
    private int shotCount;
    
    public OuterSpace() {
        setBackground(Color.black);
        
        keys = new boolean[5];
        gameOver = false;

        //instantiate other instance variables
        //Ship, Alien
        ship = new Ship(375, 500, 50, 50, 2);
        horde = new AlienHorde(50);
        for (int i = 0; i < 50; i++) {
            horde.add(new Alien(50 * (i % 10), 50 * (i / 10), 1));
        }
        shots = new Bullets();
        
        createdBullet = false;
        shotCount = 0;
        
        this.addKeyListener(this);
        new Thread(this).start();
        
        setVisible(true);
    }
    
    public void update(Graphics window) {
        paint(window);
    }
    
    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);
        /*
         graphToBack.setColor(Color.BLUE);
         graphToBack.drawString("StarFighter ", 25, 50);
         */

        
        for (Alien a : horde.getList()) {
            //check if ship is dead
            if (ship.collide(a)) {
                gameOver = true;
            }
        //check if aliens have made it to the bottom
            if (a.getY() >= 500) {
                gameOver = true;
            }
        }
        
        
        if (gameOver) {
            keys = new boolean[]{false, false, false, false, false};
            graphToBack.setColor(Color.RED);
            graphToBack.drawString("GAME OVER", 500, 540);
        }
        
        if (keys[0] == true) {
            ship.move("LEFT");
        }
        if (keys[1] == true) {
            ship.move("RIGHT");
        }
        
        if (keys[2] == true) {
            ship.move("UP");
        }
        
        if (keys[3] == true) {
            ship.move("DOWN");
        }
        
        if (keys[4] && !createdBullet) {
            shots.add(new Ammo(ship.getX() + ship.getWidth() / 2, ship.getY(), 5));
            shotCount++;
            createdBullet = true;
        }

        //add code to move Ship, Alien, etc.
        ship.draw(graphToBack);
        
        horde.moveEmAll();
        horde.drawEmAll(graphToBack);
        
        shots.moveEmAll();
        shots.cleanEmUp();
        shots.drawEmAll(graphToBack);
        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
        horde.removeDeadOnes(shots.getList());

        //draw counts
        graphToBack.setColor(Color.YELLOW);
        graphToBack.drawString("Shots fired: " + shotCount, 500, 520);
        graphToBack.drawString("Aliens destroyed: " + horde.getKillCount(), 500, 500);
        
        twoDGraph.drawImage(back, null, 0, 0);
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
            createdBullet = false;
        }
        repaint();
    }
    
    public void keyTyped(KeyEvent e) {
        //no code needed here
    }
    
    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
