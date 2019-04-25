package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingThing implements Moveable {

    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int speed;

    public MovingThing() {
        this(10, 10, 10, 10, 2);
    }

    public MovingThing(int x, int y) {
        this(x, y, 10, 10, 2);
    }

    public MovingThing(int x, int y, int s) {
        this(x, y, 10, 10, s);
    }

    public MovingThing(int x, int y, int w, int h) {
        this(x, y, w, h, 2);
    }

    public MovingThing(int x, int y, int w, int h, int s) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        speed = s;
    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getSpeed() {
        return speed;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean collide(MovingThing obj) {
        return (getY() + getHeight() >= obj.getY()
                && getY() <= obj.getY() + obj.getHeight()
                && getX() + getWidth() > obj.getX()
                && getX() <= obj.getX() + obj.getWidth());
    }

    public abstract void move(String direction);

    public abstract void draw(Graphics window);

    public String toString() {
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }
}
