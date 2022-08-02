package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {

  final Board board; // 이동한 보드(체스판) 추적
  final Piece movedPiece; // 기물의 이동 추적
  final int destinationCoordinate; // 이동한 위치 추적

  private Move(final Board board,
      final Piece movedPiece,
      final int destinationCoordinate) {
    this.board = board;
    this.movedPiece = movedPiece;
    this.destinationCoordinate = destinationCoordinate;
  }
  // MajorMove.
  public static final class MajorMove extends Move {

    public MajorMove(final Board board,
              final Piece movedPiece,
              final int destinationCoordinate) {
      super(board, movedPiece, destinationCoordinate);
    }
  }
  // AttackMove : 공격 시 이동
  public static final class AttackMove extends Move {
    final Piece attackedPiece;
    public AttackMove(final Board board,
               final Piece movedPiece,
               final int destinationCoordinate,
               final Piece attackedPiece) {
      super(board, movedPiece, destinationCoordinate);
      this.attackedPiece = attackedPiece;
    }
  }




}
