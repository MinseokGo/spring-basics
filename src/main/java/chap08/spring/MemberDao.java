package chap08.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Collection<Member> selectAll() {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER",
                    new MemberRowMapper());
        return results;
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                    new MemberRowMapper(), email);
        return results.isEmpty() ? null : results.get(0);
    }
}

//람다식
//(rs, rowNum) -> {
//                    Member member = new Member(
//                            rs.getString("EMAIL"),
//                            rs.getString("PASSWORD"),
//                            rs.getString("NAME"),
//                            rs.getTimestamp("REGDATE").toLocalDateTime());
//                    member.setId(rs.getLong("ID"));
//
//                    return member;
//                },
