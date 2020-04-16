package randombits.main.utils;

import static java.lang.Math.*;

public class GammaUtils {


    private static final int ITMAX = 100;
    private static final double EPS = 3.0e-7;
    private static final double FPMIN = Double.MIN_VALUE;


    private static double gammln(double xx) {
        double x, y, tmp, ser;

        final double[] cof = {76.18009172947146, -86.50532032941677,
                24.01409824083091, -1.231739572450155, 0.1208650973866179e-2,
                -0.5395239384953e-5};
        int j;
        y = x = xx;
        tmp = x + 5.5;
        tmp -= (x + 0.5) * log(tmp);
        ser = 1.000000000190015;
        for (j = 0; j <= 5; j++) {
            ser += cof[j] / ++y;
        }

        return -tmp + log(2.5066282746310005 * ser / x);
    }


    public static double regularizedUpperIncomplete(double a, double x) {
        if (a <= 0.0)
            throw new IllegalArgumentException("a <= 0");
        return 1.0 - regularizedGammaP(a, x);
    }

    private static double regularizedGammaP(double a, double x) {
        if (x < (a + 1.0)) {
            return gser(a, x);
        } else {
            return 1.0 - gcf(a, x);
        }
    }


    private static double gser(double a, double x) {
        double gln = gammln(a);
        if (x <= 0.0) {
            if (x < 0.0)
                throw new IllegalArgumentException("x < 0");
            return 0.0;
        } else {
            double gamser;
            double ap = a;
            double del, sum;
            del = sum = 1.0 / a;
            for (int n = 1; n <= ITMAX; n++) {
                ++ap;
                del *= x / ap;
                sum += del;
                if (abs(del) < abs(sum) * EPS) {
                    gamser = sum * exp(-x + a * log(x) - gln);
                    return gamser;
                }
            }
            throw new IllegalArgumentException("a is too large");
        }
    }

    private static double gcf(double a, double x) {
        double an, del;
        int i;
        double b = x + 1.0 - a;
        double c = 1.0 / FPMIN;
        double d = 1.0 / b;
        double h = d;
        for (i = 1; i <= ITMAX; i++) {
            an = -i * (i - a);
            b += 2.0;
            d = an * d + b;
            if (abs(d) < FPMIN)
                d = FPMIN;
            c = b + an / c;
            if (abs(c) < FPMIN)
                c = FPMIN;
            d = 1.0 / d;
            del = d * c;
            h *= del;
            if (abs(del - 1.0) < EPS)
                break;
        }
        if (i > ITMAX)
            throw new IllegalArgumentException("a is too large");
        double gln = gammln(a);
        return exp(-x + a * log(x) - gln) * h;
    }

}