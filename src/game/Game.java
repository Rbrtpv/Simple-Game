package game;

import controls.Keyboard;
import controls.Mouse;
import entities.characters.GameCharacter;
import entities.characters.Pierce;
import entities.characters.Slash;
import entities.dmg.AreaOfEffect;
import entities.dmg.Projectile;
import entities.enemies.Enemy;
import entities.enemies.Machines;
import entities.enemies.Organic;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ui.LevelsMenu;
import ui.MainMenu;
import ui.PauseMenu;
import ui.RestartMenu;
import ui.StatsMenu;
import user.HUD;
import user.Player;

public class Game implements Runnable {

    // game window
    private Window window;
    private Keyboard keyboard;
    private Mouse mouse;
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
    private MainMenu mainMenu;
    private PauseMenu pauseMenu;
    private RestartMenu restartMenu;
    private LevelsMenu levelsMenu;
    private StatsMenu statsMenu;
    private GameState currentGameState;

    public Game() {
    }

    public void init() {
        window = new Window();
        GameCharacter pierce = new Pierce((window.getWidth() / 2) - 25, (window.getHeight() - 300) - 25, 0, 0, 0);
        player = new Player(pierce);
        keyboard = new Keyboard(player);
        player.setKeyboard(keyboard);
        mouse = new Mouse(player);
        window.loadWindow(keyboard);
        hud = new HUD(player);
        enemies = new ArrayList<>();
        enemies.add(new Machines());
        enemies.add(new Organic());
        for (Enemy enemy : enemies) {
            enemy.setTarget(player);
        }
        mainMenu = new MainMenu(window, this);
        pauseMenu = new PauseMenu(window, this);
        restartMenu = new RestartMenu(window, this);
        levelsMenu = new LevelsMenu();
        statsMenu = new StatsMenu();
        currentGameState = GameState.MAIN_MENU;

        window.addMouseListener(mainMenu);
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
        if (keyboard.consumeEscapePress()) {
            if (currentGameState == GameState.PLAYING) {
                setGameState(GameState.PAUSE);
            } else if (currentGameState == GameState.PAUSE) {
                setGameState(GameState.PLAYING);
            }
            return;
        }
        switch (currentGameState) {
            case MAIN_MENU:
                mainMenu.update();
                break;
            case PLAYING:
                player.update();
                if (player.getCurrentCharacter().getHealthPoints() <= 0) {
                    setGameState(GameState.RESTART);
                    break;
                }
                keyboard.update();

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
                            projectile.setActive(false);
                            break;
                        }
                    }
                }

                List<AreaOfEffect> playerAttacks = player.getAttacks();
                Iterator<AreaOfEffect> aoeIterator = playerAttacks.iterator();
                while (aoeIterator.hasNext()) {
                    AreaOfEffect attack = aoeIterator.next();
                    attack.update();

                    if (!attack.isActive()) {
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
                        enemyUpdateIterator.remove();
                    } else {
                        //
                        if (enemy instanceof Machines) {
                            Machines machineEnemy = (Machines) enemy;
                            Iterator<Projectile> enemyProjectileIterator = machineEnemy.getProjectiles().iterator();
                            while (enemyProjectileIterator.hasNext()) {
                                Projectile enemyProjectile = enemyProjectileIterator.next();

                                if (checkCollision(enemyProjectile.getBounds(),
                                        player.getCurrentCharacter().getBounds())) {
                                    player.getCurrentCharacter().takeDamage(enemyProjectile.getDamage());
                                    enemyProjectile.setActive(false);
                                    enemyProjectileIterator.remove();
                                }
                            }
                        }
                    }
                }
                break;
            case PAUSE:
                pauseMenu.update();
                break;
            case RESTART:
                restartMenu.update();
                break;
            case LEVELS:
                levelsMenu.update();
                break;
            case STATS:
                statsMenu.update();
                break;
        }
    }

    private void repaint() {
        window.draw();

        switch (currentGameState) {
            case MAIN_MENU:
                mainMenu.draw(window.getGraphics());
                break;
            case PLAYING:
                for (Projectile projectile : new ArrayList<>(player.getProjectiles())) {
                    projectile.draw(window.getGraphics());
                }
                for (AreaOfEffect aoe : new ArrayList<>(player.getAttacks())) {
                    aoe.draw(window.getGraphics());
                }
                player.draw(window.getGraphics());
                for (Enemy enemy : enemies) {
                    enemy.draw(window.getGraphics());
                    if (enemy instanceof Machines) {
                        for (Projectile p : new ArrayList<>(((Machines) enemy).getProjectiles())) {
                            p.draw(window.getGraphics());
                        }
                    }
                }
                hud.draw(window.getGraphics());
                break;
            case PAUSE:
                for (Projectile projectile : new ArrayList<>(player.getProjectiles())) {
                    projectile.draw(window.getGraphics());
                }
                for (AreaOfEffect aoe : new ArrayList<>(player.getAttacks())) {
                    aoe.draw(window.getGraphics());
                }
                player.draw(window.getGraphics());
                for (Enemy enemy : enemies) {
                    enemy.draw(window.getGraphics());
                    if (enemy instanceof Machines) {
                        for (Projectile p : new ArrayList<>(((Machines) enemy).getProjectiles())) {
                            p.draw(window.getGraphics());
                        }
                    }
                }
                hud.draw(window.getGraphics());
                pauseMenu.draw(window.getGraphics());
                break;
            case RESTART:
                for (Projectile projectile : new ArrayList<>(player.getProjectiles())) {
                    projectile.draw(window.getGraphics());
                }
                for (AreaOfEffect aoe : new ArrayList<>(player.getAttacks())) {
                    aoe.draw(window.getGraphics());
                }
                player.draw(window.getGraphics());
                for (Enemy enemy : enemies) {
                    enemy.draw(window.getGraphics());
                    if (enemy instanceof Machines) {
                        for (Projectile p : new ArrayList<>(((Machines) enemy).getProjectiles())) {
                            p.draw(window.getGraphics());
                        }
                    }
                }
                hud.draw(window.getGraphics());
                restartMenu.draw(window.getGraphics());
                break;
            case LEVELS:
                levelsMenu.draw(window.getGraphics());
                break;
            case STATS:
                statsMenu.draw(window.getGraphics());
                break;
        }

        window.render();
    }

    public void setGameState(GameState newGameState) {
        if (null != currentGameState)
            switch (currentGameState) {
                case MAIN_MENU -> window.removeMouseListener(mainMenu);
                case PLAYING -> window.removeMouseListener(mouse);
                case RESTART -> window.removeMouseListener(restartMenu);
                case PAUSE -> window.removeMouseListener(pauseMenu);
                default -> {
                }
            }

        this.currentGameState = newGameState;

        if (null != newGameState)
            switch (newGameState) {
                case MAIN_MENU -> {
                    window.addMouseListener(mainMenu);
                    resetGame();
                }
                case PLAYING -> window.addMouseListener(mouse);
                case RESTART -> {
                    window.addMouseListener(restartMenu);
                    restartMenu.startCountdown();
                }
                case PAUSE -> window.addMouseListener(pauseMenu);
                default -> {
                }
            }
    }

    public void resetGame() {
        player.getCurrentCharacter().setHealthPoints(player.getCurrentCharacter().getMaxHealthPoints());
        player.getCurrentCharacter().setX((window.getWidth() / 2) - player.getCurrentCharacter().getW() / 2);
        player.getCurrentCharacter().setY((window.getHeight() - 300) - player.getCurrentCharacter().getH() / 2);

        if (player.getCurrentCharacter() instanceof Pierce) {
            ((Pierce) player.getCurrentCharacter()).getProjectiles().clear();
        } else if (player.getCurrentCharacter() instanceof Slash) {
            ((Slash) player.getCurrentCharacter()).getAttacks().clear();
        }

        enemies.clear();
        enemies.add(new Machines());
        enemies.add(new Organic());
        for (Enemy enemy : enemies) {
            enemy.setTarget(player);
        }
    }

    // Getters and Setters
    private boolean checkCollision(Rectangle r1, Rectangle r2) {
        return r1.intersects(r2);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameState getGameState() {
        return currentGameState;
    }
}
