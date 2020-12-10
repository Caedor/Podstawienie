
public class Subsitution {
    private int dimension;
    private double[][] matrixA;
    private double[] vectorB;
    private  long start;
    private  long stop;

    public Subsitution(int dimension, double[][] matrixA, double[] vectorB) {
        this.dimension = dimension;
        this.matrixA = matrixA;
        this.vectorB = vectorB;
    }
    public Subsitution(int dimension){
        this.dimension = dimension;
        this.matrixA = generateTestMatrix();
        this.vectorB = new double[dimension];
        //przypisannie wartości 1 we wszystkich kom. wektoraB
        for(int i = 0;i < dimension; i++){
            vectorB[i] = 1;
        }
    }

    public double[][] generateTestMatrix(){
        double[][] texstMatrix = new double[dimension][dimension];
        for(int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                if (i >= j){
                    texstMatrix[i][j] = 1;
                } else {
                    texstMatrix[i][j] = 0;
                }
            }
        }

        return texstMatrix;
    }

    public long getTimeInNano(){
        return stop - start;
    }

    public int getDimension() {
        return dimension;
    }

    public double[][] getMatrixA() {
        return matrixA;
    }

    public double[] getVectorB() {
        return vectorB;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setMatrixA(double[][] matrixA) {
        this.matrixA = matrixA;
    }

    public void setVectorB(double[] vectorB) {
        this.vectorB = vectorB;
    }

    //petle obliczeniowe - implementacja algorytmu
    public double[] calculate(){
        double[] vectorX = new double[dimension];
        //start pomiaru czasu
        start = System.nanoTime();

        for(int i = 0; i < dimension; i++){
            vectorX[i] = vectorB[i] / matrixA[i][i];
            for(int j = i + 1; j < dimension; j++){
                vectorB[j] = vectorB[j] - matrixA[j][i] * vectorX[i];
            }
        }
        //koniec pomiaru czsu
        stop = System.nanoTime();

        return vectorX;
    }


}