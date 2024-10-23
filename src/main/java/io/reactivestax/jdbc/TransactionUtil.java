package io.reactivestax.jdbc;

public interface TransactionUtil {

    void startTrasaction();
    void commitTransaction();
    void rollbackTransaction();
}
