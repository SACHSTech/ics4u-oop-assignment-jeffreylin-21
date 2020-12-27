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
    return super.getID() + ": " + super.getType() + " equipped with " + this.getAmmo().getAmmoType();
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

  public Ammo getAmmo(){
    return this.ammoType;
  }

  public void shootGun(Grid board, int intX, int intY){
      Ship destroyed = new Ship('X', "Destoyed", 0, true);
      Ship empty = new Ship(' ', "Empty", 0, true);
      if(!this.getStatus() && this.getAmmo().getQuantity() != 0){

        this.getAmmo().setAmmo();

        if(intX >= board.getSize() || intY >= board.getSize() || intX < 0 || intY < 0){
          System.out.println("Shot missed!");
        }else{
          
          if(board.getLocation(intX, intY) != null){
            System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
            board.getLocation(intX, intY).setHealth();
            board.setGrid(intX, intY, destroyed);
            if(this.getAmmo().getExplosive()){
              board.getLocation(intX, intY).setFire();
            }else if(this.getAmmo().getAmmoType().equals("mortar")){
              for(int intCount = 0; intCount < this.getAmmo().getGunSpread(); intCount++){
                if(intX+intCount < board.getSize()){
                  if(board.getLocation(intX+intCount, intY) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.getLocation(intX+intCount, intY).setHealth();
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX+intCount, intY, empty);
                    System.out.println("Shot missed!");
                  }
                }if(intX-intCount > 0){
                  if(board.getLocation(intX-intCount, intY) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.getLocation(intX-intCount, intY).setHealth();
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX-intCount, intY, empty);
                    System.out.println("Shot missed!");
                  }
                }if(intY+intCount < board.getSize()){
                  if(board.getLocation(intX, intY+intCount) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.getLocation(intX, intY+intCount).setHealth();
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX, intY+intCount, empty);
                    System.out.println("Shot missed!");
                  }
                }if(intY-intCount > 0){
                  if(board.getLocation(intX, intY-intCount) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.getLocation(intX, intY-intCount).setHealth();
                    board.setGrid(intX, intY, destroyed);
                  }else{
                    board.setGrid(intX, intY-intCount, empty);
                    System.out.println("Shot missed!");
                  }
                }
               
              }
            }
          }else{
            board.setGrid(intX, intY, empty);
            System.out.println("Shot missed!");
          }
          
        }
      }else{
        System.out.println("Gun jammed!");
      }
      this.setStatus();
  }
}
