package randombits.main.utils;

public class TemplateUtils {

    public static String[] getTemplates(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("You can't build templates with with size >= 0");
        }
        int len = (int) Math.pow(2, n);
        String[] temp = new String[len];
        for (int i = 0; i < Math.pow(2, n); i++) {
            temp[i] = Integer.toBinaryString((1 << n) | i).substring(1);
        }
        return temp;
    }

    public static int getTmplOverlapping(String sequence, String tmpl) {
        int n = 0;
        for (int i = 0; i < sequence.length() - tmpl.length() + 1; i++) {
            if (tmpl.equals(sequence.substring(i, i + tmpl.length()))) {
                n++;
            }
        }
        return n;
    }

    public static int getTmplNonOverlapping(String sequence, String tmpl) {
        int n = 0;
        int i = 0;
        while (i < sequence.length() - tmpl.length() + 1) {
            if (tmpl.equals(sequence.substring(i, i + tmpl.length()))) {
                n++;
                i += tmpl.length();
            } else {
                i++;
            }
        }
        return n;
    }
}
