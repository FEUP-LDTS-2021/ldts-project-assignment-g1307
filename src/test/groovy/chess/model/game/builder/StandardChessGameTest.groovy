package chess.model.game.builder

import chess.model.game.pieces.Bishop
import chess.model.game.pieces.King
import chess.model.game.pieces.Knight
import chess.model.game.pieces.Pawn
import chess.model.game.pieces.Piece
import chess.model.game.pieces.Queen
import chess.model.game.pieces.Rook
import spock.lang.Specification

class StandardChessGameTest extends Specification {
    def standardGame = new StandardChessGame()

    def "Reset"() {
        when:
        standardGame.buildPieces()
        standardGame.buildRules();

        def result = standardGame.reset()
        then:
        standardGame.piecesArrangementBlack.isEmpty()
        standardGame.piecesArrangementWhite.isEmpty()
        result != null
        !standardGame.getResults().getPiecesInGame()
        !standardGame.getResults().getRules()
    }

    def "BuildPawns"() {
        when:
        standardGame.buildPawns()
        def r = standardGame.getResults()
        then: 'It Should return 16 pawns , 8 black and 8 white'
        r.getPiecesInGame().size() == 16

        def w = 0
        def b = 0
        for (def e : r.getPiecesInGame()) {
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
        r.getPiecesInGame().size() == 2

        def w = 0
        def b = 0
        for (def e :  r.getPiecesInGame()) {
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
        r.getPiecesInGame().size() == 2

        def w = 0
        def b = 0
        for (def e :  r.getPiecesInGame()) {
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
        r.getPiecesInGame().size() == 4

        def w = 0
        def b = 0
        for (def e :  r.getPiecesInGame()) {
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
        r.getPiecesInGame().size() == 4

        def w = 0
        def b = 0
        for (def e :  r.getPiecesInGame()) {
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
        r.getPiecesInGame().size() == 4

        def w = 0
        def b = 0
        for (def e :  r.getPiecesInGame()) {
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
        def result = standardGame.buildPieces()
        def r = standardGame.getResults()
        then:
        r.getPiecesInGame().size() == 32
        def p = 0
        def q = 0
        def b = 0
        def k = 0
        def rook = 0
        def king = 0
        for (def e :  r.getPiecesInGame()) {
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
        result != null
    }

    def "BuildRules"() {
        given:
        GameBuilder gameBuilder = new StandardChessGame()

        def model = gameBuilder.buildRules().getResults()

        when:
        def r = model.getRules().size()
        then:
        r == 10

    }

    def "Build cursor, square board and game players"(){
        when:
        def cursor = standardGame.getResults().getCursor()
        def squareBoard = standardGame.getResults().getBoardModel()
        def players = standardGame.getResults().getGamePlayers()
        then:
        cursor != null
        squareBoard != null
        players.size() == 2
    }
}
