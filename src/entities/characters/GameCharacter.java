package entities.characters;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameCharacter {

    private int x, y, w, h, speed;
    private Color color;

    public GameCharacter(int x, int y, int w, int h, int speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.color = Color.BLUE;
    }

    public void attack() {
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public abstract void update();

    // Getters & Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
