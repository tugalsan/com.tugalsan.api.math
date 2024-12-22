package com.tugalsan.api.math.server;

import com.tugalsan.api.function.client.TGS_Func_OutTyped_In2;
import java.util.stream.IntStream;

public class TS_MathArrayScalarBooleanUtils {

    public static void bytes(boolean parallel,
            boolean[] output, byte[] inputA, byte[] inputB,
            TGS_Func_OutTyped_In2<Boolean, Byte, Byte> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void shorts(boolean parallel,
            boolean[] output, short[] inputA, short[] inputB,
            TGS_Func_OutTyped_In2<Boolean, Short, Short> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void integers(boolean parallel,
            boolean[] output, int[] inputA, int[] inputB,
            TGS_Func_OutTyped_In2<Boolean, Integer, Integer> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void longs(boolean parallel,
            boolean[] output, long[] inputA, long[] inputB,
            TGS_Func_OutTyped_In2<Boolean, Long, Long> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void floats(boolean parallel,
            boolean[] output, float[] inputA, float[] inputB,
            TGS_Func_OutTyped_In2<Boolean, Float, Float> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void doubles(boolean parallel,
            boolean[] output, double[] inputA, double[] inputB,
            TGS_Func_OutTyped_In2<Boolean, Double, Double> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

}
