public class EitherUsage {
    public static void main(String[] args) {
        Either<Integer, Integer> success = Either.right(42);
        Either<Integer, Integer> failure = Either.left(500);

        Either<Integer, Integer> doubled = success.map(x -> x * 2);
        System.out.println("doubled: " + doubled.getRight());

        String result = failure.match(
            error -> "Failure: " + error,
            value -> "Success: " + value
        );
        System.out.println("result: " + result);
    }
} 