import java.util.function.Function;
import java.util.function.Supplier;

public class Maybe<T> {
    private final T value;

    public Maybe() {
        this.value = null;
    }

    public Maybe(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public <R> R match(Function<? super T, ? extends R> someFn, Supplier<? extends R> noneFn) {
        return isEmpty() ? noneFn.get() : someFn.apply(value);
    }
} 