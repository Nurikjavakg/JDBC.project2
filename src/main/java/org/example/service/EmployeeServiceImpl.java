package org.example.service;

import org.example.dao.EmployeeImpl;
import org.example.model.Employee;
import org.example.model.Job;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements Eployee{
    EmployeeImpl employees = new EmployeeImpl();
    @Override
    public void createEmployee() {
        employees.createEmployee();


    }

    @Override
    public void addEmployee(Employee employee) {
        employees.addEmployee(employee);

    }

    @Override
    public void dropTable() {
        employees.dropTable();

    }

    @Override
    public void cleanTable() {
        employees.cleanTable();

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        employees.updateEmployee(id,employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees.getAllEmployees();

    }

    @Override
    public Employee findByEmail(String email) {
        return employees.findByEmail(email);

    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return  employees.getEmployeeById(employeeId);

    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employees.getEmployeeByPosition(position);

    }
}
