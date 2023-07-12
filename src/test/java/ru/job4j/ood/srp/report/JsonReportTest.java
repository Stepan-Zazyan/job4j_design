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
        Employee worker2 = new Employee("Kolya", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        JsonReport jsonReport = new JsonReport(store, parser);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject1.put("name", worker1.getName());
        jsonObject1.put("hired", parser.parse(worker1.getHired()));
        jsonObject1.put("fired", parser.parse(worker1.getFired()));
        jsonObject1.put("salary", worker1.getSalary());
        jsonObject2.put("name", worker2.getName());
        jsonObject2.put("hired", parser.parse(worker2.getHired()));
        jsonObject2.put("fired", parser.parse(worker2.getFired()));
        jsonObject2.put("salary", worker2.getSalary());
        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject2);
        assertThat(jsonReport.generate(s -> true)).isEqualTo(jsonArray.toString());
    }
}