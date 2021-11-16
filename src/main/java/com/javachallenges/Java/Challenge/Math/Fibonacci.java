package com.javachallenges.Java.Challenge.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fibonacci {

    private static final List<Integer> series = new ArrayList<>();

    public static String print(int fbNumbers) {
        int iteration = 0;
        while (iteration < fbNumbers) {
            int fbNumber = execute(iteration);
            series.add(fbNumber);
            ++iteration;
        }
        return resultAsString();
    }

    private static String resultAsString() {
        return Arrays.toString(Fibonacci.series.toArray())
                .replace(",", "")
                .replace("[", "")
                .replace("]", "");
    }

    private static int execute(int number) {
        if (number == 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        } else {
            return execute(number - 1) + execute(number - 2);
        }
    }
}
