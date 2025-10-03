package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

// import ui.MainMenu;

public class Window {

    private String title = "Ataca!";
    private int width = 990, height = 800;
    private JFrame frame;
    private Canvas canvas;
    private Graphics g;
    private BufferStrategy bs;

    public Window() {
    }

    public void loadWindow(KeyListener keyListener) {

        frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        if (keyListener != null) {
            canvas.addKeyListener(keyListener);
        }

        canvas.setFocusable(true);
        canvas.createBufferStrategy(3);
    }

    public void draw() {
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);
        g.setColor(Color.black);

        g.fillRect(0, 0, width, height);

    }

    public void render() {
        bs.show();
        g.dispose();
    }

    // Getters & setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Graphics getGraphics() {
        return g;
    }

    public void addMouseListener(MouseListener listener) {
        if (canvas != null) {
            canvas.addMouseListener(listener);
        }
    }

    public void removeMouseListener(MouseListener listener) {
        if (canvas != null) {
            canvas.removeMouseListener(listener);
        }
    }
}
