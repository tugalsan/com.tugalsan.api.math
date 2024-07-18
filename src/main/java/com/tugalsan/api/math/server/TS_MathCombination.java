package com.tugalsan.api.math.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TS_MathCombination {

    public static <T> Stream<List<T>> combinations(List<T> arr) {
        final long N = (long) Math.pow(2, arr.size());
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<List<T>>(N, Spliterator.SIZED) {
            long i = 1;

            @Override
            public boolean tryAdvance(Consumer<? super List<T>> action) {
                if (i < N) {
                    List<T> out = new ArrayList(Long.bitCount(i));
                    for (int bit = 0; bit < arr.size(); bit++) {
                        if ((i & (1 << bit)) != 0) {
                            out.add(arr.get(bit));
                        }
                    }
                    action.accept(out);
                    ++i;
                    return true;
                } else {
                    return false;
                }
            }
        }, false);
    }
}
