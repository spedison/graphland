package br.com.spedison.graphland.helper;

import br.com.spedison.graphland.base.Orientation;

public class ConverterValues {

    private ConverterValues() {
    }

    public static Double stringToDouble(String strValue) {
        strValue = strValue.trim().toUpperCase();
        if (strValue.equals("INF") || strValue.equals("+INF"))
            return Double.MAX_VALUE;
        if (strValue.equals("-INF"))
            return -Double.MAX_VALUE;
        return Double.parseDouble(strValue);
    }

    public static Integer stringToInteger(String strValue) {
        strValue = strValue.trim().toUpperCase();
        if (strValue.equals("INF") || strValue.equals("+INF"))
            return Integer.MAX_VALUE;
        if (strValue.equals("-INF"))
            return Integer.MIN_VALUE;
        return Integer.parseInt(strValue);
    }

    public static Orientation stringToOrientation(String strValue) {
        if (strValue.trim().compareToIgnoreCase("FULL") == 0)
            return Orientation.FULL_CONNECTED;
        else return Orientation.SIMPLE_CONNECTED;
    }

    public static String integerToString(Integer intValue) {
        if (intValue == Integer.MAX_VALUE)
            return "+INF";
        if (intValue == Integer.MIN_VALUE || intValue == -Integer.MAX_VALUE)
            return "-INF";
        return "%d".formatted(intValue);
    }

    public static String doubleToString(Double doubleValue) {
        if (doubleValue == Double.MAX_VALUE)
            return "+INF";
        if (doubleValue == -Double.MAX_VALUE)
            return "-INF";
        return "%.15f".formatted(doubleValue);
    }

    public static String bytesToHexString(byte[] byteValues) {
        StringBuilder ret = new StringBuilder();
        for (byte v : byteValues)
            ret.append("%02x".formatted(v));
        return ret.toString();
    }
}
