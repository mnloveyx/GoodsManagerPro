package com.panli.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Scheme extends Item {
		private List<Plan> plans;
}
