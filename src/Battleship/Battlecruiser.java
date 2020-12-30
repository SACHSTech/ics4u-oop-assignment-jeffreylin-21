package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Battlecruiser extends Ship {
	
  // Instance variables
	private Ammo ammoType;
	private boolean isJammed;
	private int intLength;
  // static variable of how many ships were shot down in a game
  private static int intPlanesShot = 0;

  /**
  * Constructor - creates new instance of an battlecruiser object
  *
  * @param chrID - the character that will represent this object on the grid
  * @param strType - the type of ship this is
  * @param intLength - the length of this battlecruiser (width is auto set to 1)
  * @param isDestroyed - if this battlecruiser is destoyed 
  * @param ammoType - the ammo object of this battlecruiser
  */		
	public Battlecruiser (char chrID, String strType, int intLength, boolean isDestroyed, Ammo ammoType) {

		super(chrID, strType, intLength, isDestroyed);
    this.intLength = intLength;
		this.isJammed = false;
		this.ammoType = ammoType;

	}

  /**
  * String reprensentation of this battlecruiser object
  *
  * @return icon that represents this ship on the grid, type of ship, and weapon it is equipped with
  */
  public String toString() {
    return super.getID() + ": " + super.getType() + " equipped with " + this.getAmmo().getAmmoType() + " shell. ";
  }

  /**
  * Returns the length of the current object
  *
  * @return int variable intLength, which represents the length of the object
  */	
  public int getLength() {
    return this.intLength;
  }
  
  /**
  * Takes the static variable intPlanesShot and increments it by 1
  */	  
  public static void setPlaneShot() {
    intPlanesShot++;
  }

  /**
  * Returns the number of planes that have been shot
  *
  * @return static int variable intPlanesShot, which holds the number of planes that have been shot
  */
  public static int getPlaneShot() {
    return intPlanesShot;
  }

  /**
  * Helper method that randomly chooses if the ship's gun is jammed, which determines if it can shoot or not
  *
  */
  public void setStatus() {
    int intRand = (int)(Math.random() * 10) + 1;
    if(intRand == 1){
      isJammed = true;
    }
    else {
      isJammed = false;
    }
  }

  /**
  * Returns boolean of whether the gun is jammed in other words, if the ship can shoot
  *
  * @return boolean variable isJammed, which represents if the gun is jammed
  */
  public boolean getStatus() {
    return this.isJammed;
  }

  /**
  * Returns the ammo object of this ship
  *
  * @return Ammo object, which allows you to access the ammo object's methods
  */
  public Ammo getAmmo() {
    return this.ammoType;
  }

  /**
  * Attack method, which takes in a board, and coordinates and shoots that target according to the objects gun and current state.
  *
  * @param board - the Grid of the other player which will be shot
  * @param intX - the int value of the x coordinate of the target
  * @param intY - the int value of the y coordinate of the target
  */
  public void attack(Grid board, int intX, int intY) {

      // If the target is empty fill it with empty ship, if the target is a ship replace the object in the array with a destoyed ship object
      Ship destroyed = new Ship('X', "Destoyed", 0, true);
      Ship empty = new Ship(' ', "Empty", 0, true);

      // Check the current ship for if the gun is jammed and if there is enough ammo
      if (!this.getStatus() && this.getAmmo().getQuantity() != 0) {
        this.getAmmo().setAmmo();

        // Check if target is out of bounds for the grid given
        if (intX >= board.getSize() || intY >= board.getSize() || intX < 0 || intY < 0) {
          System.out.println("Shot missed!");
        } 
        else {

          // Check if there is an object in the specified index of the array and whether is it an destroyed object or actual ship
          if (board.getLocation(intX, intY) != null && !board.getLocation(intX, intY).getState()) {
            
            // Print out that target has been hit, change the health of the ship that was hit, and replace the hit target with a destoyed ship object
            System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
            board.getLocation(intX, intY).setHealth(board);
            if (this.getAmmo().getExplosive()) {
              // If the ammo was explosive, set the ship on fire/reveal the ship to both players
              board.getLocation(intX, intY).setDiscovered();
            }
            board.setGrid(intX, intY, destroyed);

          } 
          else if (board.getLocation(intX, intY) == null) {
            // If shot hit an empty spot, print missed and replace empty spot with empty grid object
            board.setGrid(intX, intY, empty);
            System.out.println("Shot missed!");   

          } 
          else {
            // If shot hit an already revealed spot or a destoyed ship, print shot missed
            System.out.println("Shot missed!");
          } 
          if (this.getAmmo().getAmmoType().equals("mortar")) {
            
            // If the ammo name is mortar, then also attack north, east, south, and west of the target with distance equal to the range of the ammo
            for (int intCount = 1; intCount < this.getAmmo().getGunSpread(); intCount++) {
              
              // Check if going south of the target will be out of bounds
              if (intX + intCount < board.getSize()) {
                
                // Check if there is ship in the new target
                if (board.getLocation(intX + intCount, intY) != null && !board.getLocation(intX + intCount, intY).getState()) {
                  System.out.println("Shot hit a " + board.getLocation(intX + intCount, intY).getType());
                  board.getLocation(intX + intCount, intY).setHealth(board);
                  board.setGrid(intX + intCount, intY, destroyed);
                } 
                // Check if new location is empty
                else if (board.getLocation(intX + intCount, intY) == null) {
                  board.setGrid(intX + intCount, intY, empty);
                  System.out.println("Shot missed!");
                }

              }
              // Check if going north of the target will be out of bounds
              if (intX - intCount > -1) {
                
                // Check if there is a ship in the new target
                if (board.getLocation(intX-intCount, intY) != null && !board.getLocation(intX - intCount, intY).getState()) {
                  System.out.println("Shot hit a " + board.getLocation(intX - intCount, intY).getType());
                  board.getLocation(intX - intCount, intY).setHealth(board);
                  board.setGrid(intX - intCount, intY, destroyed);
                } 
                // Check if the new target is empty
                else if (board.getLocation(intX - intCount, intY) == null) {
                  board.setGrid(intX - intCount, intY, empty);
                  System.out.println("Shot missed!");
                }

              }
              // Check if going west of the target will be out of bounds, board is inverted so increasing the value will go west not east
              if (intY + intCount < board.getSize()) {
                
                // Check if there is a ship int he new target
                if (board.getLocation(intX, intY + intCount) != null && !board.getLocation(intX, intY + intCount).getState()) {
                  System.out.println("Shot hit a " + board.getLocation(intX, intY + intCount).getType());
                  board.getLocation(intX, intY + intCount).setHealth(board);
                  board.setGrid(intX, intY + intCount, destroyed);
                } 
                // Check if the new target is empty
                else if (board.getLocation(intX, intY + intCount) == null) {
                  board.setGrid(intX, intY + intCount, empty);
                  System.out.println("Shot missed!");
                }

              }
              // Check if going east of the target will be out of bounds 
              if (intY - intCount > -1) {

                // Check if there is a ship in the new target
                if (board.getLocation(intX, intY - intCount) != null && !board.getLocation(intX, intY - intCount).getState()) {
                  System.out.println("Shot hit a " + board.getLocation(intX, intY - intCount).getType());
                  board.getLocation(intX, intY - intCount).setHealth(board);
                  board.setGrid(intX, intY - intCount, destroyed);
                } 
                // Check if target is empty
                else if (board.getLocation(intX, intY - intCount) == null) {
                  board.setGrid(intX, intY - intCount, empty);
                  System.out.println("Shot missed!");
                }

              }
        
            }

          }

        }

      } 
      // If shot cannot be fired, print out the reason why
      else if (this.getStatus()) {
        System.out.println("Gun jammed!");
      } 
      else {
        System.out.println("Out of ammo");
      }
      // Randomly set if gun gets jammed for the next shot
      this.setStatus();
  }
}
