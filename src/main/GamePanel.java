package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private BufferedImage img;
  private BufferedImage[] idleAnimation;
  private byte animationTick, animationIndex, animationSpeed = 15;

  public GamePanel() {
    mouseInputs = new MouseInputs(this);

    importImg();
    loadAnimations();

    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  private void loadAnimations() {
    idleAnimation = new BufferedImage[5];

    for (int i = 0; i < idleAnimation.length; i++) {
      idleAnimation[i] = img.getSubimage(i * 64, 0, 64, 40);
    }
  }

  private void importImg() {
    InputStream is = getClass().getResourceAsStream("/player_sprites.png");

    try {
      img = ImageIO.read(is);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void setPanelSize() {
    Dimension size = new Dimension(1280, 800);
    setMinimumSize(size);
    setPreferredSize(size);
    setMaximumSize(size);
  }

  public void changeXDelta(int value) {
    this.xDelta += value;
  }

  public void changeYDelta(int value) {
    this.yDelta += value;
  }

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= idleAnimation.length) {
        animationIndex = 0;
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    updateAnimationTick();

    g.drawImage(idleAnimation[animationIndex], (int) xDelta, (int) yDelta, 128, 80, null);
  }


}
