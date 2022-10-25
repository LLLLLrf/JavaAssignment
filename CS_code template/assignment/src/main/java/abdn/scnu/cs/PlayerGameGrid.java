package abdn.scnu.cs;

public class PlayerGameGrid extends GameGrid{
    public PlayerGameGrid(int height,int width,int num) {
        super(height,width,num);
    }


    public void printGrid(){
        System.out.println("\nMy GameGrid");
        System.out.print(" ");
        for(int i=0;i<gameGrid[0].length;i++){System.out.print(" "+i);}
        System.out.println("");
        for (int i=0;i<gameGrid.length;i++) {
            System.out.print(i);
            for (int j=0;j<gameGrid[0].length;j++) {
                    System.out.print(" "+gameGrid[i][j]);
            }
            System.out.println();
        }
    }
}
