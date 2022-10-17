package abdn.scnu.cs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunGame {

    public static void exit() {
        System.out.println("Exiting game-thank you for playing...");
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was something wrong");
        };
        System.exit(0);
    }

    public static String[] findCoordinate(Pattern p, String input) {
        Matcher m = p.matcher(input);
        String[] coord = { "none", "none", "none" };
        while (m.find()) {
            coord[0] = m.group(1);
            coord[1] = m.group(2);
            try {
                coord[2] = m.group(3);
            } catch (IndexOutOfBoundsException NumberFormatException) {

            }
            return coord;
        }
        return coord;
    }

    public static void main(String[] args) {
        String pt1 = "(\\d?\\d?\\d?\\d)[\\s,/.](\\d?\\d?\\d?\\d)[\\s,/.](\\d?\\d?\\d?\\d)";
        String pt2 = "(-?\\d?\\d?\\d?\\d)[\\s,/.](-?\\d?\\d?\\d?\\d)";
        Pattern p1 = Pattern.compile(pt1);
        Pattern p2 = Pattern.compile(pt2);
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the size of game grid and the number of ships");

        String input = sc.nextLine();
        if (input.equals("exit")) {
            sc.close();
            exit();
        }
        String[] gridScale = findCoordinate(p1, input);
        int row = Integer.parseInt(gridScale[0]);
        int col = Integer.parseInt(gridScale[1]);
        int num = Integer.parseInt(gridScale[2]);

        Game game = new Game(row, col, num);

        while (true) {
            String playerInput = sc.nextLine();
            if(playerInput.equals("exit")){exit();}
            game.clearScreen();
            String[] temp = findCoordinate(p2, playerInput);
            if (temp[0] == "none") {
                System.out.println("Incorrect input");
                System.out.println("");
                game.oppGameGrid.printGrid();
                game.myGameGrid.printGrid();
                continue;
            }
            int tmp1 = Integer.parseInt(temp[0]);
            int tmp2 = Integer.parseInt(temp[1]);
            temp[0] = String.valueOf(tmp1);
            temp[1] = String.valueOf(tmp2);
            String coordinates = temp[0] + "," + temp[1];
            // System.out.println(coordinates);
            game.playRound(coordinates);
            // game.myGameGrid.printGrid();
            if (game.checkVictory()) {
                exit();
            }

        }
    }
}
