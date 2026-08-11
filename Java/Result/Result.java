public class Result<T, E extends Exception> {
    private final T value;
    private final E error;

    public Result(T value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <T, E extends Exception> Result<T, E> create(Object input) {
        return (input instanceof Exception)
            ? new Result<>(null, (E) input)
            : new Result<>((T) input, null);
    }

    public static <T> Result<T, Exception> fromValue(T value) {
        return new Result<>(value, null);
    }

    public static <T, E extends Exception> Result<T, E> fromError(E error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public boolean isError() {
        return error != null;
    }

    public T getValue() {
        return value;
    }

    public E getError() {
        return error;
    }
}
