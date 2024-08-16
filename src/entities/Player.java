package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {

  private BufferedImage[][] animations;
  private byte animationTick, animationIndex, animationSpeed = 15;
  private int playerAction = IDLE;
  private boolean moving = false;
  private boolean left, right, up, down;
  private float playerSpeed = 2.0f;

  public Player(float x, float y) {
    super(x, y);
    loadAnimations();
  }

  public void update() {
    updatePosition();
    updateAnimationTick();
    setAnimation();
  }

  public void render(Graphics g) {
    g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 256, 160, null);
  }


  private void updateAnimationTick() {

    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= GetSpriteAmount(playerAction)) {
        animationIndex = 0;
      }
    }

  }

  private void setAnimation() {
    if (moving) {
      playerAction = RUNNING;
    } else {
      playerAction = IDLE;
    }
  }

  private void updatePosition() {
    moving = false;

    if (left && !right) {
      x -= playerSpeed;
      moving = true;
    } else if (right && !left) {
      x += playerSpeed;
      moving = true;
    }

    if (up && !down) {
      y -= playerSpeed;
      moving = true;
    } else if (down && !up) {
      y += playerSpeed;
      moving = true;
    }
  }

  private void loadAnimations() {
    InputStream is = getClass().getResourceAsStream("/player_sprites.png");

    try {
      BufferedImage img = ImageIO.read(is);

      animations = new BufferedImage[9][6];
      for (int j = 0; j < animations.length; j++)
        for (int i = 0; i < animations[j].length; i++)
          animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void resetDirectionBooleans() {
    left = false;
    right = false;
    up = false;
    down = false;
  }

  public boolean isLeft() {
    return left;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public boolean isRight() {
    return right;
  }

  public void setRight(boolean right) {
    this.right = right;
  }

  public boolean isUp() {
    return up;
  }

  public void setUp(boolean up) {
    this.up = up;
  }

  public boolean isDown() {
    return down;
  }

  public void setDown(boolean down) {
    this.down = down;
  }
}
