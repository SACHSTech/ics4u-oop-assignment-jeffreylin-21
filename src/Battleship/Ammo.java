package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Ammo {
	
  // Instance variables
	private String strName;
	private int intSpread;
  private int intQuantity;
	private boolean isExplosive;

  /**
  * Constructor - creates new instance of an Ammo object
  *
  * @param strName - the name of the ammo being created
  * @param intSpread - the range of the ammo, i.e how many spaces does it target
  * @param intQuantity - the amount of ammo there is
  * @param isExplosive - if this ammo can explode or not, i.e set a ship on fire, which reveals the entire ship when it is hit
  */	
	public Ammo (String strName, int intSpread, int intQuantity, boolean isExplosive) {
		this.strName = strName;
		this.intSpread = intSpread;
    this.intQuantity = intQuantity;
		this.isExplosive = isExplosive;
	}

  /**
  * Returns int of the spread/range of an ammo object 
  *
  * @return int variable intSpread, which represents the range of the ammo object
  */	  
  public int getGunSpread() {
    return this.intSpread;
  }

  /**
  * Returns boolean of  whether the current object can explode
  *
  * @return boolean variable isExplosive, which represents if the object can explode
  */		
  public boolean getExplosive() {
    return this.isExplosive;
  }

  /**
  * Returns String of the name of the current object 
  *
  * @return String of what the name of the current object is
  */	
  public String getAmmoType() {
    return this.strName;
  }

  /**
  * Returns the current amount of ammo left 
  *
  * @return int variable intQuantity which represents how many of this type of ammo there are
  */	
  public int getQuantity() {
    return this.intQuantity;
  }

  /**
  * Takes the current amount of ammo in this obect and decrement it by 1 
  *
  */
  public void setAmmo() {
    this.intQuantity--;
  }

}
