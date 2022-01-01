package model.game

import spock.lang.Specification

class SquareBoardSquaresTest extends Specification{
    List<Position> comparison = []

    def "Test position of white squares"(){
        BoardModel boardModel = new SquareBoard(8,8)
        Set<Position> whiteSquares = boardModel.getWhiteSquares()

        expect:
            for(Position position : comparison){
                true == whiteSquares.contains(position)
            }
    }

    def setup(){
        for(int row = 1; row <= 7; row = row+2){
            for(int col = 1; col <= 7; col = col+2){
                comparison.add(new Position(row, col))
            }
        }
        for(int row = 2; row <= 8; row = row+2){
            for(int col = 2; col <= 8; col = col+2){
                comparison.add(new Position(row, col))
            }
        }
        return comparison
    }
}
