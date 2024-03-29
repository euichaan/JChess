package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

  protected final int tileCoordinate; // 타일 좌표
  // protected : subclass 에서만 access
  // final : 멤버 필드가 생성자로 인해 생성되면 다시 설정할 수 없다.

  // 유효한 빈 타일을 새로 생성하고 싶을 때 마다 새로 생성하는 대신 하나를 검색. 캐시에서 조회
  private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

  private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

    final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();

    for (int i = 0; i < BoardUtils.NUM_TITLES; i++) {
      emptyTileMap.put(i,new EmptyTile(i));
    }
    return ImmutableMap.copyOf(emptyTileMap);
  }

  // Tile 을 생성하는 유일한 방법.
  // piece 가 null 이라면 캐시된 빈 타일 중 하나를 얻게 된다.
  public static Tile createTile(final int tileCoordinate,final Piece piece) {
    return piece !=null ? new OccupiedTile(tileCoordinate,piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
  }

  private Tile(final int tileCoordinate) {
    this.tileCoordinate = tileCoordinate;
  }

  public abstract boolean isTileOccupied(); // 주어진 타일이 사용 중인지 여부
  public abstract Piece getPiece();

  public static final class EmptyTile extends Tile {

    private EmptyTile(final int tileCoordinate) {
      super(tileCoordinate);
    }

    @Override
    public String toString() {
      return "-";
    }

    @Override
    public boolean isTileOccupied() {
      return false;
    }

    @Override
    public Piece getPiece() {
      return null;
    }
  }
  public static final class OccupiedTile extends Tile {
    private final Piece pieceOnTile;

    private OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
      super(tileCoordinate);
      this.pieceOnTile = pieceOnTile;
    }

    @Override
    public String toString() {
      return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
          getPiece().toString();
    }

    @Override
    public boolean isTileOccupied() {
      return true;
    }
    @Override
    public Piece getPiece() {
      return this.pieceOnTile;
    }
  }


}