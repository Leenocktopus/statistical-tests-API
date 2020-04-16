package randombits.main.utils;

import java.util.Arrays;

public class FunctionUtils {


    private static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + t * (-0.18628806 + t * (0.27886807 +
                        t * (-1.13520398 + t * (1.48851587 + t * (-0.82215223 + t * (0.17087277))))))))));
        if (z >= 0) return ans;
        else return -ans;
    }

    public static double erfc(double z) {
        return 1 - erf(z);
    }

    public static double normalD(double z) {
        return z > 0 ? 0.5 * (1 + erf(z / Math.sqrt(2))) : 0.5 * (1 - erf(-z / Math.sqrt(2)));
    }

    public static double[] dft(int[] realIn, int[] imagIn) {
        double[] realOut = new double[realIn.length];
        double[] imagOut = new double[realIn.length];
        for (int k = 0; k < realIn.length; k++) {  // For each output element
            for (int t = 0; t < realIn.length; t++) {  // For each input element
                double angle = (2.0 * Math.PI * t / realIn.length) * k;
                realOut[t] += realIn[k] * Math.cos(angle);
                imagOut[t] += realIn[k] * Math.sin(angle);
                realOut[t] -= imagIn[k] * Math.sin(angle);
                imagOut[t] += imagIn[k] * Math.cos(angle);
            }
        }

        for (int i = 0; i < realOut.length; i++) {
            realOut[i] = Math.sqrt(Math.pow(imagOut[i], 2) + Math.pow(realOut[i], 2));
        }
        return realOut;
    }

    public static int berlekampMassey(String sequence) {
        int[] C = new int[sequence.length()];
        int[] B = new int[sequence.length()];
        int[] s = new int[sequence.length()];
        int[] t;
        B[0] = 1;
        C[0] = 1;
        int L = 0, N = 0, m = -1;

        for (int i = 0; i < sequence.length(); i++) {
            s[i] = Character.getNumericValue(sequence.charAt(i));
        }

        while (N < sequence.length()) {
            int d = s[N];
            for (int i = 1; i <= L; i++) {
                d += s[N - i] * C[i];
            }

            d = d % 2;

            int[] p = new int[sequence.length()];
            if (d == 1) {
                t = Arrays.copyOf(C, C.length);
                for (int i = 0; i < L; i++) {
                    if (B[i] == 1) {
                        p[i + N - m] = 1;
                    }
                }
                for (int i = 0; i < sequence.length(); i++) {
                    C[i] = p[i] ^ C[i];
                }
                if (L <= (N / 2)) {
                    L = N + 1 - L;
                    m = N;
                    B = Arrays.copyOf(t, t.length);
                }
            }
            N++;

        }
        return L;
    }

    public static int onesInBlock(String sequence) {
        if (sequence.length() == 0) return 0;
        if (sequence.charAt(0) == '1') return 1 + onesInBlock(sequence.substring(1));
        return onesInBlock(sequence.substring(1));
    }
}

