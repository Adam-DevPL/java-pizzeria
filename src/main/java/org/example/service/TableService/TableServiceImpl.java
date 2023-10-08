package org.example.service.TableService;

import org.example.model.Table.Table;
import org.example.model.Table.TableDto;
import org.example.utils.error.CustomError;
import org.example.utils.error.TableException;

import java.util.*;

public class TableServiceImpl implements TableService {

    private final Set<Table> tables = new HashSet<>();
    private final Set<Table> freeTables = new HashSet<>();

    @Override
    public String addTable(final TableDto tableDto) {

        Table table = new Table(tableDto);

        boolean isTableAdded = tables.add(table);

        if (!isTableAdded) {
            throw new TableException(new IllegalArgumentException("Table already exists"));
        }
        
        tables.add(table);
        freeTables.add(table);
        
        return table.getId();
    }

    @Override
    public boolean deleteTable(final String tableId) {
        Optional<Table> foundTable = findTableById(tableId);
        
        if (foundTable.isEmpty()) {
            throw new TableException(new NoSuchElementException("Table not found"));
        }
        
        Optional<Table> foundFreeTable = findFreeTableById(tableId);
        
        if (foundFreeTable.isEmpty()) {
            throw new TableException(new IllegalArgumentException("Table is occupied"));
        }
        
        freeTables.remove(foundTable.get());
        tables.remove(foundTable.get());
        
        return true;
    }

    @Override
    public String getFreeTable(final int seats) {
        Optional<Table> foundTable = getTableWithNumberOfSeats(seats);
        
        if (foundTable.isEmpty()) {
            return null;
        }
        
        Table table = foundTable.get();
        
        freeTables.remove(table);
        
        return table.getId();
    }



    @Override
    public String getTable(final String tableId) {
        Optional<Table> foundTable = findTableById(tableId);

        if (foundTable.isEmpty()) {
            throw new TableException(new NoSuchElementException("Table not found"));
        }

        return foundTable.get().getId();
    }

    @Override
    public Set<Table> getAllTables() {
        return tables;
    }

    private Optional<Table> findTableById(final String tableId) {
        return tables.stream()
                .filter(table -> table.getId().equals(tableId))
                .findFirst();
    }

    private Optional<Table> findFreeTableById(String tableId) {
        return freeTables.stream()
                .filter(table -> table.getId().equals(tableId))
                .findFirst();
    }

    private Optional<Table> getTableWithNumberOfSeats(int seats) {
        return freeTables.stream()
                .filter(table -> table.getSeats() >= seats)
                .findFirst();
    }
}
