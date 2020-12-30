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
  */ 
  public void setGrid(int intX, int intY, Ship ship) {
    this.grid[intX][intY] = ship;
  }

  /**
  * Goes through the board displayed to the userand prints what each character on their board represents
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
    System.out.println();

  }

  /**
  * Prints the Ship[][] array and the oppnonents array beside it
  *
  * @param computer - the enemy board that will be printed
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

    System.out.println();

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
    System.out.println();
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
  * Randomizes current board based on its size
  */
  public void randomBoard() {
    
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
    intCarriers = random(this.getShipNum(), 0);
    intCruisers = this.getShipNum() - intCarriers;

    // Create carriers and place them on the grid
    while (intCarriers > 0) {

      intCarriers--;
      // generate random size and location
      randSize = random(3, 1);
      randX = random(this.getSize() - randSize, 0);
      randY = random(this.getSize() - 2, 0);

      // Go through the generated location and make sure it is valid, i.e no other ship is already there
      for (int intCount2 = randX; intCount2 < randX + randSize; intCount2++) {
        for (int intCount = randY; intCount < randY + 2; intCount++) {
          
          // If there is a ship already there, exit this loop and regenerate a new carrier and location
          if (this.getLocation(intCount2, intCount) != null) {
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
            this.getActive()[shipNum] = temp;
            shipNum++;    
          }
          else {
            // Instantiate carrier
            temp = new Carrier((char)(shipNum + 'A'), "Carrier", randSize + 1, randSize, false, new Plane("scout", 0, true));
            this.getActive()[shipNum] = temp;
            shipNum++;

          }
          // Place on board
          for (int intCount3 = randX; intCount3 < randX + randSize; intCount3++) {
            for (int intCount4 = randY; intCount4 < randY + 2; intCount4++) {
              this.setGrid(intCount3, intCount4, temp);
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
      randX = random(this.getSize() - randSize, 0);
      randY = random(this.getSize() - 1, 0);

      // Go through the generated location and make sure it is valid, i.e no other ship is already there
      for (int intCount2 = randX; intCount2 < randX + randSize; intCount2++) {
        for (int intCount = randY; intCount < randY + 1; intCount++) {
          
          // If there is a ship already there, exit this loop and regenerate a new cruiser and location
          if (this.getLocation(intCount2, intCount) != null) {
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
            this.getActive()[shipNum] = temp2;
            shipNum++; 

          }
          else {
            // Instantiate battlecruiser
            temp2 = new Battlecruiser((char)(shipNum + 'A'), "Battlecruiser", randSize, false, new Ammo("mortar", random(3, 2), 10, false));
            this.getActive()[shipNum] = temp2;
            shipNum++;
          }

          // Place on grid
          for (int intCount3 = randX; intCount3 < randX + randSize; intCount3++) {
            for (int intCount4 = randY; intCount4 < randY + 1; intCount4++) {
                this.setGrid(intCount3, intCount4, temp2);
            }
          }

        }

      }

    }
  }

  /**
  * Takes in grid that is being attacked and chooses a random ship on this grid to attack a random target on the given grid
  *
  * @param target - the grid the computer must attack
  *
  * @return String of the attacking ship and coordinates of attack 
  */  
  public String generateAttack(Grid target) {

    // variable initialization
    boolean hasFound;
    int rand, randX, randY;
    rand = 0;
    randX = 0;
    randY = 0;
    hasFound = false;

    // Pick a random ship from the available ships
    while (!hasFound && this.getShipNum() > 0) {
      
      // Keep generating a random number and check the corresponding element of the Ship[] array until an alive ship is found
      rand = random(this.getActive().length, 0);
      if (!this.getActive()[rand].getState()) {
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

}