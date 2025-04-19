package br.com.spedison.graphland.tree.ast.operands;

import br.com.spedison.graphland.tree.ast.Token;
import br.com.spedison.graphland.tree.ast.TypeToken;

import java.util.List;
import java.util.Stack;

public class LogOperator implements OperatorInterface {

    @Override
    public Token evoluate(Token operator, Stack<Token> operands) {

        if (operands.size() < 1)
            throw new RuntimeException("Operator deve receber 1 operador");

        Token op1 = operands.pop();
        Double valueOp1 = op1.doubleValue();

        Token ret = switch (operator.getValue()) {
            case "log2" -> new Token("%f".formatted(Math.log10(valueOp1) / Math.log10(2)), TypeToken.NUMBER);
            case "log10" -> new Token("%f".formatted(Math.log10(valueOp1)), TypeToken.NUMBER);
            case "ln" -> new Token("%f".formatted(Math.log(valueOp1)), TypeToken.NUMBER);
            default -> throw new RuntimeException("Não faz parte desse operator");
        };

        operands.push(ret);

        return ret;
    }

    @Override
    public List<String> getSymbolOperators() {
        return List.of("log2", "log10", "ln");
    }

    @Override
    public void fillOperatorsByType(List<String> listUnaryOperators, List<String> listBinaryOperators) {
        listUnaryOperators.addAll(getSymbolOperators());
    }
}