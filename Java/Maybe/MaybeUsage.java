public class MaybeUsage {
    public static void main(String[] args) {
        Maybe<Integer> some = new Maybe<>(42);
        Maybe<Integer> none = new Maybe<>();

        System.out.println(some);
        System.out.println(none);

        System.out.println(some.isEmpty());
        System.out.println(none.isEmpty());

        System.out.println(some.getValue());
        System.out.println(none.getValue());

        String res1 = some.match(
            v -> "Got value " + v,
            () -> "No value"
        );
        System.out.println(res1);

        String res2 = none.match(
            v -> "Got value " + v,
            () -> "No value"
        );
        System.out.println(res2);
    }
} 