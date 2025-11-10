package mini_Project.web_board.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PostCreateReq {
    private Long memberId;
    private String title;
    private String content;
}
