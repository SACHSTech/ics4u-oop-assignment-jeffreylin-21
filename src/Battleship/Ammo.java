package Battleship;

public class Ammo {
	
	private String strAmmoName;
	private int intGunSpread;
  private int intAmmoQuantity;
	private boolean isExplosive;
	
	public Ammo (String strAmmoName, int intGunSpread, int intAmmoQuantity, boolean isExplosive) {
		this.strAmmoName = strAmmoName;
		this.intGunSpread = intGunSpread;
    this.intAmmoQuantity = intAmmoQuantity;
		this.isExplosive = isExplosive;
	}
	
}
