package abdn.scnu.cs;

import java.util.Random;

public class BattleShip extends AbstractBattleShip {
    Random r = new Random();


    protected String[] orientations = { "horizontal", "vertical" };

    // Define constructors
    public BattleShip() {
    }

    public BattleShip(String name) {
        this.name = name;
        this.shipOrientation = orientations[r.nextInt(2)];
    }

    // Define getter and setter
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHits() {
        return this.hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getShipOrientation() {
        return this.shipOrientation;
    }

    public void setShipOrientation(String shipOrientation) {
        this.shipOrientation = shipOrientation;
    }

    public int[][] getShipCoordinates() {
        return this.shipCoordinates;
    }

    public void setShipCoordinates(int[][] shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    // Define other methods
    @Override
    public boolean checkAttack(int row, int column) {

        if (this.hits < 3) {
            if(shipOrientation=="horizontal"){
                if(shipCoordinates[0][0]==row && shipCoordinates[0][1]==column){
                    hits++;
                    return true;
                }else if(shipCoordinates[0][0]==row && (shipCoordinates[0][1]+1)==column){
                    hits++;
                    return true;
                }else if(shipCoordinates[0][0]==row && (shipCoordinates[0][1]-1)==column){
                    hits++;
                    return true;
                }
            }else{
                if(shipCoordinates[0][0]==row && shipCoordinates[0][1]==column){
                    hits++;
                    return true;
                }else if((shipCoordinates[0][0]+1)==row && shipCoordinates[0][1]==column){
                    hits++;
                    return true;
                }else if((shipCoordinates[0][0]-1)==row && shipCoordinates[0][1]==column){
                    hits++;
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
