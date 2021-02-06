import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
    static String isBalanced(String s) {
        List<Character> opens = Arrays.asList('{', '[', '(');
        List<Character> closes = Arrays.asList('}', ']', ')');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 닫히는 기호면 스택에서 꺼내면서 짝 맞는지 확인
            if (closes.contains(c)) {
                if (stack.isEmpty()) {
                    return "NO";
                }

                char closeChar = stack.pop();
                if (!(closeChar == '{' && c == '}') && !(closeChar == '[' && c == ']') && !(closeChar == '(' && c == ')')) {
                    return "NO";
                }
            }

            // 열리는 기호면 스택에 넣기
            if (opens.contains(c)) {
                stack.push(c);
            }
        }
        if (stack.size() > 0) {
            return "NO";
        }
        return "YES";
    }


    public static void main(String agrs[]) {
        String s = "[]([{][][)(])}()([}[}(})}])}))]](}{}})[]({{}}))[])(}}[[{]{}]()[(][])}({]{}[[))[[}[}{(]})()){{(]]){][";
//        String s = "{{[[(())]]}}";
        String result = isBalanced(s);
        System.out.print(result);
    }
}

