package telran.employees;

public class Employee {
    private long id;
    private int salary;
    private String department;

    public Employee(long id, int salary, String department) {
        this.id = id;
        this.salary = salary;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public int computeSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Employee) obj).getId();
    }
}
