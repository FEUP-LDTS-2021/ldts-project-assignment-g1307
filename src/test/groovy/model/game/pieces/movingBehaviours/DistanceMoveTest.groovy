package model.game.pieces.movingBehaviours

import model.game.Position

class DistanceMoveTest<T extends MovingBehaviour> {
    T behaviour
    int minDistance
    DistanceMoveTest(T behaviour, int minDistance){
        this.behaviour = behaviour
        this.minDistance = minDistance
    }

    static def distance(Position curObjPos, Position targetPos ) {
        return Math.max(Math.abs(targetPos.getRow() - curObjPos.getRow()), Math.abs(targetPos.getCol() - curObjPos.getCol()))
    }
    def getMoves(board, position){
        def lMoves = behaviour.getMoves(board, position)

        for (def l : lMoves) {
            if (distance(position,l) != minDistance) // TODO : THE DISTANCE SHOULD BE PASSED AS A MEMBER ATTRIBUTE
                return false
        }
        return true
    }
}
