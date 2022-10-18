package abdn.scnu.cs;

import java.util.Random;
// import abdn.scnu.cs.RunGame;

// import java.io.*;

public class Game implements GameControls{
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

    public void playRound(String input) {
        String[] coord = input.split(",");
        int[] hit_coordinates = { Integer.parseInt(coord[0]), Integer.parseInt(coord[1]) };

        // check the input
        if (hit_coordinates[0] >= this.row || hit_coordinates[1] >= this.col) {
            System.out.printf(
                    "Coordinates out of range, please enter a number less than %d for row and less than %d for column\n\n",
                    this.row, this.col);
            oppGameGrid.printGrid();
            myGameGrid.printGrid();
            return;
        }
        if (hit_coordinates[0] < 0 || hit_coordinates[1] < 0) {
            System.out.println("Please enter a nonnegative number\n");
            oppGameGrid.printGrid();
            myGameGrid.printGrid();
            return;
        }
        if (oppGameGrid.gameGrid[hit_coordinates[0]][hit_coordinates[1]] == "%"
                || oppGameGrid.gameGrid[hit_coordinates[0]][hit_coordinates[1]] == "X") {
            System.out.println("The coordinates has been attacked, please choose another one\n");
            oppGameGrid.printGrid();
            myGameGrid.printGrid();
            return;
        }

        // player's turn

        // mark the result, if hit, res=true
        boolean res = false;
        // traverse all the ships
        for (int i = 0; i < oppGameGrid.ships.length; i++) {
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
            }
        }

        if (!res) {
            System.out.print("MISS!!!");
        }

        System.out.println("\n");
        oppGameGrid.printGrid();

        // opponent's turn
        Random rand = new Random();
        res = false;
        while (true) {
            int r = rand.nextInt(this.row);
            int c = rand.nextInt(this.col);
            // if the coordinate has been attacked, randomly choose another one
            if (myGameGrid.gameGrid[r][c].equals("%") || myGameGrid.gameGrid[r][c].equals("X")) {
                continue;
            }
            for (int i = 0; i < myGameGrid.ships.length; i++) {
                if (myGameGrid.ships[i].checkAttack(r, c)) {
                    myGameGrid.gameGrid[r][c] = "X";
                    if(!res){
                        res=true;
                    }
                } else {
                    if(!res){
                        myGameGrid.gameGrid[r][c] = "%";
                    }
                }
            }
            myGameGrid.printGrid();
            break;
        }
    };

    public boolean checkShips(String input){
        
        if(input.equals("check")){
            RunGame.clear();
            System.out.println("___________myships___________");
            for (int i = 0; i < myGameGrid.ships.length; i++) {
                AbstractBattleShip ship = myGameGrid.ships[i];
                if (ship.getHits() < 3) {
                    System.out.println(ship.name+" alive"+" hits:"+String.valueOf(ship.hits));
                } else {
                    System.out.println(ship.name+" dead");
                }
            }
            System.out.println("_________opponentship_________");
            for (int i = 0; i < oppGameGrid.ships.length; i++) {
                AbstractBattleShip ship = oppGameGrid.ships[i];
                if (ship.getHits() < 3) {
                    System.out.println(ship.name+" alive"+" hits:"+String.valueOf(ship.hits));
                } else {
                    System.out.println(ship.name+" dead");
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkVictory() {
        // record the number of dead ships, if all die, game over
        int my_deadships = 0;
        int opp_deadships = 0;
        for (int i = 0; i < myGameGrid.ships.length; i++) {
            AbstractBattleShip ship = myGameGrid.ships[i];
            // if hits>=3, the ship has died
            if (ship.getHits() < 3) {
                continue;
            } else {
                my_deadships++;
            }
        }
        for (int i = 0; i < oppGameGrid.ships.length; i++) {
            AbstractBattleShip ship = oppGameGrid.ships[i];
            if (ship.getHits() < 3) {
                continue;
            } else {
                opp_deadships++;
            }
        }
        boolean Iwin = false;
        boolean Ilose = false;
        if (my_deadships == myGameGrid.ships.length) {
            Ilose = true;
        }
        if(opp_deadships == oppGameGrid.ships.length) {
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
            System.out.println("You have lost!");
            return true;
        }
        return false;
    };

    public void exitGame(String input) {
        if (input == "exit") {
            RunGame.exit();
        }
    };

    public AbstractGameGrid getPlayersGrid() {
        return myGameGrid;
    };

    public AbstractGameGrid getOpponentssGrid() {
        return oppGameGrid;
    };
}