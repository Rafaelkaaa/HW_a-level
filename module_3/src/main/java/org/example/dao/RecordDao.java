package org.example.dao;

import org.example.entity.Record;
import org.example.factory.JdbcFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class RecordDao {
    private final JdbcFactory sqlConfig = JdbcFactory.getInstance();

    public Collection<Record> findRecordsByAccountId(UUID accountId) {
        try (ResultSet rs = sqlConfig
                .getStatement()
                .executeQuery("SELECT * " +
                        "FROM bank.transactions t " +
                        "JOIN bank.transfers tr ON t.id = tr.transaction_id " +
                        "WHERE tr.account_id = '" + accountId + "'")) {
            List<Record> records = new ArrayList<>();
            while (rs.next()) {
                records.add(recordFromRs(rs));
            }
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Record recordFromRs(ResultSet rs) throws SQLException {
        Record record = new Record();
        record.setAccountId((UUID) rs.getObject("account_id"));
        record.setAmount(rs.getInt("transaction_amount"));
        record.setDate(rs.getTimestamp("date"));
        record.setOperation(rs.getString("operation"));
        return record;
    }
}
