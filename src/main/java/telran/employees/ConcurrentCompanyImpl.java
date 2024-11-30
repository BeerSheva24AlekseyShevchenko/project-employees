package telran.employees;

import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class ConcurrentCompanyImpl extends CompanyImpl {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    @Override
    public Employee getEmployee(long id) {
        return syncRead(() -> super.getEmployee(id));
    }

    @Override
    public String[] getDepartments() {
        return syncRead(super::getDepartments);
    }

    @Override
    public int getDepartmentBudget(String department) {
        return syncRead(() -> super.getDepartmentBudget(department));
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        return syncRead(super::getManagersWithMostFactor);
    }

    @Override
    public void addEmployee(Employee empl) {
        syncWrite(() -> super.addEmployee(empl));
    }

    @Override
    public Employee removeEmployee(long id) {
        return syncWrite(() -> super.removeEmployee(id));
    }

    @Override
    public Iterator<Employee> iterator() {
        return syncRead(super::iterator);
    }

    @Override
    public void saveToFile(String fileName) {
        syncRead(() -> super.saveToFile(fileName));
    }

    private <T> T syncRead(Supplier<T> callback) {
        T result;
        readLock.lock();
        try {
            result = callback.get();
        } finally {
            readLock.unlock();
        }
        return result;
    }

    protected void syncRead(Runnable callback) {
        syncRead(() -> {
            callback.run();
            return null;
        });
    }

    protected <T> T syncWrite(Supplier<T> callback) {
        T result;
        writeLock.lock();
        try {
            result = callback.get();
        } finally {
            writeLock.unlock();
        }
        return result;
    }

    protected void syncWrite(Runnable callback) {
        syncWrite(() -> {
            callback.run();
            return null;
        });
    }
}
