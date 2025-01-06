package telran.employees.db;

import java.util.List;

import telran.employees.Employee;
import telran.employees.Manager;

public interface CompanyRepository {
    public List<Employee> getEmployees();
    public void insertEmployee(Employee empl);
    public Employee findEmployee(long id);
    public Employee removeEmployee(long id);
    public List<Employee> getEmployeesByDepartment(String department);
    public List<String> getDepartments();
    public List<Manager> findManagersWithMostFactor();
}
