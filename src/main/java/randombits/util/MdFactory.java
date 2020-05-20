package randombits.util;

import md.MdTest;
import randombits.exceptions.IllegalTestParametersException;


import java.util.Map;

public enum MdFactory {
    ONE {
        @Override
        Double test(String sequence) {
            return MdTest.one(sequence);
        }
    }, TWO {
        @Override
        Double test(String sequence) {
            return MdTest.two(sequence);
        }
    }, THREE {
        @Override
        Double test(String sequence) {
            return MdTest.three(sequence);
        }
    }, FOUR {
        @Override
        Double test(String sequence) {
            return MdTest.four(sequence);
        }
    }, FIVE {
        @Override
        Double test(String sequence) {
            return MdTest.five(sequence);
        }
    }, SIX {
        @Override
        Double test(String sequence) {
            return MdTest.six(sequence);
        }
    }, SEVEN {
        @Override
        Double test(String sequence) {
            return MdTest.seven(sequence);
        }
    }, EIGHT{
        @Override
        Double test(String sequence) {
            return MdTest.eight(sequence);
        }
    };

    abstract Double test(String sequence);

    public MdResult getResult(Map<String, String> testParams) {
        try{

            return new MdResult(this.test(testParams.get("sequence")));
        }catch (IllegalArgumentException ex){
            throw new IllegalTestParametersException(ex.getMessage(), new Throwable(this.name()));
        }
    }
}
