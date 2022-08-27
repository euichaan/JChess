package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Pawn class
 */
public class Pawn extends Piece {

  private static final int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

  public Pawn(final Alliance pieceAlliance, final int piecePosition) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {

    final List<Move> legalMoves = new ArrayList<>();

    for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

      final int candidateDestinationCoordinate =
          this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset);

      if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
        continue;
      }

      if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate)
          .isTileOccupied()) {
        //todo more work (deal with promotions) + AttackMoves
        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
      } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
          (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
          (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
        // 조건 : 두 칸 이동시 첫 번째 이동이어야 함.
        // 두번째 행 -> 검은색 기물, 일곱번째 행 -> 하얀색 기물
        final int behindCandidateDestinationCoordinate =
            this.piecePosition + (this.pieceAlliance.getDirection() * 8);
        if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
            !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
          legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
        }
      } else if (currentCandidateOffset == 7 && // AttackMove 계산
            !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
              (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())) )) {
          if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
            final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
            if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
              // TODO more to do here (Promotion)
              legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }
          }
      } else if (currentCandidateOffset == 9 && // AttackMove 계산
            !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
              (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())) )) {
        if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
          final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
          if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
            // TODO more to do here (Promotion)
            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }
}
