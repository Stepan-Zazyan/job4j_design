package ru.job4j.ood.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportDateTimeParser extends XmlAdapter<String, Calendar> implements DateTimeParser<Calendar> {

    private static final ThreadLocal<DateFormat> DATE_FORMAT
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd:MM:yyyy HH:mm"));

    @Override
    public String parse(Calendar calendar) {
        return DATE_FORMAT.get().format(calendar.getTime());
    }

    @Override
    public Calendar unmarshal(String d) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DATE_FORMAT.get().parse(d));
        return cal;
    }

    @Override
    public String marshal(Calendar d) {
        return DATE_FORMAT.get().format(d.getTime());
    }
}