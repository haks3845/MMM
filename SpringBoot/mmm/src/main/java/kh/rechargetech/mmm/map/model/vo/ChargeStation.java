package kh.rechargetech.mmm.map.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class ChargeStation {
	private String statId;        // 충전소ID
    private String chgerId;       // 충전기ID
    private String statNm;        // 충전소명
    private String chgerType;     // 충전기타입 코드
    private String addr;          // 주소
    private String addrDetail;    // 주소상세
    private String location;      // 상세위치
    private Double lat;           // 위도
    private Double lng;           // 경도
    private String useTime;       // 이용가능시간
    private String busiId;        // 기관 아이디
    private String bnm;           // 기관명
    private String busiNm;        // 운영기관명
    private String busiCall;      // 운영기관연락처
    private String stat;          // 충전기상태
    private String statUpdDt;     // 상태갱신일시
    private String lastTsdt;      // 마지막 충전시작일시
    private String lastTedt;      // 마지막 충전종료일시
    private String nowTsdt;       // 충전중 시작일시
    private Long output;          // 충전용량
    private String method;        // 충전방식
    private String zcode;         // 지역코드
    private String zscode;        // 지역구분 상세 코드
    private String kind;          // 충전소 구분 코드
    private String kindDetail;    // 충전소 구분 상세코드
    private String parkingFree;   // 주차료무료 여부 (Y/N)
    private String note;          // 충전소 안내
    private String limitYn;       // 이용자 제한 (Y/N)
    private String limitDetail;   // 이용제한 사유
    private String delYn;         // 삭제 여부 (Y/N)
    private String delDetail;     // 삭제 사유
    private String trafficYn;     // 편의제공 여부 (Y/N)
    private String year;          // 설치년도
    private String floorNum;      // 지상/지하 층수
    private String floorType;     // 지상/지하 구분 (지상:F, 지하:B)
}
