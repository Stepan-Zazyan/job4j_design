package ru.job4j.ood.srp.model;

import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;
import java.util.Objects;
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Comparable<Employee> {
    @XmlAttribute
    private String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(ReportDateTimeParser.class)
    private Calendar hired;
    @XmlAttribute
    @XmlJavaTypeAdapter(ReportDateTimeParser.class)
    private Calendar fired;
    @XmlAttribute
    private double salary;

    public  Employee() {

    }

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(o.salary, salary);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", hired=" + hired
                + ", fired=" + fired
                + ", salary=" + salary
                + '}';
    }
}