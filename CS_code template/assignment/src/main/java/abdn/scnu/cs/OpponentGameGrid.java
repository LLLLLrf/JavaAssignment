package abdn.scnu.cs;

public class OpponentGameGrid extends GameGrid {

    public OpponentGameGrid(int height, int width, int num) {
        super(height, width, num);
    }

    public void printGrid() {
        System.out.println("Opponent's GameGrid");
        System.out.print(" ");
        for (int i = 0; i < gameGrid.length; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");
        for (int i = 0; i < gameGrid.length; i++) {
            System.out.print(i);
            for (int j = 0; j < gameGrid[0].length; j++) {
                if (gameGrid[i][j] == "*") {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + gameGrid[i][j]);
                }
            }
            System.out.println();
        }
    }
}
