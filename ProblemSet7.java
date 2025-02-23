/* This code is my own work. It was written without consulting code written by
other students or code from online resources. Daniel Cardenas*/

import java.util.Scanner;

public class ProblemSet7 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // to get input from players

        char[][] grid = createGrid();

        int turn = 1;
        String player = "Red";
        boolean winner = false;

        //while loop to play the game until either there is a winner or the board is full
        while (winner == false && turn <= 42){
            boolean validPlay = false;
            int play = -1;
            while(!validPlay){
                displayGrid(grid);
                System.out.print("Player " + player + ", choose a column: ");
                play = in.nextInt(); // receive input from player

                // validate play
                validPlay = validate(grid, play);

            }

            // place the token
            placeToken(grid, play, player);

            //determine if there is a winner
            winner = isWinner(grid, player);

            //switch players
            if (player.equals("Red")){
                player = "Blue";
            }else{
                player = "Red";
            }

            turn++;
        }
        displayGrid(grid);

        if (winner){
            if (player.equals("Red")){
                System.out.println("Player 2 (Blue) won");
            }else{
                System.out.println("Player 1 (Red) won");
            }
        }else{
            System.out.println("Tie game");
        }

    }

    public static char[][] createGrid(){
        int rows = 6;
        int cols = 7;

        char[][] grid = new char[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                grid[i][j] = ' ';
            }
        }
        return grid;
    }

    public static void displayGrid(char[][] grid){
        for(int i = 1; i <= grid[0].length; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i = 0; i < grid.length; i++) {
            System.out.println("---------------");
            for(int j = 0; j < grid[i].length; j++){
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    public static boolean validate(char[][] grid, int play){
        if(play < 1 || play > 7) {
            return false;
        }

        int col = play - 1;
        for(int i = 0; i < grid.length; i++){
            if(grid[i][col] == ' '){
                return true;
            }
        }
        return false;
    }

    public static void placeToken(char[][] grid, int play, String player){
        char token;
        if (player.equals("Red")){
            token = 'R';
        }
        else{
            token = 'B';
        }

        int col = play - 1;
        for(int i = grid.length - 1; i >= 0; i--){
            if(grid[i][col] == ' '){
                grid[i][col] = token;
                break;
            }
        }
    }

    public static boolean winHorizontal(char[][] grid, String player){
        char token;
        if(player.equals("Red")){
            token = 'R';
        }
        else{
            token = 'B';
        }

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col <= grid[0].length - 4; col++){
                if(grid[row][col] == token && grid[row][col + 1] == token && grid[row][col + 2] == token && grid[row][col + 3] == token){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean winVertical(char[][] grid, String player){
        char token;
        if(player.equals("Red")){
            token = 'R';
        }
        else{
            token = 'B';
        }

        for(int col = 0; col < grid[0].length; col++){
            for(int row = 0; row <= grid.length - 4; row++){
                if(grid[row][col] == token && grid[row + 1][col] == token &&
                        grid[row + 2][col] == token && grid[row + 3][col] == token){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean winDiagonal(char[][] grid, String player) {
        char token;
        if(player.equals("Red")){
            token = 'R';
        }
        else{
            token = 'B';
        }

        for(int row = 0; row <= grid.length - 4; row++){
            for(int col = 0; col <= grid[0].length - 4; col++){
                if(grid[row][col] == token && grid[row + 1][col + 1] == token &&
                        grid[row + 2][col + 2] == token && grid[row + 3][col + 3] == token){
                    return true;
                }
            }
        }

        for(int row = 3; row < grid.length; row++){
            for(int col = 0; col <= grid[0].length - 4; col++){
                if(grid[row][col] == token && grid[row - 1][col + 1] == token && grid[row - 2][col + 2] == token && grid[row - 3][col + 3] == token){
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isWinner(char[][] grid, String player){
        return winHorizontal(grid, player) || winVertical(grid, player) || winDiagonal(grid, player);
    }
}