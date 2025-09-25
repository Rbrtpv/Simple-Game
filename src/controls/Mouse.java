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
                System.out.println("Left button clicked");
                break;
            case MouseEvent.BUTTON2:
                System.out.println("Middle button clicked");
                break;
            case MouseEvent.BUTTON3:
                System.out.println("Right button clicked");
                break;
            default:
                System.out.println("Mouse clicked at: " + me.getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        switch (me.getButton()) {
            case MouseEvent.BUTTON1:
                System.out.println("Left button pressed");
                break;
            case MouseEvent.BUTTON2:
                System.out.println("Middle button pressed");
                break;
            case MouseEvent.BUTTON3:
                System.out.println("Right button pressed");
                break;
            default:
                System.out.println("Mouse pressed at: " + me.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        switch (me.getButton()) {
            case MouseEvent.BUTTON1:
                System.out.println("Left button released");
                break;
            case MouseEvent.BUTTON2:
                System.out.println("Middle button released");
                break;
            case MouseEvent.BUTTON3:
                System.out.println("Right button released");
                break;
            default:
                System.out.println("Mouse released at: " + me.getPoint());
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("Mouse entered component");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("Mouse exited component");
    }
}
