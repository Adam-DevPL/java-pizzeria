package org.example.service.EmployeeService;

import org.example.model.Employee.Employee;
import org.example.model.Employee.EmployeeDto;
import org.example.model.Enums.Role;
import org.example.utils.error.CustomError;
import org.example.utils.error.EmployeeException;

import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {

    private final Set<Employee> employees = new HashSet<>();
    private final Set<Employee> freeEmployees = new HashSet<>();

    @Override
    public String addEmployee(final EmployeeDto employeeDto) {

        Employee employee = new Employee(employeeDto);

        boolean isEmployeeAdded = employees.add(employee);

        if (!isEmployeeAdded) {
            throw new EmployeeException(new IllegalArgumentException("Employee already exists"));
        }

        freeEmployees.add(employee);

        return employee.getId();
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        Optional<Employee> foundEmployee = findEmployeeById(employeeId);

        if (foundEmployee.isEmpty()) {
            throw new EmployeeException(new NoSuchElementException("Employee not found"));
        }

        boolean isEmployeeFree = findFreeEmployeeById(employeeId).isPresent();

        if (!isEmployeeFree) {
            throw new EmployeeException(new IllegalArgumentException("Employee is occupied"));
        }

        freeEmployees.remove(foundEmployee.get());

        return employees.remove(foundEmployee.get());
    }

    @Override
    public String getFreeEmployee(Role role) {
        Optional<Employee> foundEmployee = findEmployeeByRole(role);

        if (foundEmployee.isEmpty()) {
            return null;
        }

        Employee employee = foundEmployee.get();

        freeEmployees.remove(employee);

        return employee.getId();
    }

    @Override
    public Employee getEmployee(String employeeId) {
        Optional<Employee> foundEmployee = findEmployeeById(employeeId);

        if (foundEmployee.isEmpty()) {
            throw new EmployeeException(new NoSuchElementException("Employee not found"));
        }

        return foundEmployee.get();
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return employees;
    }


    private Optional<Employee> findEmployeeById(final String employeeId) {
        return employees.stream().filter(e -> e.getId().equals(employeeId)).findFirst();
    }

    private Optional<Employee> findFreeEmployeeById(final String employeeId) {
        return freeEmployees.stream().filter(e -> e.getId().equals(employeeId)).findFirst();
    }

    private Optional<Employee> findEmployeeByRole(final Role role) {
        return freeEmployees.stream().filter(e -> e.getRole().equals(role)).findFirst();
    }
}
