package eu.koboo.benchmark;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(StringBuilderBenchmark.N)
public class StringBuilderBenchmark {

    public static final int N = 5_000;
    public static final String name = "KobooGotFunWithJavaBenchmarks";

    @Benchmark
    public String operateString() {
        return "Koboo" + "Got" + "Fun" + "With" + "Java" + "Benchmarks";
    }

    @Benchmark
    public String operateStringBuilder() {
        return new StringBuilder("Koboo")
            .append("Got").append("Fun").append("With").append("Java").append("Benchmarks").toString();
    }

}
