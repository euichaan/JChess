package com.chess.engine.pieces;


import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import java.util.Collection;

public abstract class Piece {

  protected final int piecePosition; // 위치
  protected final Alliance pieceAlliance; // 흰색, 검은색 중 하나
  protected final boolean isFirstMove;

  public int getPiecePosition() {
    return this.piecePosition;
  }

  public Alliance getPieceAlliance() {
    return this.pieceAlliance;
  }

  // Constructor
  public Piece(final int piecePosition,final Alliance pieceAlliance) {
    this.piecePosition = piecePosition;
    this.pieceAlliance = pieceAlliance;
    // TODO more work here
    this.isFirstMove = false;
  }
  public boolean isFirstMove() {
    return this.isFirstMove;
  }

  // 기물의 이동을 구현할 method
  // 움직임의 list(모음)을 반환
  public abstract Collection<Move> calculateLegalMoves(final Board board);

}
