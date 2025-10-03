package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import game.Window;
import game.Game;
import game.GameState;

public class MainMenu extends Ui implements MouseListener {

  private Window window;
  private Game game;
  private Rectangle playButton;
  private Rectangle exitButton;

  public MainMenu(Window window, Game game) {
    this.window = window;
    this.game = game;
    
    int buttonWidth = 200;
    int buttonHeight = 50;
    int startY = 300;
    int spacing = 70;

    int playX = (window.getWidth() - buttonWidth) / 2;
    int playY = startY - buttonHeight / 2;
    playButton = new Rectangle(playX, playY, buttonWidth, buttonHeight);

    int exitX = (window.getWidth() - buttonWidth) / 2;
    int exitY = (startY + spacing) - buttonHeight / 2;
    exitButton = new Rectangle(exitX, exitY, buttonWidth, buttonHeight);
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setColor(new Color(20, 20, 20));
    g2d.fillRect(0, 0, window.getWidth(), window.getHeight());

    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 48));
    String title = "Juego Atacar!";
    int titleWidth = g2d.getFontMetrics().stringWidth(title);
    g2d.drawString(title, (window.getWidth() - titleWidth) / 2, 150);

    g2d.setFont(new Font("Arial", Font.PLAIN, 30));
    
    g2d.setColor(new Color(50, 150, 50));
    g2d.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
    g2d.setColor(Color.WHITE);
    String playText = "Jugar";
    int playTextWidth = g2d.getFontMetrics().stringWidth(playText);
    
    g2d.drawString(playText, playButton.x + (playButton.width - playTextWidth) / 2, playButton.y + 35);

    g2d.setColor(new Color(150, 50, 50));
    g2d.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
    g2d.setColor(Color.WHITE);
    String exitText = "Salir";
    int exitTextWidth = g2d.getFontMetrics().stringWidth(exitText);
    
    g2d.drawString(exitText, exitButton.x + (exitButton.width - exitTextWidth) / 2, exitButton.y + 35);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();

    if (playButton.contains(mouseX, mouseY)) {
      game.setGameState(GameState.PLAYING);
    } else if (exitButton.contains(mouseX, mouseY)) {
      System.exit(0);
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