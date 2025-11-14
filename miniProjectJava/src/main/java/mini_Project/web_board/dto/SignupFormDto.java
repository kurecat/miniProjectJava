package mini_Project.web_board.dto; // ⭐️ 본인 프로젝트에 맞게 수정

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupFormDto {

    // (Email)
    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

    // (Password)
    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "비밀번호는 최소 8자, 하나 이상의 문자와 숫자를 포함해야 합니다.")
    private String password;

    // (Password check)
    @NotEmpty(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String passwordCheck;

    // (Username)
    @NotEmpty(message = "사용자 이름은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$",
            message = "사용자 이름은 3~16자의 영문자, 숫자, 밑줄(_), 하이픈(-)만 사용 가능합니다.")
    private String username;

    // profile-image는 DTO 필드로 받지 않고
    // @RequestParam("profile-image") MultipartFile로 컨트롤러에서 직접 받습니다.
}