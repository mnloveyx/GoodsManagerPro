package com.amuse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
	private Double  balance;
	private Double maxLimit;
	private Integer type;
	private Double betting;
	private Double result;
}
