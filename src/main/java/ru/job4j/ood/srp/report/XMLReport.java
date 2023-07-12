package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.formatter.DateTimeParser;
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
        for (Employee employee : store.findBy(filter)) {
            /* Получаем контекст для доступа к АПИ */
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            /* Создаем сериализатор */
            Marshaller marshaller = context.createMarshaller();
            /* Указываем, что нам нужно форматирование */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                /* Сериализуем */
                marshaller.marshal(employee, writer);
                text.append(writer.getBuffer().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return text.toString();
    }

}

