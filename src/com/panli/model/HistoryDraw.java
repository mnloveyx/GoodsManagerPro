package com.panli.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDraw extends Result<HistoryDraw>{
	private Date date;
	private List<OpenInfo> list;
}
