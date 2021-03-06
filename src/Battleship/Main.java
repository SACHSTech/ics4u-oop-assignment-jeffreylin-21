package Battleship;

/**
* Main class file, simulates system
* @author: J. Lin
* 
*/

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {

  // static BufferedReader so all methods can access it
  static BufferedReader keyboard;

  /**
  * Prints starting messages, logo, instructions, and asks for game mode
  */
  private static void start() {
    System.out.println("______       _   _   _           _     _");
    System.out.println("| ___ \\     | | | | | |         | |   (_)");
    System.out.println("| |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __");
    System.out.println("| ___ \\/ _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\");
    System.out.println("| |_/ / (_| | |_| |_| |  __/\\__ \\ | | | | |_) |");
    System.out.println("\\____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/");
    System.out.println("                                        | |");
    System.out.println("                                        |_|");
    System.out.println("Welcone to Battleship, here are the instructions:");
    newLine();
    System.out.println("You can either watch two bots play against each other or play against a bot yourself. Each board size correlates with a different number of ships. The type and location of each shuip will be random. The two different ship types are a battlecruiser and a carrier. The battlecruiser can have either an explosive shell or a mortar shell. The explosive shell will reveal the entire ship if part of the ship is hit. The mortar shell will target an entire area, the range of this area depends on your luck. There are two plane types for a carrier a scout and a bomber. A scout plane will search the row and coloumn of the target you select for ships and the bomber will search the coloumn in your target until it finds a ship. Lastly, there is a limited number of ammo and planes, so if planes are shot down or ammo runs out, you may be out of luck.");
    newLine();
    System.out.println("Enter 1 to fight a computer or 2 to watch a computer simualated game");
  }

  /**
  * Takes a string from the user and returns it as an int
  *
  * @return int of whatever was entered by the user
  */
  private static int readInt() throws IOException {
    return Integer.parseInt(keyboard.readLine());
  }

  /**
  * Prints out blank line
  */
  private static void newLine() {
    System.out.println();
  }

  /**
  * Takes a int value from the user and pauses the program for the same amount of seconds
  *
  * @param intSec - the amount of seconds for the program to sleep in int
  */
  private static void sleep(int intSec) {

    try { 
      TimeUnit.SECONDS.sleep(intSec);
    }
    catch (InterruptedException e) { 
        System.out.println(e);
    } 
    
  }

  /**
  * Simulates a game of the user versus the computer
  */  
  private static void singlePlayer() throws IOException {

    // Variable initialization
    int intMaxMoves;
    int intChoice;
    String strCommand;
    int intShip, intX, intY;

    // Get grid size
    newLine();
    System.out.println("Choose your board size, '3', '5', '7', '9'");
    intChoice = readInt();

    // grid initialization
    Grid player = new Grid(1, intChoice, (intChoice - 1) / 2);
    Grid computer = new Grid(2, intChoice, (intChoice - 1) / 2);
    intMaxMoves = intChoice * intChoice;

    // generate random boards
    newLine();
    System.out.println("Setting up both boards...");
    player.randomBoard();
    computer.randomBoard();
    sleep(1);

    // Print legend and board to player
    newLine();
    player.printLegend();
    player.printBoard(computer);
    System.out.println("Enter 0 0 as a coordinate to quit, 'A 0 0'");

    // while the max number of moves has not been reached and both players still have ships alive, continue the game
    while (intMaxMoves > 0 && player.getShipNum() != 0 && computer.getShipNum() != 0) {
      intMaxMoves--;

      // Get ship and attack target from user
      System.out.println("Choose ship to attack, row of target, and coloumn of target with a space inbetween each character, e.g 'A 3 4'");
      strCommand = keyboard.readLine();
      intShip = strCommand.charAt(0) - 'A';
      intX = strCommand.charAt(2) - '0';
      intY = strCommand.charAt(4) - '0';

      System.out.println("You:");
      if (intX != 0 && intY != 0) {
        // If ship is available to attack, the ship attacks
        if (!player.getActive()[intShip].getState()) {
          player.getActive()[intShip].attack(computer, intX - 1, intY - 1);
        }
        else {
          System.out.println("Ship has been destroyed.");
        }

      }
      // If coordinates 0 0 are entered the game is ended
      else {
        intMaxMoves = 0;
      }

      // Get random ship and attack target for the computer
      strCommand = computer.generateAttack(player);
      intShip = strCommand.charAt(0) - 'A';
      intX = strCommand.charAt(2) - '0';
      intY = strCommand.charAt(4) - '0';
      
      // Verify that the ship is alive
      if (!computer.getActive()[intShip].getState()) {
        System.out.println("Enemy:");
        computer.getActive()[intShip].attack(player, intX - 1, intY - 1);
      }

      // Print the current state of the game to the user
      player.printBoard(computer);

    }
    // Print end game statements
    if (computer.getShipNum() == 0) {
      System.out.println("You Win!");
    } else {
      System.out.println("You lose...");
    }
    System.out.println("A total of " + Carrier.getShipsFound() + " ships were found and "  + Battlecruiser.getPlaneShot() + " planes were shot that round.");
  }

  /**
  * Simulates a game of between two computer controlled players
  */  
  private static void watchGame() throws IOException {

    // Variable initialization
    int intMaxMoves;
    int intChoice;
    String strCommand;
    int intShip, intX, intY;

    // Get grid size
    newLine();
    System.out.println("Choose your board size, '3', '5', '7', '9'");
    intChoice = readInt();

    // Instantiate grids
    Grid player1 = new Grid(1, intChoice, (intChoice - 1) / 2);
    Grid player2 = new Grid(2, intChoice, (intChoice - 1) / 2);
    intMaxMoves = intChoice * intChoice;

    newLine();
    System.out.println("Setting up both boards...");
    player1.randomBoard();
    player2.randomBoard();
    sleep(1);

    // Print out both player's grid and legend
    newLine();
    System.out.println("Player 1:");
    player1.printLegend();
    player1.printBoard(player2);
    System.out.println("Player 2:");
    player2.printLegend();
    player2.printBoard(player1);

    // While the max number of moves has not been reached and both players still have ships alive, continue the game    
    while (intMaxMoves > 0 && player1.getShipNum() != 0 && player2.getShipNum() != 0) {
      intMaxMoves--;
      System.out.println("--------------------------------------------");
      // Get ship and target for the first player
      strCommand = player1.generateAttack(player2);
      intShip = strCommand.charAt(0) - 'A';
      intX = strCommand.charAt(2) - '0';
      intY = strCommand.charAt(4) - '0';
      
      // Verify ship is alive
      if (!player1.getActive()[intShip].getState()) {
        System.out.println("Player 1:");
        player1.getActive()[intShip].attack(player2, intX - 1, intY - 1);
      }

      // Get attack for second player
      strCommand = player2.generateAttack(player1);
      intShip = strCommand.charAt(0) - 'A';
      intX = strCommand.charAt(2) - '0';
      intY = strCommand.charAt(4) - '0';
      
      // Verify that the ship is alive
      if (!player2.getActive()[intShip].getState()) {
        System.out.println("Player 2:");
        player2.getActive()[intShip].attack(player1, intX - 1, intY - 1);
      }

      // Print both boards to the user and sleep for 1 second to slow down the game
      player1.printBoard(player2);
      player2.printBoard(player1);
      sleep(1);
    }

    // Print endgame statements
    if (player1.getShipNum() == 0) {
      System.out.println("Player 2 wins!");
    } 
    else if (player2.getShipNum() == 0) {
      System.out.println("Player 1 wins!");
    }
    else {
      System.out.println("Draw...");
    }
    System.out.println("A total of " + Carrier.getShipsFound() + " ships were found and "  + Battlecruiser.getPlaneShot() + " planes were shot that round.");
  }
  
  /**
  * Starts the game and controls which game mode to present
  */
  public static void main (String[] args) throws IOException {

    // Gets the gamemode choice
    int intChoice;
    keyboard = new BufferedReader(new InputStreamReader(System.in));
    start();
    intChoice = readInt();

    // Starts the appropriate game modes
    if (intChoice == 1) {
      singlePlayer();
    }
    else {
      watchGame();
    }
    
  }
} 