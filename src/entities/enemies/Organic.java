package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import user.Player;

public class Organic extends Enemy {

    public Organic() {
        super(410, 30, 100, 100, 2, 1000,
                1000, 60, 400,
                200.0f, 0.3f);
    }

    @Override
    public void update() {
        chase();
    }

    public void draw(Graphics g) {
        drawEnemy(g);
        healthBar(g);
    }

    @Override
    public void drawEnemy(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, w, h);
        g.drawString("O ", x, y - 10);
    }

    @Override
    public void attack(Player target) {
        if (checkIfTargetInRange(target)) {
            target.getCurrentCharacter().takeDamage(attackDmg);
        }
    }
}
