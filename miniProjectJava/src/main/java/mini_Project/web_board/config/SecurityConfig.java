package mini_Project.web_board.config; // ⭐️ 본인 프로젝트의 패키지 경로

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ⬇️⬇️⬇️ [import 4줄 추가] ⬇️⬇️⬇️
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// ⬆️⬆️⬆️ [import 4줄 추가] ⬆️⬆️⬆️

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security 필터 체인 설정
     * (님께서 제공해주신 코드와 동일합니다)
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. [권한 설정]
                .authorizeHttpRequests(auth -> auth
                        // ⭐️ 누구나 접근 가능한 페이지
                        .requestMatchers("/", "/signin", "/signup", "/findpw", "/mypage", "/postlist").permitAll()
                        // ⭐️ 정적 리소스(CSS, JS, 이미지)도 모두 허용
                        .requestMatchers("/css/**", "/js/**", "/Image/**").permitAll()
                        // ⭐️ 그 외의 모든 요청은 일단 "모두 허용" (나중에 변경)
                        .anyRequest().permitAll() // .authenticated()
                )

                // 2. [폼 로그인 설정]
                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/signin")
                        .usernameParameter("email") // ⭐️ "email" 필드를 아이디로 사용
                        .defaultSuccessUrl("/", true) // ⭐️ 로그인 성공 시 루트로 이동
                        .permitAll()
                )

                // 3. [로그아웃 설정]
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                );

        return http.build();
    }

    // ⬇️⬇️⬇️ [요청하신 In-Memory Bean 추가] ⬇️⬇️⬇️
    /**
     * [DB 연결 전 테스트용]
     * In-Memory (메모리상) 사용자 상세 정보 서비스 Bean
     * Spring Security가 로그인 시 이 정보를 참조합니다.
     */
    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {

        // ⭐️ 중요: 님의 폼이 email을 username으로 사용하므로,
        // .username()에 이메일 형식을 넣어야 합니다.

        // 1. 테스트 사용자 (아이디: user@test.com / 비밀번호: 1234)
        UserDetails user = User.builder()
                .username("user@test.com")
                .password(passwordEncoder().encode("1234")) // ⭐️ 비밀번호는 "1234"
                .roles("USER")
                .build();

        // 2. 테스트 관리자 (아이디: admin@test.com / 비밀번호: admin123)
        UserDetails admin = User.builder()
                .username("admin@test.com")
                .password(passwordEncoder().encode("admin123")) // ⭐️ 비밀번호는 "admin123"
                .roles("ADMIN", "USER")
                .build();

        // 이 사용자들을 메모리에 등록합니다.
        return new InMemoryUserDetailsManager(user, admin);
    }
}