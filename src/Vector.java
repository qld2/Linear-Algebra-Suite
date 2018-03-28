/**
 * This class represents a vector in the form of a matrix or column vector
 * and is the superclass to these instances.
 *
 * The term vector is used more inline with definition pertaining to a vector
 * space which is why matrices are a subclass.
 *
 * This class is intended to be a part of a linear algebra
 * suite with simple linear algebra tools that can be imported
 * to help solve problems in other projects where vectors
 * and matrices might come in handy.
 *
 * @author qld2
 */
public abstract class Vector {

    protected double[][] matrix;
    protected Vector transpose;
    protected int m, n;

    public Vector (int numOfRows, int numOfCols) {
        m = numOfRows;
        n = numOfCols;
        matrix = new double[m][n];
    }

    /**
     * Multiplies matrices and vectors.
     * The criteria of matrix multiplication is a precondition.
     *
     * @param vector the Vector (matrix or column vector) to be
     *               multiplied by.
     * @return The resulting vector
     */
    public Matrix multiplyBy(final Vector vector) {
        double[][] mat2 = vector.getMatrixArray();
        double[][] result = new double[matrix.length][mat2[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < mat2[0].length; j++) {
                for(int k = 0; k < mat2.length; k++) {
                    result[i][j] += matrix[i][k] * mat2[k][j];
                }
            }
        }

        return new Matrix(result);
    }

    /**
     * Exchanges rows.
     *
     * @param rowOne the first row
     * @param rowTwo the second row
     */
    protected void rowExchange(int rowOne, int rowTwo) {
        double[] swap = matrix[rowOne];
        matrix[rowOne] = matrix[rowTwo];
        matrix[rowTwo] = swap;
    }

    /**
     * Scales a row by a double
     *
     * @param row
     * @param scalar
     */
    protected void rowScale (int row, double scalar) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] *= scalar;
        }
    }

    /**
     * Adds a multiple of one row to another row.
     *
     * @param rowOne the unchanged row
     * @param rowTwo the row to be added to
     * @param scalar multiple of rowOne to be added to rowTwo
     */
    protected void rowAdd (int rowOne, int rowTwo, double scalar) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[rowTwo][i] += scalar * matrix[rowOne][i];
        }
    }

    /**
     * Changes one value in a vector
     * @param row
     * @param column
     * @param newValue
     */
    protected void changeValue(int row, int column, int newValue) {
        matrix[row][column] = newValue;
    }

    /**
     * Changes one value in a vector
     * @param row
     * @param column
     */
    protected double getValue(int row, int column) {
        return matrix[row][column];
    }

    /**
     * Generates the transpose to the vector.
     *
     * @return the transpose as an array.
     */
    public abstract Vector generateTranspose();

    /**
     * Gets the matrix as an array.
     * @return the matrix as an array.
     */
    public double[][] getMatrixArray() {
        double[][] copy = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copy[i][j] = matrix[i][j];
            }
        }

        return copy;
    }

    /**
     * Gives the number of rows.
     *
     * @return the number of rows.
     */
    public int getNumOfRows() {
        return m;
    }

    /**
     * Gives the number of columns.
     *
     * @return the number of columns.
     */
    public int getNumOfCols() {
        return n;
    }

    /**
     * Allows the Vector to print in a convenient and neat way.
     */
    public String toString() {
        String result = new String();

        if(matrix == null) {
            return "NULL";
        }

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                result += matrix[i][j];

                result += " ";
                result += "\t";
            }
            result += "\n";
        }

        return result;
    }
}
