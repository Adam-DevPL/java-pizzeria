package org.example.model.Table;

import java.util.Objects;
import java.util.UUID;

public class Table {

    private final String id = UUID.randomUUID().toString();
    private final int number;
    private final int seats;

    public Table(final TableDto tableDto) {
        this.number = tableDto.number();
        this.seats = tableDto.seats();
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return number == table.number && seats == table.seats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, seats);
    }
}
