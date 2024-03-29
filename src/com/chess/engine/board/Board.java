package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Board {

  private final List<Tile> gameBoard;
  private final Collection<Piece> whitePieces;
  private final Collection<Piece> blackPieces;

  private Board(Builder builder) {
    this.gameBoard = createGameBoard(builder);
    this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
    this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);

    final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
    final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
    
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < BoardUtils.NUM_TITLES; i++) { // 0 ~ 63
      final String tileText = this.gameBoard.get(i).toString();
      builder.append(String.format("%3s", tileText));
      if ((i + 1) % BoardUtils.NUM_TITLES_PER_ROW == 0) {
        builder.append("\n");
      }
    }
    return builder.toString();
  }

  private static String prettyPrint(Tile tile) {
    return tile.toString();
  }

  private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {

    final List<Move> legalMoves = new ArrayList<>();
    for (final Piece piece : pieces) {
        legalMoves.addAll(piece.calculateLegalMoves(this));
    }
    return ImmutableList.copyOf(legalMoves);
  }

  private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {

    final List<Piece> activePieces = new ArrayList<>();
    for (final Tile tile : gameBoard) {
      if (tile.isTileOccupied()) {
        final Piece piece = tile.getPiece();
        if (piece.getPieceAlliance() == alliance ) {
          activePieces.add(piece);
        }
      }
    }
    return ImmutableList.copyOf(activePieces);
  }

  public Tile getTile(final int tileCoordinate) {

    return gameBoard.get(tileCoordinate);
  }

  private static List<Tile> createGameBoard(final Builder builder) {
    final Tile[] tiles = new Tile[BoardUtils.NUM_TITLES];
    for (int i = 0; i < BoardUtils.NUM_TITLES; i++) {
      tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
    }
    return ImmutableList.copyOf(tiles);
  }

  public static Board createStandardBoard() {
    //Todo : 체스 판의 초기 판 만들기
    final Builder builder = new Builder();
    // Black Layout
    builder.setPiece(new Rook(Alliance.BLACK, 0));
    builder.setPiece(new Knight(Alliance.BLACK, 1));
    builder.setPiece(new Bishop(Alliance.BLACK, 2));
    builder.setPiece(new Queen(Alliance.BLACK, 3));
    builder.setPiece(new King(Alliance.BLACK, 4));
    builder.setPiece(new Bishop(Alliance.BLACK, 5));
    builder.setPiece(new Knight(Alliance.BLACK, 6));
    builder.setPiece(new Rook(Alliance.BLACK, 7));
    builder.setPiece(new Pawn(Alliance.BLACK, 8));
    builder.setPiece(new Pawn(Alliance.BLACK, 9));
    builder.setPiece(new Pawn(Alliance.BLACK, 10));
    builder.setPiece(new Pawn(Alliance.BLACK, 11));
    builder.setPiece(new Pawn(Alliance.BLACK, 12));
    builder.setPiece(new Pawn(Alliance.BLACK, 13));
    builder.setPiece(new Pawn(Alliance.BLACK, 14));
    builder.setPiece(new Pawn(Alliance.BLACK, 15));
    // White Layout
    builder.setPiece(new Pawn(Alliance.WHITE, 48));
    builder.setPiece(new Pawn(Alliance.WHITE, 49));
    builder.setPiece(new Pawn(Alliance.WHITE, 50));
    builder.setPiece(new Pawn(Alliance.WHITE, 51));
    builder.setPiece(new Pawn(Alliance.WHITE, 52));
    builder.setPiece(new Pawn(Alliance.WHITE, 53));
    builder.setPiece(new Pawn(Alliance.WHITE, 54));
    builder.setPiece(new Pawn(Alliance.WHITE, 55));
    builder.setPiece(new Rook(Alliance.WHITE, 56));
    builder.setPiece(new Knight(Alliance.WHITE, 57));
    builder.setPiece(new Bishop(Alliance.WHITE, 58));
    builder.setPiece(new Queen(Alliance.WHITE, 59));
    builder.setPiece(new King(Alliance.WHITE, 60));
    builder.setPiece(new Bishop(Alliance.WHITE, 61));
    builder.setPiece(new Knight(Alliance.WHITE, 62));
    builder.setPiece(new Rook(Alliance.WHITE, 63));

    // 하얀색 부터 시작 
    builder.setMoveMaker(Alliance.WHITE);

    return builder.build();
  }

  //== Builder 패턴 ==//
  public static class Builder {

    Map<Integer, Piece> boardConfig; //tile ID, given Piece that tile ID
    Alliance nextMoveMaker;

    public Builder() {
      this.boardConfig = new HashMap<>();
    }
    //== setter methods==//
    public Builder setPiece(final Piece piece) {
      this.boardConfig.put(piece.getPiecePosition(), piece);
      return this;
    }

    public Builder setMoveMaker(final Alliance nextMoveMaker) {
      this.nextMoveMaker = nextMoveMaker;
      return this;
    }

    public Board build() {
      return new Board(this);
    }
  }
}
