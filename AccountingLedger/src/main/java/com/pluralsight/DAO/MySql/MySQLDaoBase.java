package com.pluralsight.DAO.MySql;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

    public abstract class MySQLDaoBase
    {
        private DataSource dataSource;

        public MySQLDaoBase(DataSource dataSource)
        {
            this.dataSource = dataSource;
        }

        protected Connection getConnection() throws SQLException
        {
            return dataSource.getConnection();
        }
    }

