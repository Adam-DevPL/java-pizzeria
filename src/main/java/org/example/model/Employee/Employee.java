package org.example.model.Employee;

import org.example.model.Enums.Role;
import org.example.model.Enums.Status;

import java.util.Objects;
import java.util.UUID;

public class Employee {

    private final String id = UUID.randomUUID().toString();
    private final String name;
    private final Role role;
    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.name();
        this.role = employeeDto.role();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && role == employee.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }
}
