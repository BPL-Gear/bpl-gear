package org.bpl.gear.te.test.fft;

import org.bpl.gear.te.utility.FileOperate;
import org.jtransforms.fft.DoubleFFT_1D;
import org.junit.Test;

import java.io.IOException;

/**
 * @description
 * @create 2016-11-06 下午11:16
 * @email gxz04220427@163.com
 */
public class JTransformsTest {
    @Test
    public void FFTTest() throws IOException {
        FileOperate f = new FileOperate();
        int ORIGIN_SIZE = 256;
        double[] originData = new double[ORIGIN_SIZE];
        for (int i = 0; i < ORIGIN_SIZE; i++) {
            originData[i] = Math.sin(2 * Math.PI * i * 0.05) + 2 * Math.sin(10 * Math.PI * i * 0.05);
        }
        f.writeArraytoFile("/Users/guoxiaozhong/Downloads/", "aaaaa.txt", originData);
        DoubleFFT_1D fft = new DoubleFFT_1D(ORIGIN_SIZE);
        double[] fftdate = new double[ORIGIN_SIZE * 2];
        System.arraycopy(originData, 0, fftdate, 0, originData.length);
//        fft.realForwardFull(fftdate);
        fft.complexForward(fftdate);
        double[] fftResultData = new double[ORIGIN_SIZE];
        for (int i = 0; i < ORIGIN_SIZE; i++) {
            fftResultData[i] = Math.sqrt(Math.pow(fftdate[2 * i], 2) + Math.pow(fftdate[2 * i + 1], 2)) / ORIGIN_SIZE;
        }
        f.writeArraytoFile("/Users/guoxiaozhong/Downloads/", "bbbbb.txt", fftResultData);
    }
}
