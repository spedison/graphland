package br.com.spedison.graphland.helper;

public class ManipuleBytes {
    static public byte[] skipLines(byte[] inData, int numberOfLinesToSkip) {
        int start = 0;
        int countLinesSkiped = 0;
        int countCurrent = 0;
        int limitBytes = inData.length;
        for (; countCurrent < limitBytes && countLinesSkiped < numberOfLinesToSkip; countCurrent++) {
            if (inData[countCurrent] == '\r' || inData[countCurrent] == '\n') {
                countLinesSkiped++;
                if (inData[countCurrent] == '\r' &&
                        (countCurrent + 1) > limitBytes &&
                        inData[countCurrent + 1] == '\n')
                    countCurrent++;
            }
        }
        byte[] ret = new byte[limitBytes - countCurrent];
        System.arraycopy(inData, countCurrent, ret, 0, ret.length);
        return ret;
    }
}
