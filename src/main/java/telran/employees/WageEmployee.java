package telran.employees;

public class WageEmployee extends Employee {
    private int wage;
    private int hours;

    public WageEmployee(long id, int salary, String department, int wage, int hours) {
        super(id, salary, department);
        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public int computeSalary() {
        return super.computeSalary() + wage * hours;
    }
}
