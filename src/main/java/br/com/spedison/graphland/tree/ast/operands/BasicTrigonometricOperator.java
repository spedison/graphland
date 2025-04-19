package br.com.spedison.graphland.tree.ast.operands;

import br.com.spedison.graphland.tree.ast.Token;
import br.com.spedison.graphland.tree.ast.TypeToken;

import java.util.List;
import java.util.Stack;

public class BasicTrigonometricOperator implements OperatorInterface {

    @Override
    public Token evoluate(Token operator, Stack<Token> operands) {

        if (operands.size() < 1)
            throw new RuntimeException("Operator deve receber 1 operador");

        Token op1 = operands.pop();
        Double valueOp1 = op1.doubleValue();

        Token ret = switch (operator.getValue()) {
            case "sin" -> new Token("%f".formatted(Math.sin(valueOp1)), TypeToken.NUMBER);
            case "cos" -> new Token("%f".formatted(Math.cos(valueOp1)), TypeToken.NUMBER);
            case "tg", "tan" -> new Token("%f".formatted(Math.tan(valueOp1)), TypeToken.NUMBER);
            case "csc" -> new Token("%f".formatted(1. / Math.sin(valueOp1)), TypeToken.NUMBER);
            case "sec" -> new Token("%f".formatted(1. / Math.cos(valueOp1)), TypeToken.NUMBER);
            case "ctg", "ctan" -> new Token("%f".formatted(1. / Math.tan(valueOp1)), TypeToken.NUMBER);
            default -> throw new RuntimeException("Não faz parte desse operator");
        };

        operands.push(ret);

        return ret;
    }

    @Override
    public List<String> getSymbolOperators() {
        return List.of("sin", "cos", "tan", "tg", "csc", "sec", "ctg", "ctan");
    }

    @Override
    public void fillOperatorsByType(List<String> listUnaryOperators, List<String> listBinaryOperators) {
        listUnaryOperators.addAll(getSymbolOperators());
    }
}