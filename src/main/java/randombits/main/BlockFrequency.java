package randombits.main;

import randombits.main.utils.FunctionUtils;
import randombits.main.utils.GammaUtils;

public class BlockFrequency {


    public static double test(String sequence, int blockSize) {
        if (blockSize > sequence.length()) {
            throw new IllegalArgumentException("Block length(" + blockSize + ") must be equal or shorter than sequence(" + sequence.length() + ")");
        }
        int times = sequence.length() / blockSize;
        double[] proportions = new double[times];
        int j = 0;
        for (int i = blockSize; i < sequence.length(); i += blockSize) {
            proportions[j] = Math.pow(FunctionUtils.onesInBlock(sequence.substring(i - blockSize, i)) / (double) blockSize - 0.5, 2);
            j++;
        }
        double sum = 0;
        for (double proportion : proportions) {
            sum += proportion;

        }
        return GammaUtils.regularizedUpperIncomplete(times / 2., 4 * blockSize * sum / 2);
    }
}
