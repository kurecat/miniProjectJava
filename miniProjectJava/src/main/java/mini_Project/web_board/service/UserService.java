package mini_Project.web_board.service; // ⭐️ 본인 프로젝트에 맞게 수정


import lombok.RequiredArgsConstructor;
import mini_Project.web_board.dto.SignupFormDto;
import org.springframework.security.crypto.password.PasswordEncoder; // ⭐️ (필수)
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional // ⭐️ 데이터 저장 시 트랜잭션 처리
public class UserService {

    // ⭐️ (필수) 비밀번호 암호화를 위해 SecurityConfig 등에 PasswordEncoder Bean 등록 필요
    // private final PasswordEncoder passwordEncoder;

    // ⭐️ (필수) DB와 통신할 Repository (예: JpaRepository)
    // private final UserRepository userRepository;

    public void registerUser(SignupFormDto signupFormDto, MultipartFile profileImage) throws Exception {

        // --- 1. 중복 회원(이메일) 검사 ---
        // if (userRepository.findByEmail(signupFormDto.getEmail()).isPresent()) {
        //     throw new IllegalStateException("이미 가입된 이메일입니다.");
        // }

        // --- (선택) 중복 사용자 이름 검사 ---
        // if (userRepository.findByUsername(signupFormDto.getUsername()).isPresent()) {
        //     throw new IllegalStateException("이미 사용 중인 사용자 이름입니다.");
        // }

        // --- 2. 프로필 이미지 저장 로직 ---
        String storedFileName = null; // 실제 파일 저장 경로/이름
        if (profileImage != null && !profileImage.isEmpty()) {
            // TODO: (중요) 파일을 서버의 특정 위치(S3, 로컬 등)에 저장하는 로직
            // 예: storedFileName = fileStorageService.storeFile(profileImage);
        }

        // --- 3. 비밀번호 암호화 (!!!필수!!!) ---
        // (주의) 절대 평문(signupFormDto.getPassword()) 그대로 저장하면 안 됩니다.
        // String encodedPassword = passwordEncoder.encode(signupFormDto.getPassword());

        // --- 4. DB에 회원 정보 저장 (JPA 예시) ---
        // User user = new User();
        // user.setEmail(signupFormDto.getEmail());
        // user.setUsername(signupFormDto.getUsername());
        // user.setPassword(encodedPassword); // ⭐️ 암호화된 비밀번호 저장
        // user.setProfileImageUrl(storedFileName); // ⭐️ 저장된 파일 경로/이름
        // user.setRole(Role.USER); // (예: 사용자 권한 설정)
        //
        // userRepository.save(user);

        System.out.println("회원가입 로직 처리됨: " + signupFormDto.getEmail());
    }
}