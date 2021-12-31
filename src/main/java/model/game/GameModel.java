package model.game;

import model.Model;
import model.game.board.BoardModel;
import model.game.pieces.Piece;
import model.game.rules.Rule;

import java.util.Set;

public class GameModel implements Model { // at a first glance it seems that the class will become to big ---> keep track of this class
    //ClockModel as a subscriber -> an array of subscribers actually, it will inform which clock should be counting
    // A Game end detector
    private Set<Piece> piecesInGame;
    private Set<Piece.COLOR> colorsInOrder;
    private Set<Rule> rules;
    private BoardModel boardModel;
    public GameModel(){}

    public Set<Piece> getPiecesInGame() {
        return piecesInGame;
    }

    public void setPiecesInGame(Set<Piece> piecesInGame) {
        this.piecesInGame = piecesInGame;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }
}
