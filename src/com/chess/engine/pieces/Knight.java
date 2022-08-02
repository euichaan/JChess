package com.chess.engine.pieces;

import static com.chess.engine.board.Move.*;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Knight
// Piece의 Concrete class
public class Knight extends Piece {

  private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15,
      17}; // 현재 위치에서 Knight 가 이동 가능한 지점의 오프셋

  public Knight(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {

    final List<Move> legalMoves = new ArrayList<>();

    // 모든 이동 가능한 위치 순회
    for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {

      final int candidateDestinationCoordinate = this.piecePosition
          + currentCandidateOffset; // 나이트 이동 가능한 후보군 좌표 = 지금 나이트의 위치 + 이동 가능한 8가지 위치 중 하나

      if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) { // 첫 번째 줄 오류 제외
          continue;
        }

        final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
        // 타일이 점유되어 있지 않을 때
        if (!candidateDestinationTile.isTileOccupied()) {
          legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate )); // 새로운 이동(Major moves) 추가
        } else { // 타일이 점유되어 있을 때
          final Piece pieceAtDestination = candidateDestinationTile.getPiece(); // 해당 위치에서 기물을 가져옴
          final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance(); // 해당 기물의 좌표가 흰색인지 검은색인지. 동맹 여부 파악

          // 현재 말의 색과 이동하려는 기물의 색이 다르다면 -> 적 (enemy)
          if (this.pieceAlliance != pieceAlliance) {
            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination)); // 공격 이동 추가
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }

  // 극단적인 상황의 예외 처리하기
  // 첫 번째 열에 있을 때 현재 위치 + Offset 위치 일 때 가능한 오류 처리
  private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
        candidateOffset == 6 || candidateOffset == 15);
  }

  // 두 번째 열에 있을 때 현재 위치 + Offset 위치 일 때 가능한 오류 처리
  private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
  }

  private static boolean isSeventhColumnExclusion(final int currentPosition,
      final int candidateOffset) {
    return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
  }

  private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 ||
        candidateOffset == 10 || candidateOffset == 17);
  }
}
