public class RowVector extends Vector {

    /**
     * Instantiates a zero vector in R^m
     *
     * @param n the number of columns
     */
    public RowVector(int n) {
        super(1, n);

        matrix = new double[m][n];
    }

    /**
     * Instantiates the given vector in R^m unless
     * the dimensions on vector are wrong, the a zero
     * vector in R^m is instantiated.
     *
     * @param vector in form of double matrix
     */
    public RowVector(double[][] vector) {
        super(1, vector[0].length);

        if (vector.length != 1) {
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
    public RowVector(double[] vector) {
        super(1, vector.length);

        matrix = new double[m][n];

        for (int i = 0; i < m; i++) {
            matrix[1][i] = vector[i];
        }

    }

    /**
     * Instantiates a vector with the given components.
     *
     * @param components of the vector
     */
    public RowVector(Double... components) {
        super(1, components.length);

        matrix = new double[m][n];

        for (int i = 0; i < components.length; i++) {
            matrix[1][i] = components[i].doubleValue();
        }
    }
}
