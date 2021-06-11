package eu.koboo.benchmark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FastStream<V> {

    public static <V> FastStream<V> of(Collection<V> collection) {
        Collection<V> streamCollection = new ArrayList<>(collection);
        return new FastStream<>(streamCollection);
    }

    private final Collection<V> collection;

    public FastStream(Collection<V> collection) {
        this.collection = collection;
    }

    public FastStream<V> filter(Predicate<V> predicate) {
        Collection<V> copy = new ArrayList<>();
        for (V value : collection) {
            if (predicate.test(value)) {
                copy.add(value);
            }
        }
        collection.clear();
        return new FastStream<>(copy);
    }

    public <R> FastStream<R> map(Function<V, R> mapper) {
        Collection<R> toMap = new ArrayList<>();
        for (V value : collection) {
            R map = mapper.apply(value);
            toMap.add(map);
        }
        collection.clear();
        return new FastStream<R>(toMap);
    }

    public FastStream<V> limit(int limit) {
        int count = 0;
        Collection<V> toLimit = new ArrayList<>();
        for(V value : collection) {
            count += 1;
            if(count <= limit) {
                toLimit.add(value);
            }
        }
        collection.clear();
        return new FastStream<>(toLimit);
    }

    public void forEach(Consumer<V> consumer) {
        for (V value : collection) {
            consumer.accept(value);
        }
        collection.clear();
    }

    public V findAny(Predicate<V> predicate) {
        V ret = null;
        for(V value : collection) {
            if(predicate.test(value)) {
                ret = value;
                break;
            }
        }
        return ret;
    }

    public boolean allMatch(Predicate<V> predicate) {
        boolean value = true;
        for (V values : collection) {
            if (!predicate.test(values)) {
                value = false;
                break;
            }
        }
        collection.clear();
        return value;
    }

    public boolean anyMatch(Predicate<V> predicate) {
        boolean value = false;
        for (V values : collection) {
            if (predicate.test(values)) {
                value = true;
                break;
            }
        }
        collection.clear();
        return value;
    }

    public boolean noneMatch(Predicate<V> predicate) {
        boolean value = true;
        for (V values : collection) {
            if (predicate.test(values)) {
                value = false;
                break;
            }
        }
        collection.clear();
        return value;
    }

    public <C extends Collection<V>> C collect(Predicate<V> predicate, C toCollection) {
        for (V values : collection) {
            if (predicate.test(values)) {
                toCollection.add(values);
            }
        }
        collection.clear();
        return toCollection;
    }

    public <C extends Collection<V>> C collect(C toCollection) {
        toCollection.addAll(collection);
        collection.clear();
        return toCollection;
    }

    public int count(Predicate<V> predicate) {
        int count = 0;
        for (V values : collection) {
            if (predicate.test(values)) {
                count += 1;
            }
        }
        collection.clear();
        return count;
    }

    public int count() {
        int size = collection.size();
        collection.clear();
        return size;
    }
}
