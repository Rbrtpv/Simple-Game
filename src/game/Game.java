package game;

import controls.Keyboard;
import controls.Mouse;
import entities.characters.GameCharacter;
import entities.characters.Pierce;
import entities.dmg.AreaOfEffect;
import entities.dmg.Projectile;
import entities.enemies.Enemy;
import entities.enemies.Machines;
import entities.enemies.Organic;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import user.HUD;
import user.Player;

public class Game implements Runnable {

    // game window
    private Window window;
    private Keyboard keyboard;
    private MouseListener mouse;
    private Player player;
    private HUD hud;
    private List<Enemy> enemies;
    // game thread
    private Thread thread;
    // game loop
    private volatile boolean running = false;
    private int fps;
    private double timePerTick;
    private double delta;
    private long now;
    private long lastTime;

    public Game() {
    }

    public void init() {
        window = new Window();
        GameCharacter pierce = new Pierce((window.getWidth() / 2) - 25, (window.getHeight() - 300) - 25, 0, 0, 0);
        player = new Player(pierce);
        keyboard = new Keyboard(player);
        player.setKeyboard(keyboard);
        mouse = new Mouse(player);
        window.loadWindow(keyboard, mouse);
        hud = new HUD(player);
        enemies = new ArrayList<>();
        enemies.add(new Machines());
        enemies.add(new Organic());
        for (Enemy enemy : enemies) {
            enemy.setTarget(player);
        }

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
    }

    private void update() {
        player.update();
        if (keyboard != null) {
            keyboard.update();
        }
        List<Projectile> playerProjectiles = player.getProjectiles();
        Iterator<Projectile> projectileIterator = playerProjectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            projectile.update();

            if (!projectile.isActive()) {
                projectileIterator.remove();
                continue;
            }

            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (checkCollision(projectile.getBounds(), enemy.getBounds())) {
                    enemy.takeDamage(projectile.getDamage());
                    projectile.setActive(false); // proyectila disapear
                    break; // projectile only for one enemy
                }
            }
        }

        List<AreaOfEffect> playerAttacks = player.getAttacks();
        Iterator<AreaOfEffect> aoeIterator = playerAttacks.iterator();
        while (aoeIterator.hasNext()) {
            AreaOfEffect attack = aoeIterator.next();
            attack.update();

            if (!attack.isActive()) { //AOE no active
                aoeIterator.remove();
                continue;
            }

            for (Enemy enemy : enemies) {
                if (checkCollision(attack.getBounds(), enemy.getBounds())) {

                    enemy.takeDamage(attack.getDamage());
                }
            }
        }

        Iterator<Enemy> enemyUpdateIterator = enemies.iterator();
        while (enemyUpdateIterator.hasNext()) {
            Enemy enemy = enemyUpdateIterator.next();
            enemy.update();

            if (!enemy.isAlive()) {
                enemyUpdateIterator.remove(); // if enemy health is 0 = remove
            } else {
                // 
                if (enemy instanceof Machines) {
                    Machines machineEnemy = (Machines) enemy;
                    Iterator<Projectile> enemyProjectileIterator = machineEnemy.getProjectiles().iterator();
                    while (enemyProjectileIterator.hasNext()) {
                        Projectile enemyProjectile = enemyProjectileIterator.next();

                        if (checkCollision(enemyProjectile.getBounds(), player.getCurrentCharacter().getBounds())) {
                            player.getCurrentCharacter().takeDamage(enemyProjectile.getDamage());
                            enemyProjectile.setActive(false);
                            enemyProjectileIterator.remove();
                        }
                    }
                }
            }
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
        for (Enemy enemy : enemies) {
            enemy.draw(window.getGraphics());
            // draw enemies projectiles
            if(enemy instanceof Machines) {
                for(Projectile p : ((Machines) enemy).getProjectiles()){
                    p.draw(window.getGraphics());
                }
            }
        }
        hud.draw(window.getGraphics());

        window.render();
    }

    // Getters and Setters
    private boolean checkCollision(Rectangle r1, Rectangle r2) {
        return r1.intersects(r2);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
