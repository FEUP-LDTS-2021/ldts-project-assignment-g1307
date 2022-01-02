package model.game.builder;

import model.game.Position;
import model.game.board.SquareBoard;
import model.game.pieces.*;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.HashSet;
import java.util.Set;

public class StandardChessGame implements GameBuilder {

    private Set<Piece> piecesArrangementWhite;
    private Set<Piece> piecesArrangementBlack;
    private final SquareBoard squareBoard;

    public StandardChessGame(){
        piecesArrangementWhite = new HashSet<>();
        piecesArrangementBlack = new HashSet<>();
        squareBoard = new SquareBoard(8);
    }

    @Override
    public void reset() {
        piecesArrangementWhite.clear();
        piecesArrangementBlack.clear();
    }

    public void buildPawns() {
        for (int i = 1; i <= squareBoard.getColumns(); i++){
            piecesArrangementWhite.add(new Pawn(Piece.COLOR.White, new Position(7, i),
                    new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)));
            piecesArrangementBlack.add(new Pawn(Piece.COLOR.BLACK, new Position(2, i),
                    new TwoAndOneStrategy(TwoAndOneStrategy.Direction.SOUTH)));
        }
    }

    public void buildKings() {
        piecesArrangementWhite.add(new King(Piece.COLOR.White, new Position(8, 5)));
        piecesArrangementBlack.add(new King(Piece.COLOR.BLACK, new Position(1, 5)));
    }

    public void buildQueens() {
        piecesArrangementWhite.add(new Queen(Piece.COLOR.White, new Position(8, 4)));
        piecesArrangementBlack.add(new Queen(Piece.COLOR.BLACK, new Position(1, 4)));
    }

    public void buildRooks() {
        piecesArrangementWhite.add(new Rook(Piece.COLOR.White, new Position(8, 1)));
        piecesArrangementWhite.add(new Rook(Piece.COLOR.White, new Position(8, 8)));
        piecesArrangementBlack.add(new Rook(Piece.COLOR.BLACK, new Position(1, 1)));
        piecesArrangementBlack.add(new Rook(Piece.COLOR.BLACK, new Position(1, 8)));
    }

    public void buildKnights() {
        piecesArrangementWhite.add(new Knight(Piece.COLOR.White, new Position(8, 2)));
        piecesArrangementWhite.add(new Knight(Piece.COLOR.White, new Position(8, 7)));
        piecesArrangementBlack.add(new Knight(Piece.COLOR.BLACK, new Position(1, 2)));
        piecesArrangementBlack.add(new Knight(Piece.COLOR.BLACK, new Position(1, 7)));
    }

    public void buildBishops() {
        piecesArrangementWhite.add(new Bishop(Piece.COLOR.White, new Position(8, 3)));
        piecesArrangementWhite.add(new Bishop(Piece.COLOR.White, new Position(8, 6)));
        piecesArrangementBlack.add(new Bishop(Piece.COLOR.BLACK, new Position(1, 3)));
        piecesArrangementBlack.add(new Bishop(Piece.COLOR.BLACK, new Position(1, 6)));
    }

    @Override
    public void buildPieces() {
        buildPawns();
        buildKings();
        buildQueens();
        buildRooks();
        buildKnights();
        buildBishops();
    }

    @Override
    public void buildRules() {

    }

    public Set<Piece> getPiecesArrangementBlack() {
        return piecesArrangementBlack;
    }

    public Set<Piece> getPiecesArrangementWhite() {
        return piecesArrangementWhite;
    }

    public SquareBoard getSquareBoard() { return squareBoard; }

    @Override
    public Set<Piece> getResults() {
        Set<Piece> res = new HashSet<>();

        res.addAll(piecesArrangementBlack);
        res.addAll(piecesArrangementWhite);

        return res;
    }
}
