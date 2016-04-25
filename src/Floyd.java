import java.util.Scanner;

/**
 * Created by benjamin on 3/23/16.
 */
public class Floyd {


    public static void main(String[] Args) {
        Floyd floyd = new Floyd();
        Double[][] table = floyd.getInfoFromUser();
        floyd.floydsAlgorithm(table);
    }

    public Double[][] getInfoFromUser() {
        Scanner sc = new Scanner(System.in);
        int vertices = getVertices(sc);
        Double[][] table = buildTable(vertices);
        fillTable(table, sc);
        return table;
    }

    private int getVertices(Scanner sc) {
        System.out.println("Please enter the Number of Vertices in your directed graph.");
        int verts;
        try {
            verts = sc.nextInt();
            if (verts <= 0) {
                throw new Exception("Invalid number of vertices: negative Integer.");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("The Value you entered was not valid, please enter a positive integer.");
            verts = getVertices(sc);
        }
        return verts;
    }

    private Double[][] buildTable(int vertices) {
        Double[][] table = new Double[vertices][vertices];
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                table[i][j] = Double.POSITIVE_INFINITY;
        return table;
    }

    private void fillTable(Double[][] table, Scanner sc) {
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table.length; column++) {
                Double d = getPathWeight(row + 1, column + 1, sc);

                if (d >= 0)
                    table[row][column] = d;
            }
        }
    }

    private double getPathWeight(int row, int column, Scanner sc) {
        System.out.printf("Please enter the weight of link from %d to %d\n" +
                "If the link does not exist, please enter -1 or another negative number.", row, column);
        Double d;
        try {
            d = sc.nextDouble();
        } catch (Exception e) {
            System.out.println();
            System.out.println("The value you entered might not have been a floating point number.\n Please try again.");
            d = getPathWeight(row, column, sc);
        }
        return d;
    }

    public String toString(Double[][] table1, int[][] table2) {
        StringBuilder sB = new StringBuilder();
        for (int row = 0; row < table1.length; row++) {
            for (int column = 0; column < table1.length; column++) {
                int intermediate = table2[row][column] + 1;
                sB.append("[ " + table1[row][column] + " | " + intermediate + " ]");
                if (!(column == table1.length - 1))
                    sB.append(",");
            }
            sB.append("\n");
        }
        return sB.toString();
    }

    public Object[] floydsAlgorithm(Double[][] weightsTable) {
        int[][] intermediatesTable = buildInitialIntermediatesTable(weightsTable);
        System.out.println("The tables initial values are");
        System.out.println();
        System.out.println("Each cell's output will follow the format:\n [ Optimal Weight | Intermediate Vertex ] \n\n");
        System.out.println(toString(weightsTable, intermediatesTable));

        for (int iteration = 0; iteration < weightsTable.length; iteration++) {
            for (int row = 0; row < weightsTable.length; row++) {
                if (weightsTable[row][iteration] == Double.POSITIVE_INFINITY) {
                } else {
                    for (int column = 0; column < weightsTable.length; column++) {
                        Double condition = weightsTable[row][iteration] + weightsTable[iteration][column];
                        if (weightsTable[row][column] > condition) {
                            weightsTable[row][column] = condition;
                            intermediatesTable[row][column] = iteration;
                        }
                    }
                }
            }
            System.out.println(toString(weightsTable, intermediatesTable));
        }
        return new Object[]{weightsTable, intermediatesTable};
    }

    public int[][] buildInitialIntermediatesTable(Double[][] weightsTable) {
        int[][] intermediatesTable = new int[weightsTable.length][weightsTable.length];
        for (int row = 0; row < weightsTable.length; row++) {
            for (int column = 0; column < weightsTable.length; column++) {
                    intermediatesTable[row][column] = -1;
            }
        }
        return intermediatesTable;
    }
}
