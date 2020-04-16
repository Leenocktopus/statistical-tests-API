package randombits.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NistTest {
    public final static double acceptance = 0.1;

    public static TestResult frequencyTest(String sequence) {
        Validator.validate(sequence);
        double pValue = Frequency.test(sequence);
        return new TestResult(pValue, pValue > acceptance);
    }

    public static TestResult blockFrequencyTest(String sequence, int blockSize) {
        Validator.validate(sequence, blockSize);
        double pValue = BlockFrequency.test(sequence, blockSize);
        return new TestResult(pValue, pValue > acceptance);
    }

    public static List<TestResult> cumulativeSumsTest(String sequence) {
        Validator.validate(sequence);
        double[] pValues = CumulativeSums.test(sequence);
        return Arrays.stream(pValues)
                .mapToObj(item -> new TestResult(item, item > acceptance))
                .collect(Collectors.toList());
    }

    public static TestResult entropyTest(String sequence, int blockSize) {
        Validator.validate(sequence, blockSize);
        double pValue = Entropy.test(sequence, blockSize);
        return new TestResult(pValue, pValue > acceptance);
    }

}
