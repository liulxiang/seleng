package com.opennice.selenium.autoscreenshot;

/**
 * 注入等级
 * @author liulxiang
 *
 */
public enum InterceptLevel {
	P1(1),
	P2(2),
	P3(4),
	P4(8),
	P5(16);
	
    private final Integer value;
	
    public Integer getValue() {
        return this.value;
    }
    
	private InterceptLevel(Integer value) {
		this.value = value;
	}
}
