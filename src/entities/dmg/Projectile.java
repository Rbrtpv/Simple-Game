package entities.dmg;

import game.Window;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile {

    private int x, y;
    private int w, h;
    private int speed;
    private double dx, dy;
    private Color color;
    private int damage;
    private boolean active;
    private Window window;

    public Projectile(int x, int y, int targetX, int targetY, int speed, int damage) {
        this.x = x;
        this.y = y;
        this.w = 10;
        this.h = 10;
        this.speed = speed;
        this.damage = damage;
        this.active = true;

        double angle = Math.atan2(targetY - y, targetX - x);
        this.dx = speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);

        window = new Window();
    }

    public void update() {
        if (!active)
            return;
        x += dx;
        y += dy;
        if (x < -w || x > window.getWidth() || y < -h || y > window.getHeight()) {
            active = false;
        }
    }

    public void draw(Graphics g) {
        if (!active)
            return;
        g.setColor(Color.cyan);
        g.fillOval(x, y, w, h);
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
