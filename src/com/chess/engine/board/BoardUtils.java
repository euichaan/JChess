package com.chess.engine.board;

public class BoardUtils {

  public static final boolean[] FIRST_COLUMN = null;
  public static final boolean[] SECOND_COLUMN = null;
  public static final boolean[] SEVENTH_COLUMN = null;
  public static final boolean[] EIGHTH_COLUMN = null;


  private BoardUtils() { // 인스턴스화를 시도하는 경우에 대비해 RuntimeException 을 던짐
    throw new RuntimeException("You cannot instantiate me!");
  }

  public static boolean isVaildTileCoordinate(int coordinate) {
    return coordinate >= 0 && coordinate < 64;
  }
}
