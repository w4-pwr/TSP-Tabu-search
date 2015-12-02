/**
 * Created by Rafal on 2015-12-02.
 */
public class TabuSearch {

    private TabuList tabuList;
    private final Matrix matrix;

    int[] currSolution;
    int numberOfIterations;
    int problemSize;

    private int[] bestSolution;
    private int bestCost;

    public TabuSearch(Matrix matrix) {
        this.matrix = matrix;
        problemSize = matrix.getEdgeCount();
        numberOfIterations = problemSize * 10;

        tabuList = new TabuList(problemSize);
        setupCurrentSolution();
        setupBestSolution();


    }

    private void setupBestSolution() {
        bestSolution = new int[problemSize + 1];
        System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
        bestCost = matrix.calculateDistance(bestSolution);
    }

    private void setupCurrentSolution() {
        currSolution = new int[problemSize + 1];
        for (int i = 0; i < problemSize; i++)
            currSolution[i] = i;
        currSolution[problemSize] = 0;
    }


    private void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
        System.out.println();
    }

    public void invoke() {

        for (int i = 0; i < numberOfIterations; i++) {
            int city1 = 0;
            int city2 = 0;

            for (int j = 1; j < currSolution.length - 1; j++) {
                for (int k = 2; k < currSolution.length - 1; k++) {
                    if (j != k) {
                        swap(j, k);
                        int currCost = matrix.calculateDistance(currSolution);
                        if ((currCost < bestCost) && tabuList.tabuList[j][k] == 0) {
                            city1 = j;
                            city2 = k;
                            System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
                            bestCost = currCost;
                        }
                    }
                }
            }


            if (city1 != 0) {
                tabuList.decrementTabu();
                tabuList.tabuMove(city1, city2);
            }
        }

       // System.out.println("Search done! \nBest Solution cost found = " + bestCost + "\nBest Solution :");
        //printSolution(bestSolution);
    }

    private void swap(int i, int k) {
        int temp = currSolution[i];
        currSolution[i] = currSolution[k];
        currSolution[k] = temp;
    }
}
