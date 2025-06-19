import java.util.function.Function;

public class Either<L, R> {
    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(value, null);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(null, value);
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public <U> Either<L, U> map(Function<? super R, ? extends U> fn) {
        if (right == null) return Either.left(left);
        return Either.right(fn.apply(right));
    }

    public <T> T match(Function<? super L, ? extends T> leftFn, Function<? super R, ? extends T> rightFn) {
        return isRight() ? rightFn.apply(right) : leftFn.apply(left);
    }
} 