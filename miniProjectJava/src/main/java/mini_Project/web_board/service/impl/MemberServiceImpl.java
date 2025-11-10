package mini_Project.web_board.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini_Project.web_board.dao.MemberDao;
import mini_Project.web_board.dto.MemberRes;
import mini_Project.web_board.dto.MemberSignupReq;
import mini_Project.web_board.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao;
    @Override
    @Transactional
    public Long Signup(MemberSignupReq req) {
        if (memberDao.findByEmail(req.getEmail()) != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        return memberDao.save(req);
    }

    @Override
    public MemberRes login(String email, String pwd) {
        MemberRes member = memberDao.findByEmail(email);
        if (member == null || !member.getPwd().equals(pwd)){
            throw new IllegalArgumentException("이메일이 존재 하지 않거나, 비밀번호가 맞지 않습니다.");
        }
        return new MemberRes(member.getId(),member.getEmail(), member.getPwd(), member.getNickname(), member.getGrade(), member.getNick_changed_date(), member.getReg_date(), member.getPoint());
    }
}
