package entities.enemies;

import entities.characters.GameCharacter;
import java.awt.Color;
import java.awt.Graphics;
import user.Player;

public abstract class Enemy {

  protected int x, y;
  protected int w, h;
  protected int speed;
  protected Color color;
  protected int healthPoints;
  protected int attackDmg;
  protected int defense;
  protected float attackRange;
  protected float attackCooldown;
  protected String name;
  protected float currentCooldownTimer;

  protected Player target;

  public Enemy(int x, int y, int w, int h, int speed, Color color, int hp, int attackDmg, int defense,
      float attackRange, float attackCooldown, String name) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.speed = speed;
    this.color = color;
    this.healthPoints = hp;
    this.attackDmg = attackDmg;
    this.defense = defense;
    this.attackRange = attackRange;
    this.attackCooldown = attackCooldown;
    this.name = name;
    this.currentCooldownTimer = 0;
  }

  public abstract void update();

  public abstract void draw(Graphics g);

  public abstract void attack(Player target);

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

  protected void chase() {
    float deltaTime = 1.0f / 60.0f;
    updateCooldown(deltaTime);

    if (target == null || healthPoints <= 0) {
      return;
    }
    boolean isInRange = checkIfTargetInRange(target);
    if (isInRange && target.currentCharacter.getHealth()>0) {
      if (isAttackReady()) {
        attack(target);
        resetAttackCooldown();
      }
    } else {
      move(target.currentCharacter);
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

  // Getters and Setters
  public void setTarget(Player target) {
    this.target = target;
  }
}
