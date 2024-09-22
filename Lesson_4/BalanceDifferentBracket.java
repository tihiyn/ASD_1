import java.util.Set;

public class BalanceDifferentBracket {
    public boolean isBalanced(String str) {
        Stack<Character> bracketStack = new Stack<>();
        byte[] byteArray = str.getBytes();
        Set<Character> open = Set.of('(', '[', '{');

        for (byte b: byteArray) {
            if (open.contains((char) b)) {
                bracketStack.push((char) b);
                continue;
            }

            if ((bracketStack.peek() == null || (Math.abs(b - bracketStack.peek())) > 2)) {
                return false;
            }

            bracketStack.pop();
        }

        return bracketStack.size() == 0;
    }
}

