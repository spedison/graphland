package br.com.spedison.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConfigLogger {

    static public Logger getLogger(Class<?> classToLog) {
        ConfigLogger a = new ConfigLogger();
        return a.getLoggerLocal(classToLog);
    }
    public Logger getLoggerLocal(Class<?> classToLog) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("log.properties");

        // Para ler de um arquivo externo.
        //LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
        Logger log = null;
        try {
            LogManager.getLogManager().readConfiguration(is);
            log = Logger.getLogger(classToLog.getName());
            return log;
        } catch (IOException e) {
            System.err.println("Problems while read file configuration for log. There are not log.");
            throw new RuntimeException(e);
        }
    }
}
