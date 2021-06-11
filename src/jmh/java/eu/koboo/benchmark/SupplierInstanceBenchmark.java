package eu.koboo.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(5_000)
public class SupplierInstanceBenchmark {

    public static final Class<TestDao> clazz = TestDao.class;
    public static final Supplier<TestDao> supplier = TestDao::new;

    @Benchmark
    public List<TestDao> createInstance() {
        List<TestDao> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                list.add(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Benchmark
    public List<TestDao> createSupplier() {
        List<TestDao> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    private static class TestDao {

        String test1, test2;

        public TestDao() {
            this.test1 = "Shit 1";
            this.test2 = "Shit 2";
        }
    }

}
