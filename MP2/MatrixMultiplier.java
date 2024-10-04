class MatrixMultiplier implements Runnable {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] result;
    private int startRow, endRow;

    public MatrixMultiplier(int[][] matrixA, int[][] matrixB, int[][] result, int startRow, int endRow) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        int size = matrixB[0].length;  // Assuming square matrices (20x20)
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = 0;
                for (int k = 0; k < size; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
    }
}
