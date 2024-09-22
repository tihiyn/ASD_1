public class PostfixNotation {
    public int compute(Stack<Character> input) {
        Stack<Integer> result = new Stack<>();
        while (input.size() > 0) {
            char symbol = input.pop();
            switch (symbol) {
                case '+' -> {
                    int a = result.pop();
                    int b = result.pop();
                    result.push(a + b);
                }
                case '*' -> {
                    int a = result.pop();
                    int b = result.pop();
                    result.push(a * b);
                }
                case '=' -> {
                    return result.pop();
                }
                default -> result.push(symbol - 48);
            }
        }

        return result.pop();
    }
}

