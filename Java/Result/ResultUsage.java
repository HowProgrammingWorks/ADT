public class ResultUsage {
    public static void main(String[] args) {
        // Basic usage
        Result<String, Exception> success = Result.create("Successfully received data");
        Result<String, Exception> failure = Result.create(new Exception("Network error"));

        if (success.isSuccess()) {
            System.out.println("Success: " + success.getValue());
        }

        if (failure.isError()) {
            System.out.println("Failure: " + failure.getError().getMessage());
        }

        // Additional usage example
        // Handling a method that may throw and wrapping the result
        Result<Integer, Exception> result1 = parseIntSafe("123");
        Result<Integer, Exception> result2 = parseIntSafe("abc");

        if (result1.isSuccess()) {
            System.out.println("Parsed value: " + result1.getValue());
        } else {
            System.out.println("Parse error: " + result1.getError().getMessage());
        }

        if (result2.isSuccess()) {
            System.out.println("Parsed value: " + result2.getValue());
        } else {
            System.out.println("Parse error: " + result2.getError().getMessage());
        }
    }

    public static Result<Integer, Exception> parseIntSafe(String input) {
        try {
            int value = Integer.parseInt(input);
            return Result.fromValue(value);
        } catch (Exception e) {
            return Result.fromError(e);
        }
    }
}
