package com.tugalsan.api.math.server;

import com.tugalsan.api.list.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.util.*;
import org.apache.commons.math3.analysis.interpolation.*;
import org.apache.commons.math3.analysis.polynomials.*;

public class TS_MathCurveDriver {

    final public double[] input_values;
    final public double[] output_values;
    final public List<TS_MathResult2<Double>> calculated_results = TGS_ListUtils.of();
    final private double[] indexes;
    final public double indexMin;
    final public double indexMax;
    final private PolynomialSplineFunction funcInput;
    final private PolynomialSplineFunction funcOutput;

    public TS_MathCurveDriver(double[] input_values, double[] output_values) {
        this.input_values = input_values;
        this.output_values = output_values;
        indexes = TGS_ListUtils.createDouble(0, 1, output_values.length);
        indexMin = indexes[0];
        indexMax = indexes[indexes.length - 1];
        funcInput = new SplineInterpolator().interpolate(indexes, input_values);
        funcOutput = new SplineInterpolator().interpolate(indexes, output_values);
//        IntStream.range(0, input_values.length).forEachOrdered(i -> System.out.println("indexes[" + i + "]: " + indexes[i]));
//        IntStream.range(0, input_values.length).forEachOrdered(i -> System.out.println("input_values[" + i + "]: " + input_values[i]));
//        IntStream.range(0, input_values.length).forEachOrdered(i -> System.out.println("output_values[" + i + "]: " + output_values[i]));
    }

    public TGS_UnionExcuse<TS_MathResult3<Double>> calc_return_idx_input_output(Double idx) {
        if (idx == null || idx < indexMin || idx > indexMax) {
            return TGS_UnionExcuse.ofExcuse(TS_MathCurveDriver.class.getSimpleName(), "calc_return_idx_input_output", "idx == null || idx < indexMin || idx > indexMax");
        }
        return TGS_UnionExcuse.of(new TS_MathResult3(idx, funcInput.value(idx), funcOutput.value(idx)));
    }

    public TGS_UnionExcuse<List<TS_MathResult3<Double>>> calc_return_table_of_idx_input_output(double indexStep) {
        List<TS_MathResult3<Double>> table = TGS_ListUtils.of();
        for (var idx = indexMin; idx <= indexMax; idx += indexStep) {
            var u = calc_return_idx_input_output(idx);
            if (u.isExcuse()) {
                return u.toExcuse();
            }
            table.add(u.value());
        }
        if (table.get(table.size() - 1).idx() != indexMax) {//JAVA DOUBLE FIX
            var u = calc_return_idx_input_output(indexMax);
            if (u.isExcuse()) {
                return u.toExcuse();
            }
            table.add(u.value());
        }
        return TGS_UnionExcuse.of(table);
    }

}
