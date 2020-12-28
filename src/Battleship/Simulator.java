package Battleship;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Simulator {

static BufferedReader keyboard;

  public static void start() {
    System.out.println("______       _   _   _           _     _");
    System.out.println("| ___ \"    | | | | | |         | |   (_)");
    System.out.println("| |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __");
    System.out.println("| ___ \"/ _` | __| __| |/ _ \"/ __| '_ \"| | '_ \"");
    System.out.println("| |_/ / (_| | |_| |_| |  __/\"__ \" | | | | |_) |");
    System.out.println("\"____/ \"__,_|\"__|\"__|_|\"___||___/_| |_|_| .__/");
    System.out.println("                                        | |");
    System.out.println("                                        |_|");
    System.out.println("Welcone to Battleship");
    System.out.println("Enter 1 to watch a computer simualated game or 2 to fight a computer");
  }

  public static int readInt() throws IOException{
    return Integer.parseInt(keyboard.readLine());
  }

  public static int random(int intRange, int intStart){
    return (int)(Math.random()*intRange+intStart);
  }

  public static void newLine(){
    System.out.println();
  }

  public static void sleep(int intSec){
    try{ 
      TimeUnit.SECONDS.sleep(intSec);
    }catch (InterruptedException e) { 
        System.out.println(e);
    } 
    
  }

  public static void randomBoard(Grid board){
    int randX;
    int randY;
    int randSize;
    int randWeapon;
    int intCarriers;
    int intCruisers;
    int shipNum;
    shipNum = 0;
    intCarriers = random(board.getShipNum(), 0);
    intCruisers = board.getShipNum()-intCarriers;

    while(intCarriers > 0){
      intCarriers--;
      randSize = random(3, 1);
      randX = random(board.getSize()-randSize, 0);
      randY = random(board.getSize()-2, 0);
      for(int intCount2 = randX; intCount2 < randX + randSize; intCount2++){
        for(int intCount = randY; intCount < randY+2; intCount++){
          if(board.getLocation(intCount2, intCount) != null){
            intCount = 100;
            intCount2 = 100;
            intCarriers++;
          }
        }
        if(intCount2 == randX + randSize - 1 && intCount2 != 100){
          randWeapon = random(2, 1);
          if(randWeapon == 1){
            Carrier temp = new Carrier((char)(shipNum + 'A'), "Carrier", randSize+1, randSize, false, new Plane("fighter", random(2, 0), false));
            board.getActive()[shipNum] = temp;
            shipNum++;       
            for(int intCount3 = randX; intCount3 < randX + randSize; intCount3++){
              for(int intCount4 = randY; intCount4 < randY+2; intCount4++){
                board.setGrid(intCount3, intCount4, temp);
              }
            }            
          }else{
            Carrier temp = new Carrier((char)(shipNum + 'A'), "Carrier", randSize+1, randSize, false, new Plane("scout", 0, true));
            board.getActive()[shipNum] = temp;
            shipNum++;
            for(int intCount3 = randX; intCount3 < randX + randSize; intCount3++){
              for(int intCount4 = randY; intCount4 < randY+2; intCount4++){
                board.setGrid(intCount3, intCount4, temp);
              }
            }
          }
        }
      }
    }
    while(intCruisers > 0){
      intCruisers--;
      randSize = random(2, 2);
      randX = random(board.getSize()-randSize, 0);
      randY = random(board.getSize()-1, 0);
      for(int intCount2 = randX; intCount2 < randX + randSize; intCount2++){
        for(int intCount = randY; intCount < randY+1; intCount++){
          if(board.getLocation(intCount2, intCount) != null){
            intCount = 100;
            intCount2 = 100;
            intCruisers++;
          }
        }
        if(intCount2 == randX + randSize - 1 && intCount2 != 100){
          randWeapon = random(2, 1);
          if(randWeapon == 3){
            Battlecruiser temp = new Battlecruiser((char)(shipNum + 'A'), "Battlecruiser", randSize, false, new Ammo("explosive", 0, 10, true));
            board.getActive()[shipNum] = temp;
            shipNum++;
            for(int intCount3 = randX; intCount3 < randX + randSize; intCount3++){
              for(int intCount4 = randY; intCount4 < randY+1; intCount4++){
                board.setGrid(intCount3, intCount4, temp);
              }
            }            
          }else{
            Battlecruiser temp = new Battlecruiser((char)(shipNum + 'A'), "Battlecruiser", randSize, false, new Ammo("mortar", random(3, 1), 10, false));
            board.getActive()[shipNum] = temp;
            shipNum++;
            for(int intCount3 = randX; intCount3 < randX + randSize; intCount3++){
              for(int intCount4 = randY; intCount4 < randY+1; intCount4++){
                board.setGrid(intCount3, intCount4, temp);
              }
            }
          }
        }
      }
    }
  }

  public static void singlePlayer() throws IOException{
    int intMaxMoves;
    int intChoice;

    newLine();
    System.out.println("Choose your board size, '3', '5', '7', '9'");
    intChoice = readInt();

    Grid player = new Grid(1, intChoice, (intChoice-1)/2);
    Grid computer = new Grid(2, intChoice, (intChoice-1)/2);
    intMaxMoves = intChoice * intChoice;

    newLine();
    System.out.println("Setting up both boards...");
    randomBoard(player);
    randomBoard(computer);
    //sleep(1);

    newLine();
    player.printLegend();
    player.printBoard(computer);

    while (intMaxMoves > 0 && player.getShipNum() != 0 && computer.getShipNum() != 0){
      intMaxMoves--;

      System.out.println("Choose ship to attack, row of target, and coloumn of target, e.g 'A 3 4'");
      String strCommand = keyboard.readLine();
      int intShip = strCommand.charAt(0) - 'A';
      int intX = strCommand.charAt(2) - '0';
      int intY = strCommand.charAt(4) - '0';

      if(intX != -1 && intY != -1){
        player.getActive()[intShip].attack(computer, intX-1, intY-1);
      }else{
        intMaxMoves = 0;
      }
      player.printBoard(computer);

    }
    
  }

  public static void watchGame(){

  }
  
  public static void main (String[] args) throws IOException{
    int intChoice;
    keyboard = new BufferedReader(new InputStreamReader(System.in));
    start();
    intChoice = readInt();
    if(intChoice == 2){
      singlePlayer();
    }else{
      watchGame();
    }
  }
}