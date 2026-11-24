package kh.rechargetech.mmm.config;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements ApplicationRunner {

    // 1. 서버가 완전히 켜진 직후 자동 실행
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("🚀 [서버 시작] 웹 애플리케이션 초기화 완료 (수동 수집 준비 상태)");
    }

    // 2. 서버가 종료되기 직전 자동 실행
    @PreDestroy
    public void onDestroy() {
        System.out.println("🛑 웹 애플리케이션이 종료되었습니다.");
    }
}