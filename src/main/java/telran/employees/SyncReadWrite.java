package telran.employees;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public abstract class SyncReadWrite {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    protected <T> T syncRead(Supplier<T> callback) {
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
