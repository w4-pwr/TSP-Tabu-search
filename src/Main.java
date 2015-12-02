/**
 * Created by Rafal on 2015-12-02.
 */
public  class Main {


    public static void main(String[] args){
        Matrix matrix = new Matrix(10);
        TabuSearch tabuSearch = new TabuSearch(matrix);
        tabuSearch.invoke();

    }
}
