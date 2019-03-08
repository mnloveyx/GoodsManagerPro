package com.panli.enums;




import lombok.Getter;

public enum SchemeEnum {
	
	PK10JSC("PK10JSC", "极速赛车"),
	LUCKYSB("LUCKYSB", "极速飞艇 "),
	XYFT("XYFT", "幸运飞艇");

	@Getter
	private String value;

	@Getter
	private String name;

	private SchemeEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getName(Integer value) {
		for (SchemeEnum enum1 : SchemeEnum.values()) {
			if (enum1.value.equals(value)) {
				return enum1.name;
			}
		}
		return null;
	}

	public static SchemeEnum getEnum(String value) {
		for (SchemeEnum enum1 : SchemeEnum.values()) {
			if (enum1.value.equals(value)) {
				return enum1;
			}
		}
		return null;
	}
	
	public static String[] enumsToStringArray() {
        String[] results = new String[SchemeEnum.values().length];
        int count = 0;
        for (SchemeEnum each  : SchemeEnum.values()) {
            results[count] = each.getValue();
            count++;
        }
        return results;
    }
     
//    @Override
//    public String toString() {
//        return Utilities.printNice(super.toString());
//    }

}
