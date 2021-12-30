package model.game

import model.game.pieces.Bishop
import model.game.pieces.King
import model.game.pieces.Knight
import model.game.pieces.Pawn
import model.game.pieces.Piece
import model.game.pieces.Queen
import model.game.pieces.Rook
import spock.lang.Specification

class StandardChessGameTest extends Specification {
    def standardGame = new StandardChessGame()

    def "Reset"() {
        when:
        standardGame.buildPieces()
        standardGame.reset()
        then:
        standardGame.getResults().size() == 0
    }

    def "BuildPawns"() {
        when:
        standardGame.buildPawns()
        def r = standardGame.getResults()
        then: 'It Should return 16 pawns , 8 black and 8 white'
        r.size() == 16

        def w = 0
        def b = 0
        for (def e : r) {
            if (e instanceof Pawn){
                if (e.getColor() == Piece.COLOR.BLACK)
                    b++
                else if(e.getColor() == Piece.COLOR.White)
                    w++
            }
        }
        b == 8
        w == 8
    }

    def "BuildPieces"() {
        when:
        standardGame.buildPieces()
        def r = standardGame.getResults()
        then:
        r.size() == 16
        def q = 0
        def b = 0
        def k = 0
        def rook = 0
        def king = 0
        for (def e : r) {
            if (e instanceof Bishop){
                b++
            }else if(e instanceof Knight) {
                k++
            }else if(e instanceof Queen){
                q++
            }else if(e instanceof Rook) {
                rook++
            } else if(e instanceof King) {
                king++
            }
        }
        q    == 2
        b    == 4
        k    == 4
        rook == 4
        king == 2
    }

    def "BuildRules"() {

    }
}
