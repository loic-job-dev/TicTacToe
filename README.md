# SquareGames

School project in Java

---

## Description
This project aims to create a TicTacToe game or a Gomoku game, or a Connect4 game, playable in console.

The first menu allows the user to choose the type of game he wants to play, and a second menu allows the user to choose between 3 different game modes :
- human versus human
- human versus computer
- computer versus computer

---

## Prerequisites

To build and run this project, you will need:

- **Java Development Kit (JDK)**: version 17 or higher.
- **Gradle**: the project is compatible with Gradle 8+ and it is recommended to use the **Gradle Wrapper** provided (`./gradlew`).
- **Recommended IDE**: IntelliJ IDEA (or any other Gradle-compatible Java IDE).
- **Internet connection**: required to download dependencies from Maven Central (JUnit 5).

---

### Main Dependencies

- JUnit 5 (`org.junit.jupiter:junit-jupiter`) for unit testing.
- JUnit Platform (`org.junit.platform:junit-platform-launcher`) for test runtime.

---

## Build and Run

To compile and run the project using Gradle:

```bash
# Build the project
./gradlew build

# Run the application
java -cp build/libs/TicTacToe-1.0-SNAPSHOT.jar fr.campus.loic.tictactoe.Main
```

---

## Class diagram

```mermaid
classDiagram
    class Main {
        +main()
    }
    Main --> Game

    class Game {
        -View view
        -InteractionUtilisateur clavier
        -Board board
        -Player player1
        -Player player2
        +play()
        +display()
        +getMoveFromPlayer()
        +setOwner()
        +playerTurn()
        +checkWinnerCondition()
        +chooseGameMode()
    }
    Game --> View
    Game --> InteractionUtilisateur
    Game --> Board
    Game --> Player
    
    class TicTacToe {
    }
    Game <|-- TicTacToe
    
    class Gomoku{
    }
    Game <|-- Gomoku
    
    class Connect4{
    }
    Game <|-- Connect4

    class View {
        +println()
        +print()
    }

    class InteractionUtilisateur {
        +Scanner clavier
        +nextInt()
        +nextLine()
    }

    class Board {
        -int SIZE
        -Tile[][] tiles
        +createBoard()
    }
    Board --> Tile

    class Tile {
        -int coordinateX
        -int coordinateY
        -boolean hasPawn
        -String representation
    }

    class Player {
        -String representation
        -int number
        -String color
    }
    Player <|-- HumanPlayer
    Player <|-- ArtificialPlayer

    class HumanPlayer {
    }

    class ArtificialPlayer {
        -SecureRandom RANDOM
        +randomCoordinatePlayed(int size)
    }
    ArtificialPlayer ..|> RandomCoordinateCapable
```

---

## Algorithm for the checkWinnerCondition() method

```mermaid
flowchart TD
    Start([Start checkWinnerCondition])
    iLoop{For each row i}
    jLoop{For each column j}
    TileCheck{Tile i_j not empty?}
    DirectionsLoop{For each direction -dx,dy-}
SetCount[Set count = 1]
kLoop{For k from 1 to condition-1}
CheckNextTile{Next tile in direction dx_dy within board?}
SameOwner{Same owner as start tile?}
IncrementCount[Increment count]
CountCheck{count == condition?}
ReturnTrue[Return true]
ContinueDirection[Continue to next direction]
End([Return false])

%% Flèches
Start --> iLoop
iLoop --> jLoop
jLoop --> TileCheck
TileCheck -- No --> jLoop
TileCheck -- Yes --> DirectionsLoop
DirectionsLoop --> SetCount
SetCount --> kLoop
kLoop --> CheckNextTile
CheckNextTile -- No --> ContinueDirection
CheckNextTile -- Yes --> SameOwner
SameOwner -- Yes --> IncrementCount --> kLoop
SameOwner -- No --> ContinueDirection
kLoop --> CountCheck
CountCheck -- Yes --> ReturnTrue
CountCheck -- No --> DirectionsLoop
ContinueDirection --> DirectionsLoop
DirectionsLoop --> jLoop
jLoop --> iLoop
iLoop --> End
```