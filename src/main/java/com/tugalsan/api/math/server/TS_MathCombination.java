package com.tugalsan.api.math.server;

import module combinatoricslib3;
import java.util.*;
import java.util.stream.*;

public class TS_MathCombination {

    public static <T> List<List<T>> combinations(List<T> arr, int innerItemCount) {
        return Generator.combination(arr)
                .simple(innerItemCount)
                .stream()
                .collect(Collectors.toList());
    }
}
