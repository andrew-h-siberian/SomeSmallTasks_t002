import java.util.Scanner;

public class Program {
    private static final int SIZE_LOWER_LIMIT = 1;
    private static final int SIZE_UPPER_LIMIT = 32;
    // INPUT_TO_EXIT is case insensitive
    private static final String INPUT_TO_EXIT = "q";

    public static void main(String[] args) {
        // init tableFinalNumber with 0 to NOT satisfy the conditions of a variable value being between 1 and 32
        int tableFinalNumber = 0;
        int tableMaxNumberLength;
        int tableLeftIndexWidth;
        boolean inputValid = false;

        try (Scanner sc = new Scanner(System.in)) {
            while (!inputValid) {
                System.out.println("Enter size of the table (from " + SIZE_LOWER_LIMIT +
                        " to " + SIZE_UPPER_LIMIT + "), or " + INPUT_TO_EXIT + " to exit: ");
                if (sc.hasNextInt()) {
                    tableFinalNumber = sc.nextInt();
                    if (tableFinalNumber >= SIZE_LOWER_LIMIT && tableFinalNumber <= SIZE_UPPER_LIMIT) {
                        inputValid = true;
                    } else {
                        // reset tableFinalNumber, or after exit code will print table of this wrong size
                        tableFinalNumber = 0;
                        System.out.println("Number is not between " + SIZE_LOWER_LIMIT +
                                " and " + SIZE_UPPER_LIMIT + "! Try again.");
                    }
                } else {
                    if (sc.hasNext() && sc.next().equalsIgnoreCase(INPUT_TO_EXIT)) {
                        inputValid = true;
                    } else {
                        System.out.println("You should enter a number (integer) only.");
                        sc.nextLine();
                    }
                }
            }
        }

        // if exit was not because of exit conditions then tableFinalNumber is in given range, else it's 0 (so no output)
        if (0 != tableFinalNumber) {

            tableMaxNumberLength = getDecNumberLength(tableFinalNumber * tableFinalNumber);
            tableLeftIndexWidth = getDecNumberLength(tableFinalNumber);

            for (int i = 0; i <= (tableFinalNumber * 2) + 1; i++) {
                for (int j = 0; j <= tableFinalNumber; j++) {

                    if (i % 2 == 1) {

                        // lines with cell borders

                        if (0 == j) {

                            // first column (left indexes)
                            printCellHorizontalBorder(tableLeftIndexWidth, true);

                        } else {

                            // usual cells bottom borders
                            printCellHorizontalBorder(tableMaxNumberLength, false);

                        }


                    } else {

                        // (even) lines with numbers (number rows)

                        if (i > 0 && j > 0) {
                            // usual cell "in the field"
                            printCell((i / 2) * j, tableMaxNumberLength);

                        } else if (0 == i && j > 0) {
                            // for a first row (upper/top index)
                            printCell(j, tableMaxNumberLength);

                        } else if (0 == j && i > 0) {
                            // for a first column (left index)
                            printCellOfLeftIndex(i / 2, tableLeftIndexWidth);

                        } else if (0 == i && 0 == j) {
                            // for the first one cell (which is empty)
                            for (int s = 0; s < tableLeftIndexWidth; s++) {
                                System.out.print(" ");
                            }
                        }
                    }
                }
                System.out.println();
            }
        }

    }


    // count digits in given number
    private static int getDecNumberLength(int numberToCheckLengthOf) {
        return String.valueOf(numberToCheckLengthOf).length();
    }


    // printCell will print some spaces before number, depending on the length of number and maximum width needed
    private static void printCell(int numberToPrintInCell, int lengthOfColumn) {
        System.out.print("|");
        for (int i = 0; i < lengthOfColumn - getDecNumberLength(numberToPrintInCell); i++) {
            System.out.print(" ");
        }
        System.out.print(numberToPrintInCell);
    }


    // printCellOfLeftIndex won't print "|" first (it's not implemented as overloaded function because of review!)
    private static void printCellOfLeftIndex(int numberToPrintInCell, int lengthOfColumn) {
        for (int i = 0; i < lengthOfColumn - getDecNumberLength(numberToPrintInCell); i++) {
            System.out.print(" ");
        }
        System.out.print(numberToPrintInCell);
    }


    // printCellHorizontalBorder with option isFirst to prevent printing "+" for first column (no "lines" crossing)
    private static void printCellHorizontalBorder(int lengthOfLine, boolean isFirst) {
        if (isFirst) {
            for (int i = 0; i < lengthOfLine; i++) {
                System.out.print("-");
            }
        } else {
            System.out.print("+");
            for (int i = 0; i < lengthOfLine; i++) {
                System.out.print("-");
            }
        }
    }

}
