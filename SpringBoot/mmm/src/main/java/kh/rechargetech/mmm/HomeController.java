package kh.rechargetech.mmm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 실제 접속하시는 메인 페이지 URL에 맞춰주세요 (예: "/" 또는 "/mmm" 또는 "/mmm/")
    @GetMapping({"/", "/mmm", "/mmm/"})
    public String home() {
        return "index"; // templates/index.html 을 Thymeleaf로 렌더링
    }
}