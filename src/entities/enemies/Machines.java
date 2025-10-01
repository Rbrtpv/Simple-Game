package entities.enemies;

import entities.dmg.Projectile;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import user.Player;

public class Machines extends Enemy {

    private List<Projectile> projectiles;

    public Machines() {
        super(300, 30, 100, 100, 2, 2000, 2000,
                90, 450, 400.0f, 0.5f);
        this.projectiles = new ArrayList<>();
    }

    @Override
    public void update() {
        chase();

        projectiles.removeIf(p -> {
            p.update();
            return !p.isActive(); // remove no active projectiles
        });
    }

    @Override
    public void draw(Graphics g) {
        drawEnemy(g);
        healthBar(g);
    }

    @Override
    public void drawEnemy(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);
        g.drawString("Machine.", x, y - 10);
        // Draw projectiles
        for (Projectile p : projectiles) {
            p.draw(g);
        }
    }

    @Override
    public void attack(Player target) {
        if (target != null && target.getCurrentCharacter() != null) {
            int targetX = target.getCurrentCharacter().getX() + target.getCurrentCharacter().getW() / 2;
            int targetY = target.getCurrentCharacter().getY() + target.getCurrentCharacter().getH() / 2;
            projectiles.add(new Projectile(x + w / 2, y + h / 2, targetX, targetY, 8, attackDmg));
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
