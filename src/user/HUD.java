package user;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

  private Player player;

  public HUD(Player player){
    this.player = player;
  }

  public void update() {

  }

  public void draw(Graphics g) {
    drawHUD(g);
  }

  public void drawHUD(Graphics g) {
    int playerHealthBarX = 10;
    int playerHealthBarY = 10;
    int playerHealthBarWidth = 250;
    int playerHealthBarHeight = 20;

    g.setColor(Color.RED);
    g.fillRect(playerHealthBarX, playerHealthBarY, playerHealthBarWidth, playerHealthBarHeight);

    if (player != null) {
      float healthPercentage = (float) player.currentCharacter.getHealthPoints() / player.currentCharacter.getMaxHealthPoints();
      int currentHealthWidth = (int) (playerHealthBarWidth * healthPercentage);
      g.setColor(Color.GREEN);
      g.fillRect(playerHealthBarX, playerHealthBarY, currentHealthWidth, playerHealthBarHeight);
    }

    g.setColor(Color.DARK_GRAY);
    g.drawRect(playerHealthBarX, playerHealthBarY, playerHealthBarWidth, playerHealthBarHeight);

    g.setColor(Color.DARK_GRAY);
    g.drawString("HP: " + (player != null ? player.currentCharacter.getHealthPoints(): "N/A")+"/"+(player != null ? player.currentCharacter.getMaxHealthPoints(): "N/A"), playerHealthBarX + 5, playerHealthBarY+15);
  }
}
