package org.example.utils.validations;

import org.example.model.Table.TableDto;

public class ValidateTableDto {
    public static boolean validateTableDto(TableDto tableDto) {
        return tableDto.number() > 0 && tableDto.seats() > 0;
    }
}
