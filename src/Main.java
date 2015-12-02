/**
 * Created by Rafal on 2015-12-02.
 */
public  class Main {


    public static void main(String[] args){

        int[][] m = new int[][]{{0, 1, 3, 4, 5},
                    {1, 0, 1, 4, 8},
                    {3, 1, 0, 5, 1},
                    {4, 4, 5, 0, 2},
                    {5, 8, 1, 2, 0}};
        Matrix matrix = new Matrix(m);

        TabuSearch tabuSearch = new TabuSearch(matrix);
        tabuSearch.invoke();

    }
}
