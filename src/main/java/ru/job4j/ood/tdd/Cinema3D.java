package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    List<Session> list = new ArrayList<>();
    int[][] hall = new int[30][30];
    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> l = new ArrayList<>();
        for (Session x : list) {
            if (filter.test(x)) {
               l = list;
            }
        }
        return l;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row < 0 || row > 30 || column < 0 || column > 30) {
            throw new IllegalArgumentException();
        }
        Ticket ticket = new Ticket3D(row, column);
        account.addTicketToAccount(ticket);
        return ticket;
    }

    @Override
    public void add(Session session) {
        list.add(session);
    }
}
