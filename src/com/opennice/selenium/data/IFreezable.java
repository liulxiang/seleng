package com.opennice.selenium.data;

/**
 * 一个object可被冻结的接口
 * @author liulxiang
 *
 */
public interface IFreezable {
	/**
	 * 冻结对象
	 * @author liulxiang
	 */
	public void freeze();
	
	/**
	 * 对象是否被冻结
	 * @author liulxiang
	 * @return
	 */
	public boolean isFroze();
}
