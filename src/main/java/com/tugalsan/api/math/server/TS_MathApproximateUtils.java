package com.tugalsan.api.math.server;

import com.tugalsan.api.list.client.*;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.math.client.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.util.stream.*;

public class TS_MathApproximateUtils {

    final private static TS_Log d = TS_Log.of(TS_MathApproximateUtils.class);

    public static void testApprox() {
        var known_input_values = new double[]{10, 20, 30};
        var known_output_values = new double[]{8, 18, 50};
        var request_input_values = new double[]{5, 6, 7, 8, 9, 10, 11, 15, 20, 25, 30, 35, 40, 45, 50, 51, 52, 53, 54, 55};
        var calc = TS_MathApproximateUtils.calc(known_input_values, known_output_values, request_input_values);
        d.ce("testApprox", "known_input_values", TGS_StringUtils.toString(known_input_values, ", "));
        d.ce("testApprox", "known_output_values", TGS_StringUtils.toString(known_output_values, ", "));
        d.ce("testApprox", "req ", TGS_StringUtils.toString(request_input_values, ", "));
        if (calc.isExcuse()) {
            d.ct("testApprox.cals", calc.excuse());
        } else {
            d.ce("test", "TS_MathApproximateUtils.calc", TGS_StringUtils.toString(calc.value(), ", "));
        }
    }

    public static TGS_UnionExcuse<double[]> calc(double[] known_input_values, double[] known_output_values, double request_input_values[]) {
        var requested_output_values = new double[request_input_values.length];
        if (known_input_values.length == 1) {
            var offset = known_output_values[0] - known_input_values[0];
            IntStream.range(0, request_input_values.length).parallel().forEach(i -> {
                requested_output_values[i] = request_input_values[i] + offset;
            });
            return TGS_UnionExcuse.of(requested_output_values);
        }

        TGS_ListSortUtils.sortPrimativeDouble2(known_input_values, known_output_values);

        if (known_input_values.length == 2) {
            var fromMinMax = new TGS_MathRange(known_input_values[0], known_input_values[1]);
            var toMinMax = new TGS_MathRange(known_output_values[0], known_output_values[1]);
            for (var i = 0; i < request_input_values.length; i++) {
                var u = TGS_MathUtils.convertWeightedDbl(request_input_values[i], fromMinMax, toMinMax);
                if (u.isExcuse()) {
                    return u.toExcuse();
                }
                requested_output_values[i] = (Double) u.value();
            }

            return TGS_UnionExcuse.of(requested_output_values);
        }

        var curve = new TS_MathCurve(known_input_values, known_output_values);
        for (int i = 0; i < request_input_values.length; i++) {
            var u = curve.getOutputByClosestAverage(request_input_values[i]);
            if (u.isExcuse()) {
                return u.toExcuse();
            }
            requested_output_values[i] = u.value();
        }
        return TGS_UnionExcuse.of(requested_output_values);
    }
}
