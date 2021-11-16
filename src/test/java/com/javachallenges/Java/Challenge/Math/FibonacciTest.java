package com.javachallenges.Java.Challenge.Math;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FibonacciTest {

    @Test
    public void returningTheFirstFourElementsOfFibonacci() {
        // WHEN
        String fbSeries = Fibonacci.print(7);

        // THEN
        Assertions.assertThat("0 1 1 2 3 5 8".equals(fbSeries));
    }

    @Test
    public void returningTheFirst18ElementsOfFibonacci() {
        // WHEN
        String fbSeries = Fibonacci.print(18);

        // THEN
        Assertions.assertThat("0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597".equals(fbSeries));
    }
}