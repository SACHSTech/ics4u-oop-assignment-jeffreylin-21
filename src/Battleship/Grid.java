package Battleship;

public class Grid {
	
  private int intPlayerNum;
  private int intSize;
  private int intShipNum;
  private Ship[][] grid;

	public Grid (int intPlayerNum, int intSize, int intShipNum) {

    this.intPlayerNum = intPlayerNum;
    this.intSize = intSize;
    this.intShipNum = intShipNum;
    grid = new Ship[this.intSize][this.intSize];

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

  public void setShipNum(int intShipNum) {
    this.intShipNum = intShipNum;
  }

  public Ship getLocation(int intX, int intY) {
    return this.grid[intX][intY];
  }

  public void setGrid(int intX, int intY, Ship ship) {
    this.grid[intX][intY] = ship;
  }

}