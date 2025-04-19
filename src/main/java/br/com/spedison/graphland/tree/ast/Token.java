package br.com.spedison.graphland.tree.ast;

import br.com.spedison.logger.ConfigLogger;

import java.util.logging.Logger;

public class Token {

    static final Logger log = ConfigLogger.getLogger(Token.class);

    private String value;
    private TypeToken typeToken;

    public Token(String value, TypeToken typeToken) {
        this.value = value;
        this.typeToken = typeToken;
    }

    public String getValue() {
        return value;
    }

    public TypeToken getTypeToken() {
        return typeToken;
    }

    public Double doubleValue(){

        if(typeToken != TypeToken.NUMBER)
            throw new RuntimeException("Tentanto transformar um operador em um número DOUBLE.");

        return Double.parseDouble(value.replaceAll("[,]","."));
    }

    public Long longValue(){

        if(typeToken != TypeToken.NUMBER)
            throw new RuntimeException("Tentanto transformar um operador em um número LONG.");

        try {
            return Long.parseLong(value.replaceAll("[,]", "."));
        }catch (NumberFormatException nfe){
            log.info("Valor lido como long, mas será quebrado. :: " + value);
            Double valueRet = Double.parseDouble(value.replaceAll("[,]", "."));
            return valueRet.longValue();
        }
    }

    public int getOperatorPrecedence(){
        if (getTypeToken() == TypeToken.BINARY_OPERATOR){
            return switch (getValue()){
                case "^" -> 5;
                case "*","/","mod" -> 4;
                case "+","-" -> 3;
                default -> -1;
            };
        }
        return -1;
    }

    public boolean isLeftAssociative() {
        return switch (getValue()) {
            case "+", "-", "*", "/", "mod" -> true;
            case "^" -> false;
            default -> true;
        };
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", typeToken=" + typeToken +
                '}';
    }
}