package kh.rechargetech.mmm.news.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverNewsItem(String title, String originallink, String link, String description, String pubDate) {}