package org.example.service.TableService;

import org.example.model.Table.Table;
import org.example.model.Table.TableDto;

import java.util.List;
import java.util.Set;

public interface TableService {
    String addTable(TableDto tableDto);
    boolean deleteTable(String tableId);
    String getFreeTable(int seats);
    String getTable(String tableId);
    Set<Table> getAllTables();
}
