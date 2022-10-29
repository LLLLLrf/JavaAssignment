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

    // Exit the game
    public static void exit() {
        String dots = "";
        for (int i = 0; i < 7; i++) {
            clear();
            System.out.println("Exiting game-thank you for playing" + dots);
            dots = dots + ".";
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("There was something wrong");
            };
        }
        System.exit(0);
    }

    // match the coordinate to avoid misinput
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
    // match the commands to avoid misinput
    public static String findCommand(Pattern p, String input) {
        Matcher m = p.matcher(input);
        String coord="none";
        while (m.find()) {
            coord = m.group(0);
            return coord;
        }
        return coord;
    }

    public static void main(String[] args) {
        clear();
        String pt1 = "(\\d{1,})[\\s,/.](\\d{1,})[\\s,/.](\\d{1,})";
        String pt2 = "(-?\\d{1,})[\\s,/.](-?\\d{1,})";
        String pt3 = "exit|check";
        Pattern p1 = Pattern.compile(pt1);
        Pattern p2 = Pattern.compile(pt2);
        Pattern p3 = Pattern.compile(pt3);
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
            // Catch Exception
            // if the regular expression didn't match anything, the input is incorrect
            if (gridScale[2] == "none") {
                clear();
                System.out.println("Incorrect input");
                continue;
            }
            // if the width or height are less than 3, they are unreasonable
            // if the number of ships more than width*height or less than 1, it is meaningless
            if (Integer.parseInt(gridScale[0]) < 3 || Integer.parseInt(gridScale[1]) < 3 || Integer
                    .parseInt(gridScale[2]) > Integer.parseInt(gridScale[0]) * Integer.parseInt(gridScale[1]) || Integer.parseInt(gridScale[2])<1) {
                clear();
                System.out.println("Unreasonable input");
                continue;
            }
            break;
        };
        int row = Integer.parseInt(gridScale[0]);
        int col = Integer.parseInt(gridScale[1]);
        int num = Integer.parseInt(gridScale[2]);

        Game game = new Game(row, col, num);

        clear();

        while (true) {
            System.out.println("Please enter the position you wish to attack");
            System.out.println("enter 'exit' to quit the game, enter 'check' to see the current record");
            String playerInput = sc.nextLine();
            // match the commands
            if(!findCommand(p3, playerInput).equals("none")){
                playerInput = findCommand(p3, playerInput);
            }
            // check whether the input is "exit"
            if (playerInput.equals("exit")) {
                exit();
            }
            // check whether the input is "check"
            if (game.checkShips(playerInput)) {
                System.out.println("press 'enter' to return to the game");
                sc.nextLine();
                clear();
                System.out.println("\n");
                game.oppGameGrid.printGrid();
                game.myGameGrid.printGrid();
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
            // if game over, wait for 3s and then exit the game
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
