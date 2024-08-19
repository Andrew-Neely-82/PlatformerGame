package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.URMButtons.*;

public class PauseOverlay {
  private Playing playing;
  private BufferedImage backgroundImg;
  private int bgX, bgY, bgWidth, bgHeight;
  private SoundButton musicButton, sfxButton;
  private UrmButton menuB, replayB, unpauseB;
//  private VolumeButton volumeButton;

  public PauseOverlay(Playing playing) {
    this.playing = playing;
    loadBackground();
    createSoundButtons();
    createUrmButtons();
  }

  private void createUrmButtons() {
    int menyX = (int) (313 * Game.SCALE);
    int replayX = (int) (387 * Game.SCALE);
    int unpauseX = (int) (462 * Game.SCALE);
    int bY = (int) (325 * Game.SCALE);

    unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
    menuB = new UrmButton(menyX, bY, URM_SIZE, URM_SIZE, 2);
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

  public void update() {

    musicButton.update();
    sfxButton.update();

    menuB.update();
    replayB.update();
    unpauseB.update();

//    volumeButton.update();

  }

  public void draw(Graphics g) {
    // Background
    g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);

    // Sound buttons
    musicButton.draw(g);
    sfxButton.draw(g);

    // URM buttons
    unpauseB.draw(g);
    replayB.draw(g);
    menuB.draw(g);

    // Volume Button
//    volumeButton.draw(g);
  }

  public void mouseDragged(MouseEvent e) {
//    if (volumeButton.isMousePressed()) {
//      volumeButton.changeX(e.getX());
//    }
  }

  public void mouseDragged() {}

  public void mousePressed(MouseEvent e) {
    if (isIn(e, musicButton))
      musicButton.setMousePressed(true);
    else if (isIn(e, sfxButton))
      sfxButton.setMousePressed(true);
    else if (isIn(e, menuB))
      menuB.setMousePressed(true);
    else if (isIn(e, replayB))
      replayB.setMousePressed(true);
    else if (isIn(e, unpauseB))
      unpauseB.setMousePressed(true);
//    else if (isIn(e, volumeButton))
//      volumeButton.setMousePressed(true);
  }

  public void mouseReleased(MouseEvent e) {
    if (isIn(e, musicButton)) {
      if (musicButton.isMousePressed())
        musicButton.setMuted(!musicButton.isMuted());
    } else if (isIn(e, sfxButton)) {
      if (sfxButton.isMousePressed())
        sfxButton.setMuted(!sfxButton.isMuted());
    } else if (isIn(e, menuB)) {
      if (menuB.isMousePressed()) {
        Gamestate.state = Gamestate.MENU;
        playing.unpauseGame();
      }
    } else if (isIn(e, replayB)) {
      if (replayB.isMousePressed())
        System.out.println("replay lvl!");
    } else if (isIn(e, unpauseB)) {
      if (unpauseB.isMousePressed())
        playing.unpauseGame();
    }

    musicButton.resetBooleans();
    sfxButton.resetBooleans();
    menuB.resetBooleans();
    replayB.resetBooleans();
    unpauseB.resetBooleans();
//    volumeButton.resetBooleans();
  }

  public void mouseMoved(MouseEvent e) {
    musicButton.setMouseOver(false);
    sfxButton.setMouseOver(false);
    menuB.setMouseOver(false);
    replayB.setMouseOver(false);
    unpauseB.setMouseOver(false);
//    volumeButton.setMouseOver(false);

    if (isIn(e, musicButton))
      musicButton.setMouseOver(true);
    else if (isIn(e, sfxButton))
      sfxButton.setMouseOver(true);
    else if (isIn(e, menuB))
      menuB.setMouseOver(true);
    else if (isIn(e, replayB))
      replayB.setMouseOver(true);
    else if (isIn(e, unpauseB))
      unpauseB.setMouseOver(true);
//    else if (isIn(e, volumeButton))
//      volumeButton.setMouseOver(true);

  }

  private boolean isIn(MouseEvent e, PauseButton b) {return b.getBounds().contains(e.getX(), e.getY());}
}
