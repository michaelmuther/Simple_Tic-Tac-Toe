import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String enterCoordinates = "Enter the coordinates: ";
        String invalidInput = "You should enter numbers!";
        String inputNotInRange = "Coordinates should be from 1 to 3!";
        String cellOccupied = "This cell is occupied! Choose another one!";
        boolean xTurn = true;
        char turn = 'X';
        Scanner scanner = new Scanner(System.in);

        char[][] gameGrid = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        printGameGrid(gameGrid);
        
        /* within a do while loop:
            while (scanner.hasNextInt)
            confirm inputs are numbers, if not, then loop through and ask for them again.
            then
            confirm inputs are from 1 to 3, if not, then loop through and ask for them again.
            then
            check the inputs against the grid, if trying to enter in occupied cell, ask for inputs again.
            display reason input is invalid.
         */
        boolean validCoordinateInput = false;
        String errorMessage = "";
        int one = 1; // difference between coordinate and index
        int[] cellIndices = new int[2];
        do {
            turn = xTurn ? 'X' : 'O';
            do {
                boolean inputFormatValid = false;
                int validInputs = 0;
                do { // check if input is two numbers
                    System.out.print(enterCoordinates);
                    inputFormatValid = false;
                    validInputs = 0;
                    String input1String = scanner.next();
                    String input2String = scanner.next();
                    if (isNumeric(input1String)) {
                        cellIndices[0] = Integer.parseInt(input1String) - one;
                        validInputs++;
                    }
                    if (isNumeric(input2String)) {
                        cellIndices[1] = Integer.parseInt(input2String) - one;
                        validInputs++;
                    }
                    if (validInputs == 2) {
                        inputFormatValid = true;
                    } else {
                        System.out.println(invalidInput);
                    }
                } while (!inputFormatValid);

                if (cellIndices[0] >= 0 && cellIndices[0] <= 2 && cellIndices[1] >= 0 && cellIndices[1] <= 2) {
                    if (checkCellInput(cellIndices, gameGrid)) {
                        System.out.println(cellOccupied);
                    } else {
                        validCoordinateInput = true;
                        gameGrid[cellIndices[0]][cellIndices[1]] = turn;
                        xTurn = !xTurn;
                    }
                } else {
                    System.out.println(inputNotInRange);
                }
            } while (!validCoordinateInput);
            printGameGrid(gameGrid);
        } while (!checkGameOver(gameGrid, turn));
//        testInput(input);

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean checkCellInput(int[] cellIndices, char[][] gameGrid) {
        int index1 = cellIndices[0];
        int index2 = cellIndices[1];
        return gameGrid[index1][index2] == 'X' || gameGrid[index1][index2] == 'O';
    }

    public static boolean checkGameOver(char[][] gameGrid, char xo) {
        String winMessage = xo +" wins";
        String draw = "Draw";
        int turns = 0;
        boolean win = false;
        if (gameGrid[0][0] == xo && gameGrid[0][1] == xo && gameGrid[0][2] == xo) { //row 1 horizontal
            win = true;
        } else if (gameGrid[1][0] == xo && gameGrid[1][1] == xo && gameGrid[1][2] == xo) { // row 2 horizontal
            win = true;
        } else if (gameGrid[2][0] == xo && gameGrid[2][1] == xo && gameGrid[2][2] == xo) { // row 3 horizontal
            win = true;
        } else if (gameGrid[0][0] == xo && gameGrid[1][0] == xo && gameGrid[2][0] == xo) { // column 1 vertical
            win = true;
        } else if (gameGrid[0][1] == xo && gameGrid[1][1] == xo && gameGrid[2][1] == xo) { // column 2 vertical
            win = true;
        } else if (gameGrid[0][2] == xo && gameGrid[1][2] == xo && gameGrid[2][2] == xo) { // column3 vertical
            win = true;
        } else if (gameGrid[0][0] == xo && gameGrid[1][1] == xo && gameGrid[2][2] == xo) { // backslash diagonal
            win = true;
        } else if (gameGrid[0][2] == xo && gameGrid[1][1] == xo && gameGrid[2][0] == xo) { // forwardslash diagonal
            win = true;
        }
        if(win) {
            System.out.println(winMessage);
            return win;
        }
        for (int i = 0; i <gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j] != ' ') {
                    turns++;
                }
            }
        }
        if(turns == 9) {
            System.out.println(draw);
            return true;
        }
        return win;
    }

    public static int countXo (char[][] gameGrid, char xo) {
        int count = 0;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j] == xo) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void printGameGrid(char[][] gameGrid) {
        String horizontalBorder = ("---------");
        String row1 = ("| " + gameGrid[0][0] + " " + gameGrid[0][1] + " " + gameGrid[0][2] + " |");
        String row2 = ("| " + gameGrid[1][0] + " " + gameGrid[1][1] + " " + gameGrid[1][2] + " |");
        String row3 = ("| " + gameGrid[2][0] + " " + gameGrid[2][1] + " " + gameGrid[2][2] + " |");
        System.out.println(horizontalBorder);
        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
        System.out.println(horizontalBorder);
    }
}


