public class Driver {

    public static Vector v1 = new ColumnVector(1,2, 3, 4);
    public static Vector v2 = new RowVector(5,6,7,8);

    public static double[][] d = {{0,0,0,4},{0,0,1,0},{1,0,0,0}};
    public static Matrix m1 = new Matrix(d);

    public static void main(String[] args) {
        System.out.println(m1);
        Matrix p = m1.generatePermutation();
        System.out.println(p);
        System.out.println(m1);
        System.out.println(p.multiplyBy(m1));

    }
}
