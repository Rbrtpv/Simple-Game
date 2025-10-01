package entities.characters;

import entities.dmg.Projectile;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Pierce extends GameCharacter {

    private List<Projectile> projectiles;
    private int projectileDamage = 140;
    private int attackDmg;
    private int defense;

    public Pierce(int x, int y, int w, int h, int speed) {
        super(x, y, 50, 50, 7, 1000, 1000,
                300, 400);
        projectiles = new ArrayList<>();
    }

    @Override
    public void update() {
        for (Projectile projectile : projectiles) {
            projectile.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void attack(int targetX, int targetY) {
        projectiles.add(new Projectile(x + w / 2, y + h / 2, targetX, targetY, 10, projectileDamage));
    }

    @Override
    public void takeDamage(int dmg) {
        super.takeDamage(dmg);
        if (this.healthPoints <= 0) {
            this.healthPoints = 0;
        }
    }

    // Getters & Setters
    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
