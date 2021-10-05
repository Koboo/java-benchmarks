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
public class LowerCaseBenchmark {

    public static final int N = 5_000;
    public static final String name = "KobooGotFunWithJavaBenchmarks";

    @Benchmark
    public String withoutLocale() {
        return name.toLowerCase();
    }

    @Benchmark
    public String withLocale() {
        return name.toLowerCase(Locale.ROOT);
    }

}
