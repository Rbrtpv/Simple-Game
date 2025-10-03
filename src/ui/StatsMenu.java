package ui;

import java.awt.Color;
import java.awt.Graphics;

public class StatsMenu extends Ui {

  public StatsMenu() {
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.YELLOW);
    g.drawString("Stats", 350, 250);
  }

  @Override
  public void update() {
  }
}