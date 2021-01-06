package Battleship;

/**
* Plane class file
* @author: J. Lin
* 
*/

public class Plane {
	// Instance variables
	private String strPlaneName;
	private int intBombSpread;
	private boolean isScout;

  /**
  * Constructor - creates new instance of an plane object
  *
  * @param strPlaneName - the name of the plane being created
  * @param intBombSpread - the range of the plane, i.e how many spaces does it target
  * @param isScout - if this plane is a scout or not
  */	
	public Plane (String strPlaneName, int intBombSpread, boolean isScout) {
		this.strPlaneName = strPlaneName;
		this.intBombSpread = intBombSpread;
    this.isScout = isScout;
	}

  /**
  * Returns int of the spread/range of an plane object 
  *
  * @return int variable intBombSpread, which represents the range of the plane object
  */	
  public int getSpread() {
    return this.intBombSpread;
  }

  /**
  * Returns boolean of  whether the current object is a scout or not
  *
  * @return boolean variable isScout, which represents if the object is a scout
  */		
  public boolean getScout() {
    return this.isScout;
  }

  /**
  * Returns String of the name of the current object 
  *
  * @return String of what the name of the current object is
  */
  public String getName() {
    return this.strPlaneName;
  }
  
}
