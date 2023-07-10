package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class HrReportTest {

        @Test
        public void whenAccountantGenerated() {
            MemStore store = new MemStore();
            Calendar now = Calendar.getInstance();
            Employee worker1 = new Employee("Ivan", now, now, 100);
            Employee worker2 = new Employee("Kolya", now, now, 200);
            Employee worker3 = new Employee("Tolya", now, now, 3200);
            DateTimeParser<Calendar> parser = new ReportDateTimeParser();
            store.add(worker1);
            store.add(worker2);
            store.add(worker3);
            HrReport hrReport = new HrReport(store, parser);
            StringBuilder expect = new StringBuilder()
                    .append("Name; Salary;")
                    .append(System.lineSeparator())
                    .append(worker3.getName()).append(" ")
                    .append(worker3.getSalary())
                    .append(System.lineSeparator())
                    .append(worker2.getName()).append(" ")
                    .append(worker2.getSalary())
                    .append(System.lineSeparator())
                    .append(worker1.getName()).append(" ")
                    .append(worker1.getSalary())
                    .append(System.lineSeparator());
            assertThat(hrReport.generate(s -> true)).isEqualTo(expect.toString());
        }
}