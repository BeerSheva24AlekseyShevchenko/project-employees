package telran.employees.db;

import java.util.Iterator;

import telran.employees.Company;
import telran.employees.Employee;
import telran.employees.Manager;

public class CompanyDBImpl implements Company {
    private CompanyRepository repository = new CompanyRepository();

    @Override
    public Iterator<Employee> iterator() {
        return repository.getEmployees().iterator();
    }

    @Override
    public void addEmployee(Employee empl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEmployee'");
    }

    @Override
    public Employee getEmployee(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmployee'");
    }

    @Override
    public Employee removeEmployee(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEmployee'");
    }

    @Override
    public int getDepartmentBudget(String department) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartmentBudget'");
    }

    @Override
    public String[] getDepartments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartments'");
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getManagersWithMostFactor'");
    }

}
