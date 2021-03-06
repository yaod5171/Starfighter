package Starfighter2;

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
import java.util.List;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

    private Ship ship;
    /* uncomment once you are ready for this part
     */
    private AlienHorde horde;
    private Bullets shots;
    private List<PowerUp> powerups;

    private int level;
    private boolean gameOver;

    private boolean[] keys;
    private BufferedImage back;
    private boolean createdBullet;

    private int shotCount;

    private final int LEVELTIMER = 500;
    private int levelTimer;
    
    private boolean showGetPU;
    private int showGetPUTimer;
    private final int PUTIMER = 500;
    private String newPU;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];
        gameOver = false;

        level = 1;

        //instantiate other instance variables
        //Ship, Alien
        ship = new Ship(375, 500, 50, 50, 2);
        startHorde();
        shots = new Bullets();
        powerups = new ArrayList();

        createdBullet = false;
        shotCount = 0;

        levelTimer = LEVELTIMER;
        
        showGetPU = false;
        showGetPUTimer = PUTIMER;

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }
    
    public void startHorde() {
        horde = new AlienHorde(50);
        for (int i = 0; i < 50; i++) {
            horde.add(new Alien(50 * (i % 10), 50 * (i / 10), 30, 30, 1 + (double) (level * 0.2), level * 10));
        }
    }

    public void newHorde() {
        horde = new AlienHorde(50);
        for (int i = 0; i < 50; i++) {
            horde.add(new Alien(50 * (i % 10), 50 * (i / 10) - 200, 30, 30, 1 + (double) (level * 0.2), level * 10));
        }
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
        
        graphToBack.setColor(Color.RED);
        graphToBack.drawLine(0, 500, 800, 500);
        /*
         graphToBack.setColor(Color.BLUE);
         graphToBack.drawString("StarFighter ", 25, 50);
         */

        for (int i = 0; i < horde.getList().size(); i++) {
            Alien a = horde.getList().get(i);
            //check if ship has hit an alien
            if (ship.collide(a)) {
                //does the shield save its life?
                if (PowerUp.hasPowerUp("SHIELD")) {
                    horde.remove(a);
                    PowerUp.losePU("SHIELD");
                } else {
                    gameOver = true;
                }
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
            if (PowerUp.hasPowerUp("MULTISHOT")) {
                shots.add(new Ammo(ship.getX() + ship.getWidth() / 2, ship.getY(), 5, 100));
                shots.add(new Ammo(ship.getX() + ship.getWidth() / 2, ship.getY(), 5, 80));
                PowerUp.useMultishot();
            }
            
            shotCount++; //note: 1 multi-shot counts as 1 shot creating 3 ammo
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

        //get new powerups
        powerups.addAll(horde.getPowerups());

        for (int i = 0; i < powerups.size(); i++) {
            PowerUp pu = powerups.get(i);
            //draw all powerups
            pu.move("DOWN");
            pu.draw(graphToBack);

            //powerup collision detection
            if (ship.collide(pu)) {
                newPU = PowerUp.getPU();
                pu.setY(getHeight() + 100);
                showGetPU = true;
            }

            //clean up
            if (pu.getY() + 50 > getHeight()) {
                powerups.remove(i--);
            }

        }

        //are all aliens defeated?
        if (horde.getList().size() == 0) {
            levelTimer--;
            if (levelTimer == 0) {
                level++;
                newHorde();
                levelTimer = LEVELTIMER;
            }
        }

        //draw counts
        graphToBack.setColor(Color.YELLOW);
        graphToBack.drawString("Aliens destroyed: " + ((level) * 50 - horde.aliensLeft()), 550, 520);
        graphToBack.drawString("Shots fired: " + shotCount, 550, 540);
        //draw powerups
        graphToBack.drawString("Powerups:", 50, 520);
        graphToBack.drawString("Multishot: " + PowerUp.getMultishot(), 50, 540);
        graphToBack.drawString("Piercing Shot: " + PowerUp.getPiercing(), 50, 560);
        //draw level
        graphToBack.drawString("Level " + level, 50, 70);
        //draw label
        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("STARFIGHTER 2", 50, 50);
        //process and draw new powerup
        if (showGetPU) {
            graphToBack.drawString("New Powerup: " + newPU, 250, 540);
            showGetPUTimer--;
            if (showGetPUTimer == 0) {
                showGetPU = false;
                showGetPUTimer = PUTIMER;
            }
        }
        
        
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
