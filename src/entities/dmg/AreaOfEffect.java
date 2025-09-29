package entities.dmg;

import java.awt.Color;
import java.awt.Graphics;

public class AreaOfEffect {

    private int x, y, w, h;
    private int duration;
    private Color color;

    public AreaOfEffect(int x, int y, int w, int h, int duration, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.duration = duration;
        this.color = color;
    }

    public void update() {
        if (duration > 0) {
            duration--;
        }
    }

    public void draw(Graphics g) {
        if (duration > 0) {
            g.setColor(color);
            g.drawOval(x - w / 2 + 20, y - h / 2 + 20, w, h);
        }
    }

    public boolean isFinished() {
        return duration <= 0;
    }
}
