package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XMLReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }
    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        StringBuilder text = new StringBuilder();
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        for (Employee employee : store.findBy(filter)) {
            try (StringWriter writer = new StringWriter()) {
                /* Сериализуем */
                marshaller.marshal(employee, writer);
                text.append(writer.getBuffer().toString());
                marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return text.toString();
    }


    public static void main(String[] args) throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Kolya", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        XMLReport xmlReport = new XMLReport(store, parser);
        System.out.println(xmlReport.generate(em -> true));
    }

}

