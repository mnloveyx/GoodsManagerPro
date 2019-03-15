package com.amuse.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends Result<Member>{
	List<Account> accounts;

}
