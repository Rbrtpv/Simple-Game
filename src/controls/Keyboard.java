package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import user.Player;

public class Keyboard implements KeyListener {

    private boolean up, down, left, right;
    private Player player;

    public Keyboard(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = true;
                System.out.println("up");
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = true;
                System.out.println("down");
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = true;
                System.out.println("left");
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = true;
                System.out.println("right");
                break;
            case KeyEvent.VK_SPACE:
                player.swapCharacter();
                System.out.println("swap");
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("scape");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    // Getters y setters
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
