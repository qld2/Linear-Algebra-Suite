public class Driver {

    public static Vector v1 = new ColumnVector(1,2, 3, 4);
    public static Vector v2 = new RowVector(5,6,7,8);

    public static double[][] d = {{1,2,3},{3,4,4},{0,1,2},{1,0,1}};

public static Matrix m1 = new Matrix(d);

    public static void main(String[] args) {

        System.out.println(m1.rref());


    }
}
