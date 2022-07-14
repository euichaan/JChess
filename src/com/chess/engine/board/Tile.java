package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

  protected final int tileCoordinate; // 타일 좌표
  // protected : subclass 에서만 access
  // final : 멤버 필드가 생성자로 인해 생성되면 다시 설정할 수 없다.

  private static final Map<Integer,EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

  private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

    final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();

    for (int i=0; i<64; i++) {
      emptyTileMap.put(i,new EmptyTile(i));
    }
    return ImmutableMap.copyOf(emptyTileMap);
  }

  public static Tile createTile(final int tileCoordinate,final Piece piece) {
    return piece !=null ? new OccupiedTile(tileCoordinate,piece) : EMPTY_TILES.get(tileCoordinate);
  }

  private Tile(int tileCoordinate) {
    this.tileCoordinate = tileCoordinate;
  }

  public abstract boolean isTileOccupied(); // 주어진 타일이 사용 중인지 여부
  public abstract Piece getPiece();

  public static final class EmptyTile extends Tile {

    public EmptyTile(final int tileCoordinate) {
      super(tileCoordinate);
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

    public OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
      super(tileCoordinate);
      this.pieceOnTile = pieceOnTile;
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