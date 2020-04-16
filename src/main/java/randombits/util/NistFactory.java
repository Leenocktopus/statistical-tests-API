package randombits.util;

import randombits.exceptions.IllegalTestParametersException;
import randombits.main.NistTest;

import java.util.Map;

public enum NistFactory {
    FREQUENCY {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.frequencyTest(testParams.get("sequence"));
        }
    },

    BLOCK_FREQUENCY {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.blockFrequencyTest(testParams.get("sequence"), Integer.parseInt(testParams.get("block_size")));
        }
    },
    CUMULATIVE_SUMS {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.cumulativeSumsTest(testParams.get("sequence"));
        }

    };

    abstract <T> T test(Map<String, String> testParams);

    public <T> T getResult(Map<String, String> testParams) {
        try {
            return this.test(testParams);
        } catch (IllegalArgumentException e) {
            throw new IllegalTestParametersException("Allowed arguments: " +
                    "sequence: Sequence length > 2, " +
                    "blockSize: Block size < Sequence length, " +
                    "templateSize: Template size < Block size.", new Throwable(this.name()));
        }
    }
    
    /*
    BINARY_MATRIX_RANK{
        @Override
        public TestResult getResult(){
            return null;
        }
    },
    FREQUENCY{
        @Override
        public StatisticalTest getTest() {
            return new Frequency();
        }
    },
    BINARY_MATRIX_RANK{
        @Override
        public StatisticalTest getTest() {
            return new BinaryMatrixRank();
        }
    },
    BLOCK_FREQUENCY{
        @Override
        public StatisticalTest getTest() {
            return new BlockFrequency();
        }
    },
    CUMULATIVE_SUMS{
        @Override
        public StatisticalTest getTest() {
            return new CumulativeSums();
        }
    },
    SPECTRAL{
        @Override
        public StatisticalTest getTest() {
            return new Spectral();
        }
    },
    LINEAR_COMPLEXITY{
        @Override
        public StatisticalTest getTest() {
            return new LinearComplexity();
        }
    },
    LONGEST_RUN_OF_ONES{
        @Override
        public StatisticalTest getTest() {
            return new LongestRunOfOnes();
        }
    },
    MAURERS{
        @Override
        public StatisticalTest getTest() {
            return new Maurers();
        }
    },
    NON_OVERLAPPING_TEMPLATE{
        @Override
        public StatisticalTest getTest() {
            return new NonOverlappingTemplate();
        }
    },
    OVERLAPPING_TEMPLATE{
        @Override
        public StatisticalTest getTest() {
            return new OverlappingTemplate();
        }
    },
    RANDOM_EXCURSIONS{
        @Override
        public StatisticalTest getTest() {
            return new RandomExcursions();
        }
    },
    RANDOM_EXCURSION_VARIANT{
        @Override
        public StatisticalTest getTest() {
            return new RandomExcursionVariant();
        }
    },
    RUNS{
        @Override
        public StatisticalTest getTest() {
            return new Runs();
        }
    },
    SERIAL{
        @Override
        public StatisticalTest getTest() {
            return new Spectral();
        }
    },
    ENTROPY{
        @Override
        public StatisticalTest getTest() {
            return new Entropy();
        }
    };


    public abstract StatisticalTest getTest();*/
}
