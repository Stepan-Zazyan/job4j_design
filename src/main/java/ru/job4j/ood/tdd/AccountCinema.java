package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.List;

public class AccountCinema implements Account {
    List<Ticket> tickets = new ArrayList<>();
    @Override
    public Ticket addTicketToAccount(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
}
