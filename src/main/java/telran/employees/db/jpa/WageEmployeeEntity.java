package telran.employees.db.jpa;

import org.json.JSONObject;

import jakarta.persistence.*;
import telran.employees.Employee;
import telran.employees.WageEmployee;

@Entity
public class WageEmployeeEntity extends EmployeeEntity {
    @Column
    private int wage;
    @Column
    private int hours;

    @Override
    protected void fromEmployeeDto(Employee dto) {
        super.fromEmployeeDto(dto);
        wage = ((WageEmployee) dto).getWage();
        hours = ((WageEmployee) dto).getHours();
    }

    @Override
    protected void toJsonObject(JSONObject json) {
        super.toJsonObject(json);
        json.put("wage", wage);
        json.put("hours", hours);
    }
}
