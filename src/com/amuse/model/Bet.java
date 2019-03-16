package com.amuse.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bet{
	private Integer amount;//金额
	private String  contents; //D,X; 2;D,X
	private String game;//DX1; B1;DS
	private Double odds; //1.9426 赔率;
	
	public Bet(Integer amount, String contents, String game, Double odds) {
		super();
		this.amount = amount;
		this.contents = contents;
		this.game = game;
		this.odds = odds;
	}

	public Bet() {
		super();
	}

	@Override
	public String toString() {
		return "Bet [amount=" + amount + ", contents=" + contents + ", game=" + game + ", odds=" + odds + "]";
	}
	
	public BigDecimal getWinAmount()
	{
		BigDecimal o= new BigDecimal(getOdds());
		return  o.multiply(new BigDecimal(getAmount())).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getPlaceAmount()
	{
		return new BigDecimal(getAmount());
	}
	
}