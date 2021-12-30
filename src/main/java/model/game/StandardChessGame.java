package model.game;

import model.game.pieces.Pawn;
import model.game.pieces.Piece;
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
        squareBoard = new SquareBoard(8,8);
    }

    @Override
    public void reset() {

    }

    public void buildPawns() {

    }

    public void buildKings() {

    }

    public void buildQueens() {

    }

    public void buildRooks() {

    }

    public void buildKnights() {

    }

    public void buildBishops() {

    }

    @Override
    public void buildPieces() {

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

    public SquareBoard getSquareBoard() {
        return squareBoard;
    }

    @Override
    public Set<Piece> getResults() {
        return null;
    }
}
