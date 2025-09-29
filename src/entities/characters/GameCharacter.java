package entities.characters;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameCharacter {

    private int x, y;
    private int w, h;
    private int speed;
    private Color color;
    private int health;
    private int attack;
    private int defense;
    private String attacktype;

    public GameCharacter(int x, int y, int w, int h, int speed, Color color, int health, int attack, int defense,
            String attackType) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.color = color;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.attacktype = attackType;
    }

    public void attack(int x, int y) {
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
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

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
