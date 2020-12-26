package Battleship;

public class Plane {
	
	private String strPlaneName;
	private int intBombSpread;
  private boolean isAlive;
	private boolean isScout;
	
	public Plane (String strPlaneName, int intBombSpread, boolean isScout) {
		this.strPlaneName = strPlaneName;
		this.intBombSpread = intBombSpread;
    this.isScout = isScout;
		this.isAlive = true;
	}

  
	
}
