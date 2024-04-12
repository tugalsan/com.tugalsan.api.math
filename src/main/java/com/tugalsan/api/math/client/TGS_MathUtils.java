package com.tugalsan.api.math.client;

import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.util.*;
import java.util.stream.*;

public class TGS_MathUtils {

    public static double percentageValueDbl(double divident, double divisor) {
        return 100 * divident / divisor;
    }

    public static int percentageValueInt(double divident, double divisor) {
        return (int) Math.round(percentageValueDbl(divident, divisor));
    }

    public static float RADIAN_ZERO() {
        return (float) Math.toRadians(0);
    }

    public static float RADIAN_PLUS_90() {
        return (float) Math.toRadians(90);
    }

    public static float RADIAN_PLUS_180() {
        return RADIAN_PLUS_90() + RADIAN_PLUS_90();
    }

    public static float RADIAN_PLUS_270() {
        return RADIAN_PLUS_180() + RADIAN_PLUS_90();
    }

    public static float RADIAN_PLUS_360() {
        return RADIAN_PLUS_180() + RADIAN_PLUS_180();
    }

    public static float RADIAN_MINUS_90() {
        return (float) Math.toRadians(-90);
    }

    public static float RADIAN_MINUS_180() {
        return RADIAN_MINUS_90() + RADIAN_MINUS_90();
    }

    public static float RADIAN_MINUS_270() {
        return RADIAN_MINUS_180() + RADIAN_MINUS_90();
    }

    public static float RADIAN_MINUS_360() {
        return RADIAN_MINUS_180() + RADIAN_MINUS_180();
    }

    private static int[] POWERS_OF_10() {
        return new int[]{
            1,//0
            10,//1
            100,//2
            1000,//3
            10000,//4
            100000,//5
            1000000,//6
            10000000,//7
            100000000,//8
            1000000000//9
        };
    }

    public static int findIndex(double[] sortedArray, int indexRangeStartEnclosing, int indexRangeStopNotEnclosing,
            double findValue, Double optionalTolerans, int maxIterations) {
        maxIterations--;
        if (maxIterations == -1) {
            return -1;
        }
        var indexRange = indexRangeStopNotEnclosing - indexRangeStartEnclosing;
        var indexRange_2 = indexRange / 2;
        var indexMid = indexRangeStartEnclosing + indexRange_2;
        var midValue = sortedArray[indexMid];
        if (midValue == findValue) {
            return indexMid;
        }
        if (optionalTolerans != null) {
            if (midValue == findValue + optionalTolerans) {
                return indexMid;
            }
            if (midValue == findValue - optionalTolerans) {
                return indexMid;
            }
        }
        if (midValue < findValue) {
            return findIndex(sortedArray, indexRangeStartEnclosing, indexMid, findValue, optionalTolerans, maxIterations);
        }
        if (midValue > findValue) {
            return findIndex(sortedArray, indexMid, indexRangeStopNotEnclosing, findValue, optionalTolerans, maxIterations);
        }
        return -1;
    }

    public static float radianSimplified(float radiant) {
        while (radiant < RADIAN_ZERO()) {
            radiant += RADIAN_PLUS_360();
        }
        while (radiant > RADIAN_PLUS_360()) {
            radiant -= RADIAN_PLUS_360();
        }
        return radiant;
    }

    public static int getSum(int[] input) {
        return Arrays.stream(input).reduce(0, Integer::sum);
    }

    public static ArrayItem findMax(int[] input) {
        var wrap = new Object() {
            int idx = 0;
            int value = input[0];
        };
        IntStream.range(1, input.length).forEachOrdered(i -> {
            if (wrap.value < input[i]) {
                wrap.idx = i;
                wrap.value = input[i];
            }
        });
        return new ArrayItem(wrap.idx, wrap.value);
    }

    public record ArrayItem(int idx, int value) {

    }

