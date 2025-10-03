package ui;

import java.awt.Color;
import java.awt.Graphics;

public class LevelsMenu {
  public LevelsMenu() {
  }

  public void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.drawString("Level", 350, 250);
  }

  public void update() {
  }
}
