package utils;

import main.Game;

public class Constants {
  public static class UI {
    public static class Buttons {
      public static final int B_WIDTH_DEFAULT = 140;
      public static final int B_HEIGHT_DEFAULT = 56;
      public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
      public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
    }

    public static class PauseButtons {
      public static final byte SOUND_SIZE_DEFAULT = 42;
      public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
    }

    public static class URMButtons {
      public static final byte URM_DEFAULT_SIZE = 56;
      public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
    }

    public static class VolumeButtons {
      public static final int VOLUME_DEFAULT_WIDTH = 28;
      public static final int VOLUME_DEFAULT_HEIGHT = 44;
      public static final int SLIDER_DEFAULT_WIDTH = 215;

      public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
      public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
      public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
    }
  }

  public static class PlayerConstants {
    public static class Directions {
      public static final byte LEFT = 0;
      public static final byte UP = 1;
      public static final byte RIGHT = 2;
      public static final byte DOWN = 3;
    }

    public static final byte IDLE = 0;
    public static final byte RUNNING = 1;
    public static final byte JUMPING = 2;
    public static final byte FALLING = 3;
    public static final byte LANDING = 4;
    public static final byte HIT = 5;
    public static final byte ATTACK_1 = 6;
    public static final byte ATTACK_JUMP_1 = 7;
    public static final byte ATTACK_JUMP_2 = 8;

    public static int GetSpriteAmount(int player_action) {
      return switch (player_action) {
        case RUNNING -> 6;
        case IDLE -> 5;
        case HIT -> 4;
        case JUMPING, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> 3;
        case LANDING -> 2;
        default -> 1;
      };
    }
  }
}
