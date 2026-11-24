package kh.rechargetech.mmm.map.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class StationMapPin {
	private String type;
    private String id;
    private String name;
    private double lat;
    private double lng;
    private int count;
}
