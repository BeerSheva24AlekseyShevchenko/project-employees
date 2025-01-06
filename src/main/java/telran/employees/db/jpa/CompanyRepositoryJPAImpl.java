package telran.employees.db.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import jakarta.persistence.*;
import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceUnitInfo;
import telran.employees.Employee;
import telran.employees.Manager;
import telran.employees.db.CompanyRepository;

public class CompanyRepositoryJPAImpl implements CompanyRepository {
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public CompanyRepositoryJPAImpl(PersistenceUnitInfo unit, HashMap<String, Object> properties) {
        try {
            String providerName = unit.getPersistenceProviderClassName();
            Class<PersistenceProvider> clazz = (Class<PersistenceProvider>) Class.forName(providerName);
            PersistenceProvider provider = clazz.getConstructor().newInstance(null);
            EntityManagerFactory emf = provider.createContainerEntityManagerFactory(unit, properties);
            em = emf.createEntityManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getEmployees() {
        TypedQuery<EmployeeEntity> query = em.createQuery("SELECT empl FROM EmployeeEntity empl", EmployeeEntity.class);
        return toEmployeeList(query.getResultList());
    }
        
    private List<Employee> toEmployeeList(List<EmployeeEntity> resultList) {
        return resultList.stream().map(EmployeesMapper::toEmployeeDtoFromEntity).toList();
    }

    @Override
    public void insertEmployee(Employee empl) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            var emplEntity = em.find(EmployeeEntity.class, empl.getId());
            if (emplEntity != null) {
                throw new IllegalStateException("Employee already exist");
            }
            EmployeeEntity entity = EmployeesMapper.toEmployeeEntityFromDto(empl);
            em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e; 
        }
        
    }

    @Override
    public Employee findEmployee(long id) {
        EmployeeEntity entity = em.find(EmployeeEntity.class, id);
        return entity == null ? null : EmployeesMapper.toEmployeeDtoFromEntity(entity);
    }

    @Override
    public Employee removeEmployee(long id) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            EmployeeEntity entity = em.find(EmployeeEntity.class, id);
            if (entity == null) {
                throw new NoSuchElementException("Employee doesn't exist");
            }
            em.remove(entity);
            transaction.commit();
            return EmployeesMapper.toEmployeeDtoFromEntity(entity);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        TypedQuery<EmployeeEntity> query = em.createQuery("SELECT empl FROM EmployeeEntity empl WHERE empl.department=?1", EmployeeEntity.class);
        query.setParameter(1, department);
        return toEmployeeList(query.getResultList());
    }

    @Override
    public List<String> getDepartments() {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT empl.department FROM EmployeeEntity empl", String.class);
        return query.getResultList();
    }

    @Override
    public List<Manager> findManagersWithMostFactor() {
        TypedQuery<ManagerEntity> query = em.createQuery("SELECT empl FROM ManagerEntity empl WHERE factor=(select max(factor) from ManagerEntity)", ManagerEntity.class);
        List<ManagerEntity> managers = query.getResultList();
        
        return managers.stream().map(EmployeesMapper::toEmployeeDtoFromEntity)
            .map(e -> (Manager) e).toList();
    }

}
