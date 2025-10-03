package ui;

import java.awt.Graphics;

public abstract class Ui {
  
  public Ui(){
  }

  public abstract void draw(Graphics g);

  public void update(){};
}
