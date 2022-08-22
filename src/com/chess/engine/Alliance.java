package com.chess.engine;

public enum Alliance {
  // 흰색은 -1 의 방향, 검은색은 +1의 방향
  /**
   * -------------
   * 검은색 진영
   *
   * 흰색 진영
   * -------------
   */
  WHITE {
    @Override
    public int getDirection() {
      return -1;
    }

    @Override
    public boolean isWhite() {
      return true;
    }

    @Override
    public boolean isBlack() {
      return false;
    }
  },
  BLACK {
    @Override
    public int getDirection() {
      return 1;
    }

    @Override
    public boolean isWhite() {
      return false;
    }

    @Override
    public boolean isBlack() {
      return true;
    }
  };

  public abstract int getDirection();
  public abstract boolean isWhite();
  public abstract boolean isBlack();
}
