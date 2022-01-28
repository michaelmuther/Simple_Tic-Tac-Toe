import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        printString(input);
        //input XXXOO__O_ X wins             SUCCESS
        //input XOXOXOXXO X wins             SUCCESS
        //input XOOOXOXXO O wins             SUCCESS
        //input XOXOOXXXO Draw               SUCCESS
        //input XO_OOX_X_ Game not finished  SUCCESS
        //input XO_XO_XOX X Impossible       SUCCESS
        //input _O_X__X_X X Impossible       SUCCESS
        //input _OOOO_X_X X Impossible       SUCCESS
        testInput(input);
    }

    public static void testInput(String input){
        String xWins = "X wins"; //X wins && only one winner
        String oWins = "O wins"; //O wins && only one winner
        String draw = "Draw"; // 4 and 5 and no winner
        String gameNotFinished = "Game not finished"; // X and Os are even or 1 apart && less than nine total && no winner
        String impossible = "Impossible"; // 2 more of X or O || 2 winners
        int winXAmt = countWins(input, 'X');
        int winOAmt = countWins(input, 'O');
        int amountXs = countXo(input, 'X');
        int amountOs = countXo(input, 'O');
        int totalPlays = amountXs + amountOs;
        int playDifference = Math.abs(amountXs - amountOs);
        int totalWins = winXAmt + winOAmt;
        boolean impossibleFlag = (totalWins > 1) || (playDifference > 1);
        boolean gameNotFinishedFlag = (totalPlays < 9) && (totalWins < 1);
        boolean drawFlag = (totalPlays == 9) && (totalWins == 0);
        boolean xWinsFlag = winXAmt == 1;
        boolean oWinsFlag = winOAmt == 1;

        if (impossibleFlag) {
            System.out.println(impossible);
        } else if (gameNotFinishedFlag) {
            System.out.println(gameNotFinished);
        } else if (drawFlag) {
            System.out.println(draw);
        } else if (xWinsFlag) {
            System.out.println(xWins);
        } else if (oWinsFlag) {
            System.out.println(oWins);
        }
    }

    public static int countWins(String input, char xo) {
        int wins = 0;
        if (input.charAt(0) == xo && input.charAt(1) == xo && input.charAt(2) == xo) { //row 1 horizontal
            wins++;
        } else if (input.charAt(3) == xo && input.charAt(4) == xo && input.charAt(5) == xo) { // row 2 horizontal
            wins++;
        } else if (input.charAt(6) == xo && input.charAt(7) == xo && input.charAt(8) == xo) { // row 3 horizontal
            wins++;
        } else if (input.charAt(0) == xo && input.charAt(3) == xo && input.charAt(6) == xo) { // column 1 vertical
            wins++;
        } else if (input.charAt(1) == xo && input.charAt(4) == xo && input.charAt(7) == xo) { // column 2 vertical
            wins++;
        } else if (input.charAt(2) == xo && input.charAt(5) == xo && input.charAt(8) == xo) { // column3 vertical
            wins++;
        } else if (input.charAt(0) == xo && input.charAt(4) == xo && input.charAt(8) == xo) { // backslash diagonal
            wins++;
        } else if (input.charAt(2) == xo && input.charAt(4) == xo && input.charAt(6) == xo) { // forwardslash diagonal
            wins++;
        }
        return wins;
    }

    public static int countXo (String input, char xo) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == xo) {
                count++;
            }
        }
        return count;
    }

    public static void printString(String input) {
        String horizontalBorder = ("---------");
        String row1 = ("| " + input.charAt(0) + " " + input.charAt(1) + " " + input.charAt(2) + " |");
        String row2 = ("| " + input.charAt(3) + " " + input.charAt(4) + " " + input.charAt(5) + " |");
        String row3 = ("| " + input.charAt(6) + " " + input.charAt(7) + " " + input.charAt(8) + " |");
        System.out.println(horizontalBorder);
        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
        System.out.println(horizontalBorder);
    }
}
