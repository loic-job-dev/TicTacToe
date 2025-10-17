# TicTacToe

School project in Java

---

## Description
This project aims to create a TicTacToe game, playable in console.
The first menu allows the user to choose between 3 different game modes :
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
    Main --> TicTacToe

    class TicTacToe {
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
        +checkWinner()
        +chooseGameMode()
    }
    TicTacToe --> View
    TicTacToe --> InteractionUtilisateur
    TicTacToe --> Board
    TicTacToe --> Player

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

## Algorithm for the checkWinner() method

```mermaid
flowchart TD
    StartRow([Start Row Check])
    RowCheck{isNotEmpty_row_0_i ?}
    RowWin[Set rowWin = true]
    RowInnerLoop{For j from 1 to board_size-1}
    RowSameOwner{sameOwner_row_0_i_j_i ?}
    RowFail[Set rowWin = false]
    SetResultRow[Set result = true]
    EndRow([End Row Check])

    StartRow --> RowCheck
    RowCheck -- No --> EndRow
    RowCheck -- Yes --> RowWin
    RowWin --> RowInnerLoop
    RowInnerLoop --> RowSameOwner
    RowSameOwner -- Yes --> RowInnerLoop
    RowSameOwner -- No --> RowFail --> EndRow
    RowInnerLoop --> SetResultRow --> EndRow
```
