package mini_Project.web_board.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CommentCreateReq {
    private Long postId;
    private Long nickname;
    private String title;
    private String content;
}
