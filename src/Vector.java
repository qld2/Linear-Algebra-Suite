public abstract class Vector {

    protected double[][] matrix, transpose;
    protected int m, n;

    public Vector (int numOfRows, int numOfCols) {
        m = numOfRows;
        n = numOfCols;
    }

}
