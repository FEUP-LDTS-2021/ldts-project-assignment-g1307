package model.game;

import model.game.pieces.Pawn;
import model.game.pieces.Piece;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.HashSet;
import java.util.Set;

public class StandardChessGame implements GameBuilder {

    private Set<Piece> piecesArrangementWhite;
    private Set<Piece> piecesArrangementBlack;

    public StandardChessGame(){
        piecesArrangementWhite = new HashSet<>();
        piecesArrangementBlack = new HashSet<>();
    }

    @Override
    public void reset() {

    }

    @Override
    public void buildPawns() {

    }

    @Override
    public void buildKings() {

    }

    @Override
    public void buildQueens() {

    }

    @Override
    public void buildRooks() {

    }

    @Override
    public void buildKnights() {

    }

    @Override
    public void buildBishops() {

    }

    @Override
    public void buildPieces() {

    }

    @Override
    public void buildRules() {

    }

    @Override
    public Set<Piece> getResults() {
        return null;
    }
}
