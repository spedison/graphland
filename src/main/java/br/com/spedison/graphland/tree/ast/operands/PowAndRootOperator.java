package br.com.spedison.graphland.tree.ast.operands;

import br.com.spedison.graphland.tree.ast.Token;
import br.com.spedison.graphland.tree.ast.TypeToken;

import java.util.List;
import java.util.Stack;

public class PowAndRootOperator implements OperatorInterface {

    @Override
    public Token evoluate(Token operator, Stack<Token> operands) {

        if (operands.size() < 1)
            throw new RuntimeException("Operator deve receber 1 operador");

        Token op1 = operands.pop();
        Token op2 = null;

        if (operator.getTypeToken() == TypeToken.BINARY_OPERATOR)
            op2 = operands.pop();

        Double valueOp1 = op1.doubleValue();
        Double valueOp2 = op2 != null ? op2.doubleValue() : 0.;

        Token ret = switch (operator.getValue()) {
            case "^" -> new Token("%f".formatted(Math.pow(valueOp2, valueOp1)), TypeToken.NUMBER);
            case "pow2" -> new Token("%f".formatted(valueOp1 * valueOp1), TypeToken.NUMBER);
            case "pow3" -> new Token("%f".formatted(valueOp1 * valueOp1 * valueOp1), TypeToken.NUMBER);
            case "sqrt" -> new Token("%f".formatted(Math.sqrt(valueOp1)), TypeToken.NUMBER);
            case "troot", "trt" -> new Token("%f".formatted(Math.pow(valueOp1, 1. / 3.)), TypeToken.NUMBER);
            default -> throw new RuntimeException("Não faz parte desse operator");
        };

        operands.push(ret);

        return ret;
    }

    @Override
    public List<String> getSymbolOperators() {
        return List.of("^", "pow2", "pow3", "sqrt", "troot", "trt");
    }

    @Override
    public void fillOperatorsByType(List<String> listUnaryOperators, List<String> listBinaryOperators) {
        List<String> symbols = getSymbolOperators();
        listBinaryOperators.addAll(symbols.subList(0, 1));
        listUnaryOperators.addAll(symbols.subList(1, symbols.size()));
    }
}