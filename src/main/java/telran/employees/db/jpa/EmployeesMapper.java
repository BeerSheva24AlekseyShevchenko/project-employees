package telran.employees.db.jpa;

import org.json.JSONObject;

import telran.employees.Employee;

public class EmployeesMapper {
    private static final String ENTITY = "Entity";
    private static final String CLASS_NAME = "className";
    private static final String DTO_PACKAGE = "telran.employees.";
    private static final String ENTITIES_PACKAGE = "telran.employees.db.jpa.";

    public static Employee toEmployeeDtoFromEntity(EmployeeEntity entity) {
        String entityClassName = entity.getClass().getSimpleName();
        String dtoClassName = DTO_PACKAGE + entityClassName.replaceAll(ENTITY, "");
        JSONObject json = new JSONObject();
        json.put(CLASS_NAME, dtoClassName);
        entity.toJsonObject(json);
        return Employee.getEmployee(json.toString());
    }

    @SuppressWarnings("unchecked")
    public static EmployeeEntity toEmployeeEntityFromDto(Employee dto) {
        String dtoClassName = dto.getClass().getSimpleName();
        String entityClassName = ENTITIES_PACKAGE + dtoClassName + ENTITY;
        try {
            Class<EmployeeEntity> clazz = (Class<EmployeeEntity>) Class.forName(entityClassName);
            EmployeeEntity entity = clazz.getConstructor(null).newInstance();
            entity.fromEmployeeDto(dto);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
