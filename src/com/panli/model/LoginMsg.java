package com.panli.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMsg  extends Result{
	private Boolean firstTime;
	private String token;
}