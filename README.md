# TicTacToe
School project in java, to create a TicTacToe game.

classDiagram
    Main
    Main : +main()
    Main --> Game

    class Game{
    +Board Board
    +Player player1
    +Player player2
    +launchGame()
    +Game()
    }
    Game <|-- Board
    Game <|--Player

    class Board{
        -int size
        -[Tile] tiles
        -create()
    }
    Board <|--Tile

    class Tile{
        -int coordonateX
        -int coordonateY
        -hasPawn()
    }

    class Player{
        -string name
        -Pawn pawn
        -choosePawn()
    }
    Player <|--Pawn

    class Pawn{
        -string color
        -string pattern
    }
