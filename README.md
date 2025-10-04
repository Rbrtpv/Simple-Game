# Simple-Java-Game

This is a simple 2D Java game where the player controls a character to fight against various enemies. The game features character swapping, different attack types, and basic UI elements like a main menu, pause menu, and restart screen.

## Features

*   **Player Control**: Move your character using `W`, `A`, `S`, `D` or the arrow keys.
*   **Character Swapping**: Press `SPACE` to switch between two distinct characters:
    *   **Pierce**: Attacks with projectiles.
    *   **Slash**: Attacks with an area-of-effect (AOE) ability.
*   **Mouse-based Attacks**: Click the left mouse button to unleash your character's attack.
*   **Enemy Types**: Encounter different enemies with unique behaviors:
    *   **Machines**: Ranged enemies that shoot projectiles.
    *   **Organic**: Melee enemies that attack when in close range.
*   **Health System**: Both the player and enemies have health points displayed via health bars.
*   **Game States**: Includes a `MAIN_MENU`, `PLAYING`, `PAUSE`, and `RESTART` state.
*   **Pause Functionality**: Press `ESC` to pause and unpause the game.
*   **Collision Detection**: Basic collision handling for projectiles and AOE attacks with enemies, and enemy projectiles with the player.

## How to Run

To run this project, you will need a Java Development Kit (JDK) installed on your system.

1.  **Clone the repository (if applicable) or download the project files.**
2.  **Navigate to the project's root directory** in your terminal.
3.  **Compile the Java source files**:
    ```bash
    javac -d bin src/**/*.java
    ```
    This command compiles all `.java` files in the `src` directory and its subdirectories, placing the compiled `.class` files into a `bin` directory.
4.  **Run the game**:
    ```bash
    java -cp bin Main
    ```
    This command executes the `Main` class, which is the entry point of the game.

## Project Structure

The project is organized into several packages:

*   `controls`: Handles user input from keyboard and mouse.
*   `entities`: Contains base classes for game objects and specific implementations for characters, enemies, and damage types.
    *   `characters`: Defines `GameCharacter` and its subclasses (`Pierce`, `Slash`).
    *   `dmg`: Defines damage-related entities like `Projectile` and `AreaOfEffect`.
    *   `enemies`: Defines `Enemy` and its subclasses (`Machines`, `Organic`).
*   `game`: Manages the main game loop, window, and game states.
*   `ui`: Contains classes for user interface elements like `MainMenu`, `PauseMenu`, `RestartMenu`, `LevelsMenu`, and `StatsMenu`.
*   `user`: Manages the player's character and HUD.

## Technologies Used

*   **Java**: Core programming language.
*   **AWT (Abstract Window Toolkit)**: Used for basic graphics and event handling (e.g., `Graphics`, `KeyEvent`, `MouseEvent`, `JFrame`, `Canvas`).