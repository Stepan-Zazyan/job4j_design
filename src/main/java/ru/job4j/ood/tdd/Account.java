package ru.job4j.ood.tdd;

import java.util.List;

public interface Account {

    Ticket addTicketToAccount(Ticket ticket);

    List<Ticket> getTickets();
}
