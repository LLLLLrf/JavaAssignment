package abdn.scnu.cs;

import java.util.Random;

public class GameGrid extends AbstractGameGrid {
    protected int width;
    protected int height;
    protected int num;

    // Define constructors
    public GameGrid(int height, int width, int num) {
        this.height = height;
        this.width = width;
        this.num = num;

        // Initialize the game grid, open up an appropriate storage space
        gameGrid = new String[height][width];
        initializeGrid();

        // generate ships for game
        generateShips(num);
        // place the ships
        for (int i = 0; i < num; i++) {
            placeShip(ships[i]);
        }
    }

    // Define getter and setter methods
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

    // populate the grid with "." characters
    @Override
    public void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.gameGrid[i][j] = ".";
            }
        }
    };

    // this function place the ship on the grid using "*" symbol
    @Override
    public void placeShip(AbstractBattleShip ship) {
        Random r = new Random();
        int[][] coordinates = new int[3][2];
        // use getter method to get the orientation of the ship
        String orient = ship.getShipOrientation();
        int row;
        int col;
        if (orient.equals("horizontal")) {
            row = r.nextInt(height);
            col = r.nextInt(width - 2) + 1;
            coordinates[0][0] = row;
            coordinates[0][1] = col;
            ship.setShipCoordinates(coordinates);
            this.gameGrid[row][col - 1] = "*";
            this.gameGrid[row][col + 1] = "*";
            this.gameGrid[row][col] = "*";
        } else if (orient.equals("vertical")) {
            row = r.nextInt(height - 2) + 1;
            col = r.nextInt(width);
            coordinates[0][0] = row;
            coordinates[0][1] = col;
            ship.setShipCoordinates(coordinates);
            this.gameGrid[row + 1][col] = "*";
            this.gameGrid[row - 1][col] = "*";
            this.gameGrid[row][col] = "*";
        }

    };

    // this should generate ships for both player and the opponent
    @Override
    public void generateShips(int numberOfShips) {
        ships = new AbstractBattleShip[numberOfShips];
        for (int i = 0; i < numberOfShips; i++) {
            String name = "Ship " + (i+1);
            ships[i] = new BattleShip(name);
        }
    }
}
