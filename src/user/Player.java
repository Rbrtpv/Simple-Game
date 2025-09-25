package user;

import controls.Keyboard;
import entities.characters.GameCharacter;
import entities.characters.Pierce;
import entities.characters.Slash;
import java.awt.Canvas;
import java.awt.Graphics;

public class Player {

    private int x, y, w, h, speed;
    private GameCharacter currentCharacter;
    private Pierce pierce;
    private Slash slash;
    private Canvas canvas;
    private Keyboard keyboard;

    public Player(GameCharacter character) {
        this.currentCharacter = character;
        if (character instanceof Pierce) {
            this.pierce = (Pierce) character;
            // Create a new Slash character at the same initial position
            this.slash = new Slash(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
        } else if (character instanceof Slash) {
            this.slash = (Slash) character;
            // Create a new Pierce character at the same initial position
            this.pierce = new Pierce(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
        } else {
            // Fallback: if the initial character is neither Pierce nor Slash,
            // create default instances for both.
            System.err.println(
                    "Warning: Initial GameCharacter is neither Pierce nor Slash. Creating default Pierce and Slash characters.");
            this.pierce = new Pierce(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
            this.slash = new Slash(character.getX(), character.getY(), character.getW(), character.getH(),
                    character.getSpeed());
            this.currentCharacter = this.pierce; // Default to Pierce
        }
        this.x = character.getX();
        this.y = character.getY();
        this.w = character.getW();
        this.h = character.getH();
        this.speed = character.getSpeed();
        this.keyboard = null;
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
        // It's good practice to update the Player's own x,y,w,h,speed fields
        // to reflect the currentCharacter's state after movement.
        this.x = currentCharacter.getX();
        this.y = currentCharacter.getY();
        this.w = currentCharacter.getW();
        this.h = currentCharacter.getH();
        this.speed = currentCharacter.getSpeed();
        checkBounds(); // Call checkBounds after updating position
    }

    public void draw(Graphics g) {
        currentCharacter.draw(g);
    }

    public void checkBounds() {
        // Use the Player's own w and h for bounds checking,
        // or ensure currentCharacter's w and h are used consistently.
        // For now, assuming currentCharacter's dimensions are used.
        int charX = currentCharacter.getX();
        int charY = currentCharacter.getY();
        int charW = currentCharacter.getW();
        int charH = currentCharacter.getH();

        if (charX < 0)
            currentCharacter.setX(0);
        if (charY < 0)
            currentCharacter.setY(0);
        // Assuming 990 and 800 are the window dimensions
        if (charX > 990 - charW) // Corrected boundary check
            currentCharacter.setX(990 - charW); // Set to boundary, not 0
        if (charY > 800 - charH) // Corrected boundary check
            currentCharacter.setY(800 - charH); // Set to boundary, not 0
    }

    public void swapCharacter() {
        // Ensure pierce and slash are initialized before attempting to swap
        if (this.pierce == null || this.slash == null) {
            System.err.println("Error: Cannot swap character. 'pierce' or 'slash' are not initialized.");
            return;
        }

        // Save the current position of the active character before swapping
        int currentX = currentCharacter.getX();
        int currentY = currentCharacter.getY();

        if (this.currentCharacter == this.pierce) {
            // If current is Pierce, save Pierce's state and switch to Slash
            this.pierce.setX(currentX); // Update Pierce's last known position
            this.pierce.setY(currentY);
            this.currentCharacter = this.slash;
            // Set Slash to the position where Pierce was
            this.slash.setX(currentX);
            this.slash.setY(currentY);
        } else if (this.currentCharacter == this.slash) {
            // If current is Slash, save Slash's state and switch to Pierce
            this.slash.setX(currentX); // Update Slash's last known position
            this.slash.setY(currentY);
            this.currentCharacter = this.pierce;
            // Set Pierce to the position where Slash was
            this.pierce.setX(currentX);
            this.pierce.setY(currentY);
        } else {
            // This case should ideally not happen if constructor logic is sound,
            // but as a safeguard, if currentCharacter is neither, default to Pierce.
            System.out.println("Warning: currentCharacter was an unexpected type. Defaulting to Pierce.");
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

        System.out.println("Character swapped to: " + currentCharacter.getClass().getSimpleName() + " at (" + this.x
                + ", " + this.y + ")");
    }

    public void attack() {
        // Implement attack logic for currentCharacter
        // currentCharacter.attack(); // Assuming GameCharacter has an attack method
    }

    // Getters & setters
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
