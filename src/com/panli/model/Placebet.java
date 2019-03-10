package com.panli.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Placebet {
	private Date drawTime; //投注时间
	private String drawNumber;//期数
	private String lottery; //彩种; PK10JSC
	private List<Bet> bets; //投注号码类型
	@Getter
	@Setter
	public class Bet{
		private Integer amount;//金额
		private String  contents; //D,X; 2;D,X
		private String game;//DX1; B1;DS
		private Double odds; //1.9426 赔率;
	}
}
