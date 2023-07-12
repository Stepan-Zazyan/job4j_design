package ru.job4j.ood.srp.report;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JsonReportTest {

    @Test
    void generate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        JsonReport jsonReport = new JsonReport(store, parser);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", worker1.getName());
        jsonObject.put("hired", parser.parse(worker1.getHired()));
        jsonObject.put("fired", parser.parse(worker1.getFired()));
        jsonObject.put("salary", worker1.getSalary());
        jsonArray.put(jsonObject);
        assertThat(jsonReport.generate(s -> true)).isEqualTo(jsonArray.toString());
    }
}