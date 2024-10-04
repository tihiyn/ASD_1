import java.util.Map;
import java.util.Stack;

public class BalanceDifferentBracketWithCharacters {
    public boolean isBalanced(String str) {
        Stack<Character> bracketStack = new Stack<>();
        byte[] byteArray = str.getBytes();
        Map<Character, Character> map = Map.of('(', ')', '[', ']', '{', '}');

        for (byte b: byteArray) {
            if (map.containsKey((char) b)) {
                bracketStack.push(map.get((char) b));
                continue;
            }

            if (map.containsValue((char) b) && (bracketStack.isEmpty() || (bracketStack.peek() != (char) b))) {
                return false;
            }

            if (!map.containsValue((char) b)) {
                continue;
            }

            bracketStack.pop();
        }

        return bracketStack.isEmpty();
    }
}

