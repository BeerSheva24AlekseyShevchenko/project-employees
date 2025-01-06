package telran.employees.db.jpa;

import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import telran.employees.Employee;
import telran.employees.SalesPerson;

@Entity
@DiscriminatorValue("SalesPerson")
public class SalesPersonEntity extends WageEmployeeEntity {
    @Column
    private float percent;
    @Column
    private long sales;

    @Override
    protected void fromEmployeeDto(Employee dto) {
        super.fromEmployeeDto(dto);
        percent = ((SalesPerson) dto).getPercent();
        sales = ((SalesPerson) dto).getSales();
    }

    @Override
    protected void toJsonObject(JSONObject json) {
        super.toJsonObject(json);
        json.put("percent", percent);
        json.put("sales", sales);
    }
}
