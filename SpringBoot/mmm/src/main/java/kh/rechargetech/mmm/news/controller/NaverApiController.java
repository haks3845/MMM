package kh.rechargetech.mmm.news.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kh.rechargetech.mmm.news.model.vo.NaverNewsData;
import kh.rechargetech.mmm.news.service.NaverApiService;

@RestController
@RequestMapping("/mmm/api/news")
public class NaverApiController {

    private final NaverApiService service;

    public NaverApiController(NaverApiService service) {
        this.service = service;
    }

    @GetMapping("/main")
    public List<NaverNewsData> mainNews() {
        return service.mainNews();
    }
}