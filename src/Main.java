/**
 * Created by Rafal on 2015-12-02.
 */
public class Main {


    public static void main(String[] args) {

//        int[][] m = new int[][]{{0, 1, 3, 4, 5},
//                    {1, 0, 1, 4, 8},
//                    {3, 1, 0, 5, 1},
//                    {4, 4, 5, 0, 2},
//                    {5, 8, 1, 2, 0}};

        int size = 10;
        while (size < 2000) {
            Matrix matrix = new Matrix(size);
            TabuSearch tabuSearch = new TabuSearch(matrix);
            Long start = System.currentTimeMillis();
            tabuSearch.invoke();
            Long stop = System.currentTimeMillis() - start;
            System.out.println(String.format("Rozmiar: %d\t czas: %d ",size,stop));
            size +=10;
        }


    }
}
