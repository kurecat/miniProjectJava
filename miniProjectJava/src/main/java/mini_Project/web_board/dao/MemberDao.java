package mini_Project.web_board.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini_Project.web_board.dto.MemberRes;
import mini_Project.web_board.dto.MemberSignupReq;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberDao {
    private final JdbcTemplate jdbc;

    public Long save(MemberSignupReq m) { // 회원가입.
        @Language("SQL")
        String sql = """
                        INSERT INTO members(id, email, pwd, nickname, grade, nick_changed_date, reg_date, point)
                        VALUES (seq_member.NEXTVAL, ?, ?, ?, "admin", SYSDATE, SYSDATE, 0)
                     """;
        jdbc.update(sql, m.getEmail(), m.getPwd(), m.getNickName());
        return jdbc.queryForObject("SELECT seq_member.CURRVAL FROM dual", Long.class);
    }

    public MemberRes findByEmail(String email) {
        @Language("SQL")
        String sql = "SELECT * FROM members WHERE email = ?";
        List<MemberRes> list = jdbc.query(sql, new MembersRowMapper(), email);
        return list.isEmpty() ? null : list.get(0);
    }

    public MemberRes delete(Long email){ // 이메일 기준으로 회원 삭제.
        @Language("SQL")
        String sql ="DELETE FROM members WHERE email = ?";
        return jdbc.queryForObject(sql, new MembersRowMapper());
    }

    static class MembersRowMapper implements RowMapper<MemberRes> {
        @Override
        public MemberRes mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MemberRes(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("pwd"),
                    rs.getString("nickname"),
                    rs.getString("grade"),
                    rs.getTimestamp("nick_changed_date").toLocalDateTime(),
                    rs.getTimestamp("reg_date").toLocalDateTime(),
                    rs.getInt("point")
            );
        }
    }
}
