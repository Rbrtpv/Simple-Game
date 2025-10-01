package entities.enemies;

import entities.characters.GameCharacter;
import java.awt.Color;
import java.awt.Graphics;
import user.Player;

public abstract class Enemy extends GameCharacter {

  protected Color color;
  protected int attackDmg;
  protected int defense;
  protected float attackRange;
  protected float attackCooldown;
  protected String name;
  protected float currentCooldownTimer;
  protected Player target;

  public Enemy(int x, int y, int w, int h, int speed, int healthPoints, int maxHealthPoints, int attackDmg, int defense,
      float attackRange, float attackCooldown) {
    super(x, y, w, h, speed, healthPoints, maxHealthPoints, attackDmg, defense);
    this.attackDmg = attackDmg;
    this.defense = defense;
    this.attackRange = attackRange;
    this.attackCooldown = attackCooldown;
    this.currentCooldownTimer = 0;
  }

  public abstract void update();

  public abstract void draw(Graphics g);

  public abstract void attack(Player target);

  protected void chase() {
    float deltaTime = 1.0f / 60.0f;
    updateCooldown(deltaTime);

    if (target == null || target.currentCharacter == null || target.currentCharacter.getHealthPoints() <= 0
        || this.healthPoints <= 0) {
      return;
    }

    boolean isInRange = checkIfTargetInRange(target);

    if (isInRange && target.currentCharacter.getHealthPoints() > 0) {
      if (isAttackReady()) {
        attack(target);
        resetAttackCooldown();
      }
    } else {
      move(target.currentCharacter);
    }
  }

  protected void move(GameCharacter target) {
    if ((target.getX() - 25) > x) {
      x += speed;
    } else {
      x -= speed;
    }
    if ((target.getY() - 25) > y) {
      y += speed;
    } else {
      y -= speed;
    }
  }

  protected boolean checkIfTargetInRange(Player target) {

    double machineCenterX = x + w / 2.0;
    double machineCenterY = y + h / 2.0;

    double targetCenterX = target.currentCharacter.getX() + target.currentCharacter.getW() / 2.0;
    double targetCenterY = target.currentCharacter.getY() + target.currentCharacter.getH() / 2.0;

    double dx = targetCenterX - machineCenterX;
    double dy = targetCenterY - machineCenterY;
    double distance = Math.sqrt(dx * dx + dy * dy);
    return distance <= attackRange;
  }

  protected void updateCooldown(float deltaTime) {
    if (currentCooldownTimer > 0) {
      currentCooldownTimer -= deltaTime;
      if (currentCooldownTimer < 0) {
        currentCooldownTimer = 0; // no negative
      }
    }
  }

  protected boolean isAttackReady() {
    return currentCooldownTimer <= 0;
  }

  protected void resetAttackCooldown() {
    this.currentCooldownTimer = this.attackCooldown;
  }

  protected void loadEnemyDraw(Graphics g) {
    drawEnemy(g);
    healthBar(g);
  }

  protected void drawEnemy(Graphics g) {
    g.setColor(Color.red);
    g.drawRect(x, y, w, h);
  }

  protected void healthBar(Graphics g) {
    int barWidth = w;
    int barHeight = 5;
    int barX = x;
    int barY = y - barHeight - 2;

    g.setColor(Color.RED); // lost health
    g.fillRect(barX, barY, barWidth, barHeight);

    float healthPercentage = (float) healthPoints / maxHealthPoints;
    int currentHealthBarWidth = (int) (barWidth * healthPercentage);

    g.setColor(Color.GREEN); // remaining health
    g.fillRect(barX, barY, currentHealthBarWidth, barHeight);
  }

  @Override
  public void takeDamage(int damage) {
    this.healthPoints -= damage;
    if (this.healthPoints < 0) {
      this.healthPoints = 0;
    }
  }

  public boolean isAlive() {
    return healthPoints > 0;
  }

  // Getters and Setters
  public void setTarget(Player target) {
    this.target = target;
  }

  @Override
  public int getHealthPoints() {
    return healthPoints;
  }

  @Override
  public int getMaxHealthPoints() {
    return maxHealthPoints;
  }
}
