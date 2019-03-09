package com.panli.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Scheme extends Item {
		private List<Plan> plans;
		
		public static Map<String,String> keyMap =  new HashMap<>();
		
		static {
			keyMap.put("极速极赛车", "PK10JSC");
			keyMap.put("极速飞艇 ", "LUCKYSB");
			keyMap.put("幸运飞艇", "XYFT");
		}
		
		public static Map<String,String> valueMap =  new HashMap<>();
		
		static {
			valueMap.put("PK10JSC","极速极赛车");
			valueMap.put("LUCKYSB","极速飞艇 ");
			valueMap.put("XYFT","幸运飞艇");
		}

		public Scheme(String key, String value, List<Plan> plans) {
			super(key, value);
			this.plans = plans;
		}
		
		public Scheme(String key, String value) {
			super(key, value);
		}
		
}
