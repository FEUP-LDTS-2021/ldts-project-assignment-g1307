package model.game.pieces;


import model.game.board.BoardModel;
import model.game.Position;
import model.game.move.Move;
import model.game.move.SimpleMove;
import model.game.pieces.movingBehaviours.MovingBehaviour;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {
    boolean hasMoved;
    COLOR color;
    char figure;
    Position position;
    MovingBehaviour movingBehaviour;

    public Piece(COLOR color, char figure, Position position, MovingBehaviour movingBehaviour) {
        this.color = color;
        this.figure = figure;
        this.position = position;
        this.movingBehaviour = movingBehaviour;
        hasMoved = false;
    }

    public enum COLOR { // To ask ... does This counts as an OCP Violation?
        BLACK(0x00, 0x00, 0x00),
        White(0xFF, 0xFF, 0xFF),
        BLUE(0x00, 0x00, 0xFF),
        Red(0x00, 0x00, 0xFF),
        Green(0x00, 0x00, 0xFF);

        private final int red;
        private final int green;
        private final int blue;

        COLOR(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        @Override
        public String toString() {
            return String.format("#%02X%02X%02X", red, green, blue);
        }
    }

    public Set<Position> getMovesPositions(BoardModel boardModel) {return movingBehaviour.getMoves(boardModel,position);}

    public Set<Move> getMoves(BoardModel boardModel) {
        Set<Position> positions = movingBehaviour.getMoves(boardModel,position);
        Set<Move> moves = new HashSet<>();

        for (Position position: positions) {
            moves.add(new SimpleMove(this,position));
        }

        return moves;
    }

    public Position getPosition() {
        return position;
    }

    public void moveToPosition(Position position) {
        this.position = position;
        hasMoved = true;
    } // this allows the piece to be moved to any position

    public MovingBehaviour getMovingBehaviour() {
        return movingBehaviour;
    }

    public COLOR getColor() {
        return color;
    }

    public char getFigure() {
        return figure;
    }

    public boolean isMoved() {
        return hasMoved;
    }

}
