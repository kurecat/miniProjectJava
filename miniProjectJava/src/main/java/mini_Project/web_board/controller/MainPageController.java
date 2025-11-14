package mini_Project.web_board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MainPageController {
    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping ("/signin")
    public String signInPage(){
        return "signin";
    }

    @GetMapping ("/mypage")
    public String myPage(){
        return "myPage";
    }

    @GetMapping ("/postlist")
    public String postlist() {
        return "postlist";
    }

}