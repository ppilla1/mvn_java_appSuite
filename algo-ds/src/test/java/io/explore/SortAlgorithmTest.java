package io.explore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class SortedPair<T extends Comparable<? super T>>{
    private final T first;
    private final T second;

    SortedPair(T left, T right) {

        if (left.compareTo(right) > 0) {
            this.second = left;
            this.first = right;
        } else {
            this.second = right;
            this.first = left;
        }

    }

}
@Log4j2
public class SortAlgorithmTest {

    @Test
    public void test_map_sort_using_lambda() {
        Integer one = Integer.parseInt("1");
        Map<String, Integer> map = Map.of(
                "eight", 8,
                "four", 4,
                "five", 4,
                "two", 2);

        log.info("Before:\n{}", map);

        List<Map.Entry<String, Integer>> entryList = map.entrySet().stream().collect(Collectors.toList());
        Collections.sort(entryList, (e1, e2) -> {
            int valueComparison = e1.getValue().compareTo(e2.getValue());
            if ( valueComparison == 0) {
                return e1.getKey().compareTo(e2.getKey());
            }

            return valueComparison;
        });

        Map<String, Integer> treeMap = entryList.stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1, LinkedHashMap::new));
        log.info("After:\n{}", treeMap);
    }
}
