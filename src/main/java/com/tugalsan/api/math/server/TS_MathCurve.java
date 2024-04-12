package com.tugalsan.api.math.server;

import com.tugalsan.api.list.client.TGS_ListUtils;
import com.tugalsan.api.math.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.util.*;

public class TS_MathCurve {

    public static double DEFAULT_INDEX_STEPS() {
        return 0.1d;
    }

    final public TS_MathCurveDriver curve;
    final public List<TS_MathResult3<Double>> table_of_idx_input_output;
    final public int tableSize;

    public TS_MathCurve(double[] input_values, double[] output_values) {
        this(input_values, output_values, DEFAULT_INDEX_STEPS());
    }

    public TS_MathCurve(double[] input_values, double[] output_values, double indexSteps) {
        curve = new TS_MathCurveDriver(input_values, output_values);
        table_of_idx_input_output = curve.calc_return_table_of_idx_input_output(indexSteps).orElse(excuse -> {
            excuse.printStackTrace();
            return TGS_ListUtils.of();
        });
        tableSize = table_of_idx_input_output.size();
    }

    public double getIndexMin() {
        return table_of_idx_input_output.get(0).idx();
    }

    public double getIndexMax() {
        return table_of_idx_input_output.get(tableSize - 1).idx();
    }

    public double getInputMin() {
        return table_of_idx_input_output.get(0).input();
    }

    public double getInputMax() {
        return table_of_idx_input_output.get(tableSize - 1).input();
    }

    public double getOutputForInputMin() {
        return table_of_idx_input_output.get(0).output();
    }

    public double getOutputForInputMax() {
        return table_of_idx_input_output.get(tableSize - 1).output();
    }

    public TGS_UnionExcuse<Double> getOutputByClosestAverage(double forInputValue) {
        if (forInputValue > getInputMax()) {
            var pack0 = table_of_idx_input_output.get(tableSize - 2);
            var pack1 = table_of_idx_input_output.get(tableSize - 1);
            var fromMinMax = new TGS_MathRange(pack0.input(), pack1.input());
            var toMinMax = new TGS_MathRange(pack0.output(), pack1.output());
            return TGS_MathUtils.convertWeightedDbl(forInputValue, fromMinMax, toMinMax);
        }
        if (forInputValue < getInputMin()) {
            var pack0 = table_of_idx_input_output.get(0);
            var pack1 = table_of_idx_input_output.get(1);
            var fromMinMax = new TGS_MathRange(pack0.input(), pack1.input());
            var toMinMax = new TGS_MathRange(pack0.output(), pack1.output());
            return TGS_MathUtils.convertWeightedDbl(forInputValue, fromMinMax, toMinMax);
        }

        if (table_of_idx_input_output.get(0).input() == forInputValue) {
            return TGS_UnionExcuse.of(table_of_idx_input_output.get(0).output());
        }
        if (table_of_idx_input_output.get(tableSize - 1).input() == forInputValue) {
            return TGS_UnionExcuse.of(table_of_idx_input_output.get(tableSize - 1).output());
        }

        var closestTableIdx = 0;
        for (var tableIdx = 1; tableIdx < tableSize; tableIdx++) {
            var tableRow = table_of_idx_input_output.get(tableIdx);
            var distance = tableRow.input() - forInputValue;
            if (distance == 0) {
                return TGS_UnionExcuse.of(tableRow.output());
            }
            if (distance > 0) {
                break;
            }
            closestTableIdx = tableIdx;
        }

        if (closestTableIdx == tableSize - 1) {
            return TGS_UnionExcuse.of(table_of_idx_input_output.get(tableSize - 1).output());
        }

        return TGS_MathUtils.convertWeightedDbl(
                forInputValue,
                new TGS_MathRange(
                        table_of_idx_input_output.get(closestTableIdx).input(),
                        table_of_idx_input_output.get(closestTableIdx + 1).input()
                ),
                new TGS_MathRange(
                        table_of_idx_input_output.get(closestTableIdx).output(),
                        table_of_idx_input_output.get(closestTableIdx + 1).output()
                )
        );
    }

//    public static void main(String... s) {
//        var input_values = new double[]{
//            1d, 20d, 50d
//        };
//        var output_values = new double[]{
//            100d, 200d, 500d
//        };
//        var approximator = new TGS_MathApproximator(input_values, output_values);
//        var table = approximator.table_of_idx_input_output;
//        System.out.println("TABLE.INDEX:");
//        table.stream().forEachOrdered(row -> System.out.println(row.value0));
//        System.out.println("TABLE.INPUT:");
//        table.stream().forEachOrdered(row -> System.out.println(row.value1));
//        System.out.println("TABLE.OUTPUT:");
//        table.stream().forEachOrdered(row -> System.out.println(row.value2));
//
//        List<TGS_Tuple2<Double, Double>> closest_input_output = TGS_ListUtils.of();
//        for (var i = -10; i <= 60; i++) {
//            var closestOutput = approximator.getOutputByClosestAverage(i, true);
//            TGS_Tuple2<Double, Double> pack = new TGS_Tuple2((double) i, closestOutput);
//            closest_input_output.add(pack);
//        }
//        System.out.println("CLOSEST.INPUT:");
//        closest_input_output.stream().forEachOrdered(row -> System.out.println(row.value0));
//        System.out.println("CLOSEST.OUTPUT:");
//        closest_input_output.stream().forEachOrdered(row -> System.out.println(row.value1));
//    }
}
