package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Simulator {

  // static BufferedReader so all methods can access it
  static BufferedReader keyboard;

  /**
  * Prints starting messages, logo, instructions, and asks for game mode
  */
  public static void start() {
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
  public static int readInt() throws IOException {
    return Integer.parseInt(keyboard.readLine());
  }

  /**
  * Takes a string from the user and returns it as an int
  *
  * @param intRange - the range of the random number to be generated
  * @param intStart - the starting value of the random number to be generated 
  *
  * @return int of randomly generated number
  */
  public static int random(int intRange, int intStart) {
    return (int)(Math.random() * intRange + intStart);
  }

  /**
  * Prints out blank line
  */
  public static void newLine() {
    System.out.println();
  }

  /**
  * Takes a int value from the user and pauses the program for the same amount of seconds
  *
  * @param intSec - the amount of seconds for the program to sleep in int
  */
  public static void sleep(int intSec) {

    try { 
      TimeUnit.SECONDS.sleep(intSec);
    }
    catch (InterruptedException e) { 
        System.out.println(e);
    } 
    
  }

  /**
  * Takes in a board and randomly fills it according to its size
  *
  * @param board - the board that is being filled
  */
  public static void randomBoard(Grid board) {
    
    // Variable initialization
    int randX;
    int randY;
    int randSize;
    int randWeapon;
    int intCarriers;
    int intCruisers;
    int shipNum;
    Carrier temp;
    Battlecruiser temp2;
    shipNum = 0;

    // Randomly generate an amount of carriers and make the rest of the ships cruisers
    intCarriers = random(board.getShipNum(), 0);
    intCruisers = board.getShipNum()-intCarriers;

    // Create carriers and place them on the grid
    while (intCarriers > 0) {

      intCarriers--;
      // generate random size and location
      randSize = random(3, 1);
      randX = random(board.getSize() - randSize, 0);
      randY = random(board.getSize() - 2, 0);

      // Go through the generated location and make sure it is valid, i.e no other ship is already there
      for (int intCount2 = randX; intCount2 < randX + randSize; intCount2++) {
        for (int intCount = randY; intCount < randY + 2; intCount++) {
          
          // If there is a ship already there, exit this loop and regenerate a new carrier and location
          if (board.getLocation(intCount2, intCount) != null) {
            intCount = 100;
            intCount2 = 100;
            intCarriers++;
          }

        }
        if (intCount2 == randX + randSize - 1 && intCount2 != 100) {
          
          // Generate a random weapon
          randWeapon = random(2, 1);
          if (randWeapon == 1) {
            // Instantiate carrier
            temp = new Carrier((char)(shipNum + 'A'), "Carrier", randSize+1, randSize, false, new Plane("bomber", random(2, 0), false));
            board.getActive()[shipNum] = temp;
            shipNum++;    
          }
          else {
            // Instantiate carrier
            temp = new Carrier((char)(shipNum + 'A'), "Carrier", randSize + 1, randSize, false, new Plane("scout", 0, true));
            board.getActive()[shipNum] = temp;
            shipNum++;

          }
          // Place on board
          for (int intCount3 = randX; intCount3 < randX + randSize; intCount3++) {
            for (int intCount4 = randY; intCount4 < randY + 2; intCount4++) {
              board.setGrid(intCount3, intCount4, temp);
            }
          }
        }

      }
    }

    // Create cruisers and place them on the grid
    while (intCruisers > 0) {

      // generate random size and location
      intCruisers--;
      randSize = random(2, 2);
      randX = random(board.getSize() - randSize, 0);
      randY = random(board.getSize() - 1, 0);

      // Go through the generated location and make sure it is valid, i.e no other ship is already there
      for (int intCount2 = randX; intCount2 < randX + randSize; intCount2++) {
        for (int intCount = randY; intCount < randY + 1; intCount++) {
          
          // If there is a ship already there, exit this loop and regenerate a new cruiser and location
          if (board.getLocation(intCount2, intCount) != null) {
            intCount = 100;
            intCount2 = 100;
            intCruisers++;
          }

        }

        if (intCount2 == randX + randSize - 1 && intCount2 != 100) {
          
          // Generate a random weapon
          randWeapon = random(2, 1);
          if (randWeapon == 1) {
            
            // Instantiate battlecruiser
            temp2 = new Battlecruiser((char)(shipNum + 'A'), "Battlecruiser", randSize, false, new Ammo("explosive", 0, 10, true));
            board.getActive()[shipNum] = temp2;
            shipNum++; 

          }
          else {
            // Instantiate battlecruiser
            temp2 = new Battlecruiser((char)(shipNum + 'A'), "Battlecruiser", randSize, false, new Ammo("mortar", random(3, 2), 10, false));
            board.getActive()[shipNum] = temp2;
            shipNum++;
          }

          // Place on grid
          for (int intCount3 = randX; intCount3 < randX + randSize; intCount3++) {
            for (int intCount4 = randY; intCount4 < randY + 1; intCount4++) {
                board.setGrid(intCount3, intCount4, temp2);
            }
          }

        }

      }

    }
  }

  /**
  * Takes a the grid the computer is attacking from and chooses a random ship and shoots a random target on the enemy board
  *
  * @param computer - the grid that the computer is attacking from
  * @param target - the grid the computer must attack
  *
  * @return String of the attacking ship and coordinates of attack 
  */  
  public static String computerAttack(Grid computer, Grid target) {

    // variable initialization
    boolean hasFound;
    int rand, randX, randY;
    rand = 0;
    randX = 0;
    randY = 0;
    hasFound = false;

    // Pick a random ship from the available ships
    while (!hasFound && computer.getShipNum() > 0) {
      
      // Keep generating a random number and check the corresponding element of the Ship[] array until an alive ship is found
      rand = random(computer.getActive().length, 0);
      if (!computer.getActive()[rand].getState()) {
        hasFound = true;
      }

    }

    // Keep generating random coordinates until a spot that is not attacked yet is found
    hasFound = false;
    while (!hasFound) {
      randX = random(target.getSize(), 1);
      randY = random(target.getSize(), 1);
      if (target.getLocation(randX - 1, randY - 1) == null || !target.getLocation(randX - 1, randY - 1).getState()) {
        hasFound = true;
      }
    }

    // return the ship chrID as a number by changing it to its corresponding position in the alphabet, and return coordinates of the target seperated by a space
    return Character.toString(rand + 'A') + " " + Integer.toString(randX) + " " + Integer.toString(randY);

  }

  /**
  * Simulates a game of the user versus the computer
  */  
  public static void singlePlayer() throws IOException {

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
    randomBoard(player);
    randomBoard(computer);
    sleep(1);

    // Print legend and board to player
    newLine();
    player.printLegend();
    player.printBoard(computer);
    System.out.println("Enter -1 -1 as a coordinate to quit");

    // while the max number of moves has not been reached and both players still have ships alive, continue the game
    while (intMaxMoves > 0 && player.getShipNum() != 0 && computer.getShipNum() != 0) {
      intMaxMoves--;

      // Get ship and attack target from user
      System.out.println("Choose ship to attack, row of target, and coloumn of target, e.g 'A 3 4'");
      strCommand = keyboard.readLine();
      intShip = strCommand.charAt(0) - 'A';
      intX = strCommand.charAt(2) - '0';
      intY = strCommand.charAt(4) - '0';

      System.out.println("You:");
      if (intX != -1 && intY != -1) {
        // If ship is available to attack, the ship attacks
        if (!player.getActive()[intShip].getState()) {
          player.getActive()[intShip].attack(computer, intX - 1, intY - 1);
        }
        else {
          System.out.println("Ship has been destroyed.");
        }

      }
      // If coordinates -1 -1 are entered the game is ended
      else {
        intMaxMoves = 0;
      }

      // Get random ship and attack target for the computer
      strCommand = computerAttack(computer, player);
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
  public static void watchGame() throws IOException {

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
    randomBoard(player1);
    randomBoard(player2);
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
      strCommand = computerAttack(player1, player2);
      intShip = strCommand.charAt(0) - 'A';
      intX = strCommand.charAt(2) - '0';
      intY = strCommand.charAt(4) - '0';
      
      // Verify ship is alive
      if (!player1.getActive()[intShip].getState()) {
        System.out.println("Player 1:");
        player1.getActive()[intShip].attack(player2, intX - 1, intY - 1);
      }

      // Get attack for second player
      strCommand = computerAttack(player2, player1);
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
    if(intChoice == 1) {
      singlePlayer();
    }
    else {
      watchGame();
    }
    
  }
} 