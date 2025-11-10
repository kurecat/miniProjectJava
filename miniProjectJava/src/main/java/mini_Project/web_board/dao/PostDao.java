package mini_Project.web_board.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini_Project.web_board.dto.CategoryRes;
import mini_Project.web_board.dto.MemberRes;
import mini_Project.web_board.dto.PostCreateReq;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostDao {
    private final JdbcTemplate jdbc;
    private final MemberRes memberRes;
    private final CategoryRes categoryRes;

    public Long create(PostCreateReq p){
        @Language("SQL")
        String sql = """
                     INSERT INTO posts(id, member_id, title, contnet, category_id, view_count, recommendations_count,created_at)
                     VALUES (seq_posts.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE)
                     """;
        jdbc.update(sql, memberRes.getId(), p.getTitle(), p.getContent(), categoryRes.getId(), 0, 0);
        return jdbc.queryForObject("SELCET seq_posts.CURRVAL FROM daul", Long.class);
    }
}
