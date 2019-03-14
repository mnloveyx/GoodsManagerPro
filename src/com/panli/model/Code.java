package com.panli.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Code extends Result<Code> {
	private String captchImageData;
	private String cryptograph;
}
