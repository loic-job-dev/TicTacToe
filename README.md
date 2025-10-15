# TicTacToe

School project in Java

---

## Description
This projects aims to create a TicTacToe game, playable in console.

---

## Class diagram

```mermaid
classDiagram
    class Main {
        +main()
    }
    Main --> Game

    class Game {
        +Board board
        +Player player1
        +Player player2
        +launchGame()
        +Game()
    }
    Game --> Board
    Game --> Player

    class Board {
        -int size
        -Tile[] tiles
        +create()
    }
    Board --> Tile

    class Tile {
        -int coordinateX
        -int coordinateY
        +hasPawn()
    }

    class Player {
        -String name
        -Pawn pawn
        +choosePawn()
    }
    Player --> Pawn

    class Pawn {
        -String color
        -String pattern
    }
