package persistence;

public interface TransactionManager {
    void beginRead();
    void beginWrite();
    void commit();
    void rollback();
}
