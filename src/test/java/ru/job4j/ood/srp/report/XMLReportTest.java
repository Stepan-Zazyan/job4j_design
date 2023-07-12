package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {

    @Test
    void generate() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Kolya", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        XMLReport xmlReport = new XMLReport(store, parser);
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + System.lineSeparator()
                + "<employee name=\"Ivan\" hired=\"" + parser.parse(now) + "\" fired=\"" + parser.parse(now) + "\" salary=\"100.0\"/>" + System.lineSeparator()
                + "<employee name=\"Kolya\" hired=\"" + parser.parse(now) + "\" fired=\"" + parser.parse(now) + "\" salary=\"200.0\"/>");
        assertThat(xmlReport.generate(em -> true)).isEqualTo(stringBuilder.toString());
    }
}