package Battleship;

public class Battlecruiser extends Ship{
	
	private Ammo ammoType;
	private boolean isJammed;
	private int intLength;
  private static int intPlanesShot = 0;
	
	public Battlecruiser (String strType, int intLength, boolean isDestroyed, Ammo ammoType) {

		super(strType, intLength, isDestroyed);
    this.intLength = intLength;
		this.isJammed = false;
		this.ammoType = ammoType;

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
      Ship destroyed = new Ship("Destoyed", 0, true);
      if(!this.getStatus() && this.getAmmo().getQuantity() != 0){

        this.getAmmo().setAmmo();

        if(intX >= board.getSize() || intY >= board.getSize() || intX < 0 || intY < 0){
          System.out.println("Shot missed!");
        }else{
          
          if(board.getLocation(intX, intY) != null){
            System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
            board.setGrid(intX, intY, destroyed);
            if(this.getAmmo().getExplosive()){
              board.getLocation(intX, intY).setFire();
            }else if(this.getAmmo().getAmmoType().equals("mortar")){
              for(int intCount = 0; intCount < this.getAmmo().getGunSpread(); intCount++){
                if(intX+intCount < board.getSize()){
                  if(board.getLocation(intX+intCount, intY) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }
                }if(intX-intCount > 0){
                  if(board.getLocation(intX-intCount, intY) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }
                }if(intY+intCount < board.getSize()){
                  if(board.getLocation(intX, intY+intCount) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }
                }if(intY-intCount > 0){
                  if(board.getLocation(intX, intY-intCount) != null){
                    System.out.println("Shot hit a " + board.getLocation(intX, intY).getType());
                    board.setGrid(intX, intY, destroyed);
                  }
                }
               
              }
            }
          }
          
        }
     }else{
       System.out.println("Gun jammed!");
     }
     this.setStatus();
  }
}
