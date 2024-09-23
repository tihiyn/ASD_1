import java.util.Map;

public class BalanceDifferentBracket {
    public boolean isBalanced(String str) {
        Stack<Character> bracketStack = new Stack<>();
        byte[] byteArray = str.getBytes();
        Map<Character, Character> map = Map.of('(', ')', '[', ']', '{', '}');

        for (byte b: byteArray) {
            if (map.containsKey((char) b)) {
                bracketStack.push(map.get((char) b));
                continue;
            }

            if (bracketStack.peek() == null || (bracketStack.peek() != (char) b)) {
                return false;
            }

            bracketStack.pop();
        }

        return bracketStack.size() == 0;
    }
}

