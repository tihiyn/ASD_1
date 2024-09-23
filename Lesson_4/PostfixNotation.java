public class PostfixNotation {
    public BigDecimal compute(Stack<Character> input) {
        Stack<BigDecimal> result = new Stack<>();
        while (input.size() > 0) {
            char symbol = input.pop();
            switch (symbol) {
                case '+' -> {
                    BigDecimal a = result.pop();
                    BigDecimal b = result.pop();
                    result.push(a.add(b));
                }
                case '*' -> {
                    BigDecimal a = result.pop();
                    BigDecimal b = result.pop();
                    result.push(a.multiply(b));
                }
                case '-' -> {
                    BigDecimal a = result.pop();
                    BigDecimal b = result.pop();
                    result.push(a.subtract(b));
                }
                case '/' -> {
                    BigDecimal a = result.pop();
                    BigDecimal b = result.pop();
                    result.push(a.divide(b, 2, RoundingMode.HALF_UP));
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

