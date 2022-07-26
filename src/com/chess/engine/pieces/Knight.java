package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;

// Knight
// Piece의 Concrete class
public class Knight extends Piece {

  private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

  public Knight(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public List<Move> calculateLegalMoves(Board board) {

    int candidateDestinationCoordinate; // 나이트가 이동 가능한 후보 위치
    final List<Move> legalMoves = new ArrayList<>();

    // 모든 이동 가능한 위치 순회
    for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {

      candidateDestinationCoordinate = this.piecePosition
          + currentCandidate; // 나이트 이동 가능한 후보군 좌표 = 지금 나이트의 위치 + 이동 가능한 8가지 위치 중 하나

      if (true /*isVaildTileCoordinate -> 타일을 벗어나지 않는다면 */) {
        final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

        // 타일이 점유되어 있지 않을 때
        if (!candidateDestinationTile.isTileOccupied()) {
          legalMoves.add(new Move()); // 새로운 이동 추가
        } else { // 타일이 점유되어 있을
          final Piece pieceAtDestination = candidateDestinationTile.getPiece(); // 해당 위치에서 기물을 가져옴
          final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance(); // 해당 기물의 좌표가 흰색인지 검은색인지. 동맹 여부 파악

          // 현재 말의 색과 이동하려는 기물의 색이 다르다면 -> 적 (enemy)
          if (this.pieceAlliance != pieceAlliance) {
            legalMoves.add(new Move()); // 공격 이동 추가
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }
}
