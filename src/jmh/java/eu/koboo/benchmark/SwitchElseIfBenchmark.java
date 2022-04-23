package eu.koboo.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(LowerCaseBenchmark.N)
public class SwitchElseIfBenchmark {

    public static final int N = 5_000;
    public static final String name = "KobooGotFunWithJavaBenchmarks";

    @Benchmark
    public String switchCase() {
        for(Test test : Test.values()) {
            switch (test) {
                case A:
                    return name + "A";
                case B:
                    return name + "B";
                case C:
                    return name + "C";
                case D:
                    return name + "D";
            }
        }
        return "Which is faster?";
    }

    @Benchmark
    public String elseIf() {
        for (Test test : Test.values()) {
            if(test == Test.A) {
                return name + "A";
            } else if(test == Test.B) {
                return name + "B";
            } else if(test == Test.C) {
                return name + "C";
            } else if(test == Test.D) {
                return name + "D";
            }
        }
        return "Which is faster?";
    }

    @Benchmark
    public String ifReturn() {
        for (Test test : Test.values()) {
            if(test == Test.A) {
                return name + "A";
            }
            if(test == Test.B) {
                return name + "B";
            }
            if(test == Test.C) {
                return name + "C";
            }
            if(test == Test.D) {
                return name + "D";
            }
        }
        return "Which is faster?";
    }

    private enum Test {
        A, B, C, D
    }

}
