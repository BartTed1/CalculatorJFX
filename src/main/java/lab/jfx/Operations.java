package lab.jfx;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Operations {
    public static double eval(String expression) {
        // zwróc 0 jeżeli przekazany został pusty ciąg
        if (expression == null || expression.isEmpty()) {
            return 0;
        }

        // lista z elementami wyrażenia
        List<String> components = new LinkedList<>(List.of(expression.split("[\\+\\-\\*\\/\u0025]")));
        // zamień przecinki na kropki oraz liczby zapisane jak int na double
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).contains(",")) components.set(i, components.get(i).replace(",", "."));
            else if (!components.get(i).contains(".")) components.set(i, components.get(i) + ".0");
        }

        // zakończ jeżeli nie ma co liczyć
        if (components.size() == 0) return 0.0;
        else if (components.size() == 1) return Double.parseDouble(components.get(0));

        // lista z operacjami wyrażenia
        List<String> operators = new LinkedList<>(List.of((expression.split("[0-9\\.]"))));
        operators.removeAll(Collections.singleton("")); // usuń elementy których wartość to ""
        operators.removeAll(Collections.singleton(",")); // usuń elementy których wartość to ","

        // operacje ustawione w kolejności wykonywania działań
        operation("%", components, operators);
        operation("/", components, operators);
        operation("*", components, operators);
        operation("+", components, operators);
        operation("-", components, operators);

        return Double.parseDouble(components.get(0)); // zwróć wynik
    }

    private static void operation(String operator, List<String> components, List<String> operators) {
        // metoda szuka w liście operatorów podany operator i wykonuje operację na sąsiadujących elementach,
        // wynik zapisuje na miejscu pierwszego elementu, a drugi element usuwa.
        while (operators.indexOf(operator) != -1) {
            int index = operators.indexOf(operator);
            switch (operator) {
                case "%":
                    components.set(index, String.valueOf(mod(components.get(index), components.get(index + 1))));
                    break;
                case "/":
                    components.set(index, String.valueOf(divide(components.get(index), components.get(index + 1))));
                    break;
                case "*":
                    components.set(index, String.valueOf(multiply(components.get(index), components.get(index + 1))));
                    break;
                case "+":
                    components.set(index, String.valueOf(add(components.get(index), components.get(index + 1))));
                    break;
                case "-":
                    components.set(index, String.valueOf(subtract(components.get(index), components.get(index + 1))));
                    break;
            }
            components.remove(index + 1);
            operators.remove(index);
        }
    }

    public static double add(String a, String b) {
        return Double.parseDouble(a) + Double.parseDouble(b);
    }

    public static double subtract(String a, String b) {
        return Double.parseDouble(a) - Double.parseDouble(b);
    }

    public static double multiply(String a, String b) {
        return Double.parseDouble(a) * Double.parseDouble(b);
    }

    public static double divide(String a, String b) {
        return Double.parseDouble(a) / Double.parseDouble(b);
    }

    public static double reverse(String a) { return 1 / eval(a); }

    public static double sqrt(String a) {
        return Math.sqrt(eval(a));
    }

    public static double log(String a) {
        return Math.log(eval(a));
    }

    public static double mod(String a, String b) {
        return Double.parseDouble(a) % Double.parseDouble(b);
    }
}
