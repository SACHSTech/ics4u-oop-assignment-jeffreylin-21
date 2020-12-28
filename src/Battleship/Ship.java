package Battleship;

public class Ship {
	
	private String strType;
  private char chrID;
	private int intShipSunk;
	private int intHealth;
	private boolean isDestroyed;	
  private boolean isOnFire;
	
	public Ship (char chrID, String strType, int intHealth, boolean isDestroyed) {
    
    this.chrID = chrID;
		this.strType = strType;
		this.intHealth = intHealth;
    this.intShipSunk = 0;
		this.isDestroyed = isDestroyed;
    this.isOnFire = false;

	}

  public String toString(){
    return strType + " sunk " + this.getShipsSunk();
  }

  public char getID(){
    return this.chrID;
  }

  public String getType(){
    return this.strType;
  }

  public int getHealth() {
    return this.intHealth;
  }

  public void setHealth(Grid board) {
    this.intHealth--;
    if (intHealth == 0) {
      this.changeState();
      board.setShipNum();
    }
  } 

  public void changeState() {
    this.isDestroyed = true;
  }

  public boolean getState(){
    return this.isDestroyed;
  }

  public boolean isRevealed(){
    return this.isOnFire;
  }

  public void setFire(){
    this.isOnFire = true;
  }

  public void attack(Grid board, int intX, int intY){
    System.out.println("Attacked");
  }

  public int getShipsSunk(){
    return this.intShipSunk;
  }
}
