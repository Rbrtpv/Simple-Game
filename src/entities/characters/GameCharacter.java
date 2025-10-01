package entities.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameCharacter {

    protected int x, y;
    protected int w, h;
    protected int speed;
    protected Color color;
    protected int healthPoints;
    protected int maxHealthPoints;
    protected int attackDmg;
    protected int defense;

    public GameCharacter(int x, int y, int w, int h, int speed, int healthPoints, int maxHealthPoints, int attackDmg,
            int defense) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.attackDmg = attackDmg;
        this.defense = defense;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public void attack(int x, int y) {
    }

    public void takeDamage(int dmg) {
        setHealthPoints(this.healthPoints -= dmg);
        if (this.healthPoints <= 0) {
        }
    }

    // Movement
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

    // Getters & Setters
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
        if (this.healthPoints > this.maxHealthPoints) {
            this.healthPoints = this.maxHealthPoints;
        }
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
        if (this.healthPoints > this.maxHealthPoints) {
            this.healthPoints = this.maxHealthPoints;
        }
    }

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

    public int getHealthPoints() {
        return healthPoints;
    }

    public Rectangle getBounds() { // for collisions
        return new Rectangle(x, y, w, h);
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

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }
}
