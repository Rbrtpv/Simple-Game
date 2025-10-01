package entities.characters;

import entities.dmg.AreaOfEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Slash extends GameCharacter {

    private List<AreaOfEffect> attacks;
    private int aoeDamage = 10;
    private long aoeDuration = 300;

    public Slash(int x, int y, int w, int h, int speed) {
        super(x, y, 60, 60, 5, 1000, 1000,
                300, 400);
        attacks = new ArrayList<>();
    }

    @Override
    public void update() {
        for (AreaOfEffect attack : attacks) {
            attack.update();
        }
        attacks.removeIf(AreaOfEffect::isFinished);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void attack(int x, int y) {
        attacks.add(new AreaOfEffect(x - w / 2, y - h / 2, w * 2, h * 2, aoeDamage, aoeDuration));
    }

    @Override
    public void takeDamage(int dmg) {
        super.takeDamage(dmg);
        if (this.healthPoints <= 0) {
            this.healthPoints = 0;
        }
    }

    // Getters and Setters
    public List<AreaOfEffect> getAttacks() {
        return attacks;
    }
}
