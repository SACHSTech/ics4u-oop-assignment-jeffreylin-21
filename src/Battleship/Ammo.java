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
	  
  public int getGunSpread(){
    return this.intGunSpread;
  }
	
  public boolean getExplosive(){
    return this.isExplosive;
  }

  public String getAmmoType(){
    return this.strAmmoName;
  }

  public int getQuantity(){
    return this.intAmmoQuantity;
  }

  public void setAmmo(){
    this.intAmmoQuantity--;
  }

}
