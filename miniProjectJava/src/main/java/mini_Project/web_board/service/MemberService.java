package mini_Project.web_board.service;

import mini_Project.web_board.dto.MemberRes;
import mini_Project.web_board.dto.MemberSignupReq;

public interface MemberService {
    Long Signup(MemberSignupReq req);

    MemberRes login(String email, String pwd);
}
