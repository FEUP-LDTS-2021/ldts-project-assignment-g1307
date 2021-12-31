package model.game.pieces;


import model.game.board.BoardModel;
import model.game.Position;
import model.game.pieces.movingBehaviours.MovingBehaviour;

import java.util.Set;

public abstract class Piece {

    COLOR color;
    char figure;
    Position position;
    MovingBehaviour movingBehaviour;

    public Piece(COLOR color, char figure, Position position, MovingBehaviour movingBehaviour) {
        this.color = color;
        this.figure = figure;
        this.position = position;
        this.movingBehaviour = movingBehaviour;
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

    public Set<Position> getMoves(BoardModel boardModel) {return movingBehaviour.getMoves(boardModel,position);}

    public Position getPosition() {
        return position;
    }

    public void moveToPosition(Position position) {
        this.position = position;
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
}
