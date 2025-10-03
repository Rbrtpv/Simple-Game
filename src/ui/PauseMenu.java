package ui;

import java.awt.Color;
import java.awt.Graphics;

public class PauseMenu {

  public PauseMenu() {
  }

  public void draw(Graphics g) {
    g.setColor(new Color(0, 0, 0, 150));
    g.fillRect(0, 0, 800, 600);
    g.setColor(Color.WHITE);
    g.drawString("Pause", 350, 250);
  }

  public void update() {
  }
}
