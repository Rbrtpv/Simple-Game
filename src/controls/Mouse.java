package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import user.Player;

public class Mouse implements MouseListener {

    private Player player;

    public Mouse(Player player) {
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        switch (me.getButton()) {
            case MouseEvent.BUTTON1:
                break;
            case MouseEvent.BUTTON2:
                break;
            case MouseEvent.BUTTON3:
                break;
            default:
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        switch (me.getButton()) {
            case MouseEvent.BUTTON1:
                player.attack(me.getX(), me.getY());
                break;
            case MouseEvent.BUTTON2:
                break;
            case MouseEvent.BUTTON3:
                break;
            default:
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        switch (me.getButton()) {
            case MouseEvent.BUTTON1:
                break;
            case MouseEvent.BUTTON2:
                break;
            case MouseEvent.BUTTON3:
                break;
            default:
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
