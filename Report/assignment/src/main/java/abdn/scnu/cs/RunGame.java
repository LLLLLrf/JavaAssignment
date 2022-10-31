// Welcome to this game! Please make sure you have check README.md before you run the program
package abdn.scnu.cs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunGame {
    // Use this function to clear the console output
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

    // match the coordinate to avoid mistyping
    public static String[] findCoordinate(Pattern p, String input) {
        Matcher m = p.matcher(input);
        String[] coord = { "none", "none", "none" };
        while (m.find()) {
            coord[0] = m.group(1);
            coord[1] = m.group(2);
            try {
                coord[2] = m.group(3);
            } catch (IndexOutOfBoundsException NumberFormatException) {}
            return coord;
        }
        return coord;
    }
    // match the commands to avoid mistyping
    public static String findCommand(Pattern p, String input) {
        Matcher m = p.matcher(input);
        String coord="none";
        while (m.find()) {
            coord = m.group(0);
            return coord;
        }
        return coord;
    }
    // catch exception, see whether the input is valid
    public static boolean checkInput(String[] gridScale){
        // if the regular expression didn't match anything, the input is incorrect
        if (gridScale[2] == "none") {
            clear();
            System.out.println("Incorrect input");
            sleep3s();
            return false;
        }
        // if the width or height are less than 3, they are unreasonable
        // if the number of ships more than width*height or less than 1, it is meaningless
        if (Integer.parseInt(gridScale[0]) < 3 || Integer.parseInt(gridScale[1]) < 3 || Integer
                .parseInt(gridScale[2]) > Integer.parseInt(gridScale[0]) * Integer.parseInt(gridScale[1]) || Integer.parseInt(gridScale[2])<1) {
            clear();
            System.out.println("Unreasonable input");
            sleep3s();
            return false;
        }
        return true;
    }

    public static void sleep3s(){
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was something wrong");
        };
    }


    // main function
    public static void main(String[] args) {
        clear();
        String pt1 = "(\\d{1,})[\\s,/.](\\d{1,})[\\s,/.](\\d{1,})";
        String pt2 = "(-?\\d{1,})[\\s,/.](-?\\d{1,})";
        String pt3 = "exit|check";
        Pattern p1 = Pattern.compile(pt1);
        Pattern p2 = Pattern.compile(pt2);
        Pattern p3 = Pattern.compile(pt3);
        String[] gridScale;
        Scanner sc = new Scanner(System.in);
        
        // this version do not need any arguments of main method
        /* 
        while (true) {
            System.out.println("Please enter the size of game grid and the number of ships");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                sc.close();
                exit();
            }
            gridScale = findCoordinate(p1, input);
            // Catch Exception
            if(!checkInput(gridScale)){
                sc.close();
                continue;
            }
            break;
        };
        */

        // this version use the arguments of the main method
        String input = String.join(",",args);
        gridScale = findCoordinate(p1, input);
        // Catch Exception
        if(!checkInput(gridScale)){
            sc.close();
            exit();
        }

        int row = Integer.parseInt(gridScale[0]);
        int col = Integer.parseInt(gridScale[1]);
        int num = Integer.parseInt(gridScale[2]);
        // create an instance of Game class
        Game game = new Game(row, col, num);
        game.oppGameGrid.printGrid();
        game.myGameGrid.printGrid();

        // start the game round
        while (true) {
            System.out.println("Please enter the position you wish to attack");
            System.out.println("enter 'exit' to quit the game, enter 'check' to see the current record");
            String playerInput = sc.nextLine();
            // clear the console
            clear();
            // match the commands
            if(!findCommand(p3, playerInput).equals("none")){
                playerInput = findCommand(p3, playerInput);
            }
            // check whether the input is "exit"
            if (playerInput.equals("exit")) {
                // close the scanner before exit
                sc.close();
                exit();
            }
            // check whether the input is "check"
            if (game.checkShips(playerInput)) {
                sc.nextLine();
                clear();
                System.out.println("\n");
                game.oppGameGrid.printGrid();
                game.myGameGrid.printGrid();
                continue;
            }

            // use regular expression to match the coordinates
            String[] coord = findCoordinate(p2, playerInput);

            // if the regular expression didn't match anything, the input is incorrect
            if (coord[0] == "none") {
                System.out.println("Incorrect input\n");
                game.oppGameGrid.printGrid();
                game.myGameGrid.printGrid();
                continue;
            }
            String coordinates = coord[0] + "," + coord[1];

            // pass the attack coordinates input by player to the playRound() method
            game.playRound(coordinates);
            // if game is over, wait for 3s and then exit the game
            if (game.checkVictory()) {
                sleep3s();
                sc.close();
                exit();
            }
        }
    }
}
