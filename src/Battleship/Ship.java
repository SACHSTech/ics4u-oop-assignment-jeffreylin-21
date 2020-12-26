package Battleship;

public class Ship {
	
	private String strType;
	private int intShipSunk;
	private int intHealth;
	private boolean isDestroyed;	
	
	public Ship (String strType, int intHealth, boolean isDestroyed) {

		this.strType = strType;
		this.intHealth = intHealth;
    this.intShipsSunk = 0;
		this.isDestroyed = isDestroyed;

	}

  public String getType(){
    return this.getType;
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

}
