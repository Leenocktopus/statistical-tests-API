package randombits.main;


public class Validator {

    public static void validate(String sequence) {
        if (sequence.length() < 2) {
            throw new IllegalArgumentException("Sequence length should be at least 2 or more. Length given: " + sequence.length());
        } else if (!sequence.matches("[01]+")) {
            throw new IllegalArgumentException("Sequence should contain only ones (1) and zeroes (0).");
        }
    }

    public static void validate(String sequence, int blockSize) {
        validate(sequence);
        if (sequence.length() < blockSize) {
            throw new IllegalArgumentException("Block length(" + blockSize + ") must be equal or shorter than sequence(" + sequence.length() + ")");
        }

    }

    public static void validate(String sequence, int blockSize, int tmplSize) {
        validate(sequence, blockSize);
        if (tmplSize > blockSize) {
            throw new IllegalArgumentException("Template length(" + tmplSize + ") must be equal or shorter than block(" + blockSize + ")");
        }
    }

}
