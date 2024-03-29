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

/**
 * King class needs to be
 * FIRST_COLUMN EXCEPTION
 * EIGHTH_COLUMN EXCEPTION
 */
public class King extends Piece {

  private static final int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

  public King(final Alliance pieceAlliance, final int piecePosition) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {

    final List<Move> legalMoves = new ArrayList<>();
    for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

      final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

      if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { // valid Tile일 때
        // 예외처리
        if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
          continue;
        }

        final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
        // 타일이 점유되어 있지 않을 때
        if (!candidateDestinationTile.isTileOccupied()) {
          legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
        } else { // 타일이 점유되어 있을 때
          final Piece pieceAtDestination = candidateDestinationTile.getPiece();
          final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
          if (this.pieceAlliance != pieceAlliance) {
            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination ));
          }
        }
      }

    }
    return ImmutableList.copyOf(legalMoves);
  }

  @Override
  public String toString() {
    return PieceType.KING.toString();
  }

  private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
  }

  private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7  || candidateOffset == 1 || candidateOffset == 9);
  }

}
