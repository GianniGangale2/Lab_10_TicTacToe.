import java.util.Scanner;

public class TicTacToe
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        // PSEUDO CODE
        // do (play again loop)
        //   clear the board, set player to X, moveCount = 0, done = false
        //   do (move loop)
        //     show the board
        //     do: get row and col (1-3), convert to 0-2, until isValidMove
        //     record the move, increment moveCount
        //     if moveCount >= 5 check isWin -> announce winner, done
        //     else if moveCount >= 7 check isTie -> announce tie, done
        //     toggle player
        //   while not done
        //   ask to play again
        // while play again

        boolean playAgain;
        do
        {
            String player = "X";
            int moveCount = 0;
            boolean done = false;
            int row, col;

            clearBoard();

            do
            {
                display();

                do
                {
                    System.out.println("\nPlayer " + player + "'s turn.");
                    row = SafeInput.getRangedInt(in, "Enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(in, "Enter column", 1, COLS) - 1;
                    if (!isValidMove(row, col))
                    {
                        System.out.println("That space is already taken. Try again.");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCount++;

                if (moveCount >= 5 && isWin(player))
                {
                    display();
                    System.out.println("\nPlayer " + player + " wins!");
                    done = true;
                }
                else if (moveCount >= 7 && isTie())
                {
                    display();
                    System.out.println("\nIt's a tie!");
                    done = true;
                }
                else
                {
                    player = player.equals("X") ? "O" : "X";
                }
            } while (!done);

            playAgain = SafeInput.getYNConfirm(in, "Play again?");
        } while (playAgain);

        System.out.println("\nThanks for playing!");
    }

    // sets all the board elements to a space
    private static void clearBoard()
    {
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                board[r][c] = " ";
    }

    // shows the board
    private static void display()
    {
        System.out.println();
        System.out.println("      1   2   3");
        for (int r = 0; r < ROWS; r++)
        {
            System.out.print("  " + (r + 1) + "   ");
            for (int c = 0; c < COLS; c++)
            {
                System.out.print(board[r][c]);
                if (c < COLS - 1) System.out.print(" | ");
            }
            System.out.println();
            if (r < ROWS - 1) System.out.println("     ---+---+---");
        }
    }

    // true if the space at row, col is empty
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }

    private static boolean isColWin(String player)
    {
        for (int c = 0; c < COLS; c++)
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player))
                return true;
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int r = 0; r < ROWS; r++)
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player))
                return true;
        return false;
    }

    private static boolean isDiagnalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
                || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    // tie: board full, or every win vector contains both an X and an O
    private static boolean isTie()
    {
        boolean full = true;
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                if (board[r][c].equals(" ")) full = false;
        if (full) return true;

        for (int r = 0; r < ROWS; r++)
            if (!blocked(board[r][0], board[r][1], board[r][2])) return false;
        for (int c = 0; c < COLS; c++)
            if (!blocked(board[0][c], board[1][c], board[2][c])) return false;
        if (!blocked(board[0][0], board[1][1], board[2][2])) return false;
        if (!blocked(board[0][2], board[1][1], board[2][0])) return false;
        return true;
    }

    // true if the three cells contain both an X and an O
    private static boolean blocked(String a, String b, String c)
    {
        boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
        boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");
        return hasX && hasO;
    }
}
