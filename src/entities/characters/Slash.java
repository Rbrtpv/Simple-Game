package entities.characters;

import entities.dmg.AreaOfEffect;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Slash extends GameCharacter {

    private List<AreaOfEffect> attacks;

    public Slash(int x, int y, int w, int h, int speed) {
        super(x, y, w, h, 8, Color.WHITE, 100, 20, 10, "Slash");
        this.attacks = new ArrayList<>();
    }

    @Override
    public void update() {
        for (AreaOfEffect attack : attacks) {
            attack.update();
        }
        attacks.removeIf(AreaOfEffect::isFinished);
    }

    @Override
    public void attack(int x, int y) {
        attacks.add(new AreaOfEffect(getX(), getY(), 150, 150, 10, Color.RED));
    }

    public List<AreaOfEffect> getAttacks() {
        return attacks;
    }
}
