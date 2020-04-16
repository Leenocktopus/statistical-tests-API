package randombits.main.utils;

public class MatrixUtils {

    public static int[][][] getMatricesFromSequence(String sequence) {
        int m = 3;
        int n = sequence.length() / (m * m);
        int[][][] matrices = new int[n][m][m];
        for (int i = 0; i < n * m * m; i += m * m) {
            String temp = sequence.substring(i, i + m * m);
            int j = 0;
            for (int k = 0; k < m; k++) {
                for (int l = 0; l < m; l++) {
                    matrices[i / (m * m)][k][l] = Character.getNumericValue(temp.charAt(j));
                    j++;
                }
            }
        }
        return matrices;
    }

    public static int computeRank(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int max = i;
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[j][i] > matrix[max][i]) {
                    max = j;
                }
            }
            int[] temporary = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temporary;
            for (int k = i + 1; k < matrix.length; k++) {
                double coeff = (matrix[i][i] == 0) ? 0 : matrix[k][i] / matrix[i][i];
                for (int j = i; j < matrix.length; j++) {
                    matrix[k][j] -= coeff * matrix[i][j];
                }
            }
        }
        int rank = matrix.length;
        for (int[] matrix1 : matrix) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix1[j];
            }
            if (sum == 0) {
                rank--;
            }
        }
        return rank;
    }

}
