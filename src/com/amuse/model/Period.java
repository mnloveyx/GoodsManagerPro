package com.amuse.model;

import java.util.Date;

import com.amuse.util.DateUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Period extends Result<Period>{
	private Date  closeTime;
	private Date currentTime;
	private Date drawDate;
	private Date drawTime;
	private Date openTime;
	private String  drawNumber;
	private String pnumber;
	@Override
	public String toString() {
		return "Period [closeTime=" + String.valueOf(DateUtils.formatDate(closeTime, DateUtils.TIME_PATTERN_DEFAULT)) + ", currentTime=" + String.valueOf(DateUtils.formatDate(currentTime, DateUtils.TIME_PATTERN_DEFAULT)) + ", drawDate=" + String.valueOf(DateUtils.formatDate(drawDate, DateUtils.TIME_PATTERN_DEFAULT))
				+ ", drawTime=" + String.valueOf(DateUtils.formatDate(drawTime, DateUtils.TIME_PATTERN_DEFAULT)) + ", openTime=" + String.valueOf(DateUtils.formatDate(openTime, DateUtils.TIME_PATTERN_DEFAULT)) + ", drawNumber=" + drawNumber + ", pnumber="
				+ pnumber + "]";
	}
	
	public Long getRestPlaceTime()
	{
		return closeTime.getTime()-currentTime.getTime();
	}
	
	public Long getRestOpenTime()
	{
		return closeTime.getTime()-openTime.getTime();
	}
	
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//	}
	
}
