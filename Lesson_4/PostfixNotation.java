public class PostfixNotation {
    public BigDecimal compute(Stack<Character> input) {
        Stack<BigDecimal> result = new Stack<>();
        while (input.size() > 0) {
            char symbol = input.pop();
            switch (symbol) {
                case '+' -> {
                    result.push(result.pop().add(result.pop()));
                }
                case '*' -> {
                    result.push(result.pop().multiply(result.pop()));
                }
                case '-' -> {
                    result.push(result.pop().subtract(result.pop()));
                }
                case '/' -> {
                    result.push(result.pop().divide(result.pop(), 2, RoundingMode.HALF_UP));
                }
                case '=' -> {
                    return result.pop();
                }
                default -> result.push(BigDecimal.valueOf(symbol - 48));
            }
        }

        return result.pop();
    }
}

