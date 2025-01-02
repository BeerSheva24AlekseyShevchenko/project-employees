package telran.employees.db.jpa;

import org.json.JSONObject;

import jakarta.persistence.*;
import telran.employees.Employee;

@Table(name="employees")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EmployeeEntity {
    @Id
    private long id;
    @Column
    private int salary;
    @Column
    private String department;

    protected void fromEmployeeDto(Employee dto) {
        id = dto.getId();
        salary = dto.getSalary();
        department = dto.getDepartment();
    }

    protected void toJsonObject(JSONObject json) {
        json.put("id", id);
        json.put("salary", salary);
        json.put("department", department);
    }

}
