package randombits.util;

import randombits.main.TestResult;

import java.util.Map;

public enum MdFactory {
    ONE {
        @Override
        public TestResult test(Map<String, String> testParams) {
            return new TestResult(1, true);
        }
    }, TWO {
        @Override
        public TestResult test(Map<String, String> testParams) {
            return new TestResult(1, true);
        }
    };

    abstract TestResult test(Map<String, String> testParams);

    public TestResult getResult(Map<String, String> testParams) {
        return this.test(testParams);
    }
}