    public static ArrayItem findMin(int[] input) {
        var wrap = new Object() {
            int idx = 0;
            int value = input[0];
        };
        IntStream.range(1, input.length).forEachOrdered(i -> {
            if (wrap.value > input[i]) {
                wrap.idx = i;
                wrap.value = input[i];
            }
        });
        return new ArrayItem(wrap.idx, wrap.value);
    }

    
    public static TGS_UnionExcuse<Integer> convertWeightedInt(int input, TGS_MathRange<Integer> fromMinMax, TGS_MathRange<Integer> toMinMax) {
        if (toMinMax == null || fromMinMax == null) {
            return TGS_UnionExcuse.ofExcuse(TGS_MathUtils.class.getSimpleName(), "convertWeightedInt", "toMinMax == null || fromMinMax == null");
        }
        if (fromMinMax.min > fromMinMax.max) {
            var tmp = fromMinMax.min;
            fromMinMax.min = fromMinMax.max;
            fromMinMax.max = tmp;
        }
        if (input < fromMinMax.min || input > fromMinMax.max) {
            return TGS_UnionExcuse.ofExcuse(TGS_MathUtils.class.getSimpleName(), "convertWeightedInt", "input < fromMinMax.min || input > fromMinMax.max");
        }
        var fromRange = fromMinMax.max - fromMinMax.min;
        var fromLeftRange = input - fromMinMax.min;
        var percent = fromLeftRange * 1f / fromRange;

        if (toMinMax.min > toMinMax.max) {
            var tmp = toMinMax.min;
            toMinMax.min = toMinMax.max;
            toMinMax.max = tmp;
        }
        var toRange = toMinMax.max - toMinMax.min;
        var toLeftRange = percent * toRange;
        var toValue = toMinMax.min + Math.round(toLeftRange);
        return TGS_UnionExcuse.of(toValue > toMinMax.max ? toMinMax.max : toValue);
    }

    public static TGS_UnionExcuse<Double> convertWeightedDbl(double input, TGS_MathRange<Double> fromMinMax, TGS_MathRange<Double> toMinMax) {
        if (toMinMax == null || fromMinMax == null) {
            return TGS_UnionExcuse.ofExcuse(TGS_MathUtils.class.getSimpleName(), "convertWeightedInt", "toMinMax == null || fromMinMax == null");
        }
        if (fromMinMax.min > fromMinMax.max) {
            var tmp = fromMinMax.min;
            fromMinMax.min = fromMinMax.max;
            fromMinMax.max = tmp;
        }
        if (input < fromMinMax.min || input > fromMinMax.max) {
//            System.out.println("convertWeightedDbl" + "RANGE OUT DETECTED");
//            System.out.println("input:" + input);
//            System.out.println("fromMinMax.value0:" + fromMinMax.value0);
//            System.out.println("fromMinMax.value1:" + fromMinMax.value1);
            var inputRange = fromMinMax.max - fromMinMax.min;
            var outputRange = toMinMax.max - toMinMax.min;
            var outputIncrement = outputRange / inputRange;
            if (input < fromMinMax.min) {
                var inputLess = fromMinMax.min - input;
                return TGS_UnionExcuse.of(toMinMax.min - inputLess * outputIncrement);
            } else {//input > fromMinMax.value1)
                var inputMore = input - fromMinMax.max;
                return TGS_UnionExcuse.of(toMinMax.max + inputMore * outputIncrement);
            }
        }
        var fromRange = fromMinMax.max - fromMinMax.min;
        var fromLeftRange = input - fromMinMax.min;
        var percent = fromLeftRange * 1f / fromRange;

        if (toMinMax.min > toMinMax.max) {
            var tmp = toMinMax.min;
            toMinMax.min = toMinMax.max;
            toMinMax.max = tmp;
        }
        var toRange = toMinMax.max - toMinMax.min;
        var toLeftRange = percent * toRange;
        var toValue = toMinMax.min + toLeftRange;
        return TGS_UnionExcuse.of(toValue > toMinMax.max ? toMinMax.max : toValue);
    }

    public static int[] convertWeighted(int[] input, int minWeighted, int preferedSumWeight) {
        var weighted = new int[input.length];//MAY ARRAY
        var sum0 = getSum(input);//input-sum
        IntStream.range(0, weighted.length).parallel().forEach(i -> {
            weighted[i] = Math.round(1f * preferedSumWeight * input[i] / sum0);
            weighted[i] = weighted[i] < minWeighted ? minWeighted : weighted[i];
        });
        while (true) {
            var sum = getSum(weighted);
            var remainer = sum - preferedSumWeight;
            if (remainer == 0) {
                break;
            } else if (remainer < 0) {
                weighted[findMin(weighted).idx]++;
            } else {
                weighted[findMax(weighted).idx]--;
            }
        }
        return weighted;
    }

    public static double long2Double(double dbValue, int pow_from0_to9) {
        return dbValue / powerOf10(pow_from0_to9);
    }

    public static long double2Long(double dbValue, int pow_from0_to9) {
        return Math.round(dbValue * powerOf10(pow_from0_to9));
    }

    public static int powerOf10(int pow_from0_to9) {
        return POWERS_OF_10()[pow_from0_to9];
    }

    public static OptionalDouble average(Double skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .average();
    }

    public static OptionalDouble average(Integer skip, int... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .average();
    }

    public static OptionalInt min(Integer skip, int... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .min();
    }

    public static OptionalDouble min(Integer skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .min();
    }

    public static OptionalInt max(Integer skip, int... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .max();
    }

    public static OptionalDouble max(Integer skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .max();
    }

}
