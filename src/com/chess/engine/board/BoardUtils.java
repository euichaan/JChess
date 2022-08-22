package com.chess.engine.board;

public class BoardUtils {

  public static final boolean[] FIRST_COLUMN = initColumn(0);
  public static final boolean[] SECOND_COLUMN = initColumn(1);
  public static final boolean[] SEVENTH_COLUMN = initColumn(6);
  public static final boolean[] EIGHTH_COLUMN = initColumn(7); // zero Offset

  public static final boolean[] SECOND_ROW = null; // 검은색 Pawn 의 시작 행
  public static final boolean[] SEVENTH_ROW = null; // 흰색 Pawn 의 시작 행

  public static final int NUM_TITLES = 64;
  public static final int NUM_TITLES_PER_ROW = 8;


  private BoardUtils() { // 인스턴스화를 시도하는 경우에 대비해 RuntimeException 을 던짐
    throw new RuntimeException("You cannot instantiate me!");
  }

  // 각 열을 모두 true 로 만들기.
  // knight 예외 조건에서 현재 위치를 가지고 배열이 true 라면, 해당 줄에 있다는 것을 알 수 있다.
  private static boolean[] initColumn(int columnNumber) {
    final boolean[] column = new boolean[NUM_TITLES];

    do {
      column[columnNumber] = true;
      columnNumber += NUM_TITLES_PER_ROW;
    } while(columnNumber < NUM_TITLES);

    return column;
  }
  public static boolean isValidTileCoordinate(int coordinate) {
    return coordinate >= 0 && coordinate < NUM_TITLES;
  }
}
