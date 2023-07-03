package com.example.server.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Component
public class H2DatabaseConfig implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

//            Statement statement = connection.createStatement();
//
//            String BEOTS_TABLE = "CREATE TABLE beots(" +
//                    "id bigint NOT NULL auto_increment, " +
//                    "from_member_id VARCHAR(300) NOT NULL, " +
//                    "to_member_id VARCHAR(300) NOT NULL, " +
//                    "created_at TIMESTAMP NOT NULL, " +
//                    "PRIMARY KEY (id)" +
//                    ")";
//            statement.executeUpdate(BEOTS_TABLE); // 벗 테이블 구현
//            String MEMBERS_TABLE = "CREATE TABLE members(" +
//                    "id varchar(300) NOT NULL, " +
//                    "name VARCHAR(30), " +
//                    "nickname VARCHAR(30), " +
//                    "birthday TIMESTAMP, " +
//                    "PRIMARY KEY (id)" +
//                    ")";
//            statement.executeUpdate(MEMBERS_TABLE); // 멤버 테이블 구현
//
//            String UUID1 = UUID.randomUUID().toString();
//            String UUID2 = UUID.randomUUID().toString();
//            String INIT_MEMBER_1 = "INSERT INTO members (id, name, nickname, birthday) " +
//                    "value (" + UUID1 + ", test1" + ", test1" + ", 1998-12-12" + ")";
//            String INIT_MEMBER_2 = "INSERT INTO members (id, name, nickname, birthday) " +
//                    "value (" + UUID2 + ", test2" + ", test2" + ", 1990-12-12" + ")";
//            statement.execute(INIT_MEMBER_1);
//            statement.execute(INIT_MEMBER_2);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
