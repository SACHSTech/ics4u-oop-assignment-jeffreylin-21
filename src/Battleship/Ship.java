package Battleship;

public class Ship {
	
	private String strType;
  private int intID;
	private int intShipSunk;
	private int intHealth;
	private boolean isDestroyed;	
  private boolean isOnFire;
	
	public Ship (int intID, String strType, int intHealth, boolean isDestroyed) {
    this.intID = intID;
		this.strType = strType;
		this.intHealth = intHealth;
    this.intShipSunk = 0;
		this.isDestroyed = isDestroyed;
    this.isOnFire = false;

	}

  public String toString(){
    return strType + " sunk" + this.getShipsSunk();
  }

  public int getID(){
    return this.intID;
  }
  public String getType(){
    return this.strType;
  }

  public int getHealth() {
    return this.intHealth;
  }

  public void setHealth() {
    this.intHealth--;
    if (intHealth == 0) {
      this.changeState();
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

  public int getShipsSunk(){
    return this.intShipSunk;
  }
}
