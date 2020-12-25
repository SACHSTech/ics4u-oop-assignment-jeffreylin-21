package Battleship;

public class Ship {
	
	private String strType;
	private int intShipsSunk;
	private int intHealth;
	private boolean isDestroyed;	
	
	public Ship (String strType, int intHealth) {

		this.strType = strType;
		this.intHealth = intHealth;
    this.intShipsSunk = 0;
		this.isDestroyed = false;
    
	}
		
}
