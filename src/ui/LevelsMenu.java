package ui;

import java.awt.Color;
import java.awt.Graphics;

public class LevelsMenu {
  public LevelsMenu() {
    // Constructor
  }

  // @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.drawString("Level Selector", 350, 250);
    // Agrega botones para seleccionar niveles
  }

  // @Override
  public void update() {
    // Lógica de actualización
  }
}
