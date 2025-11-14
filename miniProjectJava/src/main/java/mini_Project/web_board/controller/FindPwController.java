package mini_Project.web_board.controller; // ⭐️ 본인 프로젝트에 맞게 수정

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindPwController {

    /**
     * 1. [페이지 보여주기]
     * 사용자가 http://localhost:8112/findpw URL로 접속하면
     * 'findpw.html' 템플릿을 보여줍니다.
     */
    @GetMapping("/findpw")
    public String showFindPwPage() {

        // 템플릿 파일 이름이 "findpw.html"이 맞는지 확인하세요.
        // (만약 다르다면 이 return "파일이름"을 수정해야 합니다)
        return "findpw";
    }

    /**
     * 2. [폼 처리하기]
     * 'findpw.html'의 <form action="/reset-password" method="post">와
     * 정확히 일치하는 요청을 받습니다.
     * * 'reset password' 버튼을 누르면 이 메서드가 실행됩니다.
     */
    @PostMapping("/reset-password")
    public String processResetPassword() {

        // (DB가 없으므로) 비밀번호 변경 로직 시뮬레이션
        System.out.println("=========================================");
        System.out.println("POST /reset-password 요청 수신 성공.");
        System.out.println("비밀번호 변경 로직 수행 (시뮬레이션)...");
        System.out.println("=========================================");

        // 3. [페이지 이동]
        //    요청하신 대로, /login 페이지로 리다이렉트합니다.
        return "redirect:/signin";
    }
}