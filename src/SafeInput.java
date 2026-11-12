import java.util.Scanner;

public class SafeInput
{
    /**
     * Gets an int within an inclusive range from the user.
     * @param console Scanner to read from
     * @param prompt  prompt shown to the user (no range text needed)
     * @param low     lowest allowed value
     * @param high    highest allowed value
     * @return a value between low and high inclusive
     */
    public static int getRangedInt(Scanner console, String prompt, int low, int high)
    {
        int value = 0;
        boolean done = false;
        String trash;

        do
        {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (console.hasNextInt())
            {
                value = console.nextInt();
                console.nextLine(); // clear the rest of the line
                if (value >= low && value <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("Number out of range: " + value);
                }
            }
            else
            {
                trash = console.nextLine();
                System.out.println("You must enter an integer, not: " + trash);
            }
        } while (!done);

        return value;
    }

    /**
     * Gets a Y or N confirmation from the user.
     * @param console Scanner to read from
     * @param prompt  prompt shown to the user
     * @return true for Y/y, false for N/n
     */
    public static boolean getYNConfirm(Scanner console, String prompt)
    {
        String response;
        boolean result = false;
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = console.nextLine().trim();
            if (response.equalsIgnoreCase("Y"))
            {
                result = true;
                done = true;
            }
            else if (response.equalsIgnoreCase("N"))
            {
                result = false;
                done = true;
            }
            else
            {
                System.out.println("Please enter Y or N, not: " + response);
            }
        } while (!done);

        return result;
    }
}