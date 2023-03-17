package io.explore.dp;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class Fibonacci
{
    public static int fib(int n, Map<Integer, Integer> memo) {
        if (n <= 2) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        int fib = fib(n-2, memo) + fib(n-1, memo);
        memo.put(n, fib);
        return fib;
    }
}
