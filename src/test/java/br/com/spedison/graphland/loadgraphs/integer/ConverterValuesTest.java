package br.com.spedison.graphland.loadgraphs.integer;

import br.com.spedison.graphland.base.Orientation;
import br.com.spedison.graphland.helper.ConverterValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterValuesTest {

    @Test
    void stringToDouble() {
        String valueStr1 = "10.25";
        Double valueDouble1 = 10.25;
        assertEquals(valueDouble1, ConverterValues.stringToDouble(valueStr1));

        String valueStr2 = "-INF";
        Double valueDouble2 = -Double.MAX_VALUE;
        assertEquals(valueDouble2,ConverterValues.stringToDouble(valueStr2));

        String valueStr3 = "INF";
        Double valueDouble3 = Double.MAX_VALUE;
        assertEquals(valueDouble3,ConverterValues.stringToDouble(valueStr3));
    }

    @Test
    void stringToInteger() {
        String valueStr1 = "1025";
        Integer valueInteger1 = 1025;
        assertEquals(valueInteger1,ConverterValues.stringToInteger(valueStr1));

        String valueStr2 = "-INF";
        Integer valueInteger2 = Integer.MIN_VALUE;
        assertEquals(valueInteger2,ConverterValues.stringToInteger(valueStr2));

        String valueStr3 = "INF";
        Integer valueInteger3 = Integer.MAX_VALUE;
        assertEquals(valueInteger3,ConverterValues.stringToInteger(valueStr3));
    }

    @Test
    void stringToOrientation() {
        Orientation o1 = Orientation.FULL_CONNECTED;
        String oStr1 = "FULL";
        assertEquals(o1,ConverterValues.stringToOrientation(oStr1));

        Orientation o2 = Orientation.SIMPLE_CONNECTED;
        String oStr2 = "SIMPLE";
        assertEquals(o2,ConverterValues.stringToOrientation(oStr2));
    }

    @Test
    void integerToString() {
        Integer valInt1 = 10;
        String  valStr1 = "10";
        assertEquals(valStr1, ConverterValues.integerToString(valInt1));

        Integer valInt2 = Integer.MAX_VALUE;
        String  valStr2 = "+INF";
        assertEquals(valStr2, ConverterValues.integerToString(valInt2));

        Integer valInt3 = Integer.MIN_VALUE;
        String  valStr3 = "-INF";
        assertEquals(valStr3, ConverterValues.integerToString(valInt3));
    }

    @Test
    void doubleToString() {

        Double valDouble1 = 10.;
        String  valStr1 = "10,000000000000000";
        assertEquals(valStr1, ConverterValues.doubleToString(valDouble1));

        Double valDouble2 = Double.MAX_VALUE;
        String  valStr2 = "+INF";
        assertEquals(valStr2, ConverterValues.doubleToString(valDouble2));

        Double valDouble3 = -Double.MAX_VALUE;
        String  valStr3 = "-INF";
        assertEquals(valStr3, ConverterValues.doubleToString(valDouble3));
    }
}