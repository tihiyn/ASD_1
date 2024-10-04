public class Palindrom {
    public boolean isPalindrom(String s) {
        Deque<Character> deque = new Deque<>();
        s = s.toLowerCase().replaceAll(" ", "");

        for (char b: s.toCharArray()) {
            deque.addTail(b);
        }


        while (deque.size() > 1) {
            if (!deque.removeFront().equals(deque.removeTail())) {
                return false;
            }
        }

        return true;
    }
}

