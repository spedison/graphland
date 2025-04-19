package br.com.spedison.graphland.tree.ast;

import br.com.spedison.graphland.tree.Node;
import br.com.spedison.graphland.tree.ast.operands.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class MathIntepreter extends AbstractSyntaxTree {

    Map<String, OperatorInterface> operators = new HashMap<>();
    List<String> unaryOperators = new LinkedList<>();
    List<String> binaryOperators = new LinkedList<>();

    private void addOperator(OperatorInterface operatorInterface) {
        operatorInterface.makeHashMapOperators(operators);
        operatorInterface.fillOperatorsByType(unaryOperators, binaryOperators);
    }

    public MathIntepreter() {
        // This part customize the interpreter.
        // If you need new operations add new Operator using class here.

        this.addOperator(new BasicOperator());
        this.addOperator(new LogOperator());
        this.addOperator(new BasicTrigonometricOperator());
        this.addOperator(new PowAndRootOperator());

        // Interpreter Know String operations.
        // Caution use lowercase.
        super.setBinaryOperator(binaryOperators);
        super.setUnaryOperators(unaryOperators);
    }

    public Stack<Token> evoluate() {
        final Stack<Token> ret = new Stack<>();
        processTreePosOrder(getRootNode(), new Consumer<Node<Token>>() {
            @Override
            public void accept(Node<Token> tokenNode) {
                if (tokenNode.getData().getTypeToken() == TypeToken.NUMBER)
                    ret.push(tokenNode.getData());

                if (tokenNode.getData().getTypeToken() == TypeToken.BINARY_OPERATOR ||
                        tokenNode.getData().getTypeToken() == TypeToken.UNARY_OPERATOR) {

                    // In this point call external implementation of operation
                    OperatorInterface op = operators.getOrDefault(tokenNode.getData().getValue(), null);
                    if (op == null) {
                        throw new RuntimeException("Operacao Não implementada");
                    }
                    op.evoluate(tokenNode.getData(), ret);
                }
            }
        });
        return ret;
    }

    public static void main(String[] args) {

        MathIntepreter mi = new MathIntepreter();
        String calc1 = "( ( log10 ( 10.99 + 5.5 * 40 ) ) mod 45 ) / sin ( 3 )";
        mi.setTokens(calc1);
        mi.fillTree();
        mi.generateDotFile("/tmp/nova.dot", new Function<Token, String>() {
            @Override
            public String apply(Token token) {
                return "%s\n%s".formatted(token.getValue(), token.getTypeToken());
            }
        }, true, true);
        Stack<Token> ret = mi.evoluate();
        log.info("Tamanho da pilha de retorno : %d".formatted(ret.size()));
        log.info("Evoluindo a expressão [[ %s ]] teremos a pilha [[ %s ]]".formatted(calc1, ret.toString()));

        //mi.setTokens("log10 ( ( 102 mod 2 ) + 10 )");
        String equacao = "( ( sin ( 3.141592 / 2.0 ) ) + 2 ) ^ ( 3 + 2 )";
        mi.setTokens(equacao);
        mi.fillTree();
        Stack<Token> tk = mi.evoluate();
        log.info("Valor do %s = %s".formatted(equacao, tk.get(0).getValue()));
    }

}
