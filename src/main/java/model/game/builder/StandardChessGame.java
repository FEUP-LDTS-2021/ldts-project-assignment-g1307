package model.game.builder;

import model.game.GameModel;
import model.game.Position;
import model.game.board.SquareBoard;
import model.game.pieces.*;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;
import model.game.rules.*;

import java.util.HashSet;
import java.util.Set;

public class StandardChessGame implements GameBuilder {

    private final GameModel gameModel;
    private final Set<Piece> piecesArrangementWhite;
    private final Set<Piece> piecesArrangementBlack;
    private final Set<Rule> rules;
    private final SquareBoard squareBoard;
    // to add ... clock or a player that has a clock

    public StandardChessGame(){
        this.gameModel = new GameModel();
        piecesArrangementWhite = new HashSet<>();
        piecesArrangementBlack = new HashSet<>();

        squareBoard = new SquareBoard(8);
        gameModel.setBoardModel(squareBoard);
        rules = new HashSet<>();
    }

    @Override
    public GameBuilder reset() {
        piecesArrangementWhite.clear();
        piecesArrangementBlack.clear();
        rules.clear();
        return this;
    }

    public void buildPawns() {
        for (int i = 1; i <= squareBoard.getColumns(); i++){
            piecesArrangementWhite.add(new Pawn(Piece.COLOR.White, new Position(7, i),
                    new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)));
            piecesArrangementBlack.add(new Pawn(Piece.COLOR.BLACK, new Position(2, i),
                    new TwoAndOneStrategy(TwoAndOneStrategy.Direction.SOUTH)));
        }
        updateModelPieces();

    }

    public void buildKings() {
        piecesArrangementWhite.add(new King(Piece.COLOR.White, new Position(8, 5)));
        piecesArrangementBlack.add(new King(Piece.COLOR.BLACK, new Position(1, 5)));
        updateModelPieces();

    }

    public void buildQueens() {
        piecesArrangementWhite.add(new Queen(Piece.COLOR.White, new Position(8, 4)));
        piecesArrangementBlack.add(new Queen(Piece.COLOR.BLACK, new Position(1, 4)));
        updateModelPieces();

    }

    public void buildRooks() {
        piecesArrangementWhite.add(new Rook(Piece.COLOR.White, new Position(8, 1)));
        piecesArrangementWhite.add(new Rook(Piece.COLOR.White, new Position(8, 8)));
        piecesArrangementBlack.add(new Rook(Piece.COLOR.BLACK, new Position(1, 1)));
        piecesArrangementBlack.add(new Rook(Piece.COLOR.BLACK, new Position(1, 8)));
        updateModelPieces();
    }

    public void buildKnights() {
        piecesArrangementWhite.add(new Knight(Piece.COLOR.White, new Position(8, 2)));
        piecesArrangementWhite.add(new Knight(Piece.COLOR.White, new Position(8, 7)));
        piecesArrangementBlack.add(new Knight(Piece.COLOR.BLACK, new Position(1, 2)));
        piecesArrangementBlack.add(new Knight(Piece.COLOR.BLACK, new Position(1, 7)));
        updateModelPieces();

    }

    public void buildBishops() {
        piecesArrangementWhite.add(new Bishop(Piece.COLOR.White, new Position(8, 3)));
        piecesArrangementWhite.add(new Bishop(Piece.COLOR.White, new Position(8, 6)));
        piecesArrangementBlack.add(new Bishop(Piece.COLOR.BLACK, new Position(1, 3)));
        piecesArrangementBlack.add(new Bishop(Piece.COLOR.BLACK, new Position(1, 6)));
        updateModelPieces();

    }

    @Override
    public GameBuilder buildPieces() {
        buildPawns();
        buildKings();
        buildQueens();
        buildRooks();
        buildKnights();
        buildBishops();
        updateModelPieces();
        return this;
    }

    private void updateModelPieces() {
        piecesArrangementWhite.addAll(piecesArrangementBlack);
        gameModel.setPiecesInGame(piecesArrangementWhite);
    }

    @Override
    public GameBuilder buildRules() {
        try {
            rules.add(new Castle(gameModel));
            rules.add(new EnPassant(gameModel));
            rules.add(new KillPieceOnCapture(gameModel));
            rules.add(new NoStepOverPiece(gameModel));
            rules.add(new NotCapturingSameColor(gameModel));
            rules.add(new PawnsDiagonalCapturing(gameModel));
            rules.add(new PawnsStandardMoveRule(gameModel));
            rules.add(new PromotingPawns(gameModel));
        } catch (NotSupportedBoard e) {
            e.printStackTrace();
        }
        gameModel.setRules(rules);
        return this;
    }


    @Override
    public GameModel getResults() {
        return gameModel;
    }
}
