import java.util.Scanner;
public class TicTacToe {
    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    static char currentPlayer = 'X';
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;
        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column, both 1-3). Example: 1 1 for top-left.");
            int row = -1, col = -1;
            boolean validInput = false;
            while (!validInput) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter two integers between 1 and 3 separated by space.");
                    scanner.next(); // consume invalid token
                    continue;
                }
                row = scanner.nextInt();
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter two integers between 1 and 3 separated by space.");
                    continue;
                }
                col = scanner.nextInt();
                row = row - 1;
                col = col - 1;
                if (isValidMove(row, col)) {
                    validInput = true;
                } else {
                    System.out.println("Invalid move. Row and column must be 1-3 and cell must be empty. Try again:");
                }
            }
            board[row][col] = currentPlayer;
            if (hasWon(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (isDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        scanner.close();
    }
    static void printBoard() {
        System.out.println("    1   2   3");
        System.out.println("  -------------");
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1) + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("  -------------");
        }
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
    static boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }
}
