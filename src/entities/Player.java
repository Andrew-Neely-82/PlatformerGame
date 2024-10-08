package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

public class Player extends Entity {
  private BufferedImage[][] animations;
  private byte animationTick, animationIndex, animationSpeed = 25;
  private int playerAction = IDLE;
  private boolean moving, attacking = false;
  private boolean left, right, up, down, jump;
  private float playerSpeed = 0.75f * Game.SCALE;
  private int[][] lvlData;
  private float xDrawOffset = 21 * Game.SCALE;
  private float yDrawOffset = 4 * Game.SCALE;
  // ==================== Jumping / Gravity ==================== //
  private float airSpeed = 0f;
  private float gravity = 0.04f * Game.SCALE;
  private float jumpSpeed = -2.5f * Game.SCALE;
  private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
  private boolean inAir = false;

  public Player(float x, float y, int width, int height) {
    super(x, y, width, height);
    loadAnimations();
    initHitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));
  }

  public void update() {
    updatePosition();
    updateAnimationTick();
    setAnimation();
  }

  public void render(Graphics g) {
    g.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
  }

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= GetSpriteAmount(playerAction)) {
        animationIndex = 0;
        attacking = false;
      }
    }
  }

  private void setAnimation() {
    int startAnimation = playerAction;
    if (moving)
      playerAction = RUNNING;
    if (!moving)
      playerAction = IDLE;
    if (inAir) {
      if (airSpeed < 0)
        playerAction = JUMPING;
      else
        playerAction = FALLING;
    }
    if (attacking)
      playerAction = ATTACK_1;
    if (startAnimation != playerAction)
      resetAnimationTick();
  }

  private void resetAnimationTick() {
    animationTick = 0;
    animationIndex = 0;
  }

  private void updatePosition() {
    moving = false;
    if (jump)
      jump();
    if (!left && !right && !inAir) return;
    float xSpeed = 0;
    if (left) xSpeed -= playerSpeed;
    if (right) xSpeed += playerSpeed;
    if (!inAir) {
      if (!IsEntityOnFloor(hitbox, lvlData)) {
        inAir = true;
      }
    }
    if (inAir) {
      if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
        hitbox.y += airSpeed;
        airSpeed += gravity;
        updateXPosition(xSpeed);
      } else {
        hitbox.y = GetEntityYPositionUnderRoofOrAboveFloor(hitbox, airSpeed);
        if (airSpeed > 0)
          resetInAir();
        else
          airSpeed = fallSpeedAfterCollision;
        updateXPosition(xSpeed);
      }
    } else
      updateXPosition(xSpeed);
    moving = true;
  }

  private void jump() {
    if (inAir) return;
    inAir = true;
    airSpeed = jumpSpeed;
  }

  private void resetInAir() {
    inAir = false;
    airSpeed = 0;
  }

  private void updateXPosition(float xSpeed) {
    if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
      hitbox.x += xSpeed;
    } else {
      hitbox.x = GetEntityXPositionNextToWall(hitbox, xSpeed);
    }
  }

  private void loadAnimations() {
    BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
    animations = new BufferedImage[9][6];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
  }

  public void loadLvlData(int[][] lvlData) {
    this.lvlData = lvlData;
    if (!IsEntityOnFloor(hitbox, lvlData))
      inAir = true;
  }

  public void resetDirectionBooleans() {
    left = false;
    right = false;
    up = false;
    down = false;
  }

  public void setAttacking(boolean attacking) {this.attacking = attacking;}

  public boolean isLeft() {return left;}

  public void setLeft(boolean left) {this.left = left;}

  public boolean isRight() {return right;}

  public void setRight(boolean right) {this.right = right;}

  public boolean isUp() {return up;}

  public void setUp(boolean up) {this.up = up;}

  public boolean isDown() {return down;}

  public void setDown(boolean down) {this.down = down;}

  public void setJump(boolean jump) {this.jump = jump;}
}
