package br.com.spedison.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
public class FormatterLog extends Formatter {
    Locale loc = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
    DateFormat sdf = DateFormat.getDateInstance(DateFormat.LONG, loc);
    @Override
    public String format(LogRecord record) {
        return "[%s]\t[%s]\t[%s]\t%s".formatted(
                sdf.format(new Date(record.getMillis())),
                record.getLevel().toString(),
                record.getSourceClassName(),
                record.getMessage()
        );
    }
}