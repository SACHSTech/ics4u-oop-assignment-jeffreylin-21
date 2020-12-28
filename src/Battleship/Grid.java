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
    activeShips = new Ship[this.intSize];

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
  public void setShipNum(int intShipNum) {
    this.intShipNum = intShipNum;
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
    Simulator.newLine();
  }

  public void printBoard(){
    System.out.println("Player");
    for(int i = 0; i <= this.getSize(); i++){
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
      System.out.println();
    }
    Simulator.newLine();
  }

  public void printEnemyBoard(){
    
    System.out.println("Enemy");
    for(int i = 0; i <= this.getSize(); i++){
      System.out.print(i + " ");
    }

    Simulator.newLine();

    for(int i = 0; i < this.getSize(); i++){
      System.out.print((i+1) + " ");

      for(int j = 0; j < this.getSize(); j++){

        if(this.getLocation(i, j) != null && (this.getLocation(i, j).getState() || this.getLocation(i, j).isRevealed())){
          System.out.print(this.getLocation(i, j).getID() + " ");
        }else{
          System.out.print(". ");
        }

      }
      System.out.println();
    }
    Simulator.newLine();
  }

}