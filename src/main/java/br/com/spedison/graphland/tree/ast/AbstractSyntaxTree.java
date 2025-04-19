package br.com.spedison.graphland.tree.ast;

import br.com.spedison.graphland.tree.MakeTree;
import br.com.spedison.graphland.tree.Node;
import br.com.spedison.logger.ConfigLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractSyntaxTree extends MakeTree<Token> {

    static final Logger log = ConfigLogger.getLogger(AbstractSyntaxTree.class);

    private List<String> unaryOperators;
    private List<String> binaryOperator;
    private List<Token> tokens;
    private final String endOfExecution = ";";

    public AbstractSyntaxTree(List<String> binaryOperator, List<String> unaryOperators){
        super();
        this.unaryOperators = unaryOperators;
        this.binaryOperator = binaryOperator;
    }

    public AbstractSyntaxTree() {
        super();
        this.unaryOperators = new ArrayList<>();
        this.binaryOperator = new ArrayList<>();
        this.tokens = new ArrayList<>();
        addSimpleOperations();
        addUnaryOperations();
    }

    public void setBinaryOperator(List<String> binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    public void setUnaryOperators(List<String> unaryOperators) {
        this.unaryOperators = unaryOperators;
    }

    private Token convStringToToken(String str) {
        if (isUniOperator(str))
            return new Token(str, TypeToken.UNARY_OPERATOR);

        if (isBiOperator(str))
            return new Token(str, TypeToken.BINARY_OPERATOR);

        if (isNumber(str))
            return new Token(str, TypeToken.NUMBER);

        if (str.equals("("))
            return new Token(str, TypeToken.LEFT_PARENTHESIS);

        if (str.equals(")"))
            return new Token(str, TypeToken.RIGHT_PARENTHESIS);

        throw new RuntimeException("Ainda não trabalhamos com variáveis. tokem :: " + str);
    }

    public void addSimpleOperations() {
        binaryOperator.add("+");
        binaryOperator.add("-");
        binaryOperator.add("*");
        binaryOperator.add("/");
        binaryOperator.add("mod");
    }

    public void addUnaryOperations() {
        unaryOperators.add("sin");
        unaryOperators.add("cos");
        unaryOperators.add("tan");
        unaryOperators.add("log");
        unaryOperators.add("ln");
    }

    public boolean isNumber(String token) {
        Pattern p = Pattern.compile("(([+-]?)[0-9]+)([.,][0-9]*)?([eE]([+-]?)[0-9]+)?");
        Matcher m = p.matcher(token.trim());
        return m.find();
    }

    public boolean isBiOperator(String token) {
        return binaryOperator.contains(token.trim().toLowerCase());
    }

    public boolean isUniOperator(String token) {
        return unaryOperators.contains(token.trim().toLowerCase());
    }

    public void setTokens(String expression) {
        tokens.clear();
        Arrays
                .stream(expression.split("[\n\t ]"))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isBlank())
                .map(this::convStringToToken)
                .forEach(tokens::add);
    }

    public void fillTree() {
        Stack<Node<Token>> operatorsStack = new Stack<Node<Token>>(); // Operadores
        Stack<Node<Token>> operandStack = new Stack<Node<Token>>(); // Operandos

        for (Token tk : tokens) {

            if (tk.getTypeToken() == TypeToken.NUMBER) {
                Node<Token> leafNode = new Node<Token>(tk, null, null);
                operandStack.push(leafNode);
            }

            if (tk.getTypeToken() == TypeToken.UNARY_OPERATOR) {
                Node<Token> intermediateNode = new Node<>(tk, null, null);
                operatorsStack.push(intermediateNode);
            }

            if (tk.getTypeToken() == TypeToken.LEFT_PARENTHESIS) {
                Node<Token> intermediateNode = new Node<>(tk, null, null);
                operatorsStack.push(intermediateNode);
            }
            // Working with "(" and ")"
            if (tk.getTypeToken() == TypeToken.RIGHT_PARENTHESIS) {
                boolean stopPopStack = false;
                while (!stopPopStack) {

                    Node<Token> tokenOperador = operatorsStack.pop();
                    if (tokenOperador.getData().getTypeToken() == TypeToken.LEFT_PARENTHESIS) {
                        stopPopStack = true;
                        continue;
                    }

                    if (tokenOperador.getData().getTypeToken() == TypeToken.BINARY_OPERATOR) {
                        Node<Token> nk1 = operandStack.pop();
                        Node<Token> nk2 = operandStack.pop();
                        tokenOperador.setLeftNode(nk2);
                        tokenOperador.setRightNode(nk1);
                        operandStack.push(tokenOperador);
                    } else if (tokenOperador.getData().getTypeToken() == TypeToken.UNARY_OPERATOR) {
                        Node<Token> nk1 = operandStack.pop();
                        tokenOperador.setLeftNode(nk1);
                        operandStack.push(tokenOperador);
                    }
                } // Ena of while
            }// End of working with Parenthesis

            // Working with binary opeators
            if (tk.getTypeToken() == TypeToken.BINARY_OPERATOR) {

                while (
                        !operatorsStack.isEmpty() &&
                                operatorsStack.peek().getData().getTypeToken() == TypeToken.BINARY_OPERATOR &&
                                (
                                        operatorsStack.peek().getData().getOperatorPrecedence() > tk.getOperatorPrecedence() ||
                                                (
                                                        operatorsStack.peek().getData().getOperatorPrecedence() == tk.getOperatorPrecedence() &&
                                                                tk.isLeftAssociative()
                                                )
                                )
                ) {
                    // Desempilha operador da pilha
                    Node<Token> operador = operatorsStack.pop();
                    // Pega operandos (direita primeiro!)
                    Node<Token> leftNode = operandStack.pop();
                    Node<Token> rightNode = operandStack.pop();
                    operador.setLeftNode(leftNode);
                    operador.setRightNode(rightNode);
                    operandStack.push(operador);
                }

                // Agora sim empilha o novo operador atual
                Node<Token> novoOperador = new Node<>(tk);
                operatorsStack.push(novoOperador);
            }
        }

        // Passo 1: Desempilha operadores restantes
        while (!operatorsStack.isEmpty()) {
            Node<Token> operador = operatorsStack.pop();

            if (operador.getData().getTypeToken() == TypeToken.BINARY_OPERATOR) {
                Node<Token> dir = operandStack.pop();
                Node<Token> esq = operandStack.pop();
                operador.setLeftNode(esq);
                operador.setRightNode(dir);
                operandStack.push(operador);
            } else if (operador.getData().getTypeToken() == TypeToken.UNARY_OPERATOR) {
                Node<Token> arg = operandStack.pop();
                operador.setLeftNode(arg);
                operandStack.push(operador);
            }
        }

// Passo 2: Define o nó raiz da AST
        if (!operandStack.isEmpty()) {
            Node<Token> rootNodeFinal = operandStack.pop();
            setRootNode(rootNodeFinal);
        }
    }

    public static void main(String[] args) {
        AbstractSyntaxTree ast = new AbstractSyntaxTree();
        ast.setTokens("3 + ( 4 * 2 ) - 1 + log 10");
        ast.fillTree();

        log.info("Tokens iniciais :: " + ast.getTokens());
        log.info("Imprimindo os elementos na forma RPN para futuro processamento.");
        ast.printPosOrder();
        log.info("Fim da impressao da forma RPN");
        log.info("Numero de nodes = " + ast.getNumElements());
        log.info("Altua máxima = " + ast.calculateMaxHeigth());
        log.info("Altua mínima = " + ast.calculateMinHeigth());
        log.info("Lista dos tokens por nível da árvore.");
        for (int i = 0; i <= ast.calculateMaxHeigth(); i++) {
            List<Token> nos = ast.getValoresDoNivel(i);
            final int k = i;
            nos.stream().map(t -> "No nível " + k + " temos :: " + t).forEach(log::info);
        }

        ast.generateDotFile("/tmp/a.dot", new Function<Token, String>() {
            @Override
            public String apply(Token token) {
                return " [ %s ] - %s ".formatted(token.getValue(), token.getTypeToken());
            }
        },true, true);

    }

    private List<Token> getTokens() {
        return tokens;
    }
}