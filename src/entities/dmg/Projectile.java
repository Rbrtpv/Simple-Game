package entities.dmg;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile {

    private int x, y, w, h, speed;
    private double dx, dy;
    private Color color;

    public Projectile(int x, int y, int w, int h, int speed, int targetX, int targetY,
            Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.color = color;

        double angle = Math.atan2(targetY - y, targetX - x);
        this.dx = speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
