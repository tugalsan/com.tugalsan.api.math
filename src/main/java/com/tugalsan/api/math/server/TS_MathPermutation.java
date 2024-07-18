package com.tugalsan.api.math.server;

import java.util.List;
import java.util.stream.Collectors;
import org.paukov.combinatorics3.Generator;

public class TS_MathPermutation {

    public static <T> List<List<T>> permutation(List<T> arr) {
        return Generator.permutation(arr)
                .simple()
                .stream()
                .collect(Collectors.toList());
    }
}
