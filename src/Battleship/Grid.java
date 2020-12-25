import java.util.*;

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


}