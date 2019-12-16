package com.xformation.test.model.dao.sql.orders;

import com.xformation.test.model.dao.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAO {
    public void createWholeOrder(List<String> queries) {
        try (Connection c = new DataSource().getConnection()) {
            c.setAutoCommit(false);
            PreparedStatement preparedStmt;
            for (String query : queries) {
                preparedStmt = c.prepareStatement(query);
                preparedStmt.execute();
            }
            c.commit();

        } catch (SQLException e) {
            System.err.println("SQL exception while creating whole order.");
            e.printStackTrace();

        }
    }
}
