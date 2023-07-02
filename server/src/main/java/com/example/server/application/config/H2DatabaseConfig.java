package com.example.server.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class H2DatabaseConfig implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    private String MEMBERS_TABLE = "CREATE TABLE members(" +
            "id varchar(300) NOT NULL, " +
            "name VARCHAR(30), " +
            "nickname VARCHAR(30), " +
            "birthday TIMESTAMP, " +
            "PRIMARY KEY (ID) )";

    @Override
    public void run(ApplicationArguments args) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            statement.executeUpdate(MEMBERS_TABLE); // 멤버 테이블 구현
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
