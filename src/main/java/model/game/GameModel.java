package model.game;

import model.Model;
import model.game.board.BoardModel;
import model.game.pieces.Piece;
import model.game.player.Player;
import model.game.rules.Rule;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameModel implements Model { // at a first glance it seems that the class will become to big ---> keep track of this class
    private Set<Piece> piecesInGame;
    private Player[] gamePlayers;
    private Set<Rule> rules;
    private BoardModel boardModel;
    private GameCursor cursor;
    private int turn = -1;

    public GameModel(){
        rules = new HashSet<>();
        piecesInGame = new HashSet<>();
    }

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

    public void setCursor(GameCursor gameCursor) {
        this.cursor = gameCursor;
    }

    public GameCursor getCursor() {
        return cursor;
    }

    public void setGamePlayers(Player[] gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void notifyPlayers() {
        int i = 0;
        turn = ( turn + 1 ) % gamePlayers.length;
        for (Player player: gamePlayers){
            player.setTurn(turn == i);
        }
    }
}
