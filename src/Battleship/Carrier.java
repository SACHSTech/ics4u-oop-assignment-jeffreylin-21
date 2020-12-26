package Battleship;

public class Carrier extends Ship{
	
	private Plane planeType;
	private int intNumPlanes;
	private int intWidth;
  private static int intShipsFound = 0;
	
	public Carrier (String strType, int intNumPlanes, int intWidth, boolean isDestroyed,Plane planeType) {
    
    super(strType, 2, isDestroyed);
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

  public Plane getPlane(){
    return this.planeType;
  }

  public void launchPlane(Grid board, int intX, int intY){
    Ship destroyed = new Ship("Destoyed", 0, true);
    Ship empty = new Ship("Empty", 0, true);
    boolean hasFound = false;
    if(this.canLaunch()){
      if(this.getPlane().getName().equals("bomber")){
        for(int intCount = 0; intCount < board.getSize() && hasFound; intCount++){
          if(board.getLocation(intCount, intY) != null){
            if(!board.getLocation(intCount, intY).isDestroyed()){
              hasFound = true;
              System.out.println("Bomb hit a " + board.getLocation(intCount, intY).getType());
              board.setGrid(intCount, intY, destroyed);
              for(int intCount2 = 0; intCount2 < this.getPlane().getSpread(); intCount2++){
                if(intX+intCount2 < board.getSize()){
                  if(board.getLocation(intX+intCount2, intY) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX+intCount2, intY, empty);
                  }
                }if(intX-intCount2 > 0){
                  if(board.getLocation(intX-intCount2, intY) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX-intCount2, intY, empty);
                  }
                }if(intY+intCount2 < board.getSize()){
                  if(board.getLocation(intX, intY+intCount2) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX, intY+intCount2, empty);
                  }
                }if(intY-intCount2 > 0){
                  if(board.getLocation(intX, intY-intCount2) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX, intY-intCount2, empty);
                  }
                }
            }
          }

          if(board.getLocation(intX, intCount) != null){
            board.getLocation(intX, intCount).setFire();
          }      
      }else if(this.getPlane().getScout()){
        for(int intCount = 0; intCount < board.getSize(); intCount++){
          if(board.getLocation(intCount, intY) != null){
            board.getLocation(intCount, intY).setFire();
          }else{
            board.setGrid(intCount, intY, empty);
          }

          if(board.getLocation(intX, intCount) != null){
            board.getLocation(intX, intCount).setFire();
          }else{
            board.setGrid(intX, intCount, empty);
          }          
        }

      }
    }else{
      System.out.println("No available planes!");
    }
  }
		
}