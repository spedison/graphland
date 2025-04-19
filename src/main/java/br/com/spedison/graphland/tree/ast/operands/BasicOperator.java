package br.com.spedison.graphland.tree.ast.operands;

import br.com.spedison.graphland.tree.ast.Token;
import br.com.spedison.graphland.tree.ast.TypeToken;

import java.util.List;
import java.util.Stack;

public class BasicOperator implements OperatorInterface {

    @Override
    public Token evoluate(Token operator, Stack<Token> operands) {

        if (operands.size() < 2)
            throw new RuntimeException("Operator deve receber 2 operadores");

        Token op2 = operands.pop();
        Token op1 = operands.pop();

        Token ret = switch (operator.getValue()) {
            case "+" -> new Token("%f".formatted(op1.doubleValue() + op2.doubleValue()), TypeToken.NUMBER);
            case "-" -> new Token("%f".formatted(op1.doubleValue() - op2.doubleValue()), TypeToken.NUMBER);
            case "*" -> new Token("%f".formatted(op1.doubleValue() * op2.doubleValue()), TypeToken.NUMBER);
            case "/" -> new Token("%f".formatted(op1.doubleValue() / op2.doubleValue()), TypeToken.NUMBER);
            case "mod" -> new Token("%d".formatted(op1.longValue() % op2.longValue()), TypeToken.NUMBER);
            default -> throw new RuntimeException("Não faz parte desse operator");
        };

        operands.push(ret);

        return ret;
    }

    @Override
    public List<String> getSymbolOperators() {
        return List.of("+", "-", "*", "/", "mod");
    }

    @Override
    public void fillOperatorsByType(List<String> listUnaryOperatos, List<String> listBinaryOperators) {
        listBinaryOperators.addAll(getSymbolOperators());
    }
}