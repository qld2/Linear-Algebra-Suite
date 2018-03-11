public class RowVector extends Vector {

    /**
     * Instantiates a zero vector in R^m
     *
     * @param n the number of columns
     */
    public RowVector(int n) {
        super(1, n);
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

        if (vector.length == 1)
            matrix = vector;
    }

    /**
     * Instantiates a vector with the given components.
     *
     * @param components of the vector
     */
    public RowVector(double... components) {
        super(1, components.length);

        for (int i = 0; i < components.length; i++) {
            matrix[0][i] = components[i];
        }
    }

    /**
     * Generates the transpose to the vector.
     *
     * @return the transpose as a RowVector.
     */
    public Vector generateTranspose() {
        return new ColumnVector(matrix.length);
    }
}
