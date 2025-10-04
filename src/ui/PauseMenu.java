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

public class PauseMenu extends Ui implements MouseListener {

  private Window window;
  private Game game;
  private Rectangle resumeButton;
  private Rectangle exitToMainMenuButton;

  public PauseMenu(Window window, Game game) {
    this.window = window;
    this.game = game;

    int buttonWidth = 250;
    int buttonHeight = 60;
    int startY = window.getHeight() / 2 - 50; 
    int spacing = 80;

    int buttonX = (window.getWidth() - buttonWidth) / 2;

    resumeButton = new Rectangle(buttonX, startY, buttonWidth, buttonHeight);
    exitToMainMenuButton = new Rectangle(buttonX, startY + spacing, buttonWidth, buttonHeight);
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setColor(new Color(0, 0, 0, 180));
    g2d.fillRect(0, 0, window.getWidth(), window.getHeight());

    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 60));
    String title = "PAUSED";
    int titleWidth = g2d.getFontMetrics().stringWidth(title);
    g2d.drawString(title, (window.getWidth() - titleWidth) / 2, window.getHeight() / 2 - 150);

    g2d.setFont(new Font("Arial", Font.PLAIN, 36));
    g2d.setColor(new Color(70, 130, 180, 200));
    g2d.fill(resumeButton);
    g2d.setColor(Color.WHITE);
    String resumeText = "Resume";
    int resumeTextWidth = g2d.getFontMetrics().stringWidth(resumeText);
    g2d.drawString(resumeText, resumeButton.x + (resumeButton.width - resumeTextWidth) / 2, resumeButton.y + 42);

    g2d.setColor(new Color(180, 70, 70, 200));
    g2d.fill(exitToMainMenuButton);
    g2d.setColor(Color.WHITE);
    String exitText = "Main Menu";
    int exitTextWidth = g2d.getFontMetrics().stringWidth(exitText);
    g2d.drawString(exitText, exitToMainMenuButton.x + (exitToMainMenuButton.width - exitTextWidth) / 2, exitToMainMenuButton.y + 42);
  }

  @Override
  public void update() {
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();

    if (resumeButton.contains(mouseX, mouseY)) {
      game.setGameState(GameState.PLAYING);
    } else if (exitToMainMenuButton.contains(mouseX, mouseY)) {
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