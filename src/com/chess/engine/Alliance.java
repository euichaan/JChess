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
  },
  BLACK {
    @Override
    public int getDirection() {
      return 1;
    }
  };

  public abstract int getDirection();
}
