public class PostfixNotation {
    private final Map<Character, BiFunction<BigDecimal, BigDecimal, BigDecimal>> operationMap = new HashMap<>();
    
    public PostfixNotation() {
        operationMap.put('+', BigDecimal::add);
        operationMap.put('*', BigDecimal::multiply);
        operationMap.put('-', BigDecimal::subtract);
        operationMap.put('/', (x, y) -> {
            if (y.equals(BigDecimal.ZERO)) {
                throw new ArithmeticException("Division by zero");
            }
            return x.divide(y, 2, RoundingMode.HALF_UP);
        });
        operationMap.put('=', (x, y) -> x);
    } 
    
    public BigDecimal compute(Stack<Character> input) {
        Stack<BigDecimal> result = new Stack<>();
        while (input.size() > 0) {
            char symbol = input.pop();

            if (operationMap.containsKey(symbol)) {
                BigDecimal a = result.pop();
                BigDecimal b = result.pop();
                result.push(operationMap.get(symbol).apply(a, b));
            } else {
                result.push(BigDecimal.valueOf(Character.getNumericValue(symbol)));
            }
        }

        return result.pop();
    }
}

