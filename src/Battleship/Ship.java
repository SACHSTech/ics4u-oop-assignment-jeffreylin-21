package Battleship;

/**
* Ship class file
* @author: J. Lin
* 
*/

public class Ship {

  // Instance variables
	private String strType;
  private char chrID;
	private int intHealth;
	private boolean isDestroyed;	
  private boolean isDiscovered;
	
  /**
  * Constructor - creates new instance of an ship object, also the super class for {@link Battlecruiser} and {@link Carrier}
  *
  * @param chrID - the character that will represent this object on the grid
  * @param strType - the type of ship this is
  * @param intHealth - the health of the ship, also the number of instances it appears on the board or ship[][] array
  * @param isDestroyed - if this ship is destoyed or not
  */	
	public Ship (char chrID, String strType, int intHealth, boolean isDestroyed) {
    
    this.chrID = chrID;
		this.strType = strType;
		this.intHealth = intHealth;
		this.isDestroyed = isDestroyed;
    this.isDiscovered = false;

	}

  /**
  * Returns char of the character that is used to represent a ship on the grid 
  *
  * @return char variable chrID, which is the character that represents the ship on the board
  */	
  public char getID() {
    return this.chrID;
  }


  /**
  * Returns string of the type of this ship
  *
  * @return string variable strType, which is type of this ship
  */	
  public String getType() {
    return this.strType;
  }

  /**
  * Returns int of the health of the ship
  *
  * @return int variable intHealth, the current health of this ship
  */	
  public int getHealth() {
    return this.intHealth;
  }

  /**
  * Takes the current health of this object and reduce it by one, then if the health of the ship is zero, change isDestoyed to true and reduce the number of alive ships on the board
  *
  * @param Grid variable board, the current grid this object is on
  */	
  public void setHealth(Grid board) {
    this.intHealth--;
    
    if (this.intHealth == 0) {
      this.changeState();
      board.setShipNum();
    }
  } 

  /**
  * Changes the isDestroyed variable of this object to true
  */
  public void changeState() {
    this.isDestroyed = true;
  }

  /**
  * Returns boolean of whether the current ship is destroyed or not
  *
  * @return boolean variable isDestroyed, which is whether the ship is destroyed or not, true for yes, and false for no
  */
  public boolean getState() {
    return this.isDestroyed;
  }

  /**
  * Returns boolean of whether the current ship has been revealed yet
  *
  * @return boolean variable isDiscovered, which is whether the printBoard method should reveal this ship to both players
  */ 
  public boolean isRevealed() {
    return this.isDiscovered;
  }

  /**
  * Sets the variable isDiscovered as true, indicating that it should be revealed to both players
  */  
  public void setDiscovered() {
    this.isDiscovered = true;
  }

  /**
  * Attack method, which takes in a board, and coordinates and shoots that target according to the objects gun and current state, ovveridden by the methods in the subclasses
  *
  * @param board - the Grid of the other player which will be shot
  * @param intX - the int value of the x coordinate of the target
  * @param intY - the int value of the y coordinate of the target
  */
  public void attack(Grid board, int intX, int intY) {
    System.out.println("Attacked");
  }

}
 