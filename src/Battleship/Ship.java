package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Ship {
	
	private String strType;
  private char chrID;
	private int intHealth;
	private boolean isDestroyed;	
  private boolean isOnFire;
	
	public Ship (char chrID, String strType, int intHealth, boolean isDestroyed) {
    
    this.chrID = chrID;
		this.strType = strType;
		this.intHealth = intHealth;
		this.isDestroyed = isDestroyed;
    this.isOnFire = false;

	}

  public char getID() {
    return this.chrID;
  }

  public String getType() {
    return this.strType;
  }

  public int getHealth() {
    return this.intHealth;
  }

  public void setHealth(Grid board) {
    this.intHealth--;
    
    if (this.intHealth == 0) {
      this.changeState();
      board.setShipNum();
    }
  } 

  public void changeState() {
    this.isDestroyed = true;
  }

  public boolean getState() {
    return this.isDestroyed;
  }

  public boolean isRevealed() {
    return this.isOnFire;
  }

  public void setFire() {
    this.isOnFire = true;
  }

  public void attack(Grid board, int intX, int intY) {
    System.out.println("Attacked");
  }

}
