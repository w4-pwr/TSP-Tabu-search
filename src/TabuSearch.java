/**
 * Created by Rafal on 2015-12-02.
 *
 */
public class TabuSearch {

    private TabuList tabuList;
    private final Matrix matrix;

    int[] currSolution ;
    int numberOfIterations;
    int tabuLength;
    private int[] bestSolution;
    private int bestCost;

    public TabuSearch(Matrix matrix) {
        tabuLength = 10;
        numberOfIterations = 100;

        this.matrix = matrix;
        tabuList = new TabuList(tabuLength);

        currSolution = setupStartSolution();
        bestSolution = new int[currSolution.length];
        bestCost = matrix.calculateDistance(bestSolution);
        System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);


    }

    public  int[] getBestNeighbour(int[] initSolution) {

        int[] bestSol = new int[initSolution.length]; //this is the best Solution So Far
        System.arraycopy(initSolution, 0, bestSol, 0, bestSol.length);
        int bestCost = matrix.calculateDistance(initSolution);
        int city1 = 0;
        int city2 = 0;
        boolean firstNeighbor = true;

        for (int i = 1; i < bestSol.length - 1; i++) {
            for (int j = 2; j < bestSol.length - 1; j++) {
                if (i == j) {
                    continue;
                }

                int[] newBestSol = new int[bestSol.length]; //this is the best Solution So Far
                System.arraycopy(bestSol, 0, newBestSol, 0, newBestSol.length);

                newBestSol = swapOperator(i, j, initSolution); //Try swapping cities i and j
                // , maybe we get a bettersolution
                int newBestCost = matrix.calculateDistance(newBestSol);



                if ((newBestCost > bestCost || firstNeighbor) && tabuList.tabuList[i][j] == 0) { //if better move found, store it
                    firstNeighbor = false;
                    city1 = i;
                    city2 = j;
                    System.arraycopy(newBestSol, 0, bestSol, 0, newBestSol.length);
                    bestCost = newBestCost;
                }
            }
        }

        if (city1 != 0) {
            tabuList.decrementTabu();
            tabuList.tabuMove(city1, city2);
        }
        return bestSol;


    }

    //swaps two cities
    private  int[] swapOperator(int city1, int city2, int[] solution) {
        int temp = solution[city1];
        solution[city1] = solution[city2];
        solution[city2] = temp;
        return solution;
    }


    private  int[] setupStartSolution() {
        //city numbers start from 0
        // the first and last cities' positions do not change
        return new int[]{0, 1, 2, 3, 4, 0};
    }

    private  void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
        System.out.println();
    }

    private void replaceIfBetterSolution(int currCost) {
        if (currCost < bestCost) {
            System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
            bestCost = currCost;
        }
    }

    public void invoke() {

        for (int i = 0; i < numberOfIterations; i++) {
            currSolution = getBestNeighbour(currSolution);
            //printSolution(currSolution);
            int currCost = matrix.calculateDistance(currSolution);
            //System.out.println("Current best cost = " + matrix.calculateDistance(currSolution));
            replaceIfBetterSolution(currCost);
        }

        System.out.println("Search done! \nBest Solution cost found = " + bestCost + "\nBest Solution :");
        printSolution(bestSolution);

    }
}
