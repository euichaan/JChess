package com.chess.engine.pieces;


import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import java.util.List;

public abstract class Piece {

  protected final int piecePosition; // 위치
  protected final Alliance pieceAlliance; // 동맹

  // Constructor
  public Piece(final int piecePosition,final Alliance pieceAlliance) {
    this.piecePosition = piecePosition;
    this.pieceAlliance = pieceAlliance;
  }

  // 기물의 이동을 구현할 method
  // 움직임의 list(모음)을 반환
  public abstract List<Move> calculateLegalMoves(final Board board);

}
