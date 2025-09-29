package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class Machines {

    public Machines(int x, int y, int w, int h, int speed) {
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.drawOval(300, 500, 50, 50);
    }
}
