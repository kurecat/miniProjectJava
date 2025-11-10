package mini_Project.web_board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class MemberRes {
    private Long id;
    private String email;
    private String pwd;
    private String nickname;
    private String grade;
    private LocalDateTime nick_changed_date;
    private LocalDateTime reg_date;
    private int point;
}
