package kh.rechargetech.mmm.news.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverNewsResponse(int total, List<NaverNewsItem> items) {}