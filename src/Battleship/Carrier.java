package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Carrier extends Ship{
	
  // Instance variables
	private Plane planeType;
	private int intNumPlanes;
	private int intWidth;
  // static variable of how many ships were found
  private static int intShipsFound = 0;
	
  /**
  * Constructor - creates new instance of an carrier object
  *
  * @param chrID - the character that will represent this object on the grid
  * @param strType - the type of ship this is
  * @param intNumPlanes - the number of planes this ship starts off with
  * @param intWidth - the intWidth of this carrier (width is auto set to 1)
  * @param isDestroyed - if this carrier is destoyed 
  * @param planeType - the plane object of this carrier
  */	
	public Carrier (char chrID, String strType, int intNumPlanes, int intWidth, boolean isDestroyed, Plane planeType) {
    // Call to super constructor 
    super(chrID, strType, intWidth * 2, isDestroyed);
		this.planeType = planeType;
    this.intWidth = intWidth;
		this.intNumPlanes = intNumPlanes;
	}

  /**
  * String reprensentation of this carrier object
  *
  * @return icon that represents this ship on the grid, type of ship, and plane it is equipped with
  */
  public String toString() {
    return super.getID() + ": " + super.getType() + " equipped with " + this.getPlane().getName() + " plane.";
  }

  /**
  * Returns the width of the current object
  *
  * @return int variable intWidth, which represents the width of the object
  */	
  public int getWidth() {
    return this.intWidth;
  }

  /**
  * Takes the static variable intShipsFound and increments it by 1
  *
  */
  public static void setShipsFound() {
    intShipsFound++;
  }

  /**
  * Returns the number of planes that have been planes
  *
  * @return static int variable intShipssFound, which holds the number of ships that have been found
  */
  public static int getShipsFound() {
    return intShipsFound;
  }

  /**
  * Takes the current amount of planes held by this carrier and decrements it by 1
  */
  public void setPlanes() {
    this.intNumPlanes--;
  }

  /**
  * Returns the current amount of planes left 
  *
  * @return int variable intNumPlanes which represents how many planes this carrier has left
  */	
  public int getPlanes() {
    return this.intNumPlanes;
  }

  /**
  * Returns boolean of whether this carrier can launch a plane, or if there is enough planes to launch 
  *
  * @return boolean of whether there are enough planes to launch an attack
  */	 
  public boolean canLaunch() {
    if(this.getPlanes() > 0){
      return true;
    }
    return false;
  }

  /**
  * Returns the plane object of this ship
  *
  * @return Plane object, which allows you to access the plane object's methods
  */  
  public Plane getPlane() {
    return this.planeType;
  }

  /**
  * Attack method, which takes in a board, and coordinates launches a plane towards that target.
  *
  * @param board - the Grid of the other player which will be shot
  * @param intX - the int value of the x coordinate of the target
  * @param intY - the int value of the y coordinate of the target
  */
  public void attack(Grid board, int intX, int intY) {

    // Holds random number for determining if a plane gets shot down
    int intRand;
    // If the target is empty fill it with empty ship, if the target is a ship replace the object in the array with a destoyed ship object
    Ship destroyed = new Ship('X', "Destoyed", 0, true);
    Ship empty = new Ship(' ', "Empty", 0, true);

    // Check if there are enough planes
    if (this.canLaunch()) {

      // Check what type of plane this carrier has
      if (this.getPlane().getName().equals("bomber")) {

        System.out.println("Launched bomber");

        // Go through the coloumn of the target starting at the top
        for (int intCount = 0; intCount < board.getSize(); intCount++) {

          // Check if current index is empty and if there is a ship that is alive there
          if (board.getLocation(intCount, intY) != null && !board.getLocation(intCount, intY).getState()) {
            
            // Print out that target has been hit, change the health of the ship that was hit, and replace the hit target with a destoyed ship object 
            System.out.println("Bomb hit a " + board.getLocation(intCount, intY).getType());
            board.getLocation(intCount, intY).setHealth(board);
            board.setGrid(intCount, intY, destroyed);

            // Similar to a mortar shell, also attack north, east, south, and west of the target with distance equal to the range of the plane bomb
            for (int intCount2 = 0; intCount2 < this.getPlane().getSpread(); intCount2++) {
              
              // Check if going south of the target will be out of bounds
              if (intCount + intCount2 < board.getSize()) {
                
                // Check if the current location contains an alive ship
                if (board.getLocation(intCount + intCount2, intY) != null && !board.getLocation(intCount + intCount2, intY).getState()) {

                  System.out.println("Shot hit a " + board.getLocation(intCount + intCount2, intY).getType());
                  board.getLocation(intCount + intCount2, intY).setHealth(board);
                  board.setGrid(intCount + intCount2, intY, destroyed);

                } 
                // Check if location has not been visited yet
                else if(board.getLocation(intCount + intCount2, intY) == null){
                  board.setGrid(intCount + intCount2, intY, empty);
                }

              }
              // Check if going north of the target will be out of bounds
              if (intCount - intCount2 > 0) {

                // Check if the current location contains an alive ship
                if (board.getLocation(intCount - intCount2, intY) != null && !board.getLocation(intCount - intCount2, intY).getState()) {

                  System.out.println("Shot hit a " + board.getLocation(intCount - intCount2, intY).getType());
                  board.getLocation(intCount - intCount2, intY).setHealth(board);
                  board.setGrid(intCount - intCount2, intY, destroyed);
                  
                } 
                // Check if location has not been visited yet
                else if (board.getLocation(intCount - intCount2, intY) == null) {
                  board.setGrid(intCount - intCount2, intY, empty);
                }

              }
              // Check if going west of the target will be out of bounds
              if (intY + intCount2 < board.getSize()) {

                // Check if the current location contains an alive ship
                if (board.getLocation(intCount, intY + intCount2) != null && !board.getLocation(intCount, intY + intCount2).getState()) {
                  System.out.println("Shot hit a " + board.getLocation(intCount, intY + intCount2).getType());
                  board.getLocation(intCount, intY + intCount2).setHealth(board);
                  board.setGrid(intCount, intY + intCount2, destroyed);
                  
                } 
                // Check if location has not been visited yet
                else if (board.getLocation(intCount, intY + intCount2) == null) {
                  board.setGrid(intCount, intY + intCount2, empty);
                }
              } 
              // Check if going east of the target will be out of bounds
              if (intY - intCount2 > 0) {
                // Check if the current location contains an alive ship
                if (board.getLocation(intCount, intY - intCount2) != null && !board.getLocation(intCount, intY - intCount2).getState()) {

                  System.out.println("Shot hit a " + board.getLocation(intCount, intY - intCount2).getType());
                  board.getLocation(intCount, intY - intCount2).setHealth(board);
                  board.setGrid(intCount, intY - intCount2, destroyed);
                  
                }
                // Check if location has not been visited yet
                else if (board.getLocation(intCount, intY - intCount2) == null) {
                  board.setGrid(intCount, intY - intCount2, empty);
                }

              }
            }
            // Stop the search once a target is found
            intCount = 100;
          }
          // Check if original target is empty
          else if (board.getLocation(intCount, intY) == null) {
            board.setGrid(intCount, intY, empty);
          }    
        } 
      }
      // If plane is a scout traverse the coloumn and row of target
      else if (this.getPlane().getScout()) {

        System.out.println("Launched scout");
        for (int intCount = 0; intCount < board.getSize(); intCount++) {
          
          // Check the coloumn of the target
          if (board.getLocation(intCount, intY) != null && !board.getLocation(intCount, intY).getState()) {
            
            // Check if ship was already found
            if (!board.getLocation(intCount, intY).isRevealed()) {
              board.getLocation(intCount, intY).setFire();
              setShipsFound();
            }

            intRand = Simulator.random(10, 1);
            // If the ship found is a battlecruiser, then the plane may be shot down
            if (intRand == 1 && board.getLocation(intCount, intY).getType().equals("Battlecruiser")) {
              System.out.println("Plane was shot down");
              Battlecruiser.setPlaneShot();
              this.setPlanes();
            }

          }
          // If spot is null, fill it with an empty ship
          else if (board.getLocation(intCount, intY) == null) {
            board.setGrid(intCount, intY, empty);
          }     

          // Check the row of the target
          if (board.getLocation(intX, intCount) != null && !board.getLocation(intX, intCount).getState()) {

            // Check if ship was already found
            if (!board.getLocation(intX, intCount).isRevealed()) {
              board.getLocation(intX, intCount).setFire();
              setShipsFound();
            }

            intRand = Simulator.random(10, 1);
            // If the ship found is a battlecruiser, then the plane may be shot down
            if (intRand == 1 && board.getLocation(intX, intCount).getType().equals("Battlecruiser")) {
              System.out.println("Plane was shot down");
              Battlecruiser.setPlaneShot();
              this.setPlanes();
            }
          }
          // If spot is null, fill it with an empty ship
          else if (board.getLocation(intX, intCount) == null) {
            board.setGrid(intX, intCount, empty);
          }     
        }

      }
    }
    // If plane is not launch, print this message
    else {
      System.out.println("No available planes!");
    }
  }
		
}