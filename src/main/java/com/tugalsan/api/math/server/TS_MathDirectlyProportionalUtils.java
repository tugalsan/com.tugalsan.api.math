package com.tugalsan.api.math.server;

public class TS_MathDirectlyProportionalUtils {

    private TS_MathDirectlyProportionalUtils() {

    }

    public static double get_set2_value2_as_dbl(double set1_value1, double set2_value1, double set1_value2) {
        return set1_value2 * set2_value1 / set1_value1;
    }

    public static long get_set2_value2_as_lng(int set1_value1, int set2_value1, int set1_value2) {
        return Math.round(1d * set1_value2 * set2_value1 / set1_value1);
    }

}
