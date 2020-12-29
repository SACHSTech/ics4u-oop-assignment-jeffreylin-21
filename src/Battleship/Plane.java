package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Plane {
	
	private String strPlaneName;
	private int intBombSpread;
	private boolean isScout;
	
	public Plane (String strPlaneName, int intBombSpread, boolean isScout) {
		this.strPlaneName = strPlaneName;
		this.intBombSpread = intBombSpread;
    this.isScout = isScout;
	}

  public int getSpread() {
    return this.intBombSpread;
  }
	
  public boolean getScout() {
    return this.isScout;
  }

  public String getName() {
    return this.strPlaneName;
  }
}
