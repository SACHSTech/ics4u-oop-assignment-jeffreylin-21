package Battleship;

public class Carrier extends Ship{
	
	private Plane planeType;
	private int intNumPlanes;
	private int intWidth;
  private static int intShipsFound = 0;
	
	public Carrier (String strType, int intNumPlanes, int intWidth, boolean isDestroyed,Plane planeType) {
    
    super(strType, intWidth*3, isDestroyed);
		this.planeType = planeType;
    this.intWidth = intWidth;
		this.intNumPlanes = intNumPlanes;
	}

  public int getWidth(){
    return this.intWidth;
  }

  public static void setShipsFound(){
    intShipsFound++;
  }

  public static int getShipsFound(){
    return intShipsFound;
  }

  public void setPlanes(){
    this.intNumPlanes--;
  }

  public int getPlanes(){
    return this.intNumPlanes;
  }

  public boolean canLaunch(){
    if(this.getPlanes() > 0){
      return true;
    }
    return false;
  }
		
}