package kh.rechargetech.mmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration	// 설정파일 역할을 하는 클래스의 bean을 생성
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. CSRF 보호 일시 비활성화 (개발 단계 편의용)
            .csrf(csrf -> csrf.disable()) 
            
//            // 2. 폼 로그인 및 HTTP Basic 인증창 비활성화 (보내주신 창이 뜨지 않게 막음)
//            .formLogin(form -> form.disable())
//            .httpBasic(basic -> basic.disable())
            
            // 3. 시큐리티 필터는 통과하되, 모든 URL을 로그인 없이 접근 허용
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() 
            );

        return http.build(); // -> 이 빌드를 @Bean으로 올려서 스프링부트가 관리한다.
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}