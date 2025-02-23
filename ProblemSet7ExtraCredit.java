/* This code is my own work. It was written without consulting code written by
other students or code from online resources. Daniel Cardenas*/

import java.util.Scanner;
import java.util.Random;

public class ProblemSet7ExtraCredit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = createGrid();
        placeMines(grid, 10);
        calculateHints(grid);

        boolean gameRunning = true;
        while (gameRunning) {
            printGrid(grid);
            System.out.print("Enter row (0-5): ");
            int row = scanner.nextInt();
            System.out.print("Enter col (0-5): ");
            int col = scanner.nextInt();

            if (!revealCell(grid, row, col)) {
                gameRunning = false;
            } else {
                // Check if all safe cells are revealed
                boolean allSafeCellsRevealed = true;
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j] == ' ') {
                            allSafeCellsRevealed = false;
                            break;
                        }
                    }
                }
                if (allSafeCellsRevealed) {
                    System.out.println("Congratulations! You've revealed all safe cells.");
                    gameRunning = false;
                }
            }
        }

        scanner.close();
    }

    public static char[][] createGrid() {
        int rows = 6;
        int cols = 6;
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' ';
            }
        }
        return grid;
    }

    public static void placeMines(char[][] grid, int numMines) {
        Random random = new Random();
        int rows = grid.length;
        int cols = grid[0].length;
        int minesPlaced = 0;

        while (minesPlaced < numMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (grid[row][col] != '*') {
                grid[row][col] = '*';
                minesPlaced++;
            }
        }
    }

    public static void calculateHints(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ' ') {
                    int mineCount = 0;

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            int newRow = i + x;
                            int newCol = j + y;

                            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                                if (grid[newRow][newCol] == '*') {
                                    mineCount++;
                                }
                            }
                        }
                    }

                    if (mineCount > 0) {
                        grid[i][j] = (char) (mineCount + '0');
                    }
                }
            }
        }
    }

    public static boolean revealCell(char[][] grid, int row, int col) {
        if (grid[row][col] == '*') {
            System.out.println("Game Over! You hit a mine.");
            return false;
        } else if (grid[row][col] != ' ') {
            System.out.println("Cell already revealed.");
            return true;
        } else {
            revealAdjacentCells(grid, row, col);
            return true;
        }
    }

    private static void revealAdjacentCells(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != ' ') {
            return;
        }

        int mineCount = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int newRow = row + x;
                int newCol = col + y;

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (grid[newRow][newCol] == '*') {
                        mineCount++;
                    }
                }
            }
        }

        if (mineCount > 0) {
            grid[row][col] = (char) (mineCount + '0');
        } else {
            grid[row][col] = '0';
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    revealAdjacentCells(grid, row + x, col + y);
                }
            }
        }
    }

    public static void printGrid(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ' ') {
                    System.out.print("_ ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
