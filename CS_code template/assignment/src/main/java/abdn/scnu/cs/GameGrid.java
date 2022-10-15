package abdn.scnu.cs;

import java.util.Random;

public class GameGrid extends AbstractGameGrid {
    protected int width;
    protected int height;
    protected int num;

    public String[][] gameGrid;
    public AbstractBattleShip[] ships;


    
    // Define constructors
    public GameGrid() {
    }

    public GameGrid(int width, int height, int num) {
        this.width = width;
        this.height = height;
        this.num = num;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    //populate the grid with "." characters
	public void initializeGrid (int row, int col){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                this.gameGrid[i][j]=".";
            }
        }
    } ;
	
	//this should place the ship on the grid using "*" symbol
	public void placeShip (AbstractBattleShip ship){
        Random r = new Random();
        // 开的数组大小要多少
        int[][] coordinate = new int[2][1];
        String orient = ship.getShipOrientation();

        int row;
        int col;
        if(orient == "horizontal"){
            row = r.nextInt(height);
            col = r.nextInt(width-2)+1;
            coordinate[0][0] = row;
            coordinate[0][1] = col;
            ship.setShipCoordinates(coordinate);
            this.gameGrid[row][col-1]="*";
            this.gameGrid[row][col+1]="*";
            this.gameGrid[row][col]="*";
        }else if(orient == "vertical"){
            row = r.nextInt(height-2)+1;
            col = r.nextInt(width);
            coordinate[0][0] = row;
            coordinate[0][1] = col;
            ship.setShipCoordinates(coordinate);
            this.gameGrid[row+1][col]="*";
            this.gameGrid[row-1][col]="*";
            this.gameGrid[row][col]="*";
        }
        

    } ;
	
	//this should generate ships for both player and the opponent 
	public void generateShips (int numberOfShips){
        // 可以我方奇数敌方偶数
        ships = new AbstractBattleShip[numberOfShips*2];
        for(int i=0;i<numberOfShips;i++){
            String name = "myship"+i;
            ships[i] = new BattleShip(name);
        }
        for(int i=0;i<numberOfShips;i++){
            String name = "opponentship"+i;
            ships[i] = new BattleShip(name);
        }
    } 
    
}
