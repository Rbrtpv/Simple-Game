package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import user.Player;

public class Keyboard implements KeyListener {

    public boolean up, down, left, right;
    private Player player;
    private boolean swapCharacterPressed;
    private boolean[] keys;
    private volatile boolean escapeJustPressed;

    public Keyboard(Player player) {
        this.player = player;
        keys = new boolean[256];
        this.escapeJustPressed = false;
    }

    public void update() {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

        if (keys[KeyEvent.VK_SPACE]) {
            if (!swapCharacterPressed) {
                player.swapCharacter();
                swapCharacterPressed = true;
            }
        } else {
            swapCharacterPressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() >= 0 && ke.getKeyCode() < keys.length) {
            keys[ke.getKeyCode()] = true;
            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                escapeJustPressed = true;
            }
        }
    }

    public boolean consumeEscapePress() {
        if (escapeJustPressed) {
            escapeJustPressed = false;
            return true;
        }
        return false;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() >= 0 && ke.getKeyCode() < keys.length) {
            keys[ke.getKeyCode()] = false;
        }
    }

    // Getters & setters
    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
