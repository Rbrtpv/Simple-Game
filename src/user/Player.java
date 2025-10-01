package user;

import controls.Keyboard;
import entities.characters.GameCharacter;
import entities.characters.Pierce;
import entities.characters.Slash;
import game.Window;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.List;

public class Player {

    private int x, y;
    private int w, h;
    private int speed;
    public GameCharacter currentCharacter;
    private Pierce pierce;
    private Slash slash;
    private Window window;
    private Canvas canvas;
    private Keyboard keyboard;

    public Player(GameCharacter character) {
        this.currentCharacter = character;
        if (character instanceof Pierce) {
            this.pierce = (Pierce) character;
            this.slash = new Slash(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
        } else if (character instanceof Slash) {
            this.slash = (Slash) character;
            this.pierce = new Pierce(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
        } else {
            System.err.println("Create default instances.");
            this.pierce = new Pierce(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
            this.slash = new Slash(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
            this.currentCharacter = this.pierce;
        }
        this.x = character.getX();
        this.y = character.getY();
        this.w = character.getW();
        this.h = character.getH();
        this.speed = character.getSpeed();
        this.keyboard = null;
        window = new Window();
    }

    public void update() {
        if (keyboard != null) {
            if (keyboard.isUp()) {
                currentCharacter.moveUp();
            }
            if (keyboard.isDown()) {
                currentCharacter.moveDown();
            }
            if (keyboard.isLeft()) {
                currentCharacter.moveLeft();
            }
            if (keyboard.isRight()) {
                currentCharacter.moveRight();
            }
        }
        this.x = currentCharacter.getX();
        this.y = currentCharacter.getY();
        this.w = currentCharacter.getW();
        this.h = currentCharacter.getH();
        this.speed = currentCharacter.getSpeed();
        checkBounds();
    }

    public void draw(Graphics g) {
        currentCharacter.draw(g);
    }

    public void checkBounds() {
        int charX = currentCharacter.getX();
        int charY = currentCharacter.getY();
        int charW = currentCharacter.getW();
        int charH = currentCharacter.getH();

        if (charX < 0)
            currentCharacter.setX(0);
        if (charY < 0)
            currentCharacter.setY(0);
        if (charX > window.getWidth() - charW)
            currentCharacter.setX(window.getWidth() - charW);
        if (charY > window.getHeight() - charH)
            currentCharacter.setY(window.getHeight() - charH);
    }

    public void swapCharacter() {
        // Ensure pierce and slash are initialized before attempting to swap
        if (this.pierce == null || this.slash == null) {
            return;
        }
        // Save the current position of the active character before swapping
        int currentX = currentCharacter.getX();
        int currentY = currentCharacter.getY();
        if (this.currentCharacter == this.pierce) {
            this.pierce.setX(currentX);
            this.pierce.setY(currentY);
            this.currentCharacter = this.slash;
            // Set Slash to the position where Pierce was
            this.slash.setX(currentX);
            this.slash.setY(currentY);
        } else if (this.currentCharacter == this.slash) {
            this.slash.setX(currentX);
            this.slash.setY(currentY);
            this.currentCharacter = this.pierce;
            // Set Pierce to the position where Slash was
            this.pierce.setX(currentX);
            this.pierce.setY(currentY);
        } else {
            this.currentCharacter = this.pierce;
            this.pierce.setX(currentX);
            this.pierce.setY(currentY);
        }
        // Update Player's own fields to reflect the new currentCharacter's properties
        this.x = currentCharacter.getX();
        this.y = currentCharacter.getY();
        this.w = currentCharacter.getW();
        this.h = currentCharacter.getH();
        this.speed = currentCharacter.getSpeed();
    }

    public void attack(int x, int y) {
        currentCharacter.attack(x, y);
    }

    // Getters & setters
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public List<entities.dmg.Projectile> getProjectiles() {
        if (currentCharacter instanceof Pierce) {
            return ((Pierce) currentCharacter).getProjectiles();
        }
        return new java.util.ArrayList<>();
    }

    public List<entities.dmg.AreaOfEffect> getAttacks() {
        if (currentCharacter instanceof Slash) {
            return ((Slash) currentCharacter).getAttacks();
        }
        return new java.util.ArrayList<>();
    }

    public GameCharacter getCurrentCharacter() {
        return currentCharacter;
    }
}
