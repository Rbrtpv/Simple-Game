package game;

import controls.Keyboard;
import controls.Mouse;
import entities.characters.GameCharacter;
import entities.characters.Pierce;
import entities.dmg.AreaOfEffect;
import entities.dmg.Projectile;
import entities.enemies.Machines;
import java.awt.Canvas;
import java.awt.event.MouseListener;
import java.util.List;
import user.Player;

public class Game implements Runnable {

    // game window
    private Window window;
    private Keyboard keyboard;
    private MouseListener mouse;
    private Canvas canvas;
    private Player player;
    private List<GameCharacter> enemies;
    // game thread
    private Thread thread;
    // game loop
    private volatile boolean running = false;
    private int fps;
    private double timePerTick;
    private double delta;
    private long now;
    private long lastTime;
    private Machines machines;

    public Game() {
    }

    public void init() {
        GameCharacter pierce = new Pierce(100, 100, 50, 50, 5);
        machines = new Machines(400, 40, 50, 50, 5);
        player = new Player(pierce);
        keyboard = new Keyboard(player);
        player.setKeyboard(keyboard);
        mouse = new Mouse(player);
        window = new Window();
        window.loadWindow(keyboard, mouse);
        start();
    }

    @Override
    public void run() {
        fps = 60;
        timePerTick = 1_000_000_000.0 / fps;
        delta = 0;
        lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            while (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
        System.out.println("-- start --");
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-- stop --");
    }

    private void update() {
        if (keyboard != null) {
            keyboard.update();
        }
        player.update();
        for (Projectile projectile : player.getProjectiles()) {
            projectile.update();
        }
        for (AreaOfEffect attack : player.getAttacks()) {
            attack.update();
        }
    }

    private void repaint() {
        window.draw();
        for (Projectile projectile : player.getProjectiles()) {
            projectile.draw(window.getGraphics());
        }
        for (AreaOfEffect aoe : player.getAttacks()) {
            aoe.draw(window.getGraphics());
        }
        player.draw(window.getGraphics());
        machines.draw(window.getGraphics());
        window.render();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
