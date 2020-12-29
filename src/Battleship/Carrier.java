package Battleship;

/**
* ICS4U OOP Assignment
* @author: J. Lin
*
*/

public class Carrier extends Ship{
	
	private Plane planeType;
	private int intNumPlanes;
	private int intWidth;
  private static int intShipsFound = 0;
	
	public Carrier (char chrID, String strType, int intNumPlanes, int intWidth, boolean isDestroyed, Plane planeType) {
    
    super(chrID, strType, intWidth * 2, isDestroyed);
		this.planeType = planeType;
    this.intWidth = intWidth;
		this.intNumPlanes = intNumPlanes;
	}

  public String toString() {
    return super.getID() + ": " + super.getType() + " equipped with " + this.getPlane().getName() + " plane.";
  }

  public int getWidth() {
    return this.intWidth;
  }

  public static void setShipsFound() {
    intShipsFound++;
  }

  public static int getShipsFound() {
    return intShipsFound;
  }

  public void setPlanes() {
    this.intNumPlanes--;
  }

  public int getPlanes() {
    return this.intNumPlanes;
  }

  public boolean canLaunch() {
    if(this.getPlanes() > 0){
      return true;
    }
    return false;
  }

  public Plane getPlane() {
    return this.planeType;
  }

  public void attack(Grid board, int intX, int intY) {

    int intRand;
    Ship destroyed = new Ship('X', "Destoyed", 0, true);
    Ship empty = new Ship(' ', "Empty", 0, true);

    if (this.canLaunch()) {

      if (this.getPlane().getName().equals("bomber")) {

        System.out.println("Launched bomber");

        for (int intCount = 0; intCount < board.getSize(); intCount++) {

          if (board.getLocation(intCount, intY) != null && !board.getLocation(intCount, intY).getState()) {
              
            System.out.println("Bomb hit a " + board.getLocation(intCount, intY).getType());
            board.getLocation(intCount, intY).setHealth(board);
            board.setGrid(intCount, intY, destroyed);

            for (int intCount2 = 0; intCount2 < this.getPlane().getSpread(); intCount2++) {

              if (intCount + intCount2 < board.getSize()) {

                if (board.getLocation(intCount + intCount2, intY) != null && !board.getLocation(intCount + intCount2, intY).getState()) {

                  System.out.println("Shot hit a " + board.getLocation(intCount + intCount2, intY).getType());
                  board.getLocation(intCount + intCount2, intY).setHealth(board);
                  board.setGrid(intCount + intCount2, intY, destroyed);

                } 
                else if(board.getLocation(intCount + intCount2, intY) == null){
                  board.setGrid(intCount + intCount2, intY, empty);
                }

              }if (intCount - intCount2 > 0) {
                if (board.getLocation(intCount - intCount2, intY) != null && !board.getLocation(intCount - intCount2, intY).getState()) {

                  System.out.println("Shot hit a " + board.getLocation(intCount - intCount2, intY).getType());
                  board.getLocation(intCount - intCount2, intY).setHealth(board);
                  board.setGrid(intCount - intCount2, intY, destroyed);
                  
                } 
                else if (board.getLocation(intCount - intCount2, intY) == null) {
                  board.setGrid(intCount - intCount2, intY, empty);
                }

              }
              if (intY + intCount2 < board.getSize()) {

                if (board.getLocation(intCount, intY + intCount2) != null && !board.getLocation(intCount, intY + intCount2).getState()) {
                  System.out.println("Shot hit a " + board.getLocation(intCount, intY + intCount2).getType());
                  board.getLocation(intCount, intY + intCount2).setHealth(board);
                  board.setGrid(intCount, intY + intCount2, destroyed);
                  
                } 
                else if (board.getLocation(intCount, intY + intCount2) == null) {
                  board.setGrid(intCount, intY + intCount2, empty);
                }
              } 
              if (intY - intCount2 > 0) {

                if (board.getLocation(intCount, intY - intCount2) != null && !board.getLocation(intCount, intY - intCount2).getState()) {

                  System.out.println("Shot hit a " + board.getLocation(intCount, intY - intCount2).getType());
                  board.getLocation(intCount, intY - intCount2).setHealth(board);
                  board.setGrid(intCount, intY - intCount2, destroyed);
                  
                }
                else if (board.getLocation(intCount, intY - intCount2) == null) {
                  board.setGrid(intCount, intY - intCount2, empty);
                }

              }
            }
            intCount = 100;
          }
          else if (board.getLocation(intCount, intY) == null) {
            board.setGrid(intCount, intY, empty);
          }    
        } 
      }
      else if (this.getPlane().getScout()) {
        System.out.println("Launched scout");
        for (int intCount = 0; intCount < board.getSize(); intCount++) {

          if (board.getLocation(intCount, intY) != null) {

            board.getLocation(intCount, intY).setFire();
            setShipsFound();
            intRand = Simulator.random(10, 1);
            if (intRand == 1) {
              System.out.println("Plane was shot down");
              Battlecruiser.setPlaneShot();
              this.setPlanes();
            }

          }
          else {
            board.setGrid(intCount, intY, empty);
          }     

          if (board.getLocation(intX, intCount) != null) {

            board.getLocation(intX, intCount).setFire();
            setShipsFound();
            intRand = Simulator.random(10, 1);
            if (intRand == 1) {
              System.out.println("Plane was shot down");
              Battlecruiser.setPlaneShot();
              this.setPlanes();
            }
          }
          else {
            board.setGrid(intX, intCount, empty);
          }     
        }

      }
    }
    else {
      System.out.println("No available planes!");
    }
  }
		
}