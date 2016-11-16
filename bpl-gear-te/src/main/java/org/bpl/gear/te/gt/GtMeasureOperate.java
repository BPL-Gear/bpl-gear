package org.bpl.gear.te.gt;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.bpl.gear.base.gear.spur.SpurGear;
import org.bpl.gear.te.error.spur.SpurGearError;
import org.bpl.gear.te.utility.FileOperate;

import java.io.IOException;
import java.util.List;

/**
 * The type GT测量结果处理类.
 *
 * @description
 * @create 2016 -11-03 下午9:03
 * @email gxz04220427 @163.com
 */
public class GtMeasureOperate {
    /**
     * Convert measure gt measure.
     *
     * @param leftPath   the left path
     * @param rightPath  the right path
     * @param drivingNo  the driving no
     * @param drivedNo   the drived no
     * @param drivinGear the drivin gear
     * @param drivedGear the drived gear
     * @param leftError  the left error
     * @param rightError the right error
     * @param load       the load
     * @param rpm        the rpm
     *
     * @return the gt measure
     *
     * @exception IOException the io exception
     * @exception IOException the io exception
     */
    public GtMeasure ConvertMeasure(String leftPath, String rightPath, String drivingNo, String drivedNo, SpurGear drivinGear, SpurGear drivedGear, SpurGearError leftError, SpurGearError rightError, double load, double rpm) throws IOException {
        GtMeasure gtMeasure = new GtMeasure();
        FileOperate fileOperate = new FileOperate();
        List<Double> leftDatas = fileOperate.readFileLines(leftPath);
        List<Double> rightDatas = fileOperate.readFileLines(rightPath);
        gtMeasure.setDrivedGear(drivedGear).setDrivedGearNo(drivedNo).setDrivinGear(drivinGear).setDrivingGearNo(drivingNo).setLeftDatas(leftDatas).setLoad(load).setLeftError(leftError).setRightDatas(rightDatas).setRightError(rightError).setRpm(rpm);
        return gtMeasure;
    }

    /**
     * Fft filter double [ ].
     *
     * @param origin the origin
     * @param cutoff the cutoff
     *
     * @return the double [ ]
     */
    public double[] fftFilter(double[] origin, double cutoff) {
        int originLength = origin.length;  //原始数据的长度
        int n = 3;  //计算的初始次数，2^n要大于原始数据的长度
        double originLn = Math.log10(originLength) / Math.log10(2);
        while (originLn > n) {
            n++;
        }
        int calculateLenth = (int) Math.pow(2, n);  //参与计算的长度，必须是2的N次方
        double[] calculate = new double[calculateLenth];  //参与计算的数组，多出来的后面补零
        //初始数据
        for (int i = 0; i < originLength; i++) {
            calculate[i] = origin[i];
        }
        //多余补零
        for (int i = originLength; i < calculateLenth; i++) {
            calculate[i] = 0;
        }
        //傅里叶正向转换
        FastFourierTransformer fastFourierTransformer = new FastFourierTransformer(DftNormalization.STANDARD);
        //转换出的复数
        Complex[] fftComplexResult = fastFourierTransformer.transform(calculate, TransformType.FORWARD);
        //复数的模计算
        double[] fftDoubleResult = new double[calculateLenth];
        for (int i = 0; i < calculateLenth; i++) {
            fftDoubleResult[i] = 2 * fftComplexResult[i].abs() / calculateLenth;
        }
        Complex[] filteredComplex = new Complex[fftComplexResult.length];
        for (int i = 0; i < fftComplexResult.length; i++) {
            if (i > cutoff && i < fftComplexResult.length - cutoff) {
                filteredComplex[i] = new Complex(0, 0);
            } else {
                filteredComplex[i] = new Complex(fftComplexResult[i].getReal(), fftComplexResult[i].getImaginary());
            }
        }
        Complex[] filteredComplexResult = fastFourierTransformer.transform(filteredComplex, TransformType.INVERSE);

        double[] filteredDoubleResult = new double[filteredComplexResult.length];
        for (int i = 0; i < filteredComplexResult.length; i++) {
            if (filteredComplexResult[i].getReal() > 0) {
                filteredDoubleResult[i] = filteredComplexResult[i].abs();
            } else {
                filteredDoubleResult[i] = -filteredComplexResult[i].abs();
            }

        }
        double[] result = new double[originLength];
        for (int i = 0; i < originLength; i++) {
            result[i] = filteredDoubleResult[i];
        }
        return result;
    }
}
