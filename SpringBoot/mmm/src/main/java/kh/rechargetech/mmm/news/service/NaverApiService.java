package kh.rechargetech.mmm.news.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.HtmlUtils;

import kh.rechargetech.mmm.news.model.vo.NaverNewsData;
import kh.rechargetech.mmm.news.model.vo.NaverNewsResponse;
import tools.jackson.databind.ObjectMapper;

@Service
public class NaverApiService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NaverApiService(
            @Value("${naver.api.base-url}") String baseUrl,
            @Value("${naver.api.client-id}") String clientId,
            @Value("${naver.api.client-secret}") String clientSecret) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-NCP-APIGW-API-KEY-ID", clientId)
                .defaultHeader("X-NCP-APIGW-API-KEY", clientSecret)
                .build();
    }

    // 메인 페이지용: 수요반응 2개 + 전기절약 2개
    public List<NaverNewsData> mainNews() {
        List<NaverNewsData> result = new ArrayList<>();
        result.addAll(fetchNews("DR", "\"수요반응\" 전력", 2));
        result.addAll(fetchNews("SAVING", "\"전기 절약\" 캠페인", 2));
        return result;
    }

    private List<NaverNewsData> fetchNews(String category, String query, int limit) {
        String raw = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/v1/news")
                        .queryParam("query", query)
                        .queryParam("display", 10)
                        .queryParam("start", 1)
                        .queryParam("sort", "date")
                        .build())
                .retrieve()
                .body(String.class);

        NaverNewsResponse res;
        try {
            res = objectMapper.readValue(raw, NaverNewsResponse.class);
        } catch (Exception e) {
            throw new IllegalStateException("네이버 뉴스 응답 파싱 실패", e);
        }

        if (res == null || res.items() == null) {
            return List.of();
        }

        return res.items().stream()
                .map(i -> new NaverNewsData(
                        category,
                        clean(i.title()),
                        i.link(),
                        clean(i.description()),
                        i.pubDate()))
                .limit(limit)
                .toList();
    }

    // <b> 태그 제거 + &quot; 같은 HTML 엔티티 변환
    private String clean(String s) {
        if (s == null) return "";
        return HtmlUtils.htmlUnescape(s.replaceAll("<[^>]*>", ""));
    }
}