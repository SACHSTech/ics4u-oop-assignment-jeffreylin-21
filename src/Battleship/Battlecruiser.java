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
    this.intPlaneShot = 0;
		this.ammoType = ammoType;

	}
	
  public void setPlaneShot(){
    this.intPlanesShot++;
  }

  public int getPlaneShot(){
    return this.intPlanesShot;
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
