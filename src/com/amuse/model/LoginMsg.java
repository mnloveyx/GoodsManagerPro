package com.amuse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMsg  extends Result<LoginMsg>{
	private Boolean firstTime;
	private String token;
}
