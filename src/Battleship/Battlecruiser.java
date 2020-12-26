package Battleship;

public class Battlecruiser extends Ship{
	
	private Ammo ammoType;
	private boolean isJammed;
	private int intLength;
  private int intPlanesShot;
	
	public Battlecruiser (String strType, int intHealth, int intLength, Ammo ammoType) {

		super(strType, intHealth);
    this.intLength = intLength;
		this.isJammed = false;
    this.intPlanesShot = 0;
		this.ammoType = ammoType;

	}
		
}
