package entities.dmg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AreaOfEffect {

    private int x, y;
    private int w, h;
    private long duration;
    private long startTime;
    private boolean active;
    private int damage;
    private Color color;

    public AreaOfEffect(int x, int y, int w, int h, int damage, long duration) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.duration = duration;
        this.damage = damage;
        this.startTime = System.currentTimeMillis();
        this.active = true;
    }

    public void update() {
        if (!active)
            return;
        if (System.currentTimeMillis() - startTime > duration) {
            active = false; // Desactivate AOE
        }
    }

    public void draw(Graphics g) {
        if (!active)
            return;
        g.setColor(new Color(255, 0, 0, 100));
        g.fillRect(x, y, w, h);
    }

    // Getters and Setters
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isFinished() {
        return duration <= 0;
    }
}
