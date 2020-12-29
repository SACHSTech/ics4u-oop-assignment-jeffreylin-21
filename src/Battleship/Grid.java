package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Grid {
	
  // Instance variables
  private int intPlayerNum;
  private int intSize;
  private int intShipNum;
  // Actual grid of ships
  private Ship[][] grid;
  // Array of the ships on this grid
  private Ship activeShips[];

  /**
  * Constructor - creates new instance of an Grid object
  *
  * @param intPlayerNum - the player number of the player using this ship
  * @param intSize - the size of the playing board/array
  * @param intShipNum - the number of ships in this grid
  */	
	public Grid (int intPlayerNum, int intSize, int intShipNum) {

    this.intPlayerNum = intPlayerNum;
    this.intSize = intSize;
    this.intShipNum = intShipNum;
    // Initialize arrays
    grid = new Ship[this.intSize][this.intSize];
    activeShips = new Ship[this.intShipNum];

  }

  /**
  * Returns the player number of the player using this grid
  *
  * @return int variable intPlayerNum, which represents the player number of the player using this grid
  */
  public int getPlayerNum() {
    return this.intPlayerNum;
  }

  /**
  * Returns the size of the array in this grid
  *
  * @return int variable intSize, which represents size of the array representing the playing board
  */
  public int getSize() {
    return this.intSize;
  }

  /**
  * Returns the number of ships that are still alive on the array
  *
  * @return int variable intShipNum, which represents the number of ships that are still alive on this board
  */
  public int getShipNum() {
    return this.intShipNum;
  }

  /**
  * Returns Ship array of the ships on the board. Used for picking a random ship to attack for the bot
  *
  * @return Ship[], which holds the ships that are present on this board
  */
  public Ship[] getActive(){
    return this.activeShips;
  }

  /**
  * Takes the current number of active ships and decreases it by 1
  *
  */  
  public void setShipNum() {
    this.intShipNum--;
  }

  /**
  * Returns the object that is in the specific index of the Ship[][]]indicated by the method paremeters
  *
  * @param intX - the first value of the index in the 2-d ship array
  * @param intY - the second value of the index in the 2-d ship array
  *
  * @return Ship, the ship object that is in the index specified
  *
  */ 
  public Ship getLocation(int intX, int intY) {
    return this.grid[intX][intY];
  }

  /**
  * Accesses the Ship[][] array and sets the values of that index to the ship object provided
  *
  * @param intX - the first value of the index in the 2-d ship array
  * @param intY - the second value of the index in the 2-d ship array
  * @param ship - the ship that the index will be set equal to
  *
  */ 
  public void setGrid(int intX, int intY, Ship ship) {
    this.grid[intX][intY] = ship;
  }

  /**
  * Goes through the board displayed to the userand prints what each character on their board represents
  *
  */ 
  public void printLegend() {

    System.out.println("Legend: ");
    for (int i = 0; i < this.getShipNum(); i++) {
      if (!this.getActive()[i].getState()) {
        System.out.println(this.getActive()[i]);
      }
    }
    System.out.println(".: unkwown tile");
    System.out.println("X: Destroyed tile");
    System.out.println(" : Empty tile");
    Simulator.newLine();

  }

  /**
  * Prints the Ship[][] array and the oppnonents array beside it
  *
  * @param computer - the enemy board that will be printed
  *
  */
  public void printBoard(Grid computer) {
    
    // Print out "Player | Enemy" centred between the boards
    for (int intCount = 0; intCount < this.getSize() * 2 - 5; intCount++) {
      System.out.print(" ");
    }
    System.out.println("Player | Enemy");

    // Print out numbers at the top
    for (int i = 0; i <= this.getSize(); i++) {
      System.out.print(i + " ");
    }

    System.out.print("| ");
    for (int i = this.getSize(); i >= 0; i--) {
      System.out.print(i + " ");
    }    

    Simulator.newLine();

    // Iterate through the array and print out the charID of each ship object
    for (int i = 0; i < this.getSize(); i++) {
      
      // Print out coloumn labels
      System.out.print((i + 1) + " ");

      // Print out row of this current object's board
      for (int j = 0; j < this.getSize(); j++) {
        
        // If index is null, print a dot
        if (this.getLocation(i, j) == null) {
          System.out.print(". ");
        }
        // Otherwise print the charID of the ship object
        else {
          System.out.print(this.getLocation(i, j).getID() + " ");
        }

      }

      System.out.print("| ");
      // Print out row of the opponents board
      for (int j = computer.getSize() - 1; j > -1 ; j--) {

        // Only print out charID if the object is already revealed
        if (computer.getLocation(i, j) != null && (computer.getLocation(i, j).getState() || computer.getLocation(i, j).isRevealed())) {
          System.out.print(computer.getLocation(i, j).getID() + " ");
        }
        // Otherwise print out . to represent unkwown tile
        else {
          System.out.print(". ");
        }

      }
      System.out.println((i + 1) + " ");
      
    }
    Simulator.newLine();
  }

}