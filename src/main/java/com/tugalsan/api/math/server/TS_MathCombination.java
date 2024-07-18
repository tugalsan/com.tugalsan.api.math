package com.tugalsan.api.math.server;

import java.util.List;
import java.util.stream.Collectors;
import org.paukov.combinatorics3.Generator;

public class TS_MathCombination {

    public static <T> List<List<T>> combinations(List<T> arr) {
        return Generator.combination(arr)
                .simple(3)
                .stream()
                .collect(Collectors.toList());
    }
}
