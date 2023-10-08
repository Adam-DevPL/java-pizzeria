package org.example.service.EmployeeService;

import org.example.model.Employee.Employee;
import org.example.model.Employee.EmployeeDto;
import org.example.model.Enums.Role;

import java.util.Set;

public interface EmployeeService {
    String addEmployee(EmployeeDto employeeDto);
    boolean deleteEmployee(String employeeId);
    String getFreeEmployee(Role role);
    Employee getEmployee(String employeeId);
    Set<Employee> getAllEmployees();
}
