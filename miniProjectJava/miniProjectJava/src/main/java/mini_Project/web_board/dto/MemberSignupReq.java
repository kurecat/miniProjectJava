package mini_Project.web_board.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class MemberSignupReq {
    private String email;
    private String cEmail;
    private String pwd;
    private String nickName;
}
