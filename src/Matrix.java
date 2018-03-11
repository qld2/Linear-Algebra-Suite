/**
 * This class represents a matrix.
 *
 * This class is intended to be a part of a linear algebra
 * suite with simple linear algebra tools that can be imported
 * to help solve problems in other projects where vectors
 * and matrices might come in handy.
 *
 * Each instance of the class is intended to be final so
 * "changing" a matrix creates and returns a new instance
 * of the class rather than changing the current instances'
 * data.
 *
 * @author qld2
 */

public class Matrix extends Vector{

    private Matrix equivalentForm;
    private Vector augment;

    private ColumnVector[] colVectors;
    private RowVector[] rowVectors;

    public final double DETERMINANT;

    /**
     * Constructor.
     *
     * @param mat the matrix in the form of an array.
     */
    public Matrix(final double[][] mat) {
        super(mat.length, mat[0].length);

        matrix = mat;

        DETERMINANT = 0;
    }

    /**
     * Constructor with an augment for generating inverses
     * and solving systems.
     *
     * @param mat the matrix in the form of an array.
     */
    public Matrix(final double[][] mat, Vector augment) {
        super(mat.length, mat[0].length);
        this.augment = augment;

        matrix = mat;

        DETERMINANT = 0;
    }

    private void rref() {
        double[] numbers = new double[(matrix.length^2 - matrix.length) / 2];
        int counter = 0;

        equivalentForm = new Matrix(getMatrixArray());

        Matrix permutation = generatePermutation();

        equivalentForm = permutation.multiplyBy(equivalentForm);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {

            }
        }
    }

    public Matrix generatePermutation() {
        double[][] id = {{1, 0, 0},{0, 1, 0},{0, 0, 1}};
        Matrix identity = new Matrix(id);

        equivalentForm = new Matrix(getMatrixArray());

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == 0) {
                for (int j = i; j < matrix.length; j++) {
                    if (matrix[j][i] != 0) {
                        equivalentForm.rowExchange(i, j);
                        identity.rowExchange(i, j);
                        break;
                    }
                }
            }
        }

        return identity;
    }

    /**
     * Generates the inverse for non-singular matrices.
     *
     * @return the inverse matrix as an array.
     */
    public double[][] generateInverse() {
        if (m == n && DETERMINANT != 0) {
            double[][] cofactor = new double[matrix.length][matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    cofactor[i][j] = getCofactorAt(i, j);
                }
            }

            Vector adjointMat = new Matrix(cofactor).generateTranspose();
            double[][] adjoint = adjointMat.getMatrixArray();

            double[][] result = new double[matrix.length][matrix[0].length];

            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = adjoint[i][j] / DETERMINANT;
                }
            }

            return result;
        }
        return null;
    }

    /**
     * Finds the determinant for square matrices.
     *
     * Precondition: the matrix must be square
     *
     * @return the determinant.
     */
    private double determinant() {

        if(m != n) {
            return 0;
        }

        double result = 0;
        if(matrix.length == 2) {
            result = matrix[0][0]* matrix[1][1] - matrix[0][1]* matrix[1][0];
        }else{
            for(int j = 0; j < matrix[0].length; j++) {
                result += matrix[0][j]*getCofactorAt(0,j);
            }
        }
        return result;
    }

    /**
     * Helper method for getCofactorAt().
     *
     * Precondition: row and column are within the matrix.
     *
     * @param row the row.
     * @param col the column.
     * @return the minor for an element in the matrix.
     */
    private double minor(final int row, final int col) {
        double[][] result = new double[matrix.length - 1][matrix[0].length - 1];
        int x = 0, y = 0;

        for(int i = 0; i < matrix.length; i++) {
            if(i == row) {
                i++;
            }

            y = 0;

            for(int j = 0; j < matrix[0].length; j++) {
                if(j == col) {
                    j++;
                }

                if(x < result.length && y < result[0].length) {
                    result[x][y] = matrix[i][j];
                }

                y++;
            }
            x++;
        }

        Matrix m = new Matrix(result);
        return m.determinant();
    }

    /**
     * Helper method for determinant() and generateInverse().
     *
     * Precondition: row and column are within the matrix.
     *
     * @param row the row.
     * @param col the column.
     * @return the cofactor for an element in the matrix.
     */
    private double getCofactorAt(final int row,final int col) {
        boolean rowOdd = false, colOdd = false;
        if(row % 2 == 1) {
            rowOdd = true;
        }
        if(col % 2 == 1) {
            colOdd = true;
        }


        if(rowOdd == colOdd) {
            return minor(row,col);
        } else {
            return -1*minor(row,col);
        }

    }

    /**
     * From superclass. Generates the transpose.
     *
     * @return the transpose as a matrix.
     */
    public Vector generateTranspose() {

        double[][] result = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return new Matrix(result);
    }

}
