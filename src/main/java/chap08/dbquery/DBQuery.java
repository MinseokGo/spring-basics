package chap08.dbquery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    private DataSource dataSource;

    public DBQuery(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();  //커넥션 풀에서 커넥션 구함(Active)
            try(Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
                rs.next();

                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn != null) {
                try {
                    conn.close();   //커넥션 풀에 반환(Idle)
                } catch (SQLException e) {
                }
            }
        }
    }
}