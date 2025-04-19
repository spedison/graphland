package br.com.spedison.graphland.tree.ast.operands;

import br.com.spedison.graphland.tree.ast.Token;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface OperatorInterface {
    Token evoluate(Token operator, Stack<Token> operands);

    default boolean isThisOperator(Token operator) {
        return getSymbolOperators().contains(operator.getValue());
    }

    List<String> getSymbolOperators();

    void fillOperatorsByType(List<String> listUnaryOperators, List<String> listBinaryOperators);

    default void makeHashMapOperators(Map<String, OperatorInterface> map){
        getSymbolOperators().stream().forEach(a ->{
            map.put(a, this);
        });
    }
}