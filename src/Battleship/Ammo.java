package Battleship;

public class Ammo {
	
	private String strName;
	private int intSpread;
  private int intQuantity;
	private boolean isExplosive;
	
	public Gun (String strName, int intSpread, int intQuantity, boolean isExplosive) {
		this.strName = strName;
		this.intSpread = intSpread;
    this.intQuantity = intQuantity;
		this.isExplosive = isExplosive;
	}
	
}
