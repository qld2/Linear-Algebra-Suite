/**
 * This class represents a matrix.
 *
 * This class is intended to be a part of a linear algebra
 * suite with simple linear algbra tools that can be imported
 * to help solve problems in other projects where vectors
 * and matrices might come in handy.
 *
 * Each instance of the class is intended to be final so
 * "changing" a matrix creates and returns a new instance
 * of the class rather than changing the current instances'
 * instance data.
 *
 * @author qld2
 *
 */
public class Matrix {

    private double[][] mat, transpose;
    private boolean isSquare;
    public final double DETERMINANT;

    /**
     * Constructor.
     *
     * @param m the matrix in the form of an array.
     */
    public Matrix(final double[][] m) {

        this.mat = m;

        if(mat.length == mat[0].length) {
            isSquare = true;
        } else {
            isSquare = false;
        }

        DETERMINANT = determinant();

        transpose = this.generateTranspose();

    }

    /**
     * Generates the transpose to the matrix.
     *
     * @return the transpose as an array.
     */
    private double[][] generateTranspose() {
        double[][] result = new double[mat[0].length][mat.length];

        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                result[i][j] = mat[j][i];
            }
        }

        return result;
    }

    /**
     * Generates the inverse for non-singular matrices.
     *
     * @return the inverse matrix as an array.
     */
    public double[][] generateInverse() {
        if (isSquare && DETERMINANT != 0) {
            double[][] cofactor = new double[mat.length][mat[0].length];

            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    cofactor[i][j] = getCofactorAt(i, j);
                }
            }

            Matrix adjointMat = new Matrix(cofactor).getTranspose();
            double[][] adjoint = adjointMat.getMatrixArray();

            double[][] result = new double[mat.length][mat[0].length];

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

        if(!isSquare) {
            return 0;
        }

        double result = 0;
        if(mat.length == 2) {
            result = mat[0][0]*mat[1][1] - mat[0][1]*mat[1][0];
        }else{
            for(int j = 0; j < mat[0].length; j++) {
                result += mat[0][j]*getCofactorAt(0,j);
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
    private double minor(final int row,final int col) {
        double[][] result = new double[mat.length - 1][mat[0].length - 1];
        int x = 0, y = 0;

        for(int i = 0; i < mat.length; i++) {
            if(i == row) {
                i++;
            }

            y = 0;

            for(int j = 0; j < mat[0].length; j++) {
                if(j == col) {
                    j++;
                }

                if(x < result.length && y < result[0].length) {
                    result[x][y] = mat[i][j];
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

    public Matrix multiplyBy(final Matrix m) {
        double[][] mat2 = m.getMatrixArray();
        double[][] result = new double[mat.length][mat2[0].length];

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat2[0].length; j++) {
                for(int k = 0; k < mat2.length; k++) {
                    result[i][j] += mat[i][k] * mat2[k][j];
                }
            }
        }

        return new Matrix(result);
    }



    /**
     * Gets the matrix as an array.
     * @return the matrix as an array.
     */
    public double[][] getMatrixArray() {
        return mat;
    }

    /**
     * Gets the transpose.
     * @return the transpose.
     */
    public Matrix getTranspose() {
        return new Matrix(transpose);
    }

    /**
     * Allows the Matrix to print in a convienent and neat way.
     */
    public String toString() {
        String result = new String();

        if(mat == null) {
            return "NULL";
        }

        for(int i = 0; i < mat.length;i++) {
            for(int j = 0; j < mat[i].length;j++) {
                result += mat[i][j];
                result += "\t";
            }
            result += "\n";
        }

        return result;
    }
}
