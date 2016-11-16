package org.bpl.gear.te.errorcalculate.interfaces;

import org.bpl.gear.base.gear.spur.SpurGear;
import org.bpl.gear.te.error.spur.SpurGearError;

/**
 * The interface Spur gear error calculate.
 *
 * @description
 * @create 2016 -11-08 上午10:08
 * @email gxz04220427 @163.com
 */
public interface ISpurGearErrorCalculate {
    /**
     * 直齿轮计算类.
     *
     * @param tedata the tedata
     * @param gear   the gear
     *
     * @return the spur gear error
     */
    SpurGearError calculate(double[] tedata, SpurGear gear);
}
