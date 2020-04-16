package randombits.main;

public class TestResult {
    private double pValue;
    private boolean isRandom;

    public TestResult(double pValue, boolean result) {
        this.pValue = pValue;
        this.isRandom = result;
    }

    public double getpValue() {
        return pValue;
    }

    public boolean isRandom() {
        return isRandom;
    }

    @Override
    public String toString() {
        return "{p_value: " + this.pValue + ", random: " + this.isRandom + " }";
    }
}
