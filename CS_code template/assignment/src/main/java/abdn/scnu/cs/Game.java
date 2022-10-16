package abdn.scnu.cs;

import java.util.Random;

// import java.io.*;

public class Game implements GameControls {
    int row;
    int col;
    int num;
    PlayerGameGrid myGameGrid;
    OpponentGameGrid oppGameGrid;

    public Game(int row, int col, int num) {
        this.col = col;
        this.row = row;
        this.num = num;
        myGameGrid = new PlayerGameGrid(row, col, num);
        oppGameGrid = new OpponentGameGrid(row, col, num);
    }

    // Use this function to clean the output page
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void playRound(String input) {
        String[] coord = input.split(" ");
        int[] hit_coordinates = { Integer.parseInt(coord[0]), Integer.parseInt(coord[1]) };

        // check the input
        if (hit_coordinates[0] >= this.row || hit_coordinates[1] >= this.col) {
            System.out.printf(
                    "Coordinates out of range, please enter a number less than %d for row and less than %d for column\n",
                    this.row, this.col);
            oppGameGrid.printGrid();
            myGameGrid.printGrid();
            return;
        }
        if (hit_coordinates[0] < 0 || hit_coordinates[1] < 0) {
            System.out.println("Please enter a nonnegative number");
            oppGameGrid.printGrid();
            myGameGrid.printGrid();
            return;
        }
        if (oppGameGrid.gameGrid[hit_coordinates[0]][hit_coordinates[1]] == "%"
                || oppGameGrid.gameGrid[hit_coordinates[0]][hit_coordinates[1]] == "X") {
            System.out.println("The coordinates has been attacked, please choose another one");
            oppGameGrid.printGrid();
            myGameGrid.printGrid();
            return;
        }

        // player's turn
        boolean res = false;
        for (int i = 0; i < oppGameGrid.ships.length; i++) {
            // int[][] ship_coordinate = oppGameGrid.ships[i].shipCoordinates;
            // boolean hit = false;
            if (oppGameGrid.ships[i].checkAttack(hit_coordinates[0], hit_coordinates[1])) {
                System.out.printf("HIT %s!!!", oppGameGrid.ships[i].getName());
                oppGameGrid.gameGrid[hit_coordinates[0]][hit_coordinates[1]] = "X";
                if (!res) {
                    res = true;
                }
            } else {
                if (!res) {
                    oppGameGrid.gameGrid[hit_coordinates[0]][hit_coordinates[1]] = "%";
                }
                // System.out.println("MISS!!!");
            }
            // if(hit){
            // System.out.printf("HIT %s!!!",oppGameGrid.ships[i].name);
            // }else{
            // System.out.println("MISS!!!");
            // }

        }
        if (!res) {
            System.out.println("MISS!!!");
        }
        System.out.println("");

        oppGameGrid.printGrid();

        // opponent's turn
        Random rand = new Random();
        while (true) {
            int r = rand.nextInt(this.row);
            int c = rand.nextInt(this.col);
            if (oppGameGrid.gameGrid[r][c] == "%" || oppGameGrid.gameGrid[r][c] == "X") {
                continue;
            }
            for (int i = 0; i < myGameGrid.ships.length; i++) {
                if (myGameGrid.ships[i].checkAttack(r, c)) {
                    myGameGrid.gameGrid[r][c] = "X";
                } else {
                    myGameGrid.gameGrid[r][c] = "%";
                }
            }
            myGameGrid.printGrid();
            break;
        }
    };

    public boolean checkVictory() {
        int my_deadship = 0;
        int opp_deadship = 0;
        for (int i = 0; i < myGameGrid.ships.length; i++) {
            AbstractBattleShip ship = myGameGrid.ships[i];
            if (ship.getHits() < 3) {
                continue;
            } else {
                my_deadship++;
            }
        }
        for (int i = 0; i < oppGameGrid.ships.length; i++) {
            AbstractBattleShip ship = oppGameGrid.ships[i];
            if (ship.getHits() < 3) {
                continue;
            } else {
                opp_deadship++;
            }
        }
        boolean Iwin = false;
        boolean Ilose = false;
        if (my_deadship == myGameGrid.ships.length) {
            Ilose = true;
        } else if (opp_deadship == oppGameGrid.ships.length) {
            Iwin = true;
        }
        if (Iwin) {
            if (Ilose) {
                System.out.println("End in a tie!");
                return true;
            }
            System.out.println("You have won!");
            return true;
        }
        if (Ilose) {
            System.out.println("You have loss!");
            return true;
        }
        return false;
    };

    public void exitGame(String input) {
        if (input == "exit") {
            System.out.println("Exiting game-thank you for playing");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("There was something wrong");
            }
            ;
            System.exit(0);
        }
    };

    public AbstractGameGrid getPlayersGrid() {
        return myGameGrid;
    };

    public AbstractGameGrid getOpponentssGrid() {
        return oppGameGrid;
    };
}