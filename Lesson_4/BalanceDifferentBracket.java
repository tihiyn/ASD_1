import java.util.Map;

public class BalanceDifferentBracket {
    public boolean isBalanced(String str) {
        Stack<Character> bracketStack = new Stack<>();
        byte[] byteArray = str.getBytes();
        Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');

        for (byte b: byteArray) {
            if (map.containsValue((char) b)) {
                bracketStack.push((char) b);
                continue;
            }

            if (bracketStack.peek() == null || (bracketStack.peek() != map.get((char) b))) {
                return false;
            }

            bracketStack.pop();
        }

        return bracketStack.size() == 0;
    }
}

