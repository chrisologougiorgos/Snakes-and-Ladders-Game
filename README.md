
# 🎲 Snake and Ladder Game – Java Implementation

This repository contains a full Java implementation of the classic **Snake and Ladder** board game. The project was developed as part of the course "Data Structures" during the Winter Semester of the 2022-2023 academic year at the school Of ECE, AUTH.
---

## 📁 Contents

### 🧩 Main Components

- `Game.java`:  
  Main class that initializes and runs the game loop. It controls the sequence of moves, player turns, and the overall flow of the game.

- `Board.java`:  
  Represents the board structure and holds references to all game elements such as snakes, ladders, and presents.

- `Snake.java`:  
  Represents a snake on the board, which moves the player backward when landed upon.

- `Ladder.java`:  
  Represents a ladder, allowing the player to move forward to a higher square.

- `Present.java`:  
  Represents tiles that add points to the player's score when landed upon.

- `Player.java`:  
  Basic player class that moves based on random dice rolls.

- `HeuristicPlayer.java`:  
  An advanced player class that selects its move using a **heuristic evaluation**.  
  For each possible dice roll (1–6), it simulates the resulting move and computes a score:

  **Evaluation = 0.65 × (position gain) + 0.35 × (score gain)**

  The move with the highest evaluation is chosen, balancing board progress and point collection.  
  This strategy helps avoid snakes and seek out beneficial tiles like ladders and presents.

---

## ▶️ How to Run

1. Compile all `.java` files:
   ```bash
   javac *.java
   ```

2. Run the game:
   ```bash
   java Game
   ```

> 📝 Make sure all source files are in the same directory, or adjust your classpath accordingly.

---

## 👨‍🎓 Author

**Georgios Chrysologou**  
Undergraduate student, 5th year  
Department of Electrical and Computer Engineering  
Aristotle University of Thessaloniki

---

