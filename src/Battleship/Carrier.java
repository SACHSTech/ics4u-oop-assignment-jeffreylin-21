package Battleship;

public class Carrier extends Ship{
	
	private Plane planeType;
	private int intNumPlanes;
	private int intWidth;
  private int intShipsFound;
	
	public Carrier (String strType, int intHealth, int intNumPlanes, int intWidth, Plane planeType) {
    
    super(shipType, intHealth);
		this.planeType = planeType;
    this.intWidth = intWidth;
		this.intNumPlanes = intNumPlanes;
    this.intShipsFound = 0;

	}
		
}