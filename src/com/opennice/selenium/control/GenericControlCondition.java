package com.opennice.selenium.control;

import com.google.common.base.Function;

/**
 * Models a condition that might reasonably be expected to eventually evaluate
 * to something that is neither null nor false. Examples would include
 * determining if a web page has loaded or that an element is visible.
 * <p>
 * Note that it is expected that ExpectedConditions are idempotent. They will
 * be called in a loop by the {@link ActionWait} and any modification of the
 * state of the application under test may have unexpected side-effects.
 *
 * @author liulxiang
 * @param <ControlT> The control type
 * @param <ReturnT> The return type
 */
public interface GenericControlCondition<ControlT extends Control, ReturnT> extends Function<ControlT, ReturnT> {

}
