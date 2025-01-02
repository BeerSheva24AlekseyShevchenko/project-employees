package telran.employees.db.jpa;

import org.json.JSONObject;

import jakarta.persistence.*;
import telran.employees.Employee;
import telran.employees.Manager;

@Entity
public class ManagerEntity extends EmployeeEntity {
    @Column
    private float factor;

    @Override
    protected void fromEmployeeDto(Employee dto) {
        super.fromEmployeeDto(dto);
        factor = ((Manager) dto).getFactor();
    }

    @Override
    protected void toJsonObject(JSONObject json) {
        super.toJsonObject(json);
        json.put("factor", factor);
    }
}
