package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

  private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8 };

  public Rook(final Alliance pieceAlliance, final int piecePosition) {
    super(piecePosition, pieceAlliance);

  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {

    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) { // VECTOR OFFSET
      int candidateDestinationCoordinate = this.piecePosition; // 현재 비숍 위치에서 시작

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
            isEightColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)) {
          break;
        }

        candidateDestinationCoordinate += candidateCoordinateOffset;

        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
          // 타일이 점유되어 있지 않을 때
          if (!candidateDestinationTile.isTileOccupied()) {
            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate)); // 새로운 이동(Major moves) 추가. 다음 이동으로
          } else { // 타일이 점유되어 있을 때
            final Piece pieceAtDestination = candidateDestinationTile.getPiece(); // 해당 위치에서 기물을 가져옴
            final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance(); // 해당 기물의 좌표가 흰색인지 검은색인지. 동맹 여부 파악

            // 현재 말의 색과 이동하려는 기물의 색이 다르다면 -> 적 (enemy)
            if (this.pieceAlliance != pieceAlliance) {
              legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination)); // 공격 이동 추가
            }
            break; // 적을 처치할 때만 break.
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }

  @Override
  public String toString() {
    return PieceType.ROOK.toString();
  }

  private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
  }

  private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == +1);
  }
}
