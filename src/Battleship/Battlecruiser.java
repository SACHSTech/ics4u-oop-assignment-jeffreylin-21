package Battleship;

public class Battlecruiser extends Ship{
	
	private Ammo ammoType;
	private boolean isJammed;
	private int intLength;
  private static int intPlanesShot = 0;
	
	public Battlecruiser (String strType, int intLength, boolean isDestroyed, Ammo ammoType) {

		super(strType, intLength, isDestroyed);
    this.intLength = intLength;
		this.isJammed = false;
		this.ammoType = ammoType;

	}
	
  public int getLength(){
    return this.intLength;
  }
  
  public static void setPlaneShot(){
    intPlanesShot++;
  }

  public static int getPlaneShot(){
    return intPlanesShot;
  }

  public void setStatus(){
    int intRand = (int)(Math.random()*5)+1;
    if(intRand == 1){
      isJammed = true;
    }
    else {
      isJammed = false;
    }
  }

  public boolean getStatus(){
    return this.isJammed;
  }
}
