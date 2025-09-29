package entities.characters;

import entities.dmg.Projectile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Pierce extends GameCharacter {

    private List<Projectile> projectiles;

    public Pierce(int x, int y, int w, int h, int speed) {
        super(x, y, w, h, 7, Color.BLUE, 100, 20, 150, "Pierce");
        this.projectiles = new ArrayList<>();
    }

    @Override
    public void update() {
        for (Projectile projectile : projectiles) {
            projectile.update();
        }
    }

    @Override
    public void attack(int x, int y) {
        projectiles.add(new Projectile(getX() + (getW() / 2 - 5), getY() + getH() / 2 - 5, 10, 10,
                25, x, y, Color.YELLOW));
    }

    // Getters & Setters
    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
