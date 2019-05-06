package Starfighter2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingThing implements Moveable {

    private int xPos;
    private int yPos;
    private double dx;
    private double dy;
    private int width;
    private int height;
    private double speed;

    public MovingThing() {
        this(10, 10, 10, 10, 2);
    }

    public MovingThing(int x, int y) {
        this(x, y, 10, 10, 2);
    }

    public MovingThing(int x, int y, int s) {
        this(x, y, 10, 10, s);
    }
    
    public MovingThing(int x, int y, double s) {
        this(x, y, 10, 10, s);
    }

    public MovingThing(int x, int y, int w, int h) {
        this(x, y, w, h, 2);
    }

    public MovingThing(int x, int y, int w, int h, int s) {
        xPos = x;
        yPos = y;
        dx = x;
        dy = y;
        width = w;
        height = h;
        speed = s;
    }
    
    public MovingThing(int x, int y, int w, int h, double s) {
        xPos = x;
        yPos = y;
        dx = x;
        dy = y;
        width = w;
        height = h;
        speed = s;
    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void setX(double x) {
        dx = x;
        xPos = (int)dx;
    }
    
    public void setX(int x) {
        dx = x;
        xPos = x;
    }

    public void setY(double y) {
        dy = y;
        yPos = (int)dy;
    }
    
    public void setY(int y) {
        dy = y;
        yPos = y;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getX() {
        return xPos;
    }
    
    public double getExactX() {
        return dx;
    }

    public int getY() {
        return yPos;
    }
    
    public double getExactY() {
        return dy;
    }

    public int getSpeed() {
        return (int)speed;
    }
    
    public double getExactSpeed() {
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

    public void move(String direction) {
        switch (direction) {
            case "LEFT":
                setX(getExactX() - getExactSpeed());
                break;
            case "RIGHT":
                setX(getExactX() + getExactSpeed());
                break;
            case "UP":
                setY(getExactY() - getExactSpeed());
                break;
            case "DOWN":
                setY(getExactY() + getExactSpeed());
                break;
        }
    }
    
    public void move(int degrees) {
        double radians = Math.toRadians(degrees);
        double vx = getExactSpeed() * Math.cos(radians);
        double vy = getExactSpeed() * Math.sin(radians) * -1;
        
        setX(getExactX() + vx);
        setY(getExactY() + vy);
    }

    public abstract void draw(Graphics window);

    public String toString() {
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }
}
