package io.explore;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class DynamicPrograming
{
    public static int fib(int n, Map<Integer, Integer> memo) {
        if (n <= 2) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        int fib = fib(n-2, memo) + fib(n-1, memo);
        memo.put(n, fib);
        return fib;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
