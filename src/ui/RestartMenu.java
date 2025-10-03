package ui;

import game.Game;
import game.GameState;
import game.Window;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RestartMenu extends Ui implements MouseListener {

  private Window window;
  private Game game;
  private Rectangle restartButton;
  private Rectangle exitButton;
  private long countdownStartTime;
  private final long COUNTDOWN_DURATION_SECONDS = 30;
  private long remainingTimeSeconds;
  private boolean countdownActive;

  public RestartMenu(Window window, Game game) {
    this.window = window;
    this.game = game;
    
    int buttonWidth = 200;
    int buttonHeight = 50;
    int startY = window.getHeight() / 2 + 100;
    int spacing = 70;

    int restartX = (window.getWidth() - buttonWidth) / 2;
    int restartY = startY - buttonHeight / 2;
    restartButton = new Rectangle(restartX, restartY, buttonWidth, buttonHeight);

    int exitX = (window.getWidth() - buttonWidth) / 2;
    int exitY = (startY + spacing) - buttonHeight / 2;
    exitButton = new Rectangle(exitX, exitY, buttonWidth, buttonHeight);

    this.countdownActive = false;
  }
  public void startCountdown() {
    this.countdownStartTime = System.currentTimeMillis();
    this.countdownActive = true;
    this.remainingTimeSeconds = COUNTDOWN_DURATION_SECONDS;
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setColor(new Color(0, 0, 0, 180));
    g2d.fillRect(0, 0, window.getWidth(), window.getHeight());

    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 40));
    String message = "¡Restart!";
    int messageWidth = g2d.getFontMetrics().stringWidth(message);
    g2d.drawString(message, (window.getWidth() - messageWidth) / 2, window.getHeight() / 2 - 80);

    if (countdownActive) {
        g2d.setFont(new Font("Arial", Font.BOLD, 80));
        String timeText = String.valueOf(remainingTimeSeconds);
        int timeTextWidth = g2d.getFontMetrics().stringWidth(timeText);
        g2d.drawString(timeText, (window.getWidth() - timeTextWidth) / 2, window.getHeight() / 2 + 20);
    }

    g2d.setFont(new Font("Arial", Font.PLAIN, 30));
    
    g2d.setColor(new Color(50, 150, 50));
    g2d.fillRect(restartButton.x, restartButton.y, restartButton.width, restartButton.height);
    g2d.setColor(Color.WHITE);
    String restartText = "Restart";
    int restartTextWidth = g2d.getFontMetrics().stringWidth(restartText);
    g2d.drawString(restartText, restartButton.x + (restartButton.width - restartTextWidth) / 2, restartButton.y + 35);

    g2d.setColor(new Color(150, 50, 50));
    g2d.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
    g2d.setColor(Color.WHITE);
    String exitText = "Exit";
    int exitTextWidth = g2d.getFontMetrics().stringWidth(exitText);
    g2d.drawString(exitText, exitButton.x + (exitButton.width - exitTextWidth) / 2, exitButton.y + 35);
  }

  @Override
  public void update() {
    if (countdownActive) {
        long elapsedTime = (System.currentTimeMillis() - countdownStartTime) / 1000;
        remainingTimeSeconds = COUNTDOWN_DURATION_SECONDS - elapsedTime;

        if (remainingTimeSeconds <= 0) {
            remainingTimeSeconds = 0;
            countdownActive = false;
        }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();

    if (restartButton.contains(mouseX, mouseY)) {
      game.resetGame();
      game.setGameState(GameState.PLAYING);
    } else if (exitButton.contains(mouseX, mouseY)) {
      game.setGameState(GameState.MAIN_MENU);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {}
  @Override
  public void mouseReleased(MouseEvent e) {}
  @Override
  public void mouseEntered(MouseEvent e) {}
  @Override
  public void mouseExited(MouseEvent e) {}
}