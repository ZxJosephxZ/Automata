import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostfixConverter {
    public static String infixToPostfix(String regex)
    {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> precedencia = new HashMap<>();
        precedencia.put('|', 1);
        precedencia.put('.', 2);
        precedencia.put('*', 3);

        for (int i = 0; i < regex.length(); i++) {
            char c = regex.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                // Si es un operando (letra), añadirlo al resultado
                postfix.append(c);
            } else if (c == '(') {
                // Si es un paréntesis izquierdo, apilarlo
                stack.push(c);
            } else if (c == ')') {
                // Si es un paréntesis derecho, desapilar hasta encontrar el izquierdo
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Descartar el paréntesis izquierdo
            } else {
                // Si es un operador, manejar precedencia
                while (!stack.isEmpty() && precedencia.containsKey(stack.peek()) &&
                        precedencia.get(c) <= precedencia.get(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Desapilar cualquier operador restante
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

}
