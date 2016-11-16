package org.bpl.gear.te.test.fft;

import org.junit.Test;

/**
 * @description
 * @create 2016-11-07 下午4:45
 * @email gxz04220427@163.com
 */
public class FFTNot2NTest {
    @Test
    public void Not2Test() {
        int a = 3600;
        double realn = Math.log10(a) / Math.log10(2);
        int n = 0;
        while (realn > n) {
            n++;
        }
        System.out.println(n);

    }
}
