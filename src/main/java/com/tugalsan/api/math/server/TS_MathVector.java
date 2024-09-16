package com.tugalsan.api.math.server;

import java.util.Arrays;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class TS_MathVector {

    private static final VectorSpecies<Integer> PREFERRED_SPECIES_INTEGER = IntVector.SPECIES_PREFERRED;
    private static final VectorSpecies<Float> PREFERRED_SPECIES_FLOAT = FloatVector.SPECIES_PREFERRED;

    public int[] addTwoScalarArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }

    public int[] addTwoVectorArrays(int[] arr1, int[] arr2) {
        var v1 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, arr1, 0);
        var v2 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, arr2, 0);
        var result = v1.add(v2);
        return result.toArray();
    }

    public int[] addTwoVectorsWithMasks(int[] arr1, int[] arr2) {
        var finalResult = new int[arr1.length];
        var i = 0;
        for (; i < PREFERRED_SPECIES_INTEGER.loopBound(arr1.length); i += PREFERRED_SPECIES_INTEGER.length()) {
            var mask = PREFERRED_SPECIES_INTEGER.indexInRange(i, arr1.length);
            var v1 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, arr1, i, mask);
            var v2 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, arr2, i, mask);
            var result = v1.add(v2, mask);
            result.intoArray(finalResult, i, mask);
        }
        for (; i < arr1.length; i++) {// tail cleanup loop
            finalResult[i] = arr1[i] + arr2[i];
        }
        return finalResult;
    }

    public float[] scalarNormOfTwoArrays(float[] arr1, float[] arr2) {
        float[] finalResult = new float[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            finalResult[i] = (float) Math.sqrt(arr1[i] * arr1[i] + arr2[i] * arr2[i]);
        }
        return finalResult;
    }

    public float[] vectorNormalForm(float[] arr1, float[] arr2) {
        var finalResult = new float[arr1.length];
        var i = 0;
        var upperBound = PREFERRED_SPECIES_INTEGER.loopBound(arr1.length);
        for (; i < upperBound; i += PREFERRED_SPECIES_INTEGER.length()) {
            var va  = FloatVector.fromArray(PREFERRED_SPECIES_FLOAT, arr1, i);
            var vb = FloatVector.fromArray(PREFERRED_SPECIES_FLOAT, arr2, i);
            var vc = va.mul(va)
                    .add(vb.mul(vb))
                    .sqrt();
            vc.intoArray(finalResult, i);
        }

        // tail cleanup
        for (; i < arr1.length; i++) {
            finalResult[i] = (float) Math.sqrt(arr1[i] * arr1[i] + arr2[i] * arr2[i]);
        }
        return finalResult;
    }

    public double averageOfaVector(int[] arr) {
        var sum = 0d;
        for (var i = 0; i < arr.length; i += PREFERRED_SPECIES_INTEGER.length()) {
            var mask = PREFERRED_SPECIES_INTEGER.indexInRange(i, arr.length);
            var v = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, arr, i, mask);
            sum += v.reduceLanes(VectorOperators.ADD, mask);
        }
        return sum / arr.length;
    }

    public static boolean[] compareWithLoop(int[] a1, int[] a2) {
        var result = new boolean[a1.length];
        var upperBound = PREFERRED_SPECIES_INTEGER.loopBound(a1.length);
        for (var i = 0; i < upperBound; i += PREFERRED_SPECIES_INTEGER.length()) {
            var v1 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, a1, i);
            var v2 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, a2, i);
            VectorMask<Integer> v3 = v1.eq(v2);
            v3.intoArray(result, i);
        }
        // Scalar loop for end:
        for (int i = upperBound; i < a1.length; i++) {
            result[i] = a1[i] == a2[i];
        }
        return result;
    }

    public static boolean[] compareWithMask(int[] a1, int[] a2) {
        var result = new boolean[a1.length + PREFERRED_SPECIES_INTEGER.length()];
        for (var i = 0; i < a1.length; i += PREFERRED_SPECIES_INTEGER.length()) {
            var mask = PREFERRED_SPECIES_INTEGER.indexInRange(i, a1.length);
            var v1 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, a1, i, mask);
            var v2 = IntVector.fromArray(PREFERRED_SPECIES_INTEGER, a2, i, mask);
            VectorMask<Integer> v3 = v1.eq(v2);
            v3.intoArray(result, i);
        }
        // You are only interested in result for the size of a1.length:
        return Arrays.copyOf(result, a1.length);
    }

    public static void scalarComputation(float[] a, float[] b, float[] c) {
        for (var i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

    public static void vectorComputation(float[] a, float[] b, float[] c) {
        for (var i = 0; i < a.length; i += PREFERRED_SPECIES_FLOAT.length()) {
            var m = PREFERRED_SPECIES_FLOAT.indexInRange(i, a.length);
            // FloatVector va, vb, vc;
            var va  = FloatVector.fromArray(PREFERRED_SPECIES_FLOAT, a, i, m);
            var vb = FloatVector.fromArray(PREFERRED_SPECIES_FLOAT, b, i, m);
            var vc = va.mul(va).
                    add(vb.mul(vb)).
                    neg();
            vc.intoArray(c, i, m);
        }
    }

    public static void vectorComputation2(float[] a, float[] b, float[] c) {
        var i = 0;
        int upperBound = PREFERRED_SPECIES_FLOAT.loopBound(a.length);
        for (; i < upperBound; i += PREFERRED_SPECIES_FLOAT.length()) {
            // FloatVector va, vb, vc;
            var va  = FloatVector.fromArray(PREFERRED_SPECIES_FLOAT, a, i);
            var vb = FloatVector.fromArray(PREFERRED_SPECIES_FLOAT, b, i);
            var vc = va.mul(va).
                    add(vb.mul(vb)).
                    neg();
            vc.intoArray(c, i);
        }
        for (; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

    public static void vectorComputation(float[] a, float[] b, float[] c, VectorSpecies<Float> species) {
        var i = 0;
        int upperBound = species.loopBound(a.length);
        for (; i < upperBound; i += species.length()) {
            //FloatVector va, vb, vc;
            var va  = FloatVector.fromArray(species, a, i);
            var vb = FloatVector.fromArray(species, b, i);
            var vc = va.mul(va).
                    add(vb.mul(vb)).
                    neg();
            vc.intoArray(c, i);
        }

        for (; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }
}
