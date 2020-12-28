package Battleship;

public class Battlecruiser extends Ship{
	
	private Ammo ammoType;
	private boolean isJammed;
	private int intLength;
  private static int intPlanesShot = 0;
	
	public Battlecruiser (char chrID, String strType, int intLength, boolean isDestroyed, Ammo ammoType) {

		super(chrID, strType, intLength, isDestroyed);
    this.intLength = intLength;
		this.isJammed = false;
		this.ammoType = ammoType;

	}

  public String toString(){
    return super.getID() + ": " + super.getType() + " equipped with " + this.getAmmo().getAmmoType() + " shell. ";
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
    int intRand = (int)(Math.random()*10)+1;
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

  public Ammo getAmmo(){
    return this.ammoType;
  }

  public void attack(Grid board, int intX, int intY){
      Ship destroyed = new Ship('X', "Destoyed", 0, true);
      Ship empty = new Ship(' ', "Empty", 0, true);
      if(!this.getStatus() && this.getAmmo().getQuantity() != 0){

        this.getAmmo().setAmmo();

        if(intX >= board.getSize() || intY >= board.getSize() || intX < 0 || intY < 0){
          System.out.println("Shot missed!");
        }else{
          
          if(board.getLocation(intX, intY) != null){
            System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
            board.getLocation(intX, intY).setHealth(board);
            if(this.getAmmo().getExplosive()){
              board.getLocation(intX, intY).setFire();
            }
            board.setGrid(intX, intY, destroyed);
          }else{
            board.setGrid(intX, intY, empty);
            System.out.println("Shot missed! a");            
          }if(this.getAmmo().getAmmoType().equals("mortar")){
            for(int intCount = 1; intCount < this.getAmmo().getGunSpread(); intCount++){
              if(intX+intCount < board.getSize()){
                if(board.getLocation(intX+intCount, intY) != null){
                  System.out.println("Shot hit a " + board.getLocation(intX+intCount, intY).getType());
                  board.getLocation(intX+intCount, intY).setHealth(board);
                  board.setGrid(intX+intCount, intY, destroyed);
                }else{
                  board.setGrid(intX+intCount, intY, empty);
                  System.out.println("Shot missed!");
                }
              }if(intX-intCount > -1){
                if(board.getLocation(intX-intCount, intY) != null){
                  System.out.println("Shot hit a " + board.getLocation(intX-intCount, intY).getType());
                  board.getLocation(intX-intCount, intY).setHealth(board);
                  board.setGrid(intX-intCount, intY, destroyed);
                }else{
                  board.setGrid(intX-intCount, intY, empty);
                  System.out.println("Shot missed!");
                }
              }if(intY+intCount < board.getSize()){
                if(board.getLocation(intX, intY+intCount) != null){
                  System.out.println("Shot hit a " + board.getLocation(intX, intY+intCount).getType());
                  board.getLocation(intX, intY+intCount).setHealth(board);
                  board.setGrid(intX, intY+intCount, destroyed);
                }else{
                  board.setGrid(intX, intY+intCount, empty);
                  System.out.println("Shot missed!");
                }
              }if(intY-intCount > -1){
                if(board.getLocation(intX, intY-intCount) != null){
                  System.out.println("Shot hit a " + board.getLocation(intX, intY-intCount).getType());
                  board.getLocation(intX, intY-intCount).setHealth(board);
                  board.setGrid(intX, intY-intCount, destroyed);
                }else{
                  board.setGrid(intX, intY-intCount, empty);
                  System.out.println("Shot missed!");
                }
              }
              
            }
          }
        }
      }else if (this.getStatus()){
        System.out.println("Gun jammed!");
      }else{
        System.out.println("Out of ammo");
      }
      this.setStatus();
  }
}
