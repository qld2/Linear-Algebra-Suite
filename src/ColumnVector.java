public class ColumnVector extends Vector {
    /**
     * Instantiates a zero vector in R^m
     *
     * @param m the number of rows
     */
    public ColumnVector(int m) {
        super(m, 1);

        matrix = new double[m][n];
    }

    /**
     * Instantiates the given vector in R^m unless
     * the dimensions on vector are wrong, the a zero
     * vector in R^m is instantiated.
     *
     * @param vector in form of double matrix
     */
    public ColumnVector(double[][] vector) {
        super(vector.length, 1);

        if (vector[0].length != 1) {
            matrix = new double[m][n];
        } else {
            matrix = vector;
        }
    }

    /**
     * Instantiates a vector with the given components.
     *
     * @param vector the components in an array
     */
    public ColumnVector(double[] vector) {
        super(vector.length, 1);

        matrix = new double[m][n];

        for (int i = 0; i < m; i++) {
            matrix[i][1] = vector[i];
        }

    }

    /**
     * Instantiates a vector with the given components.
     *
     * @param components of the vector
     */
    public ColumnVector(Double... components) {
        super(components.length, 1);

        matrix = new double[m][n];

        for (int i = 0; i < components.length; i++) {
            matrix[i][1] = components[i].doubleValue();
        }
    }


}
