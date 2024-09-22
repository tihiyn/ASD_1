public class BalanceBracket {
    public boolean isBalanced(String str) {
        Stack<Character> bracketStack = new Stack<>();
        byte[] byteArray = str.getBytes();

        for (byte b: byteArray) {
            if (b == '(') {
                bracketStack.push((char) b);
                continue;
            }

            if (bracketStack.peek() == null || bracketStack.peek() != '(') {
                return false;
            }
            bracketStack.pop();
        }

        return bracketStack.size() == 0;
    }
}

