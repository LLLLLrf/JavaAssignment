package abdn.scnu.cs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunGame {
    // Use this function to clear the console
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void exit() {
        String dots = "";
        for (int i = 0; i < 7; i++) {
            clear();
            System.out.println("Exiting game-thank you for playing" + dots);
            dots = dots + ".";
            try {
                Thread.sleep(250);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("There was something wrong");
            }
            ;
        }
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
        clear();
        String pt1 = "(\\d?\\d?\\d?\\d)[\\s,/.](\\d?\\d?\\d?\\d)[\\s,/.](\\d?\\d?\\d?\\d)";
        String pt2 = "(-?\\d?\\d?\\d?\\d)[\\s,/.](-?\\d?\\d?\\d?\\d)";
        Pattern p1 = Pattern.compile(pt1);
        Pattern p2 = Pattern.compile(pt2);
        Scanner sc = new Scanner(System.in);
        String[] gridScale;

        while (true) {

            System.out.println("Please enter the size of game grid and the number of ships");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                sc.close();
                exit();
            }
            gridScale = findCoordinate(p1, input);
            // if the regular expression didn't match anything, the input is incorrect
            if (gridScale[2] == "none") {
                clear();
                System.out.println("Incorrect input");
                continue;
            }
            // if the width or height are less than 3, they are unreasonable, if the number of ships more than width*height, it is also meaningless
            if (Integer.parseInt(gridScale[0]) < 3 || Integer.parseInt(gridScale[1]) < 3 || Integer
                    .parseInt(gridScale[2]) > Integer.parseInt(gridScale[0]) * Integer.parseInt(gridScale[1])) {
                clear();
                System.out.println("Unreasonable input");
                continue;
            }
            break;
        }
        ;
        int row = Integer.parseInt(gridScale[0]);
        int col = Integer.parseInt(gridScale[1]);
        int num = Integer.parseInt(gridScale[2]);

        Game game = new Game(row, col, num);

        clear();

        while (true) {
            System.out.println("Please enter the position you wish to attack");
            System.out.println("enter 'exit' to quit the game, enter 'check' to see the current record");
            String playerInput = sc.nextLine();
            // check whether the input is "exit" or "check"
            if (playerInput.equals("exit")) {
                exit();
            }
            if (game.checkShips(playerInput)) {
                System.out.println("press 'enter' to return to the game");
                sc.nextLine();
                clear();
                continue;
            }
            // clear the console
            clear();
            // use regular expression to match the coordinate
            String[] temp = findCoordinate(p2, playerInput);
            // if the regular expression didn't match anything, the input is incorrect
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
            game.playRound(coordinates);
            // if game over, wait for 3s and quit the game
            if (game.checkVictory()) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("There was something wrong");
                };
                exit();
            }

        }
    }
}
