public class PostfixNotation {
    public BigDecimal compute(Stack<Character> input) {
        Stack<BigDecimal> result = new Stack<>();
        while (input.size() > 0) {
            char symbol = input.pop();
            if (Character.isDigit(symbol)) {
                result.push(BigDecimal.valueOf(Character.getNumericValue(symbol)));
                continue;
            }

            BigDecimal a = result.pop();
            BigDecimal b = result.pop();
            switch (symbol) {
                case '+' -> result.push(a.add(b));
                case '*' -> result.push(a.multiply(b));
                case '-' -> result.push(a.subtract(b));
                case '/' -> {
                    if (b.equals(BigDecimal.ZERO)) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result.push(a.divide(b, 2, RoundingMode.HALF_UP));
                }
                case '=' -> {return a;}
            }
        }

        return result.pop();
    }
}

