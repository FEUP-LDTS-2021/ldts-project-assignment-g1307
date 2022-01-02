package model.game.builder

import model.game.builder.StandardChessGame
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

    def "BuildKings"() {
        when:
        standardGame.buildKings()
        def r = standardGame.getResults()
        then: 'It Should return 2 kings , 1 black and 1 white'
        r.size() == 2

        def w = 0
        def b = 0
        for (def e : r) {
            if (e instanceof King){
                if (e.getColor() == Piece.COLOR.BLACK)
                    b++
                else if(e.getColor() == Piece.COLOR.White)
                    w++
            }
        }
        b == 1
        w == 1
    }

    def "BuildQueens"() {
        when:
        standardGame.buildQueens()
        def r = standardGame.getResults()
        then: 'It Should return 2 queens , 1 black and 1 white'
        r.size() == 2

        def w = 0
        def b = 0
        for (def e : r) {
            if (e instanceof Queen) {
                if (e.getColor() == Piece.COLOR.BLACK)
                    b++
                else if (e.getColor() == Piece.COLOR.White)
                    w++
            }
        }
        b == 1
        w == 1
    }

    def "BuildRooks"() {
        when:
        standardGame.buildRooks()
        def r = standardGame.getResults()
        then: 'It Should return 4 rooks , 2 black and 2 white'
        r.size() == 4

        def w = 0
        def b = 0
        for (def e : r) {
            if (e instanceof Rook) {
                if (e.getColor() == Piece.COLOR.BLACK)
                    b++
                else if (e.getColor() == Piece.COLOR.White)
                    w++
            }
        }
        b == 2
        w == 2
    }

    def "BuildKnights"() {
        when:
        standardGame.buildKnights()
        def r = standardGame.getResults()
        then: 'It Should return 4 knights , 2 black and 2 white'
        r.size() == 4

        def w = 0
        def b = 0
        for (def e : r) {
            if (e instanceof Knight) {
                if (e.getColor() == Piece.COLOR.BLACK)
                    b++
                else if (e.getColor() == Piece.COLOR.White)
                    w++
            }
        }
        b == 2
        w == 2
    }

    def "BuildBishops"() {
        when:
        standardGame.buildBishops()
        def r = standardGame.getResults()
        then: 'It Should return 4 bishops, 2 black and 2 white'
        r.size() == 4

        def w = 0
        def b = 0
        for (def e : r) {
            if (e instanceof Bishop) {
                if (e.getColor() == Piece.COLOR.BLACK)
                    b++
                else if (e.getColor() == Piece.COLOR.White)
                    w++
            }
        }
        b == 2
        w == 2
    }

    def "BuildPieces"() {
        when:
        standardGame.buildPieces()
        def r = standardGame.getResults()
        then:
        r.size() == 32
        def p = 0
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
            } else if (e instanceof Pawn) {
                p++
            }
        }
        p    == 16
        q    == 2
        b    == 4
        k    == 4
        rook == 4
        king == 2
    }

    def "BuildRules"() {

    }
}
