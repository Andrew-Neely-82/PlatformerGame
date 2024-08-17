package ui;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.PauseButtons.*;

public class PauseOverlay {
  private BufferedImage backgroundImg;
  private int bgX, bgY, bgWidth, bgHeight;
  private SoundButton musicButton, sfxButton;

  public PauseOverlay() {
    loadBackground();
    createSoundButtons();
  }

  private void createSoundButtons() {
    int soundX = (int) (450 * Game.SCALE);
    int musicY = (int) (140 * Game.SCALE);
    int sfxY = (int) (186 * Game.SCALE);
    musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
    sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
  }

  private void loadBackground() {
    backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_MENU);
    bgWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
    bgHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
    bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
    bgY = (int) (25 * Game.SCALE);
  }

  public void update() {}

  public void draw(Graphics g) {
    // Background
    g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);

    // Sound buttons
    musicButton.draw(g);
    sfxButton.draw(g);
  }

  public void mouseDragged() {}

  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}

  public void mouseMoved(MouseEvent e) {}

  public void keyReleased(KeyEvent e) {
//    switch (e.getKeyCode()) {
//      case KeyEvent.VK_A: player.setLeft(false); break;
//      case KeyEvent.VK_D: player.setRight(false); break;
//      // ========================= MOVE WITH ARROW KEYS ========================= //
//      case KeyEvent.VK_LEFT: player.setLeft(false); break;
//      case KeyEvent.VK_RIGHT: player.setRight(false); break;
//      // ================================= Jump ================================= //
//      case KeyEvent.VK_SPACE: player.setJump(false); break;
//    }
  }
}
