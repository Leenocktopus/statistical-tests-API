package randombits.util;

import nist.NistTest;
import randombits.exceptions.IllegalTestParametersException;


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
            return (T) NistTest.blockFrequencyTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")));
        }
    },
    BINARY_MATRIX {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.binaryMatrixRankTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("matrixSize")));
        }
    },
    NON_OVERLAPPING_TEMPLATE {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.nonOverlappingTemplateTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")), testParams.get("template"));
        }
    },
    OVERLAPPING_TEMPLATE {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.overlappingTemplateTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")), testParams.get("template"));
        }
    },
    SPECTRAL {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.spectralTest(testParams.get("sequence"));
        }
    },
    LINEAR_COMPLEXITY {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.linearComplexityTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")));
        }
    },
    LONGEST_RUN_OF_ONES {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.longestRunOfOnesTest(testParams.get("sequence"));
        }
    },
    MAURERS {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.maurersTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")), Integer.parseInt(testParams.get("blocksInInitSegment")));
        }
    },
    CUMULATIVE_SUMS {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.cumulativeSumsTest(testParams.get("sequence"));
        }

    },
    RANDOM_EXCURSIONS {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.randomExcursionsTest(testParams.get("sequence"));
        }
    },
    EXCURSION_VARIANT {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.randomExcursionsVariantTest(testParams.get("sequence"));
        }
    },
    RUNS {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.runsTest(testParams.get("sequence"));
        }
    },
    SERIAL {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.serialTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")));
        }
    },
    ENTROPY {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T test(Map<String, String> testParams) {
            return (T) NistTest.entropyTest(testParams.get("sequence"),
                    Integer.parseInt(testParams.get("blockSize")));
        }
    };

    abstract <T> T test(Map<String, String> testParams);

    public <T> T getResult(Map<String, String> testParams) {
        try {
            return this.test(testParams);
        } catch (IllegalArgumentException ex) {
            throw new IllegalTestParametersException(ex.getMessage(), new Throwable(this.name()));
        }
    }

}