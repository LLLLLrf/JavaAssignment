package abdn.scnu.cs;

import java.util.Random;

public class BattleShip extends AbstractBattleShip {
    Random r = new Random();

    protected String[] orientations = { "horizontal", "vertical" };

    // Define the constructor
    public BattleShip(String name) {
        this.name = name;
        
        this.shipOrientation = orientations[r.nextInt(2)];
    }

    // Define getter and setter methods
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHits() {
        return this.hits;
    }

    @Override
    public void setHits(int hits) {
        this.hits = hits;
    }
    
    @Override
    public String getShipOrientation() {
        return this.shipOrientation;
    }

    @Override
    public int[][] getShipCoordinates() {
        return this.shipCoordinates;
    }

    @Override
    public void setShipCoordinates(int[][] shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    // Define other methods
    // check whether the ship was attacked
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
