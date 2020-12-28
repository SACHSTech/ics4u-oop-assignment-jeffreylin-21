package Battleship;

public class Grid {
	
  private int intPlayerNum;
  private int intSize;
  private int intShipNum;
  private Ship[][] grid;
  private Ship activeShips[];

	public Grid (int intPlayerNum, int intSize, int intShipNum) {

    this.intPlayerNum = intPlayerNum;
    this.intSize = intSize;
    this.intShipNum = intShipNum;
    grid = new Ship[this.intSize][this.intSize];
    activeShips = new Ship[this.intShipNum];

  }

  public int getPlayerNum() {
    return this.intPlayerNum;
  }

  public int getSize(){
    return this.intSize;
  }

  public int getShipNum() {
    return this.intShipNum;
  }

  public Ship[] getActive(){
    return this.activeShips;
  }
  public void setShipNum() {
    this.intShipNum--;
  }

  public Ship getLocation(int intX, int intY) {
    return this.grid[intX][intY];
  }

  public void setGrid(int intX, int intY, Ship ship) {
    this.grid[intX][intY] = ship;
  }

  public void printLegend(){
    System.out.println("Legend: ");
    for(int i = 0; i < this.getShipNum(); i++){
      if(!this.getActive()[i].getState()){
        System.out.println(this.getActive()[i]);
      }
    }
    System.out.println(".: unkwown tile");
    System.out.println("X: Destroyed tile");
    System.out.println(" : Empty tile");
    Simulator.newLine();
  }

  public void printBoard(Grid computer){
    
    for(int intCount = 0; intCount < this.getSize()*2-5; intCount++){
      System.out.print(" ");
    }
    System.out.println("Player | Enemy");

    for(int i = 0; i <= this.getSize(); i++){
      System.out.print(i + " ");
    }
    System.out.print("| ");
    for(int i = this.getSize(); i >= 0; i--){
      System.out.print(i + " ");
    }    

    Simulator.newLine();

    for(int i = 0; i < this.getSize(); i++){
      System.out.print((i+1) + " ");

      for(int j = 0; j < this.getSize(); j++){

        if(this.getLocation(i, j) == null){
          System.out.print(". ");
          
        }else{
          System.out.print(this.getLocation(i, j).getID() + " ");
        }
      }
      System.out.print("| ");
      for(int j = computer.getSize()-1; j > -1 ; j--){
        if(computer.getLocation(i, j) != null && (computer.getLocation(i, j).getState() || computer.getLocation(i, j).isRevealed())){
          System.out.print(computer.getLocation(i, j).getID() + " ");
        }else{
          System.out.print(". ");
        }

      }
      System.out.println((i+1) + " ");
      
    }
    Simulator.newLine();
  }

}