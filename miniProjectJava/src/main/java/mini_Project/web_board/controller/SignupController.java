package mini_Project.web_board.controller; // ⭐️ 본인 프로젝트에 맞게 수정

// ⬇️ [수정] BindingResult가 필요 없어져서 import에서 제거
// import org.springframework.validation.BindingResult;

import jakarta.validation.Valid; // ⬅️ 사실상 이제 필요 없지만, DTO 때문에 놔둡니다.
import lombok.RequiredArgsConstructor;
import mini_Project.web_board.dto.SignupFormDto;
import mini_Project.web_board.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    // (이 메서드는 동일합니다)
    @GetMapping("/signup") // ⭐️ /가 있는지 확인!
    public String showSignupForm(Model model) {
        model.addAttribute("signupFormDto", new SignupFormDto());
        return "signup";
    }

    /**
     * [수정됨]
     * 모든 유효성 검사(@Valid, BindingResult, if문)를 제거하고
     * 폼을 받자마자 "강제로" 루트 페이지로 리다이렉트합니다.
     */
    @PostMapping("/signup")
    public String processSignup(
            // ⬇️ [수정] @Valid 와 BindingResult를 파라미터에서 아예 삭제!
            SignupFormDto signupFormDto,
            Model model,
            @RequestParam("profile-image") MultipartFile profileImage
    ) {

        // ⬇️ [수정] 모든 if (유효성 검사) 블록을 삭제했습니다.

        // (디버깅 로그)
        System.out.println("=========================================");
        System.out.println("POST /signup 요청 수신 성공! (모든 검사 건너뜀)");
        System.out.println("전송된 이메일: " + signupFormDto.getEmail());
        System.out.println("=========================================");

        // --- DB 로직 (원래대로 주석 처리 유지) ---
//        try {
//            userService.registerUser(signupFormDto, profileImage);
// ...
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "오류 발생");
//            return "signup";
//        }

        // --- 4. 성공: 루트 페이지로 리다이렉트 ---
        // 폼을 받자마자 바로 리다이렉트합니다.
        return "redirect:/";
    }
}